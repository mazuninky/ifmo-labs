package lab2

fun task6() {
    val inputList = checkNotNull(readLine())
            .split(" ")
    check(inputList.size == 15)

    val output = inputList.map(String::toFloat).sorted()
    println(output)
}
