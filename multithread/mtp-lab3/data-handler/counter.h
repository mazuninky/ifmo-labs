#ifndef LAB3_COUNTER_H
#define LAB3_COUNTER_H

#include <pthread.h>

typedef struct {
    int value;
    pthread_rwlock_t lock;
} counter_descriptor;

typedef counter_descriptor *counter_t;

int counter_init(counter_t *counter);

int incrementAndGet(counter_t counter);

int decrementAndGet(counter_t counter);

int get(counter_t counter);

#endif //LAB3_COUNTER_H
