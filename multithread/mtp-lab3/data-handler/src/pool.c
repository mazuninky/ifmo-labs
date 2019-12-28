#include <lab3/pool.h>
#include <stdlib.h>
#include <pthread.h>
#include <lab3/stats.h>

int create_pool(pool_descriptor **desc, int size, void* (executor)(void*)) {
    pool_descriptor *pool_desc = malloc(sizeof(pool_descriptor));
    if (pool_desc == NULL) {
        return 1;
    }

    if (queue_init(&pool_desc->message_queue) != 0) {
        return 2;
    }

    if (size < 0)
        return 3;

    pool_desc->working_count = 0;

    pool_desc->size = size;
    pool_desc->threads = malloc(sizeof(pthread_t) * size);

    for (int i = 0; i < size; ++i) {
        if (pthread_create(&pool_desc->threads[i], NULL, executor, pool_desc) != 0) {
            return 4;
        }
    }

    *desc = pool_desc;

    return 0;
}

void pool_cancel(pool_descriptor *pool) {
    for (int i = 0; i < pool->size; ++i) {
        pthread_cancel(pool->threads[i]);
    }
}

void *pool_add_message(pool_descriptor *pool, TMessage *message) {
    queue_add(pool->message_queue, message);
}

bool pool_is_empty(pool_descriptor *pool) {
    return is_empty(pool->message_queue) && pool->working_count == 0;
}