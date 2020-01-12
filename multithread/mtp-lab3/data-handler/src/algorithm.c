#include <stdint.h>

// O(n)
long fibonacci(int n) {
    if (n < 0)
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

// O(n^2)
void bubbleSort(uint64_t *array, long size) {
    for (long k = 0; k < size - 1; k++)
        for (long i = 0; i < size - k - 1; i++)
            if (array[i] > array[i + 1])
                swap(&array[i], &array[i + 1]);
}

// O(n)
long powBase(int base, uint32_t step) {
    if (step == 0)
        return 1;

    long number = 1;
    for (uint32_t i = 0; i < step; i++) {
        number *= base;
    }

    return number;
}