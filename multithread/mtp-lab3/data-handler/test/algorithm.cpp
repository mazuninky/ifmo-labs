#include "gtest/gtest.h"

extern "C" {
#include "lab3/algorithm.h"
}

TEST(algorithm, fibonacci_positive) {
    int data[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
    for(int i = 0; i < 13; i++) {
        ASSERT_EQ(fibonacci(i), data[i]);
    }
}

TEST(algorithm, fibonacci_negative) {
    ASSERT_EQ(fibonacci(-1), -1);
    ASSERT_EQ(fibonacci(-2), -1);
    ASSERT_EQ(fibonacci(-3), -1);
}