package xyz.mazuninky

import platform.posix.rand
import kotlin.random.Random

typealias DistributionGenerator = () -> Float

fun uniform(a: Float, b: Float): DistributionGenerator {
    return {
        a + Random.nextFloat() * (b - a)
    }
}