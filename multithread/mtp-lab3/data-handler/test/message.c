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

//TEST(message, interpret_fib)
//    int numbers[] = {3, 3};
//    TMessage message;
//    message.Type = FIBONACCI;
//    message.Size = sizeof(numbers);
//    message.Data = numbers;
//    OutputMessage * output = interpret(&message);
//    ASSERT_EQ(output->Type, FIBONACCI)
//    ASSERT_EQ(output->Size, sizeof(long))
//    ASSERT_EQ(*((long*)output->Data), 144)
//END_TEST

void message_runner() {
    message_interpret_fib();
}