package xyz.mazuninky.lab3.test

import xyz.mazuninky.lab3.logisticGenerator
import xyz.mazuninky.lab3.uniform
import xyz.mazuninky.lab3.uniformGenerator
import xyz.mazuninky.lab3.сauchyGenerator
import kotlin.math.abs
import kotlin.test.Test

inline fun <T> makeExperiment(size: Int, experimentFunc: () -> T): List<T> {
    val list = mutableListOf<T>()
    for (i in 0 until size) {
        list.add(experimentFunc())
    }
    return list
}

fun List<Float>.med() = this.sorted().let {
    if (it.size % 2 == 0)
        (it[it.size / 2] + it[it.size / 2 - 1]) / 2
    else
        it[(it.size - 1) / 2]
}

class DistributionTest {
    @Test
    fun testUnifrom() {
        val uniform = uniformGenerator(5f, 15f)
        val numbers = makeExperiment(1000, uniform)
        assert(numbers.all { it in 5f..15f })
    }

    @Test
    fun testCauchy() {
        val uniform = сauchyGenerator(2f, 1.5f)
        val numbers = makeExperiment(1000, uniform)
        println(abs(numbers.med() - 2f) <= 0.1f)
    }

    @Test
    fun testLogistic() {
        val uniform = logisticGenerator(2f, 1.5f)
        val numbers = makeExperiment(1000, uniform)
        println(abs(numbers.med() - 2f) <= 0.1f)
    }
}
