package lab2

fun main(args: Array<String>) {
    val converter = MoneyConverter()

    val value = dollar(10f)

    println(converter.convertToRubli(value))
}
