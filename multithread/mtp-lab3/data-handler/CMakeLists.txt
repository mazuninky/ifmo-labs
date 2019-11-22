cmake_minimum_required(VERSION 3.15)
project(lab3 C)

set(CMAKE_C_STANDARD 99)

find_library(PTHREAD_LIBRARY pthread)

add_executable(lab3 main.c message.h message.c queue.h queue.c algorithm.h algorithm.c output.h output.c pool.h pool.c counter.h counter.c)

target_link_libraries(lab3 ${PTHREAD_LIBRARY})