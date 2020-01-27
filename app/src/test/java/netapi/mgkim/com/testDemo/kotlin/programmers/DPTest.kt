package netapi.mgkim.com.testDemo.kotlin.programmers

import org.junit.Test

class DPTest {
    //https://programmers.co.kr/learn/courses/30/lessons/42895
    //N으로 표현

    @Test
    fun Test003() {
        var N = 5
        var number = 12
        var out = 4
        println(Solution003().solution(N, number))
    }
    class Solution003 {
        private var resultCnt = Integer.MAX_VALUE
        fun solution(N: Int, number: Int): Int {
            findResult(N, number, 0, 0)
            var answer = if(resultCnt == Int.MAX_VALUE) -1 else resultCnt
            return answer
        }

        private fun findResult(N: Int, number: Int, result:Int, index: Int) {
            if(index >= 8) {
                return
            }

            if(number == result) {
                resultCnt = Math.min(resultCnt, index)
                return
            }

            var NN = 0
            var digit = 1;
            for(i in 0 .. 8 - index ) {
                NN += N*digit
                digit *= 10
                Calculator.values().forEach { cal ->
                    findResult(N, number, cal.cal(result, NN), index+1 + i)
                }
            }
        }

        private enum class Calculator {
            PLUSE {
                override fun cal(a: Int, b: Int) = a + b
            },
            MINUS {
                override fun cal(a: Int, b: Int) = a - b
            },
            R_MINUS {
                override fun cal(a: Int, b: Int) = b - a
            },
            MUL {
                override fun cal(a: Int, b: Int) = a * b
            },
            DIV {
                override fun cal(a: Int, b: Int) = if(b == 0) 0 else a / b
            },
            R_DIV {
                override fun cal(a: Int, b: Int) = if(a == 0) 0 else b / a
            };

            abstract fun cal(a: Int, b: Int): Int
        }

    }
}
