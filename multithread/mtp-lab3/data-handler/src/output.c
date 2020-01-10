#include <stdint.h>
#include <stdio.h>
#include "lab3/message.h"
#include "lab3/output.h"
#include <stdlib.h>

OutputMessage *create_message(uint8_t type, void *data, uint64_t size) {
    OutputMessage *message = malloc(sizeof(OutputMessage));
    message->Type = type;
    message->Data = data;
    message->Size = size;
    return message;
}

char *to_string(OutputMessage *message) {
    if (message == NULL)
        return NULL;

    char *begin = malloc(sizeof(char) * 512);
    char *string = begin;
    //TODO Rewrite
    switch (message->Type) {
        case FIBONACCI:
            string += sprintf(string, "Fibonacci");
            break;
        case POW:
            string += sprintf(string, "Pow");
            break;
        case BUBBLE_SORT_UINT64:
            string += sprintf(string, "Sort");
            break;
        case STOP:
            string += sprintf(string, "Stop");
            break;
    }

    if (message->Type == FIBONACCI) {
        string += sprintf(string, " n: %ld", *((long *) message->Data));
    } else if (message->Type == POW) {
        string += sprintf(string, " pow: %ld", *((long *) message->Data));
    } else if (message->Type == BUBBLE_SORT_UINT64) {
        string += sprintf(string, "[");
        uint64_t *array = message->Data;
        long length = message->Size / sizeof(uint64_t);
        for (long i = 0; i < length; ++i) {
            string += sprintf(string, "%lld", array[i]);
            if (i + 1 < length)
                string += sprintf(string, ",");
        }
        string += sprintf(string, "]");
    }

    string += sprintf(string, "\n");

    return begin;
}
