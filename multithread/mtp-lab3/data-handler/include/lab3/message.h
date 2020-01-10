#ifndef LAB3_MESSAGE_H
#define LAB3_MESSAGE_H

#include <stdint.h>
#include <stdio.h>
#include "lab3/output.h"


typedef struct {
    uint8_t Type;
    uint64_t Size;
    uint8_t *Data;
} TMessage;

void printMessage(TMessage *message);

TMessage *readMessage(int fd);

OutputMessage *interpret(TMessage *message);

#endif //LAB3_MESSAGE_H
