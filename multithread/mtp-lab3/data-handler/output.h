#ifndef LAB3_OUTPUT_H
#define LAB3_OUTPUT_H

#include "struct.h"

typedef struct {
    EType Type;
    void *Data;
} OutputMessage;

OutputMessage *create_message(EType type, void *data);

char *to_string(OutputMessage * message);

#endif //LAB3_OUTPUT_H
