package org.jetbrains.debugger

import org.junit.jupiter.api.Test
import kotlin.concurrent.thread

class MultiThreadedTest {

    class IntHolder(@JvmField var value: Int)

    @Test
    fun test() {
        val x = IntHolder(0)
        val y = IntHolder(0)
        val t1 = Thread {
            x.value++
        }
        val t2 = Thread {
            y.value++
        }
        t1.start()
        t2.start()
        t1.join()
        t2.join()
        println("x=${x.value}")
        println("y=${y.value}")
    }

    @Test
    fun testKotlin() {
        val x = IntHolder(0)
        val y = IntHolder(0)
        val t1 = thread {
            x.value++
        }
        val t2 = thread {
            y.value++
        }
        t1.join()
        t2.join()
        println("x=${x.value}")
        println("y=${y.value}")
    }

}