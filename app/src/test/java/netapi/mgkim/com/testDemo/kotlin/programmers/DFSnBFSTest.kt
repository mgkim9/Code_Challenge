package netapi.mgkim.com.testDemo.kotlin.programmers

import org.junit.Assert
import org.junit.Test
import java.util.*

class DFSnBFSTest {
    //https://programmers.co.kr/learn/courses/30/lessons/43164
    //4.    여행경로

    /**
     * 문제 설명
     *
     * 주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
     * 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     *
     * •모든 공항은 알파벳 대문자 3글자로 이루어집니다.
     * •주어진 공항 수는 3개 이상 10,000개 이하입니다.
     * •tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
     * •주어진 항공권은 모두 사용해야 합니다.
     * •만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
     * •모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
     */
    @Test
    fun Test03() {
        var tickets = arrayOf(arrayOf("ICN", "JFK"), arrayOf("HND", "IAD"), arrayOf("JFK", "HND"))
        var result = arrayOf("ICN", "JFK", "HND", "IAD")
        var out = solution03(tickets)
        Assert.assertEquals(result, out)

        tickets = arrayOf(
            arrayOf("ICN", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("SFO", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO")
        )
        result = arrayOf("ICN", "ATL", "ICN", "SFO", "ATL", "SFO")
        out = solution03(tickets)
        Assert.assertEquals(result, out)

    }

    fun solution03(tickets: Array<Array<String>>): Array<String> {
        val st = Stack<String>()
        st.push("ICN")
        val visiteds = BooleanArray(tickets.size)
        val arrayList = ArrayList<String>()

        dfs(tickets, visiteds, st, 0, arrayList)
        arrayList.sort()
        return arrayList[0].split(" ").toTypedArray()
    }

    private fun dfs(
        tickets: Array<Array<String>>,
        visiteds: BooleanArray,
        st: Stack<String>,
        cnt: Int,
        arrayList: ArrayList<String>
    ) {
        if (cnt >= tickets.size) {
            //            System.out.println(st.toString());
            val buffer = StringBuffer()
            st.forEach { s -> buffer.append(s).append(" ") }
            arrayList.add(buffer.toString().trim())
            return
        }

        val cur = st.peek()
        for (i in tickets.indices) {
            if (!visiteds[i] && tickets[i][0] == cur) {
                st.push(tickets[i][1])
                visiteds[i] = true
                dfs(tickets, visiteds, st, cnt + 1, arrayList)
                visiteds[i] = false
                st.pop()
            }
        }
    }

    //https://programmers.co.kr/learn/courses/30/lessons/43165?language=kotlin
    //타겟 넘버

    @Test
    fun Test003() {
        var numbers: IntArray = intArrayOf(1,1,1,1,1)
        var target: Int = 3
        println(Solution003().solution(numbers, target))
    }
    class Solution003 {
        private var resultCnt = 0
        fun solution(numbers: IntArray, target: Int): Int {
            findResult(numbers, target, 0, 0)
            var answer = resultCnt
            return answer
        }

        private fun findResult(numbers: IntArray, target: Int, result:Int, index: Int) {
            if(index >= numbers.size) {
                if(target == result) {
                    resultCnt++
                }
                return
            }
            Calculator.values().forEach { cal ->
                findResult(numbers, target, cal.cal(result, numbers[index]), index+1)
            }
        }

        private enum class Calculator {
            PLUSE {
                override fun cal(a: Int, b: Int) = a + b
            },
            MINUS {
                override fun cal(a: Int, b: Int) = a - b
            }/*,
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
            }*/;

            abstract fun cal(a: Int, b: Int): Int
        }

    }
}
