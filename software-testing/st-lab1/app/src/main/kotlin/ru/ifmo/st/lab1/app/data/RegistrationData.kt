package ru.ifmo.st.lab1.app.data

const val randomAlph = "1234567890asdqwertyi"

fun String.generateRandomString(size: Int): String {
    return buildString {
        for (it in 0..size) {
            append(this@generateRandomString.random())
        }
    }
}
