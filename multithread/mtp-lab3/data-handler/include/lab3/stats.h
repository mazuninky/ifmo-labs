#ifndef LAB3_STATS_H
#define LAB3_STATS_H

#include <stdint.h>

typedef enum {
    Read, Write, Execution
} StageType;

typedef enum {
    Waiting, Working
} StateType;

// метрики: время чтения, время ожидания чтения, время записи,

void stats_init();

void stats_report(StageType stage, StateType state, double clock_time);

char *dump_metrics();

#endif //LAB3_STATS_H
