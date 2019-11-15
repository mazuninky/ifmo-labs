//
// Created by Mazunin Konstantin on 13/10/2019.
//

#ifndef LAB3_QUEUE_H
#define LAB3_QUEUE_H

#include <stdint.h>
#include <ntsid.h>
#include <stdbool.h>

typedef struct Node {
    struct Node *next;

    void *value;
} queue_node;

typedef struct {
    queue_node *root;
    queue_node *last;
    pthread_mutex_t lock;
} queue_descriptor;

typedef queue_descriptor *queue_t;

int queue_init(queue_t *queue_desc);

void queue_add(queue_t queue, void *value);

void *queue_fist(queue_t queue);

bool is_empty(queue_t queue);

#endif //LAB3_QUEUE_H
