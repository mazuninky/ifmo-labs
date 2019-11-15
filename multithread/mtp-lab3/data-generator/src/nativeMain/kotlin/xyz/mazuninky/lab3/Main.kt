package xyz.mazuninky.lab3

const val MODE_OPTION = "mode"


const val MODE_UNIFORM = "uniform"
const val UNIFORM_A_PARAM = "a"
const val UNIFORM_B_PARAM = "b"

fun main(args: Array<String>) {
//    val parsed = parse(args)
//    val mode = checkNotNull(parsed.getArg(MODE_OPTION))
//
//    val generator = when (mode) {
//        MODE_UNIFORM -> {
//            val a = checkNotNull(parsed.getFloatParam(UNIFORM_A_PARAM))
//            val b = checkNotNull(parsed.getFloatParam(UNIFORM_B_PARAM))
//            uniform(a, b)
//        }
//        else -> throw IllegalArgumentException()
//    }

    write(Fibonacci(5))
    write(Fibonacci(15))
    write(Fibonacci(20))
    write(Fibonacci(45))
    write(Fibonacci(45))
    write(Stop)
}