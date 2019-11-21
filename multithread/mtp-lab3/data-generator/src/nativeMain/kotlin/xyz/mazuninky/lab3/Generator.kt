package xyz.mazuninky.lab3

import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.random.Random

fun DistributionGenerator.nextUInt(): UInt {
    return this().roundToInt().absoluteValue.toUInt()
}

fun DistributionGenerator.nextULong(): ULong {
    return this().roundToLong().absoluteValue.toULong()
}


fun generate(distrFunc: DistributionGenerator): TMessage {
    val type = Random.nextInt(0, 2)

    return when (type) {
        0 -> Fibonacci(distrFunc.nextUInt())
        1 -> Pow(distrFunc().roundToInt(), distrFunc.nextUInt())
        2 -> {
            val size = Random.nextInt(5, 100)
            BubbleSort(List(size) { distrFunc.nextULong() })
        }
        else -> throw IllegalStateException()
    }
}