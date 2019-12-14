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
//counter_t execute_thread_count;

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

    while (true) {
        OutputMessage *message = queue_fist(write_queue);
        while (message != NULL) {
            char *string = to_string(message);
//            free(message->Data);
//            free(message);
            write(fd, string, strlen(string));
//            free(string);

            message = queue_fist(write_queue);
        }
        sleep(1);
    }
}

/*
 * Reader section
 */

void *reader_func(void *param) {
    int fd = (int) param;
    while (true) {
        TMessage *message = readMessage(fd);
        while (message != NULL) {
            if (message->Type == STOP) {
                pthread_exit(0);
            } else {
                pthread_t executor;
                if (pthread_create(&executor, NULL, execute_thread, message) == 0) {
                    execute_thread_count++;
//                    incrementAndGet(execute_thread_count);
                }
            }
            message = readMessage(fd);
        }
    }
}

/*
 * Main section
 */

int main(int argc, char *argv[]) {
    StrategyType strategy = PER_THREAD;

    int opt, index;
    opt = getopt_long(argc, argv, optString, long_options, &index);
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

    int not_empty = 0;
    while (!is_empty(write_queue));
    while (execute_thread_count != 0);
    pthread_cancel(writer);

    close(fd_writer);

#ifdef DEBUG
    close(fd_reader);
#endif

    return EXIT_SUCCESS;
}

#pragma clang diagnostic pop