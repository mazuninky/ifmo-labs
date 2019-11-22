#include <stdio.h>
#include <stdbool.h>
#include <pthread.h>
#include <string.h>
#include "queue.h"
#include <unistd.h>
#include <fcntl.h>
#include "message.h"
#include "algorithm.h"
#include "output.h"
#include "counter.h"
#include <stddef.h>
#include <stdlib.h>
#include <pthread.h>

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wmissing-noreturn"
queue_t write_queue;
counter_t execute_thread_count;

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

void *execute_thread(void *param) {
    TMessage *message = param;
    void *result = interpret(message);

//    free(message->Data);
//    free(message);

    if (result != NULL)
        queue_add(write_queue, result);

    decrementAndGet(execute_thread_count);
}

void *reader_func(void *param) {
    int fd = (int) param;
    while (true) {
        TMessage *message = readMessage(fd);
        while (message != NULL) {
            if (message->Type == STOP) {
//                queue_add(write_queue, create_message(STOP, NULL));
                pthread_exit(0);
            } else {
                pthread_t executor;
                if (pthread_create(&executor, NULL, execute_thread, message) == 0) {
                    incrementAndGet(execute_thread_count);
                }
            }
            message = readMessage(fd);
        }
    }
}

int main() {
    if (queue_init(&write_queue) != 0) {
        return 1;
    }

    if (counter_init(&execute_thread_count) != 0) {
        return 1;
    }

    int fd_writer = open("output.txt", O_WRONLY | O_CREAT);
//    int fd_example = open("task2", O_RDONLY);

    pthread_t writer;
    if (pthread_create(&writer, NULL, writer_func, fd_writer) != 0) {
        return 1;
    }

    pthread_t reader;
//    if (pthread_create(&reader, NULL, reader_func, fd_example) != 0) {
    if (pthread_create(&reader, NULL, reader_func, STDIN_FILENO) != 0) {
        return 1;
    }

    pthread_join(reader, NULL);

    while (!is_empty(write_queue));
    while (get(execute_thread_count) != 0);
    pthread_cancel(writer);

    close(fd_writer);
//    close(fd_example);

    return 0;
}

#pragma clang diagnostic pop