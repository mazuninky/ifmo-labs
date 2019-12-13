set(CMAKE_C_STANDARD 99)
#SET(CMAKE_CXX_FLAGS "-O0")
#SET(CMAKE_C_FLAGS "-DLINUX -O0 -Wall -std=c99")

file(GLOB SOURCES ./*.c)

add_executable(lab3_source ${SOURCES})

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)
target_link_libraries(lab3_source Threads::Threads)

