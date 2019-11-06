#include <stddef.h>
#include "queue.h"
#include <stdlib.h>

int queue_init(queue_t *queue) {
    *queue = malloc(sizeof(queue_descriptor));
    if (*queue == NULL) {
        return -1;
    }

    (*queue)->next = NULL;

    return 0;
}