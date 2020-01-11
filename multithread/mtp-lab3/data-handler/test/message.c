#include "lab3/output.h"
#include "test.h"
#include "lab3/const.h"
#include <string.h>
#include <lab3/message.h>

TEST(message, interpret_fib)
    int number = 12;
    TMessage message;
    message.Type = FIBONACCI;
    message.Size = sizeof(number);
    message.Data = &number;
    OutputMessage * output = interpret(&message);
    ASSERT_EQ(output->Type, FIBONACCI)
    ASSERT_EQ(output->Size, sizeof(long))
    ASSERT_EQ(*((long*)output->Data), 144)
END_TEST

TEST(message, interpret_pow)
    uint32_t numbers[] = {5, 0};
    TMessage message;
    message.Type = POW;
    message.Size = sizeof(numbers);
    message.Data = numbers;
    OutputMessage * output = interpret(&message);
    ASSERT_EQ(output->Type, POW)
    ASSERT_EQ(output->Size, sizeof(long))
    ASSERT_EQ(*((long*)output->Data), 1)
END_TEST

void message_runner() {
    message_interpret_fib();
    message_interpret_pow();
}