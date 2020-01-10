#include "test.h"
#include <stdio.h>
#include <stdlib.h>

void test_setup() {

}

void start_test(char * message) {
    printf("Test: %s starts\n", message);
}


void test_fail(char *message) {
    printf("%s", message);
    exit(1);
}
