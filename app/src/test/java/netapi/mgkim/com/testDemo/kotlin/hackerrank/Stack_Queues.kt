package netapi.mgkim.com.testDemo.kotlin.hackerrank

import org.junit.Test
import java.util.*

class Stack_Queues {


    //LinkedList
    //풍선 터트리기
    //https://www.acmicpc.net/problem/2346


    @Test
    fun Test0001() {
        val arr = intArrayOf(3, 2, 1, -3, -1)
        solution0008(arr)
    }

    private fun solution0008(arr: IntArray) {
        val myList = MyPangList(arr)
        myList.show()

        val results: IntArray = myList.start()
        println(results.contentToString())
    }

    class MyPangList {
        var head: Node? = null
        var tail: Node? = null
        var size = 0

        constructor(arr: IntArray) {
            if (arr.isEmpty()) {
                return
            }
            head = Node(Pang(arr[0], 1))
            tail = head
            size = 1
            for (i in 1 until arr.size) {
                val temp = Node(Pang(arr[i], i + 1))
                tail?.next = temp
                temp.prev = tail
                tail = temp
                size++
            }
            tail?.next = head
            head?.prev = tail
        }

        fun start(): IntArray {
            val results = IntArray(size)

            var node = head
            var index = 0

            while (node != null) {
                results[index++] = node.data.num
                node = pang(node)
            }

            return results
        }

        private fun pang(node: Node?): Node? {
            if (node == null || size == 1) {
                return null
            }
            val data = node.data
            remove(node)
            return move(node.prev, data.move)
        }

        private fun move(node: Node?, move: Int): Node? {
            val realMove = move % size
            var node = node
            if (realMove >= 0) {
                for (i in 0 until realMove) {
                    node = node?.next
                }
            } else {
                for (i in 0 until -realMove - 1) {
                    node = node?.prev
                }
            }
            return node
        }

        private fun remove(node: Node): Node? {
            val prev = node?.prev
            size--
            return prev?.apply {
                next = node.next
                next?.prev = this
            }
        }

        fun show() {
            var node = head
            var cnt = 0
            while (node != null) {
                println("num " + node.data.num + " move " + node.data.move)
                node = node.next
                cnt++
                if (cnt >= size) {
                    break
                }
            }
        }

        class Node(val data: Pang) {
            var next: Node? = null
            var prev: Node? = null
        }

        class Pang(val move: Int, val num: Int)

    }



    //중복된 수는 +1을 수행하여 유니크하게 만들고 모든값을 더하여라
    @Test
    fun Test0002() {
        val arr = intArrayOf(1, 2, 2, 5,6 ,7 ,7)
        println(getMinimumUniqueSum(arr.toTypedArray()))
    }

    fun getMinimumUniqueSum(arr: Array<Int>): Int {

        println(arr.contentToString())
        Arrays.sort(arr)
        var sum = arr[0]
        for(i in 1 until arr.size) {
            if(arr[i] <= arr[i-1]) {
                arr[i] = arr[i-1]+1
            }
            sum +=arr[i]
        }
        println(arr.contentToString())
        return sum
    }


}