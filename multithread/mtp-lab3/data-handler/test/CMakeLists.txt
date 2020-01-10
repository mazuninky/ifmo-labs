include_directories("${PROJECT_SOURCE_DIR}/include")

file(GLOB sources
        "${PROJECT_SOURCE_DIR}/include/lab3/*.h"
        "${PROJECT_SOURCE_DIR}/test/*.c"
        "${PROJECT_SOURCE_DIR}/test/*.h")

file(GLOB original "${PROJECT_SOURCE_DIR}/src/*.c")
list(REMOVE_ITEM original "${PROJECT_SOURCE_DIR}/src/main.c")

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fprofile-arcs -ftest-coverage")

add_executable(test ${sources} ${original})