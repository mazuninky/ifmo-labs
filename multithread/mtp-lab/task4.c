#include <pthread.h>
#include <stdio.h>

int[] sum = {1,2,3,4,5,6,7,8,9,10,11,12,13};
int totalSum = 0;

struct Args {
    int start;
    int end;
}

pthread_mutex_t mutex;

void *sum_func(void *param) 
{
    struct Args limits = (struct Args*)param;
    int localSum = 0;
    for(int i = limits[0]; i < limits[1]; i++) {
        localSum += sum[i];
    }

    pthread_mutex_lock(&mutex);
    totalSum += localSum;
    pthread_mutex_unlock(&mutex);
}

#define NUM_OF_THREADS 5

int main(int argc, char *argv[]) {
    pthread_t threads[NUM_OF_THREADS];

    pthread_mutex_init(&mutex, NULL);

    for(int i = 0; i < NUM_OF_THREADS; i++) {
        struct Args arg;
        arg.start = i;
        arg.end = NUM_OF_THREADS / i;
        threads[i] = pthread_create(&threads[i],NULL,sum_func,&arg);
    }

    for(int i = 0; i < NUM_OF_THREADS; i++) {
        pthread_join(threads[i],NULL);
    }

    pthread_mutex_destroy(&mutex);

    printf("Sum is %d", totalSum);
}