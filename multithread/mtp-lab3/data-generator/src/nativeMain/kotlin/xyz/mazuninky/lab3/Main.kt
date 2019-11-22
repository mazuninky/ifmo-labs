package xyz.mazuninky.lab3

const val MODE_OPTION = "mode"
const val COUNT_OPTION = "count"

const val MODE_UNIFORM = "uniform"
const val UNIFORM_A_PARAM = "a"
const val UNIFORM_B_PARAM = "b"

const val MODE_СAUCHY = "сauchy"
const val СAUCHY_X0_PARAM = "x0"
const val СAUCHY_GAMMA_PARAM = "gamma"

const val MODE_LOGISTIC = "logistic"
const val LOGISTIC_MU_PARAM = "mu"
const val LOGISTIC_S_PARAM = "s"

fun main(args: Array<String>) {
    val parsed = parse(args)
    val mode = checkNotNull(parsed.getArg(MODE_OPTION))
    val generator = when (mode) {
        MODE_UNIFORM -> {
            val a = checkNotNull(parsed.getFloatParam(UNIFORM_A_PARAM))
            val b = checkNotNull(parsed.getFloatParam(UNIFORM_B_PARAM))
            uniformGenerator(a, b)
        }
        MODE_СAUCHY -> {
            val x0 = checkNotNull(parsed.getFloatParam(СAUCHY_X0_PARAM))
            val gamma = checkNotNull(parsed.getFloatParam(СAUCHY_GAMMA_PARAM))
            сauchyGenerator(x0, gamma)
        }
        MODE_LOGISTIC -> {
            val mu = checkNotNull(parsed.getFloatParam(LOGISTIC_MU_PARAM))
            val s = checkNotNull(parsed.getFloatParam(LOGISTIC_S_PARAM))
            logisticGenerator(mu, s)
        }
        else -> throw IllegalArgumentException()
    }

    val count = parsed.getArg(COUNT_OPTION)?.toInt() ?: 15

    for (i in 0 until count) {
        write(generate(generator))
    }

    write(Stop)
}