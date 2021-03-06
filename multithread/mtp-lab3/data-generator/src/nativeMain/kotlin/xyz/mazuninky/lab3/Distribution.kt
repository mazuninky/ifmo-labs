package xyz.mazuninky.lab3

import kotlin.math.ln
import kotlin.math.log
import kotlin.random.Random

typealias DistributionGenerator = () -> Float

fun uniform(a: Float, b: Float): Float {
    return a + Random.nextFloat() * (b - a)
}

fun uniformGenerator(a: Float, b: Float): DistributionGenerator {
    return {
        uniform(a, b)
    }
}

fun сauchy(x0: Float, gamma: Float): Float {
    var x: Float
    var y: Float

    do {
        x = uniform(-1f, 1f)
        y = uniform(-1f, 1f)
    } while (x * x + y * y > 1.0 || y == 0f)
    return x0 + gamma * x / y
}

fun сauchyGenerator(x0: Float, gamma: Float): DistributionGenerator {
    return {
        сauchy(x0, gamma)
    }
}

fun logistic(mu: Float, s: Float): Float {
    return mu + s * ln(1f / uniform(0f, 1f) - 1f)
}

fun logisticGenerator(mu: Float, s: Float): DistributionGenerator {
    return {
        logistic(mu, s)
    }
}