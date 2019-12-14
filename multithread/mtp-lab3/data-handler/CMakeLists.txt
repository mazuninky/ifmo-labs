cmake_minimum_required(VERSION 3.10)
project(lab3)

## GCOV

#set(OBJECT_DIR ${CMAKE_BINARY_DIR}/CMakeFiles/main.dir)
#message("-- Object files will be output to: ${OBJECT_DIR}")
#

#
## Create the gcov target. Run coverage tests with 'make gcov'
add_custom_target(gcov
        COMMAND mkdir -p coverage
        COMMAND ${CMAKE_MAKE_PROGRAM} test
        WORKING_DIRECTORY ${CMAKE_BINARY_DIR}
        )
add_custom_command(TARGET gcov
        COMMAND echo "=================== GCOV ===================="
        COMMAND echo ${CMAKE_SOURCE_DIR}
        COMMAND gcov -b ${CMAKE_SOURCE_DIR}/src/*.c -o test/CMakeFiles/algorithm_tests.dir/
        | grep -A 5 "main.c" > CoverageSummary.tmp
        COMMAND cat CoverageSummary.tmp
        COMMAND echo "-- Coverage files have been output to ${CMAKE_BINARY_DIR}/coverage"
        WORKING_DIRECTORY ${CMAKE_BINARY_DIR}/coverage
        )
#add_dependencies(gcov lab3)
## Make sure to clean up the coverage folder
#set_property(DIRECTORY APPEND PROPERTY ADDITIONAL_MAKE_CLEAN_FILES coverage)
#
## Create the gcov-clean target. This cleans the build as well as generated
## .gcda and .gcno files.
#add_custom_target(scrub
#        COMMAND ${CMAKE_MAKE_PROGRAM} clean
#        COMMAND rm -f ${OBJECT_DIR}/*.gcno
#        COMMAND rm -f ${OBJECT_DIR}/*.gcda
#        WORKING_DIRECTORY ${CMAKE_BINARY_DIR}
#        )

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fprofile-arcs -ftest-coverage")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fprofile-arcs -ftest-coverage")

## Test
configure_file(cmake/googletest-download.cmake
        googletest-download/CMakeLists.txt)
execute_process(COMMAND ${CMAKE_COMMAND} -G "${CMAKE_GENERATOR}" .
        WORKING_DIRECTORY ${CMAKE_BINARY_DIR}/googletest-download )
execute_process(COMMAND ${CMAKE_COMMAND} --build .
        WORKING_DIRECTORY ${CMAKE_BINARY_DIR}/googletest-download )

add_subdirectory(${CMAKE_BINARY_DIR}/googletest-src
        ${CMAKE_BINARY_DIR}/googletest-build)

enable_testing()
add_subdirectory(test)

## Source

include_directories("${PROJECT_SOURCE_DIR}/include")

file(GLOB sources
        "${PROJECT_SOURCE_DIR}/include/lab3/*.h"
        "${PROJECT_SOURCE_DIR}/src/*.c")

add_executable(lab3 ${sources})

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)
target_link_libraries(lab3 Threads::Threads)