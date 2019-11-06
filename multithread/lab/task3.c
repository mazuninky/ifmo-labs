#include <pthread.h>
#include <stdio.h>

char message[] = "ping";
pthread_mutex_t mutex;
int run_thread = 0;

void *thread_func(void *param) 
{
    int count = 0;
    while(1) {
        if(!run_thread) {
            pthread_mutex_lock(&mutex);
            run_thread = 1;
            if(strcmp(message,"ping")) {
                message = "pong";
            } else  {
                message = "ping";
            }
            printf("Thread %d waiting %s)\n", pthread_self(), message);
            run_thread = 0;
            count = 0;
            pthread_mutex_unlock(&mutex);
        }
        count++;
        printf("Thread %d waiting %d)\n", pthread_self(), count);
    }
}

int main(int argc, char *argv[]) {
    pthread_t tid1; 
    pthread_t tid2; 

    pthread_mutex_init(&mutex, NULL);

    pthread_create(&tid1,NULL,thread_func,NULL);
    pthread_create(&tid2,NULL,thread_func,NULL);

    pthread_join(tid1,NULL);
    pthread_join(tid2,NULL);

    pthread_mutex_destroy(&mutex);
}