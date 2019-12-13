#include <stdint.h>
#include <stdio.h>
#include "lab3/message.h"
#include "lab3/output.h"
#include <stdlib.h>

OutputMessage *create_message(uint8_t type, void *data) {
    OutputMessage *message = malloc(sizeof(OutputMessage));
    message->Type = type;
    message->Data = data;
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
    }

    string += sprintf(string, "\n");

    return begin;
}