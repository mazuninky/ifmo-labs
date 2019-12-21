#ifndef LAB3_STATS_H
#define LAB3_STATS_H

#include <stdint.h>

typedef enum {
    Read, Write, Execution
} StageType;

typedef enum {
    Waiting, Working
} StateType;

//typedef struct {
//    StageType Stage;
//    StateType State;
//    uint32_t Time;
//} StatsMessage;

//StatsMessage * create_stats_message(StageType stage, StateType state, uint32_t time);

void stats_init();

void stats_report(StageType stage, StateType state, double clock_time);

char *dump_metrics();

#endif //LAB3_STATS_H
