#include <stdio.h>
#include <stdbool.h>
#include <pthread.h>
#include <string.h>
#include "lab3/queue.h"
#include <unistd.h>
#include <fcntl.h>
#include "lab3/message.h"
#include "lab3/output.h"
#include "lab3/stats.h"
#include <stddef.h>
#include <stdlib.h>
#include <errno.h>
#include <getopt.h>
#include <lab3/pool.h>
#include "lab3/const.h"

//#define DEBUG

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wmissing-noreturn"

/*
 * Define section
 */

queue_t task_fibonacci_queue;
queue_t task_bubble_queue;
queue_t task_pow_queue;

queue_t write_queue;

pool_descriptor *pool = NULL;

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

static const char *statsFilePath = "stats.csv";

/*
 * Stats section
 */

void *stats_writer(void *param) {
    int *args = (int *) param;
    double n = args[0];
    int fd = args[1];

    clock_t timer = clock();
    while (true) {
        if (((double) (clock() - timer)) / CLOCKS_PER_SEC * 0.001 > n) {
            char *metrics_string = dump_metrics();
            write(fd, metrics_string, strlen(metrics_string));
            free(metrics_string);
            timer = clock();
        }
    }
}

/*
 * Thread pool section
 */

void *thread_pool_executor(void *param) {
    pool_descriptor *pool = param;

    clock_t timer = 0, waiting = 0;
    while (true) {
        timer = clock();
        TMessage *message;
        do {
            waiting = clock();
            message = queue_fist(pool->message_queue);
            if (message != NULL) {
                stats_report(Execution, Waiting, (double) waiting - timer);
            }
        } while (message == NULL);

        pool->working_count++;

        void *result = interpret(message);
        stats_report(Execution, Working, (double) clock() - waiting);

        if (result != NULL)
            queue_add(write_queue, result);

        pool->working_count--;
    }
}


/*
 * Per task section
 */

TMessage *read_message_with_type(EType type) {
    switch (type) {
        case BUBBLE_SORT_UINT64:
            return queue_fist(task_bubble_queue);
        case POW:
            return queue_fist(task_pow_queue);
        case FIBONACCI:
            return queue_fist(task_fibonacci_queue);
    }

    return NULL;
}

void *task_execute_thread(void *param) {
    EType type = (EType) param;

    clock_t timer = 0, waiting = 0;
    while (true) {
        timer = clock();
        TMessage *message;
        do {
            waiting = clock();
            message = read_message_with_type(type);
            if (message != NULL) {
                stats_report(Execution, Waiting, (double) waiting - timer);
            }
        } while (message == NULL);

        void *result = interpret(message);
        stats_report(Execution, Working, (double) clock() - waiting);

        if (result != NULL)
            queue_add(write_queue, result);
    }
}


/*
 * Per thread section
 */

void *execute_thread(void *param) {
    TMessage *message = param;

    clock_t timer = clock();
    void *result = interpret(message);
    stats_report(Execution, Working, (double) clock() - timer);

//    free(message->Data);
//    free(message);

    if (result != NULL)
        queue_add(write_queue, result);

    execute_thread_count--;
    return NULL;
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
                stats_report(Write, Waiting, (double) waiting - timer);
            }
        } while (message == NULL);

        char *string = to_string(message);

//            free(message->Data);
//            free(message);
        write(fd, string, strlen(string));
        free(string);

        stats_report(Write, Working, (double) clock() - waiting);
    }
}

/*
 * Reader section
 */

void launch_thread(TMessage *message) {
    pthread_t executor;
    if (pthread_create(&executor, NULL, execute_thread, message) == 0) {
        execute_thread_count++;
    }
}

