#include "struct.h"
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

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
    if (type == 0) {
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


void printMessage(TMessage *message) {
    printf("Type: %u", message->Type);
    printf("Size: %llu", message->Size);
    switch (message->Type) {
        case FIBONACCI:
            printf("Data: %i\n", ((int *) message->Data)[0]);
            break;
    }
}
