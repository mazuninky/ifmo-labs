#include <stdint.h>

long fibonacci(int n) {
    if(n < 0)
        return -1;

    int a = 0, b = 1, c, i;
    if (n == 0)
        return a;
    for (i = 2; i <= n; i++) {
        c = a + b;
        a = b;
        b = c;
    }

    return b;
}

void swap(uint64_t *A, uint64_t *B) {
    *A ^= *B;
    *B ^= *A;
    *A ^= *B;
}

void bubbleSort(uint64_t *array, long size) {
    for (long k = 0; k < size - 1; k++)
        for (long i = 0; i < size - k - 1; i++)
            if (array[i] > array[i + 1])
                swap(&array[i], &array[i + 1]);
}

long powBase(int base, uint32_t step) {
    long number = 1;
    for (uint32_t i = 0; i < step; i++)
        number *= base;
    return number;
}