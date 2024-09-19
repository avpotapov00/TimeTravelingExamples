package org.jetbrains.debugger.algorithms



/**
 * The code is taken from
 * https://github.com/vania-pooh/kotlin-algorithms/blob/master/src/com/freaklius/kotlin/algorithms/sort/SelectionSort.kt
 * An implementation of selection sort algorithm
 * AveragePerformance = O(n^2)
 */


fun sort(arr: Array<Int>): Array<Int> {
    for (unsortedPartFirstIndex in 0..arr.size - 1){
        var minNumberIndex = unsortedPartFirstIndex
        for (unsortedPartCurrentIndex in unsortedPartFirstIndex..arr.size - 1){
            if (arr[unsortedPartCurrentIndex] < arr[minNumberIndex]){
                minNumberIndex = unsortedPartCurrentIndex
            }
        }
        if (minNumberIndex != unsortedPartFirstIndex){
            val temp = arr[unsortedPartFirstIndex]
            arr[unsortedPartFirstIndex] = arr[minNumberIndex]
            arr[minNumberIndex] = temp
        }
    }
    return arr
}

fun main() {
    val arr = arrayOf(12, 11, 13, 5, 6, 1, 6, 7, 8, 10, 22)
    println("Original array: ${arr.joinToString(", ")}")
    sort(arr)
    println("Sorted array: ${arr.joinToString(", ")}")
}
