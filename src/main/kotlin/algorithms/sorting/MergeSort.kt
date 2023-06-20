package algorithms.sorting

class MergeSort {

    fun sortMergeAlgorithm(items: MutableList<Int>) {
        fun merge(items: MutableList<Int>, left: MutableList<Int>, right: MutableList<Int>) {
            var leftIndex = 0
            var rightIndex = 0
            var targetIndex = 0
            var remaining = left.size + right.size
            while (remaining > 0) {
                when {
                    //смотрим, где должен быть массив, в зависимости от уже отсортированного массива, вставляем либо справа либо слева
                    leftIndex >= left.size -> {
                        items[targetIndex] = right[rightIndex++]
                    }
                    rightIndex >= right.size -> {
                        items[targetIndex] = left[leftIndex++]
                    }
                    left[leftIndex] - right[rightIndex] < 0 -> {
                        items[targetIndex] = left[leftIndex++]
                    }
                    else -> {
                        items[targetIndex] = right[rightIndex++]
                    }
                }

                targetIndex++
                remaining--
            }
        }

        if (items.size <= 1) {
            return
        }
        val leftSize = items.size / 2
        val rightSize = items.size - leftSize
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        for (index in 0 until items.size) {
            if (index < leftSize) {
                left.add(items[index])
            } else {
                right.add(items[index])
            }
        }
        sortMergeAlgorithm(left)
        sortMergeAlgorithm(right)
        merge(items, left, right)
    }
}

fun main() {

    val mergeSort = MergeSort()
    val listToSort = mutableListOf(3, 6, 1, 4, 89, 23, 56, 90, 132, 5, 8, 45, 67)
    mergeSort.sortMergeAlgorithm(listToSort)
    println(listToSort)
}