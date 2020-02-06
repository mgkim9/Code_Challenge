package netapi.mgkim.com.testDemo.kotlin.goorm

import netapi.mgkim.com.testDemo.kotlin.data_structure.Stack
import org.junit.Test

class TestHanoi {
    var totalCnt = 0

    @Test
    fun test() {
        val stack = Stack<Hanoi>(1)
        val input = "5"//readLine()
        var runTime = System.nanoTime()
        if (input?.toInt() is Int) {
            moveAll(input.toInt(), 1, 3, 2)
        } else {
            println("error!! input only number!!")
        }
        println("totalCnt2 $totalCnt" + "time " + (System.nanoTime() - runTime))

        runTime = System.nanoTime()
        if (input?.toInt() is Int) {
            HanoiStart(input.toInt())
        } else {
            println("error!! input only number!!")
        }
        println("totalCnt1 $totalCnt" + "time " + (System.nanoTime() - runTime))
    }

    //재귀 이용
    fun moveAll(count: Int, from: Int, to: Int, sub: Int) {
        if (count == 1) {
            move(count, from, to)
        } else {
            moveAll(count - 1, from, sub, to)
            move(count, from, to)
            moveAll(count - 1, sub, to, from)
        }
    }
    //stack 이용
    fun HanoiStart(input: Int) {
        println("HanoiStart")
        val stack = Stack<Hanoi>(input)
        var index = input
        var from = 1
        var to = 3
        while (true) {
            while (index > 1) {
                stack.add(Hanoi(index, from, to))
                index--
                to = to xor from
            }
            move(index, from, to)
            if (!stack.isEmpty()) {
                stack.pop().apply {
                    index = num
                    from = this.from
                    to = this.to
                }
                move(index, from, to)
                index--
                from = to xor from
            } else {
                break
            }
        }
    }

    fun HanoiStart2(input: Int) {
        println("HanoiStart")
        val stack = Stack<Hanoi>(input)
        var index = input
        var from = 1
        var to = 3
        while (true) {
            while (index > 1) {
                stack.add(Hanoi(index, from, to))
                index--
                to = to xor from
            }
            move(index, from, to)
            if (!stack.isEmpty()) {
                stack.pop().apply {
                    index = num
                    from = this.from
                    to = this.to
                }
                move(index, from, to)
                index--
                from = to xor from
            } else {
                break
            }
        }
    }

    fun move(index: Int, from: Int, to: Int) {
//    println("${index}:$from -> $to")
        totalCnt++
    }


    class Hanoi(val num: Int, val from: Int, val to: Int) {
        override fun toString(): String {
            return "${num}을 ${from}에서 ${to}으로 이동)"
        }
    }
}

