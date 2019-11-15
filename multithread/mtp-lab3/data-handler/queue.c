#include <stddef.h>
#include "queue.h"
#include <stdlib.h>
#include <pthread.h>

int queue_init(queue_t *queue) {
    queue_descriptor *queue_desc = malloc(sizeof(queue_descriptor));
    if (queue_desc == NULL) {
        return 1;
    }

    queue_desc->root = NULL;
    queue_desc->last = NULL;
    if (pthread_mutex_init(&queue_desc->lock, NULL) != 0) {
        free(queue_desc);
        return 1;
    }

    *queue = queue_desc;

    return 0;
}

queue_node *create_node(void *value) {
    queue_node *node = malloc(sizeof(queue_node));
    node->value = value;
    node->next = NULL;
    return node;
}

void queue_add(queue_t queue, void *value) {
    pthread_mutex_lock(&queue->lock);
    queue_node *node = create_node(value);
    if (queue->last == NULL) {
        queue->root = node;
        queue->last = node;
    } else {
        queue->last->next = node;
        queue->last = node;
    }
    pthread_mutex_unlock(&queue->lock);
}

void *queue_fist(queue_t queue) {
    pthread_mutex_lock(&queue->lock);
    if (queue->root == NULL) {
        pthread_mutex_unlock(&queue->lock);
        return NULL;
    }

    if (queue->root == queue->last) {
        void *value = queue->root->value;
        free(queue->root);
        queue->root = NULL;
        queue->last = NULL;
        pthread_mutex_unlock(&queue->lock);
        return value;
    }

    queue_node *node = queue->root;
    queue->root = node->next;
    void *value = node->value;
    free(node);
    pthread_mutex_unlock(&queue->lock);
    return value;
}