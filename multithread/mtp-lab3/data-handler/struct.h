#ifndef LAB3_STRUCT_H
#define LAB3_STRUCT_H

#include <stdint.h>
#include <stdio.h>

typedef enum {
    FIBONACCI,
    POW,
    BUBBLE_SORT_UINT64,
    STOP
} EType;

typedef struct {
    EType Type;
    uint64_t Size;
    uint8_t *Data;
} TMessage;

void printMessage(TMessage *message);

TMessage *readMessage(int fd);

#endif //LAB3_STRUCT_H
