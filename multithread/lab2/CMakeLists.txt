cmake_minimum_required(VERSION 3.14)
project(lab2 C)

set(CMAKE_C_STANDARD 99)
SET(GCC_COVERAGE_COMPILE_FLAGS "-Wall -Wextra --std=gnu99 -pedantic -Werror -D_FILE_OFFSET_BITS=64")

add_definitions(${GCC_COVERAGE_COMPILE_FLAGS})

find_package(Curses REQUIRED)
include_directories(${CURSES_INCLUDE_DIR})

add_executable(lab2 main.c)
target_link_libraries(lab2 ${CURSES_LIBRARIES})