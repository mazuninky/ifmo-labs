#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

void* *thread_func(void *param) 
{
    while(1) {
        FILE *fp;
        fp = fopen("task1.c", "r");
        if(fp == NULL) {
            return NULL;
        }

        char ch;
        while((ch = fgetc(fp)) != EOF) {

        }

        fclose(fp);
    }
    return 0;
}

int main(int argc, char *argv[]) {
    pthread_t tid; 

    pthread_create(&tid,NULL,thread_func,NULL);

    pthread_cancel(tid);

    pthread_join(tid,NULL);
}