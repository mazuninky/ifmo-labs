#include "gtest/gtest.h"

extern "C" {
#include "lab3/algorithm.h"
}

TEST(algorithm, ok) {
    ASSERT_EQ(fibonacci(4), 2);
}