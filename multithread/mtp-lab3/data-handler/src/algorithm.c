#include <stdint.h>
#include <lab3/algorithm.h>

long fibonacci(int n) {
    long first = 0, second = 1, next;

    for (int c = 0; c < n; c++) {
        if (c <= 1)
            next = c;
        else {
            next = first + second;
            first = second;
            second = next;
        }
    }

    return next;
}

void swap(uint64_t *A, uint64_t *B) {
    *A ^= *B;
    *B ^= *A;
    *A ^= *B;
}

void bubbleSort(uint64_t *array, long size) {
    for (int k = 0; k < size - 1; k++)
        for (int i = 0; i < size - k - 1; i++)
            if (array[i] > array[i + 1])
                swap(&array[i], &array[i + 1]);
}

long powBase(int base, uint32_t step) {
    long number = 1;
    for (uint32_t i = 0; i < step; i++)
        number *= base;
    return number;
}