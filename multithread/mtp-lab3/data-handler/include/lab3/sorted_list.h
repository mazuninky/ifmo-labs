//
// Created by Mazunin Konstantin on 13/10/2019.
//

#ifndef LAB3_LIST_H
#define LAB3_LIST_H

#include <stdint.h>
#include <stdbool.h>
#include <pthread.h>

typedef struct SLNode {
    struct SLNode *next;

    double value;
} sorted_list_node;

typedef struct {
    sorted_list_node *root;
    sorted_list_node *last;
    pthread_mutex_t lock;
    int size;
} sorted_list_descriptor;

int slist_init(sorted_list_descriptor **list_desc);

void slist_add(sorted_list_descriptor * list, double value);

bool list_is_empty(sorted_list_descriptor * list);

double slits_array(sorted_list_descriptor * list);

#endif //LAB3_LIST_H
