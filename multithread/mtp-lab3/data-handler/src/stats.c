#include <lab3/stats.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <lab3/sorted_list.h>

sorted_list_descriptor *read_working;
sorted_list_descriptor *read_waiting;

sorted_list_descriptor *write_working;
sorted_list_descriptor *write_waiting;

sorted_list_descriptor *execution_working;
sorted_list_descriptor *execution_waiting;

void stats_init() {
    slist_init(&read_working);
    slist_init(&read_waiting);

    slist_init(&write_working);
    slist_init(&write_waiting);

    slist_init(&execution_working);
    slist_init(&execution_waiting);
}

void stats_report(StageType stage, StateType state, double clock_time) {
    double time = clock_time * 1000000 / CLOCKS_PER_SEC;
    switch (stage) {
        case Read:
            if (state == Working) {
                slist_add(read_working, time);
            } else {
                slist_add(read_waiting, time);
            }
            break;
        case Write:
            if (state == Working) {
                slist_add(write_working, time);
            } else {
                slist_add(write_waiting, time);
            }
            break;
        case Execution:
            if (state == Working) {
                slist_add(execution_working, time);
            } else {
                slist_add(execution_waiting, time);
            }
            break;
    }
}

char *dump_metrics() {
    char *buffer = malloc(sizeof(char) * 250);
    double kn = .70;
    sprintf(buffer, "%f,%f,%f,%f,%f,%f\n",
            slits_kn(read_working, kn), slits_kn(read_waiting, kn),
            slits_kn(write_working, kn), slits_kn(write_waiting, kn),
            slits_kn(execution_working, kn), slits_kn(execution_waiting, kn)
    );
    return buffer;
}