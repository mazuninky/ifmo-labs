package lab2

fun task7() {
    val array = arrayOf(1, 2, 3, 4, 5, 6)

    try {
        println(array[6])
    } catch (e: IndexOutOfBoundsException) {
        println(e)
        println(e.message)
    }
}
