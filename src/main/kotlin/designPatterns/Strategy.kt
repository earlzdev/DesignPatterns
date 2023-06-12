package designPatterns

// Strategy Design Pattern is a pattern with which we encapsulate a family of algorithms and makes them interchangeable.
// Identify the aspects that vary and separate them from what remains the same.

interface SortStrategy {
    fun sort(array: Array<Int>)
}

class BubbleSortStrategy: SortStrategy {
    override fun sort(array: Array<Int>) {
        println("array sorted by bubble sort algorithm")
    }
}

class MergeSortStrategy: SortStrategy {

    override fun sort(array: Array<Int>) {
        println("array sorted by merging sort algorithm")
    }
}

fun main() {
    val numberToSort = arrayOf(1, 4, 2, 78, 3, 56, 32, 100)

    sort(BubbleSortStrategy(), numberToSort)
    sort(MergeSortStrategy(), numberToSort)
}

fun sort(sortStrategy: SortStrategy, number: Array<Int>) {
    sortStrategy.sort(number)
}