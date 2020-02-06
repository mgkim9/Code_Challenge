package netapi.mgkim.com.testDemo.kotlin

import org.junit.Test
import java.util.*
import java.util.Collections.swap

class SortAndSearch{

    //    HashMap에 Capacity값 읽어오기
    @Test
    fun test01() {
        val array = intArrayOf(9, 6, 3, 8, 5, 2, 7, 4, 1)
        var tempArray = array.clone()
        println("array : " + Arrays.toString(tempArray))

        tempArray = array.clone()
        sortByQuickSort(tempArray)
        println("sortByQuickSort : " + Arrays.toString(tempArray))

        tempArray = array.clone()
        radixSort(tempArray)
        println("radixSort : " + Arrays.toString(tempArray))

//        Collections.sort()
        println("binarySearch : " + binarySearch(tempArray, 7))
    }

    private fun sortByQuickSort(tempArray: IntArray) {
        quickSort(tempArray, 0 , tempArray.size - 1)
    }

    private fun quickSort(tempArray: IntArray, start: Int, end: Int) {
        if (start >= end) {
            return
        }

        val mid = partition(tempArray, start, end)
        quickSort(tempArray, start, mid - 1)
        quickSort(tempArray, mid, end)
    }

    private fun partition(tempArray: IntArray, start: Int, end: Int): Int {
        var midValue = tempArray[(start + end) shr 1]
        var startIndex = start
        var endIndex = end

        while (startIndex <= endIndex) {
            while (tempArray[startIndex] < midValue) startIndex++
            while (tempArray[endIndex] > midValue) endIndex--
            if(startIndex <= endIndex) {
                swap(tempArray, startIndex, endIndex)
                startIndex++
                endIndex--
            }
        }
        return startIndex
    }

    private fun binarySearch(tempArray: IntArray, i: Int): Any? {
        var start = 0
        var end = tempArray.size-1
        var mid:Int
        while (start <= end) {
            mid = (start + end ) shr 1
            if(tempArray[mid] < i) {
                start = mid + 1
            } else if(tempArray[mid] > i) {
                end = mid - 1
            } else {
                return mid
            }
        }
        return -start
    }

    private fun radixSort(array: IntArray) {
        val queues = Array<Queue<Int>>(10) {
            LinkedList()
        }
        var digit = 1
        var burketIndex = 0
        var finishIndex = 0
        while (finishIndex < array.size) {
            for (i in finishIndex until array.size) {
                if (array[i] < digit) {
                    array[finishIndex] = array[i]
                    finishIndex++
                } else {
                    burketIndex = (array[i] / digit) % 10
                    queues[burketIndex].add(array[i])
                }
            }

            var curIndex = finishIndex
            queues.forEach {
                while (!it.isEmpty()) {
                    array[curIndex] = it.poll()
                    curIndex++
                }
            }
            digit *= 10
        }
    }

    fun swap(array:IntArray, left:Int, right:Int) {
        val temp = array[left]
        array[left] = array[right]
        array[right] = temp
    }
}