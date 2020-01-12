#include <stddef.h>
#include <stdlib.h>
#include <pthread.h>
#include <lab3/sorted_list.h>

int slist_init(sorted_list_descriptor **list_desc) {
    sorted_list_descriptor *list = malloc(sizeof(sorted_list_descriptor));
    if (list == NULL) {
        return 1;
    }

    list->root = NULL;
    list->last = NULL;
    if (pthread_mutex_init(&list->lock, NULL) != 0) {
        free(list);
        return 1;
    }

    list->size = 0;
    *list_desc = list;

    return 0;
}

sorted_list_node *create_slist_node(double value) {
    sorted_list_node *node = malloc(sizeof(sorted_list_node));
    node->value = value;
    node->next = NULL;
    return node;
}

void slist_add(sorted_list_descriptor *list, double value) {
    pthread_mutex_lock(&list->lock);
    sorted_list_node *node = create_slist_node(value);
    if (list->root == NULL) {
        list->root = node;
        list->last = node;
    } else {
        if (list->last->value < value) {
            list->last->next = node;
            list->last = node;
        } else if (list->root->value > value) {
            node->next = list->root;
            list->root = node;
        } else {
            sorted_list_node *curr = list->root;
            sorted_list_node *next = list->root->next;
            while (next->value < value) {
                curr = next;
                next = curr->next;
            }
            curr->next = node;
            node->next = next;
        }
    }
    list->size++;
    pthread_mutex_unlock(&list->lock);
}

double slits_kn(sorted_list_descriptor *list, double k) {
    pthread_mutex_lock(&list->lock);
    if (list->root == NULL) {
        pthread_mutex_unlock(&list->lock);
        return 0.0;
    }

    int index = (int) (list->size * k);

    sorted_list_node *curr = list->root;
    for (int i = 0; i < index; i++) {
        curr = curr->next;
    }
    double value = curr->value;

    pthread_mutex_unlock(&list->lock);
    return value;
}