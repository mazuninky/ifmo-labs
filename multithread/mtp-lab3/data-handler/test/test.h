#ifndef LAB3_TEST_H
#define LAB3_TEST_H

#include <stdio.h>

#define TEST(CASE, NAME) void CASE##_##NAME() { start_test(__func__);

#define END_TEST }

#define TEST_FAIL printf("Test: %s fails\n", __func__); return;

#define ASSERT_EQ(first, second) if(first != second) { TEST_FAIL }

void start_test(const char *message);

#endif //LAB3_TEST_H