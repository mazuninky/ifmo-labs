#include "test.h"
#include "lab3/sorted_list.h"

sorted_list_descriptor *test_descriptor;

TEST(sorted_list, init)
    ASSERT_EQ(slist_init(&test_descriptor), 0)
END_TEST

TEST(sorted_list, kn)
    slist_add(test_descriptor, 10.0);
    slist_add(test_descriptor, 50.0);
    slist_add(test_descriptor, 50.0);
    slist_add(test_descriptor, 50.0);

    ASSERT_EQ(slits_kn(test_descriptor, 0.15), 10.0)
END_TEST
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

void sorted_list_runner() {
    sorted_list_init();
    sorted_list_kn();
}