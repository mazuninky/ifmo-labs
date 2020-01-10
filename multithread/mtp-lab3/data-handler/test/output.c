#include "lab3/output.h"
#include "test.h"
#include "lab3/const.h"
#include <string.h>

TEST(output, to_string_fib)
    long number = 144;
    OutputMessage *message = create_message(FIBONACCI, &number, sizeof(number));
    const char *expected = "Fibonacci n: 144\n";
    const char *output = to_string(message);
    ASSERT_EQ(strcmp(output, expected), 0);
END_TEST

TEST(output, to_string_null)
    const char *output = to_string(NULL);
    ASSERT_EQ(output, NULL);
END_TEST

TEST(output, to_string_pow)
    long number = 9;
    OutputMessage *message = create_message(POW, &number, sizeof(number));
    char *expected = "Pow: 9\n";
    char *output = to_string(message);
    ASSERT_EQ(strcmp(output, expected), 0);
END_TEST

TEST(output, to_string_sort)
    uint64_t numbers[] = {0, 1, 2, 3, 4, 5};
    OutputMessage *message = create_message(BUBBLE_SORT_UINT64, numbers, sizeof(numbers));
    char *expected = "Sort: [0,1,2,3,4,5]\n";
    char *output = to_string(message);
    ASSERT_EQ(strcmp(output, expected), 0);
END_TEST

TEST(output, to_string_stop)
    OutputMessage *message = create_message(STOP, NULL, 0);
    char *expected = "Stop\n";
    char *output = to_string(message);
    ASSERT_EQ(strcmp(output, expected), 0);
END_TEST

void output_runner() {
    output_to_string_fib();
    output_to_string_pow();
    output_to_string_sort();
    output_to_string_stop();
    output_to_string_null();
}