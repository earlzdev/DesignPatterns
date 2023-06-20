package algorithms.sorting

import algorithms.sorting.util.SortConstants

class QuickSort {

    fun quicksort(items: List<Int>): List<Int> {

        // if there is less 2 elements in array, then it is already sorted
        if (items.count() < 2) {
            return items
        }

        // choose pivot element
        val pivot = items[items.count()/2]

        // sort elements by 3 arrays - lesser, equal and greater then pivot
        val equal = items.filter { it == pivot }
        val less = items.filter { it < pivot }
        val greater = items.filter { it > pivot }

        // call function recursively for smaller and larger elements
        return quicksort(less) + equal + quicksort(greater)
    }
}


fun main() {

    val quickSort = QuickSort()
    val result = quickSort.quicksort(SortConstants.mutableListToSort)
    println(result)

}