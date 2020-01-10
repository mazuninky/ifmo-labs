#include "test.h"
#include "lab3/pool.h"

pool_descriptor *test_pool;

void *test_executor(void *param) {
    return NULL;
}

TEST(pool, pool)
    ASSERT_EQ(create_pool(&test_pool, 1, test_executor), 0)
    pool_cancel(test_pool);
END_TEST

//#define TEST_COUNT 5
//
//TEST(pool, add_get)
//    ASSERT_EQ(queue_init(&test_queue), 0)
//
//    for (long i = 0; i < TEST_COUNT; ++i) {
//        queue_add(test_queue, (void *) i);
//    }
//
//    for (long i = 0; i < TEST_COUNT; ++i) {
//        ASSERT_EQ(((long) queue_fist(test_queue)), i)
//
//    }
//END_TEST
//
//TEST(queue, is_empty)
//    ASSERT_EQ(queue_init(&test_queue), 0)
//
//    ASSERT_EQ(is_empty(test_queue), true)
//    queue_add(test_queue, (void *) 5);
//
//    ASSERT_EQ(is_empty(test_queue), false)
//
//    queue_fist(test_queue);
//    ASSERT_EQ(is_empty(test_queue), true)
//END_TEST

void pool_runner() {
    pool_pool();
}