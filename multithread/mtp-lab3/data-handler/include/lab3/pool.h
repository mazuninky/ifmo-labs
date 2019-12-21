#ifndef LAB3_POOL_H
#define LAB3_POOL_H

#include <pthread.h>
#include "lab3/message.h"
#include "lab3/queue.h"

typedef struct {
    int size;
    queue_t message_queue;
    pthread_t threads[];
} pool_descriptor;

void * executor_func(void * param) ;

pool_descriptor * create_pool(int size);

void * pool_add_message(pool_descriptor * pool, TMessage * message);

bool pool_is_empty(pool_descriptor * pool);

#endif //LAB3_POOL_H
