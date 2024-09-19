package org.jetbrains.debugger

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SumTest {

    @Test
    fun test() {
        val numbers = listOf(1, 2, 3)
        val sum = sumOfNumbers(numbers)
        assertEquals(6, sum)
    }
}

fun sumOfNumbers(numbers: List<Int>): Int {
    var sum = 0
    for (i in 0 until numbers.size - 1) {
        sum += numbers[i]
    }
    return sum
}
