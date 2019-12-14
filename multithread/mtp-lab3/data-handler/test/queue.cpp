#include "gtest/gtest.h"

extern "C" {
#include "lab3/queue.h"
}

queue_t test_queue;


TEST(queue, queue) {
    ASSERT_EQ(queue_init(&test_queue), 0);
}

#define test_count 5

TEST(queue, add_get) {
    ASSERT_EQ(queue_init(&test_queue), 0);

    for (long i = 0; i < 5; ++i) {
        queue_add(test_queue, (void *) i);
    }

    for (long i = 0; i < 5; ++i) {
        ASSERT_EQ(((long) queue_fist(test_queue)), i);
    }
}

TEST(queue, is_empty) {
    ASSERT_EQ(queue_init(&test_queue), 0);

    ASSERT_EQ(is_empty(test_queue), true);
    queue_add(test_queue, (void *) 5);

    ASSERT_EQ(is_empty(test_queue), false);

    queue_fist(test_queue);
    ASSERT_EQ(is_empty(test_queue), true);
}