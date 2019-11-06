package lab2

fun task5() {
    val points = checkNotNull(readLine()
            ?.toFloatOrNull()
    )

    when (points) {
        in 0f..60f -> println("2")
        in 60f..75f -> println("3")
        in 75f..90f -> println("4")
        in 90f..100f -> println("5")
        else -> println("Неверно количество баллов!")
    }
}
