package lab2

fun task4() {
    val points = checkNotNull(readLine()
            ?.toFloatOrNull()
    )

    @Suppress("CascadeIf")
    if (points in 0f..60f) {
        println("2")
    } else if (points in 60f..75f) {
        println("3")
    } else if (points in 75f..90f) {
        println("4")
    } else if (points in 90f..100f) {
        println("5")
    } else {
        println("Неверно количество баллов!")
    }
}
