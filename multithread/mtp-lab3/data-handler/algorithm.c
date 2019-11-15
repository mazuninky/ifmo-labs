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