#include "lab3/message.h"
#include "lab3/algorithm.h"
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include "lab3/const.h"

TMessage *readMessage(int fd) {
    uint8_t type = 0;
    if (read(fd, &type, sizeof(type)) != sizeof(type)) {
        return NULL;
    }

    uint64_t size = 0;
    if (read(fd, &size, sizeof(size)) != sizeof(size)) {
        return NULL;
    }

    uint8_t *data = NULL;
    if (type != STOP) {
        data = malloc(sizeof(uint8_t) * size);
        if (read(fd, data, size * sizeof(uint8_t)) != size * sizeof(uint8_t)) {
            return NULL;
        }
    }

    TMessage *message = malloc(sizeof(TMessage));
    message->Type = type;
    message->Size = size;
    message->Data = data;

    return message;
}

OutputMessage *interpret(TMessage *message) {
    OutputMessage *result = NULL;
    if (message->Type == FIBONACCI) {
        int n = ((int *) message->Data)[0];
        long *data = malloc(sizeof(long));
        *data = fibonacci(n);
        result = create_message(FIBONACCI, data, sizeof(long));
    } else if (message->Type == BUBBLE_SORT_UINT64) {
        uint64_t *array = (uint64_t *) message->Data;
        long size = message->Size / sizeof(uint64_t);
        bubbleSort(array, size);
        result = create_message(BUBBLE_SORT_UINT64, array, message->Size);
    } else if (message->Type == POW) {
        int base = ((int *) message->Data)[0];
        uint32_t n = ((uint32_t *) message->Data)[1];
        long *data = malloc(sizeof(long));
        *data = powBase(base, n);
        result = create_message(POW, data, sizeof(long));
    }

    return result;
}