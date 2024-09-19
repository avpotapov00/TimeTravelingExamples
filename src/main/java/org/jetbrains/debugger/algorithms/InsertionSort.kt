package org.jetbrains.debugger.algorithms


fun insertionSort(arr: Array<Int>) {
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1

        // Move elements of arr[0..i-1], that are greater than key, to one position ahead
        // of their current position
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j -= 1
        }
        arr[j + 1] = key
    }
}

fun main() {
    val arr = arrayOf(12, 11, 13, 5, 6)
    println("Original array: ${arr.joinToString(", ")}")
    insertionSort(arr)
    println("Sorted array: ${arr.joinToString(", ")}")
}
