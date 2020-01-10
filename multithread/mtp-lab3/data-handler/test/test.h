#ifndef LAB3_TEST_H
#define LAB3_TEST_H

#include <stdio.h>

#define TEST(CASE, NAME) void CASE##_##NAME() { start_test(__func__);

#define END_TEST }

#define TEST_FAIL printf("Test: %s fails\n", __func__); return;

#define ASSERT_EQ(first, second) if(first != second) { TEST_FAIL }

void test_setup();

void start_test(char *message);

void test_ok();

void test_fail(char *message);

void assert_eq_str(char *first, char *second);

void assert_eq_int(int first, int second);
void assert_eq_long(long first, long second);

#endif //LAB3_TEST_H