SOURCE_DIR=cmake-build-debug/test/CMakefiles/test.dir/__/src
lcov --directory $SOURCE_DIR \
       --base-directory $SOURCE_DIR \
       --gcov-tool gcov \
       --capture -o $SOURCE_DIR/cov.info
genhtml $SOURCE_DIR/cov.info -o coverage