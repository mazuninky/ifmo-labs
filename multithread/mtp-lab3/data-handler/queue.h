//
// Created by Mazunin Konstantin on 13/10/2019.
//

#ifndef LAB3_QUEUE_H
#define LAB3_QUEUE_H

#include <stdint.h>

typedef struct Node {
    struct Node *next;

    uint64_t Size;
    uint8_t *Data;
} queue_node;


typedef struct {
    queue_node *next;
} queue_descriptor;

typedef queue_descriptor *queue_t;

int queue_init(queue_t *queue);

#endif //LAB3_QUEUE_H
