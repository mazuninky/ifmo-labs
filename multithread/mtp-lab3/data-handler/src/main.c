#include <stdio.h>
#include <stdbool.h>
#include <pthread.h>
#include <string.h>
#include "lab3/queue.h"
#include <unistd.h>
#include <fcntl.h>
#include "lab3/message.h"
#include "lab3/algorithm.h"
#include "lab3/output.h"
#include "lab3/stats.h"
#include <stddef.h>
#include <stdlib.h>
#include <pthread.h>
#include <errno.h>
#include <getopt.h>

//#define DEBUG

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wmissing-noreturn"

/*
 * Define section
 */

queue_t write_queue;

_Atomic int execute_thread_count;

void *writer_func(void *param);

void *reader_func(void *param);

static struct option long_options[] = {
        {"strategy", required_argument, NULL, 's'},
};
static const char *optString = "n:";

static const char *PER_THREAD_STRATEGY = "per_thread";
static const char *PER_TASK_STRATEGY = "per_task";
static const char *THREAD_POOL_STRATEGY = "thread_pool";

typedef enum {
    PER_THREAD, PER_TASK, THREAD_POOL
} StrategyType;

static const char *writerFilePath = "output.txt";

/*
 * Stats section
 */

void *stats_writer(void *param) {
    int *args = (int *) param;
    double n = args[0] * 0.001;
    int fd = args[1];

    clock_t timer = clock();
    while (true) {
        if ((double) (timer - clock()) / CLOCKS_PER_SEC > n) {
            char *metrics_string = dump_metrics();
            write(fd, metrics_string, strlen(metrics_string));
            free(metrics_string);
            timer = clock();
        }
    }
}

/*
 * Per thread section
 */

void *execute_thread(void *param) {
    TMessage *message = param;
    void *result = interpret(message);

//    free(message->Data);
//    free(message);

    if (result != NULL)
        queue_add(write_queue, result);

    execute_thread_count--;
//    decrementAndGet(execute_thread_count);
}

/*
 * Writer section
 */

void *writer_func(void *param) {
    int fd = (int) param;
    clock_t timer = 0, waiting = 0;
    while (true) {
        timer = clock();
        OutputMessage *message;
        do {
            waiting = clock();
            message = queue_fist(write_queue);
            if (message != NULL) {
                stats_report(Write, Waiting, waiting - timer);
            }
        } while (message == NULL);

        char *string = to_string(message);
//            free(message->Data);
//            free(message);
        write(fd, string, strlen(string));
//            free(string);
        stats_report(Write, Working, clock() - waiting);
    }
}

/*
 * Reader section
 */

void *reader_func(void *param) {
    int fd = (int) param;
    clock_t timer = 0, waiting = 0;
    while (true) {
        timer = clock();
        TMessage *message;
        do {
            waiting = clock();
            message = readMessage(fd);
            if (message != NULL) {
                stats_report(Read, Waiting, waiting - timer);
            }
        } while (message == NULL);

        if (message->Type == STOP) {
            pthread_exit(0);
        } else {
            pthread_t executor;
            if (pthread_create(&executor, NULL, execute_thread, message) == 0) {
                execute_thread_count++;
            }
        }
        stats_report(Read, Working, clock() - waiting);
    }
}


/*
 * Main section
 */

int main(int argc, char *argv[]) {
    StrategyType strategy = PER_THREAD;

    int opt, index;
    opt = getopt_long(argc, argv, optString, long_options, &index);
    int n = 1;
    while (opt != -1) {
        switch (opt) {
            case 'n':

                break;
            case 's':
                if (strcmp(optarg, PER_THREAD_STRATEGY) == 0) {
                    strategy = PER_THREAD;
                } else if (strcmp(optarg, PER_TASK_STRATEGY) == 0) {
                    strategy = PER_TASK;
                } else if (strcmp(optarg, THREAD_POOL_STRATEGY) == 0) {
                    strategy = THREAD_POOL;
                    return ENOSYS;
                }
                break;
        }

        opt = getopt_long(argc, argv, optString, long_options, &index);
    }

    int fd_stats = open("stats.txt", O_WRONLY | O_CREAT, 0777);
    if (fd_stats == -1) {
        return EBADF;
    }

    int stats_arg[] = {n, fd_stats};

    stats_init();
    pthread_t stats;
    if (pthread_create(&stats, NULL, stats_writer, stats_arg) != 0) {
        return EAGAIN;
    }

    if (queue_init(&write_queue) != 0) {
        return EXIT_FAILURE;
    }

    int fd_writer = open(writerFilePath, O_WRONLY | O_CREAT, 0777);
    if (fd_writer == -1) {
        return EBADF;
    }

    pthread_t writer;
    if (pthread_create(&writer, NULL, writer_func, fd_writer) != 0) {
        return EAGAIN;
    }

    int fd_reader = STDIN_FILENO;

#ifdef DEBUG
    fd_reader = open("data_input.bin", O_RDONLY);
#endif

    pthread_t reader;
    if (pthread_create(&reader, NULL, reader_func, fd_reader) != 0) {
        return EAGAIN;
    }

    pthread_join(reader, NULL);

    while (!is_empty(write_queue));
    while (execute_thread_count != 0);
    pthread_cancel(writer);

    pthread_cancel(stats);

    close(fd_writer);
    close(fd_stats);

#ifdef DEBUG
    close(fd_reader);
#endif

    return EXIT_SUCCESS;
}

#pragma clang diagnostic pop