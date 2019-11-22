#include "counter.h"
#include <stdlib.h>

int counter_init(counter_t *counter) {
    counter_descriptor *counter_desc = malloc(sizeof(counter_descriptor));
    if (counter_desc == NULL)
        return 1;

    counter_desc->value = 0;
    if (pthread_rwlock_init(&counter_desc->lock, NULL) != 0) {
        free(counter_desc);
        return 1;
    }

    *counter = counter_desc;
    return 0;
}

int incrementAndGet(counter_t counter) {
    pthread_rwlock_wrlock(&counter->lock);
    int value = counter->value + 1;
    counter->value = value;
    pthread_rwlock_unlock(&counter->lock);
    return value;
}

int decrementAndGet(counter_t counter) {
    pthread_rwlock_wrlock(&counter->lock);
    int value = counter->value - 1;
    counter->value = value;
    pthread_rwlock_unlock(&counter->lock);
    return value;
}

int get(counter_t counter) {
    pthread_rwlock_wrlock(&counter->lock);
    int value = counter->value;
    pthread_rwlock_unlock(&counter->lock);
    return value;
}