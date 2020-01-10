#ifndef LAB3_OUTPUT_H
#define LAB3_OUTPUT_H

#include "message.h"

typedef struct {
    uint8_t Type;
    uint64_t Size;
    void *Data;
} OutputMessage;

OutputMessage *create_message(uint8_t type, void *data, uint64_t size);

char *to_string(OutputMessage *message);

#endif //LAB3_OUTPUT_H
