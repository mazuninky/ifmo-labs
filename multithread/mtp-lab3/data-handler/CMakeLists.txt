cmake_minimum_required(VERSION 3.10)
project(lab3)

set (CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fsanitize=address -O1 -fno-omit-frame-pointer -g")
set (CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -fno-omit-frame-pointer -fsanitize=address")

add_subdirectory(test)

include_directories("${PROJECT_SOURCE_DIR}/include")

file(GLOB sources
        "${PROJECT_SOURCE_DIR}/include/lab3/*.h"
        "${PROJECT_SOURCE_DIR}/src/*.c")

add_executable(lab3 ${sources})

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)
target_link_libraries(lab3 Threads::Threads)