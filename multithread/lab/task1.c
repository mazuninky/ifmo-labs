#include <pthread.h>
#include <stdio.h>



void *thread_func(void *param) 
{
    for(int i = 1; i <= 10; i++) {
         printf("(%d %d)\n", pthread_self(), i);
    }
}

int main(int argc, char *argv[]) {
    pthread_t tid1; 
    pthread_t tid2; 
    pthread_t tid3;

    pthread_create(&tid1,NULL,thread_func,NULL);
    pthread_create(&tid2,NULL,thread_func,NULL);
    pthread_create(&tid3,NULL,thread_func,NULL);

    pthread_join(tid1,NULL);
    pthread_join(tid2,NULL);
    pthread_join(tid3,NULL);
}