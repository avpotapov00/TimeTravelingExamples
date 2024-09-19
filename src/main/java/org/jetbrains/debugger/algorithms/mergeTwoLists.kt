package org.jetbrains.debugger.algorithms



// Definition for singly-linked list.
class ListNode1(var `val`: Int) {
    var next: ListNode1? = null
}

private class Solution1 {
    

    fun mergeTwoLists(l1: ListNode1?, l2: ListNode1?): ListNode1? {
        var r1 = l1
        var r2 = l2
        val ll = ListNode1(0)
        var rr = ll

        while (true) {
            if (r1 == null && r2 == null) {
                break
            }
            if (r1 == null) {
                rr.next = r2
                break
            }
            if (r2 == null) {
                rr.next = r1
                break
            }
            if (r1.`val` <= r2.`val`) {
                rr.next = r1
                r1 = r1.next
            } else {
                rr.next = r2
                r2 = r2.next
            }
            rr = rr.next!!
        }
        return ll.next
    }
}

fun main() {
    mergeTwoLists()
}

fun mergeTwoLists() {
    // Creating the first linked list: [1, 2, 4]
    val l1 = ListNode1(1)
    l1.next = ListNode1(2)
    l1.next?.next = ListNode1(4)

    // Creating the second linked list: [1, 3, 4]
    val l2 = ListNode1(1)
    l2.next = ListNode1(3)
    l2.next?.next = ListNode1(4)

    // Merging the lists
    val solution = Solution1()
    val mergedList = solution.mergeTwoLists(l1, l2)

    // Printing the merged linked list
    var current = mergedList
    while (current != null) {
        print("${current.`val`} ")
        current = current.next
    }
}
