#include "gtest/gtest.h"

extern "C" {
#include "lab3/algorithm.h"
}

TEST(algorithm, fibonacci_positive) {
    int data[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
    for (int i = 0; i < 13; i++) {
        ASSERT_EQ(fibonacci(i), data[i]);
    }
}

TEST(algorithm, fibonacci_negative) {
    ASSERT_EQ(fibonacci(-1), -1);
    ASSERT_EQ(fibonacci(-2), -1);
    ASSERT_EQ(fibonacci(-3), -1);
}

TEST(algorithm, bubble_sort) {
    uint64_t original[12] = {0, 2, 1, 5, 15, 1, 3, 4, 5, 24, 50, 33};
    uint64_t expected[12] = {0, 1, 1, 2, 3, 4, 5, 5, 15, 24, 33, 50};
    bubbleSort(original, 12);
    for (int i = 0; i < 12; i++) {
        ASSERT_EQ(original[i], expected[i]);
    }
}

TEST(algorithm, pow_base) {
    int base[5] = {0, 1, 4, 5, 15};
    int step[5] = {3, 5, 0, 1, 3};
    int expected[5]= {0, 1, 1, 5, 3375};
    for(int i = 0; i < 5; ++i) {
        ASSERT_EQ(powBase(base[i], step[i]), expected[i]);
    }
}