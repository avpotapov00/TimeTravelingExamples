import org.jetbrains.debugger.algorithms.mergeTwoLists
import org.junit.jupiter.api.Test


fun <T> MutableList<T>.reverse() {
    val list = this
    for (i in indices) {
        val temp = list[i]
        list[i] = list[size - 1 - i]
        list[size - 1 - i] = temp
    }
}


class ListTests {

    @Test
    fun testListReverse() {
        val x = 1
        val list = mutableListOf(x, 2, 3, 4, 5)
        list.reverse()
        println(list)
        check(list == listOf(5, 4, 3, 2, 1))
    }

}

class MergeTwoLists {
    @Test
    fun test() {
        mergeTwoLists()
    }
}