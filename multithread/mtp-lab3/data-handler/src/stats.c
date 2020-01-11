#include <lab3/stats.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

_Atomic double read_working;
_Atomic double read_waiting;
_Atomic double read_time;

_Atomic double write_working;
_Atomic double write_waiting;
_Atomic double write_time;

void stats_init() {
    read_working = 0;
    read_time = 0;
    write_working = 0;
    write_time = 0;
}

void stats_report(StageType stage, StateType state, double clock_time) {
    switch (stage) {
        case Read:
            read_time += clock_time / CLOCKS_PER_SEC;
            if (state == Waiting) {
                read_waiting += clock_time / CLOCKS_PER_SEC;
                return;
            }
            read_working += clock_time / CLOCKS_PER_SEC;
            break;
        case Write:
            write_time += clock_time / CLOCKS_PER_SEC;
            if (state == Waiting) {
                write_waiting += clock_time / CLOCKS_PER_SEC;
                return;
            }
            write_working += clock_time / CLOCKS_PER_SEC;
            break;
    }
}

double count_read_load() {
    if (read_time == 0)
        return 0;
    return read_working / read_time;
}

double count_write_load() {
    if (read_time == 0)
        return 0;
    return read_working / read_time;
}

char *dump_metrics() {
    char *buffer = malloc(sizeof(char) * 250);
    sprintf(buffer, "%f,%f,%f,%f,%f,%f,%f,%f\n", read_working, read_waiting,
            read_time, count_read_load(), write_working, write_waiting, write_time, count_write_load());
    return buffer;
}