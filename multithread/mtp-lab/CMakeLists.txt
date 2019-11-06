cmake_minimum_required(VERSION 3.1)

project(lab1)

find_package(Threads)

add_executable(task1 task1.c)
target_link_libraries(task1 ${CMAKE_THREAD_LIBS_INIT})

add_executable(task2 task2.c)
target_link_libraries(task2 ${CMAKE_THREAD_LIBS_INIT})

add_executable(task3 task3.c)
target_link_libraries(task3 ${CMAKE_THREAD_LIBS_INIT})

add_executable(task4 task4.c)
target_link_libraries(task4 ${CMAKE_THREAD_LIBS_INIT})