void *reader_func(void *param) {
    int *params = (int *) param;
    int fd = params[0];
    StrategyType type = params[1];

    clock_t timer = 0, waiting = 0;
    while (true) {
        timer = clock();
        TMessage *message;
        do {
            waiting = clock();
            message = readMessage(fd);
            if (message != NULL) {
                stats_report(Read, Waiting, (double) waiting - timer);
            }
        } while (message == NULL);

        if (message->Type == STOP) {
            pthread_exit(0);
        } else {
            if (type == PER_THREAD) {
                launch_thread(message);
            } else if (type == PER_TASK) {
                switch (message->Type) {
                    case POW:
                        queue_add(task_pow_queue, message);
                        break;
                    case BUBBLE_SORT_UINT64:
                        queue_add(task_bubble_queue, message);
                        break;
                    case FIBONACCI:
                        queue_add(task_fibonacci_queue, message);
                        break;
                }
            } else {
                pool_add_message(pool, message);
            }
        }
        stats_report(Read, Working, (double) clock() - waiting);
    }
}


/*
 * Main section
 */

int main(int argc, char *argv[]) {
    StrategyType strategy = PER_THREAD;

    // Parse params section
    int opt, index;
    opt = getopt_long(argc, argv, optString, long_options, &index);
    int n = 10;
    while (opt != -1) {
        switch (opt) {
            case 'n':
                n = (int) strtol(argv[1] + sizeof(char), (char **) NULL, 10);
                break;
            case 's':
                if (strcmp(optarg, PER_THREAD_STRATEGY) == 0) {
                    strategy = PER_THREAD;
                } else if (strcmp(optarg, PER_TASK_STRATEGY) == 0) {
                    strategy = PER_TASK;
                } else if (strcmp(optarg, THREAD_POOL_STRATEGY) == 0) {
                    strategy = THREAD_POOL;
                }
                break;
        }

        opt = getopt_long(argc, argv, optString, long_options, &index);
    }

#ifdef DEBUG
    strategy = PER_TASK;
#endif

    // Init section

    int fd_stats = open(statsFilePath, O_WRONLY | O_CREAT, 0777);
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

    pthread_t task_pow;
    pthread_t task_sort;
    pthread_t task_fibonacci;
    if (strategy == PER_TASK) {
        if (queue_init(&task_fibonacci_queue) != 0) {
            return EAGAIN;
        }

        if (queue_init(&task_pow_queue) != 0) {
            return EAGAIN;
        }

        if (queue_init(&task_bubble_queue) != 0) {
            return EAGAIN;
        }

        if (pthread_create(&task_fibonacci, NULL, task_execute_thread, FIBONACCI) != 0) {
            return EAGAIN;
        }
        if (pthread_create(&task_pow, NULL, task_execute_thread, POW) != 0) {
            return EAGAIN;
        }
        if (pthread_create(&task_sort, NULL, task_execute_thread, BUBBLE_SORT_UINT64) != 0) {
            return EAGAIN;
        }
    }

    if (strategy == THREAD_POOL) {
        if (create_pool(&pool, 5, thread_pool_executor) != 0) {
            return EAGAIN;
        }
    }

    // Start reader

    int reader_params[] = {fd_reader, strategy};
    pthread_t reader;
    if (pthread_create(&reader, NULL, reader_func, reader_params) != 0) {
        return EAGAIN;
    }

    // End work section

    pthread_join(reader, NULL);

    if (strategy == PER_THREAD) {
        while (execute_thread_count != 0);
    }

    if (strategy == PER_TASK) {
        while (!is_empty(task_fibonacci_queue) && !is_empty(task_pow_queue) && !is_empty(task_bubble_queue));
    }

    if (strategy == THREAD_POOL) {
        while (!pool_is_empty(pool));
    }

    while (!is_empty(write_queue));
    pthread_cancel(writer);

    if (strategy == PER_TASK) {
        pthread_cancel(task_fibonacci);
        pthread_cancel(task_pow);
        pthread_cancel(task_sort);
    }

    if (strategy == THREAD_POOL) {
        pool_cancel(pool);
    }

    pthread_cancel(stats);

    close(fd_writer);
    close(fd_stats);

#ifdef DEBUG
    close(fd_reader);
#endif

    return EXIT_SUCCESS;
}

#pragma clang diagnostic pop