#include <stdio.h>
#include <stdbool.h>
#include <pthread.h>
#include <string.h>
#include "queue.h"
#include <unistd.h>
#include <fcntl.h>
#include "struct.h"
#include "algorithm.h"
#include <stddef.h>
#include <stdlib.h>
#include <pthread.h>


#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wmissing-noreturn"

queue_t write_queue;

void *writer_func(void *param) {
    int fd = (int) param;

    while (true) {
        void *message = queue_fist(write_queue);
        while (message != NULL) {
            write(fd, message, strlen((char *) message));
            message = queue_fist(write_queue);
        }
    }
}

void *execute_thread(void *param) {
    TMessage *message = param;
    void *result = NULL;
    if (message->Type == FIBONACCI) {
        int n = ((int *) message->Data)[0];
        char *buf = malloc(sizeof(char) * 100);
        sprintf(buf, "%ld\n", fibonacci(n));
        result = buf;
    }

    return result;
}

void *reader_func(void *param) {
    int fd = (int) param;
    while (true) {
        TMessage *message = readMessage(fd);
        while (message != NULL) {
            if (message->Type == STOP) {
                pthread_exit(0);
            } else {
                pthread_t executor;
                pthread_create(&executor, NULL, execute_thread, message);
                char *result = NULL;
                pthread_join(executor, &result);
                queue_add(write_queue, result);
            }

            free(message->Data);
            free(message);
            message = readMessage(fd);
        }

        sleep(100);
    }
}

#pragma clang diagnostic pop

int main() {
    if (queue_init(&write_queue) != 0) {
        return 1;
    }

    int fd_writer = open("output.txt", O_WRONLY | O_CREAT);
    int fd_example = open("task", O_RDONLY);

    pthread_t writer;
    if (pthread_create(&writer, NULL, writer_func, fd_writer) != 0) {
        return 1;
    }

    pthread_t reader;
    if (pthread_create(&reader, NULL, reader_func, fd_example) != 0) {
        return 1;
    }

    pthread_join(reader, NULL);
    pthread_join(writer, NULL);

    close(fd_writer);
    close(fd_example);

    return 0;
}