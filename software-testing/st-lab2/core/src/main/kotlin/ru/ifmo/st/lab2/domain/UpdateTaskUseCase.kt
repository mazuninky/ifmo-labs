package ru.ifmo.st.lab2.domain

import ru.ifmo.st.lab2.core.Task

interface UpdateTaskUseCase {
    operator fun invoke(task: Task)
}
