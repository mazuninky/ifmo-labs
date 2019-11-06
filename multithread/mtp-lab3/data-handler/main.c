#include <stdio.h>
#include <stdbool.h>
#include <pthread.h>
#include "queue.h"
#include<unistd.h>

void *reader_func(void *param) {
    int *pipe = param;

}

void *writer_func(void *param) {
    int *pipe = param;

}

int main() {
    int pipeline[2];
    if (pipe(pipeline) != 0) {
        return 1;
    }

    pthread_t writer;
    pthread_t reader;

    if (pthread_create(&reader, NULL, reader_func, pipeline) != 0) {
        return 1;
    }

    if (pthread_create(&writer, NULL, writer_func, pipeline) != 0) {
        return 1;
    }

    

    close(pipeline[0]);
    close(pipeline[1]);

    return 0;
}