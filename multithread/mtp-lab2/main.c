#include <stdlib.h>
#include <stdio.h>

#include <string.h>
#include <ncurses.h>
#include <errno.h>
#include <fcntl.h>
#include <zconf.h>

#include <limits.h>

#define SELECT_COLOR 1

typedef struct {
    char *lines;
    int row;
    int col;
    char *filename;
    int descriptor;
} MoreContext;


void readLines(MoreContext *context) {
    int line_count = 0;

    context->lines = calloc(context->col * context->row, sizeof(char));
    if (context->lines == NULL) {
        exit(ENOMEM);
    }

    int count = 0;
    do {
        count = read(context->descriptor, context->lines + sizeof(char) * line_count * context->col,
                     (context->col - 1) * sizeof(char));
        if (count > 0)
            *(context->lines + line_count * context->col * sizeof(char) + count * sizeof(char)) = '\0';
        line_count++;

        if (count < 0) {
            exit(EIO);
        }
    } while (line_count < context->row && count > 0);

    while (line_count < context->row) {
        *(context->lines + line_count * context->col * sizeof(char)) = '\n';
        *(context->lines + line_count * context->col * sizeof(char) + 1) = '\0';
        line_count++;
    }
}

void readForward(MoreContext *context) {
    char read_buffer[context->col];
    int count = read(context->descriptor, read_buffer, sizeof(char) * (context->col - 1));
    if (count > 0) {
        memmove(context->lines, context->lines + context->col * sizeof(char),
                (context->row - 1) * context->col * sizeof(char));
        *(read_buffer + count * sizeof(char)) = '\0';
        memcpy(context->lines + context->col * sizeof(char) * (context->row - 1), read_buffer, count);
    }

    if (count < 0) {
        exit(EIO);
    }
}


void readN(int n, MoreContext *context) {
    for (int i = 0; i < n; ++i)
        readForward(context);
}

void print_lines(MoreContext *context, bool inCommandMode) {
    clear();
    for (int i = 0; i < context->row; ++i) {
        addstr(context->lines + i * context->col * sizeof(char));
    }

    addstr("\n");
    if (inCommandMode) {
        mvwaddnstr(stdscr, context->row, 0, ":", context->col);
    } else {
        attron(COLOR_PAIR(SELECT_COLOR));
        mvwaddnstr(stdscr, context->row, 0, context->filename, context->col);
        attroff(COLOR_PAIR(SELECT_COLOR));
    }
    addstr("\n");

    refresh();
}

static const char *missing_filename = "Missing filename\n";
static const char *no_such_file = ": No such file or directory\n";

bool is_number(char *str) {
    unsigned long size = strlen(str);
    for (unsigned long i = 0; i < size; ++i) {
        if (str[i] < '0' || str[i] > '9')
            return false;
    }

    return true;
}

int main(int argc, char *argv[]) {
    if (argc == 1) {
        write(STDOUT_FILENO, missing_filename, strlen(missing_filename));
        return EINVAL;
    }

    MoreContext context;

    int seek_to = -1;

    bool isIn = false;

    if (argc == 2) {
        if (strcmp(argv[1], "-") == 0) {
            context.filename = "stdin";
            isIn = true;
        } else {
            context.filename = argv[1];
        }
    } else {
        if (argv[1][0] != '+')
            return EINVAL;

        if (strcmp(argv[1], "+0") != 0) {

            if (!is_number(argv[1] + sizeof(char))) {
                return EINVAL;
            }

            const long converted_value = strtol(argv[1] + sizeof(char), (char **) NULL, 10);

            if (converted_value == LONG_MAX || converted_value == 0) {
                return EINVAL;
            }

            if (converted_value > INT_MAX) {
                return EINVAL;
            }

            seek_to = (int) converted_value;
        }
        context.filename = argv[2];
    }

    //O_LARGEFILE не рекомендуем к использованию программистом, рекомендуется использовать флаги
    context.descriptor = isIn ? STDIN_FILENO : open(context.filename, O_RDONLY);

    if (context.descriptor < 0) {
        write(STDOUT_FILENO, argv[1], strlen(argv[1]));
        write(STDOUT_FILENO, no_such_file, strlen(no_such_file));
        return ENOENT;
    }

    initscr();

    curs_set(0);

    start_color();
    init_pair(SELECT_COLOR, COLOR_BLACK, COLOR_WHITE);

    getmaxyx(stdscr, context.row, context.col);
    context.row--;

    if (seek_to != -1) {
        lseek(context.descriptor, seek_to * context.col + 1, 0);
    }

    context.lines = malloc(context.row * sizeof(char *));
    if (context.lines == NULL) {
        exit(ENOMEM);
    }

    readLines(&context);
    print_lines(&context, false);

    bool inCommandMode;
    int c;
    noecho();
    if (isIn) {
        // Ради этих строк мне пришлось читать исходники glib и android clib
        int input = fcntl(STDIN_FILENO, F_DUPFD, 0);
        // FILE *input = fdopen(dup(STDIN_FILENO), "r");
        close(STDIN_FILENO);
        fcntl(STDOUT_FILENO, F_DUPFD, STDIN_FILENO);
        // freopen(ttyname(STDOUT_FILENO), "r", stdin);

        context.descriptor = input;
    }

    while (1) {
        inCommandMode = true;
        c = wgetch(stdscr);
        if (c == 'q') {
            break;
        } else {
            switch (c) {
                case ' ':
                    readN(context.row, &context);
                    break;
                case 'd':
                    readN(context.row / 2, &context);
                    break;
                case '\n':
                    readForward(&context);
                    break;
                default:
                    continue;
            }
        }

        print_lines(&context, inCommandMode);
    }

    if (!isIn) {
        if (close(context.descriptor) != 0) {
            exit(EBADF);
        }
    }

    endwin();

    return 0;
}
