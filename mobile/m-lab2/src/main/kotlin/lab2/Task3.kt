package lab2

fun task3() {
    val input = checkNotNull(readLine())
            .split(" ")

    check(input.size == 5)

    val group = input.groupBy { it }
    val output = when (group.size) {
        5 -> "Все разные"
        1 -> input.joinToString(", ")
        else -> {
            group
                    .filter { it.value.size > 1 }
                    .map { it.value }
                    .flatten()
                    .joinToString(" ")
        }
    }

    println(output)
}
