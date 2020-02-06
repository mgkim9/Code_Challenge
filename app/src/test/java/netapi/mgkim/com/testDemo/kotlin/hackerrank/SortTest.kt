package netapi.mgkim.com.testDemo.kotlin.hackerrank

import org.junit.Test
import java.util.*


class SortTest{

    @Test
    fun test01() {
        val input: Array<Int> = arrayOf(3,2,1)
        countSwaps(input)
    }
    var swapCnt = 0
    fun countSwaps(input: Array<Int>) {
        val n = input.size
        for (i in 0 until n) {
            for (j in 0 until n - 1) {
                if (input[j] > input[j + 1]) {
                    swap(input, input[j], input[j + 1])
                }
            }
        }

        println("Array is sorted in $swapCnt swaps.")
        println("First Element: ${input[0]}")
        println("Last Element: ${input[input.size-1]}")
    }

    private fun swap(input: Array<Int>, a: Int, b: Int) {
        val temp = input[a]
        input[a] = input[b]
        input[b] = temp
    }

//https://www.hackerrank.com/challenges/mark-and-toys/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
//Mark and Toys
    fun maximumToys(prices: Array<Int>, k: Int): Int {
        var sumPrice = 0
        var sumCnt = 0
        val queues = Array<Queue<Int>>(10){
            LinkedList()
        }
        var finishIndex = 0
        var digit = 1

        while(finishIndex < prices.size) {
            for(i in finishIndex until prices.size) {
                if(prices[i] < digit) {
                    if(sumPrice + prices[i] <= k) {
                        sumPrice += prices[i]
                        sumCnt++
                    } else {
                        return sumCnt
                    }
                    prices[finishIndex++] = prices[i]
                } else {
                    queues[(prices[i] / digit) % 10].add(prices[i])
                }
            }
            var curIndex = finishIndex
            queues.forEach { queue->
                while(!queue.isEmpty()) {
                    prices[curIndex++] = queue.poll()
                }
            }
            digit *= 10
        }

        return 0
    }

}