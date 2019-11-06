cmake_minimum_required(VERSION 3.15)
project(lab3 C)

set(CMAKE_C_STANDARD 99)

find_library(PTHREAD_LIBRARY pthread)

add_executable(lab3 main.c struct.h queue.h queue.c)

target_link_libraries(lab3 ${PTHREAD_LIBRARY})