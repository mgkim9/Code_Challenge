package netapi.mgkim.com.testDemo.kotlin.kakaoTest

import netapi.mgkim.com.netapitest2.kotlin.data_structure.TrieTest
import org.junit.Assert
import org.junit.Test
import java.util.*


//    https://tech.kakao.com/2019/10/02/kakao-blind-recruitment-2020-round1/
class KakaoTest2020 {

//  https://programmers.co.kr/learn/courses/30/lessons/60057
//1. 비밀 지도(난이도: 하)
    /**
    문제 설명

    데이터 처리 전문가가 되고 싶은 어피치는 문자열을 압축하는 방법에 대해 공부를 하고 있습니다. 최근에 대량의 데이터 처리를 위한 간단한 비손실 압축 방법에 대해 공부를 하고 있는데, 문자열에서 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복되는 값으로 표현하여 더 짧은 문자열로 줄여서 표현하는 알고리즘을 공부하고 있습니다.
    간단한 예로 aabbaccc의 경우 2a2ba3c(문자가 반복되지 않아 한번만 나타난 경우 1은 생략함)와 같이 표현할 수 있는데, 이러한 방식은 반복되는 문자가 적은 경우 압축률이 낮다는 단점이 있습니다. 예를 들면, abcabcdede와 같은 문자열은 전혀 압축되지 않습니다. 어피치는 이러한 단점을 해결하기 위해 문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 방법을 찾아보려고 합니다.

    예를 들어, ababcdcdababcdcd의 경우 문자를 1개 단위로 자르면 전혀 압축되지 않지만, 2개 단위로 잘라서 압축한다면 2ab2cd2ab2cd로 표현할 수 있습니다. 다른 방법으로 8개 단위로 잘라서 압축한다면 2ababcdcd로 표현할 수 있으며, 이때가 가장 짧게 압축하여 표현할 수 있는 방법입니다.

    다른 예로, abcabcdede와 같은 경우, 문자를 2개 단위로 잘라서 압축하면 abcabc2de가 되지만, 3개 단위로 자른다면 2abcdede가 되어 3개 단위가 가장 짧은 압축 방법이 됩니다. 이때 3개 단위로 자르고 마지막에 남는 문자열은 그대로 붙여주면 됩니다.

    압축할 문자열 s가 매개변수로 주어질 때, 위에 설명한 방법으로 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.

    제한사항
    s의 길이는 1 이상 1,000 이하입니다.
    s는 알파벳 소문자로만 이루어져 있습니다.

     */
    @Test
    fun Test01() {
        var intputs = ""
        var out = 0
        intputs = "aabbaccc"
        out = 7
        Assert.assertEquals(out, solution01(intputs))

        intputs = "ababcdcdababcdcd"
        out = 9
        Assert.assertEquals(out, solution01(intputs))

        intputs = "abcabcdede"
        out = 8
        Assert.assertEquals(out, solution01(intputs))

        intputs = "abcabcabcabcdededededede"
        out = 14
        Assert.assertEquals(out, solution01(intputs))

        intputs = "xababcdcdababcdcd"
        out = 17
        Assert.assertEquals(out, solution01(intputs))
    }

    fun solution01(s: String): Int {
//        val strArray = ArrayList<String>()
        var answer = s.length
        for (splitCnt in 1..s.length / 2) {
            var resultStr = StringBuilder()
            var prevStr = ""
            var curStr = ""
            var sameCnt = 1
            for (start in 0..s.length - splitCnt step splitCnt) {
                curStr = s.substring(start, start + splitCnt)
                if (prevStr == curStr) {
                    sameCnt++
                } else if (sameCnt > 1) {
                    resultStr.append(sameCnt).append(prevStr)
                    prevStr = curStr
                    sameCnt = 1
                } else {
                    resultStr.append(prevStr)
                    prevStr = curStr
                }
            }

            if (sameCnt > 1) {
                resultStr.append(sameCnt).append(prevStr)
            } else {
                resultStr.append(prevStr)
            }

            if (s.length % splitCnt > 0) {   //남은문자를 붙인다.
                resultStr.append(s.substring(s.length - (s.length % splitCnt), s.length))
            }

            if (answer > resultStr.length) {
                answer = resultStr.length
            }
//            strArray.add(resultStr.toString())
        }

//        println(strArray.toString())
        println(answer)
        return answer
    }


    //  https://programmers.co.kr/learn/courses/30/lessons/60058
//2. 괄호 변환

    /**
    문제 설명
    카카오에 신입 개발자로 입사한 콘은 선배 개발자로부터 개발역량 강화를 위해 다른 개발자가 작성한 소스 코드를 분석하여 문제점을 발견하고 수정하라는 업무 과제를 받았습니다. 소스를 컴파일하여 로그를 보니 대부분 소스 코드 내 작성된 괄호가 개수는 맞지만 짝이 맞지 않은 형태로 작성되어 오류가 나는 것을 알게 되었습니다.
    수정해야 할 소스 파일이 너무 많아서 고민하던 콘은 소스 코드에 작성된 모든 괄호를 뽑아서 올바른 순서대로 배치된 괄호 문자열을 알려주는 프로그램을 다음과 같이 개발하려고 합니다.

    용어의 정의
    '(' 와 ')' 로만 이루어진 문자열이 있을 경우, '(' 의 개수와 ')' 의 개수가 같다면 이를 균형잡힌 괄호 문자열이라고 부릅니다.
    그리고 여기에 '('와 ')'의 괄호의 짝도 모두 맞을 경우에는 이를 올바른 괄호 문자열이라고 부릅니다.
    예를 들어, "(()))("와 같은 문자열은 균형잡힌 괄호 문자열 이지만 올바른 괄호 문자열은 아닙니다.
    반면에 "(())()"와 같은 문자열은 균형잡힌 괄호 문자열 이면서 동시에 올바른 괄호 문자열 입니다.

    '(' 와 ')' 로만 이루어진 문자열 w가 균형잡힌 괄호 문자열 이라면 다음과 같은 과정을 통해 올바른 괄호 문자열로 변환할 수 있습니다.

    1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
    2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
    3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
    3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
    4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
    4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
    4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
    4-3. ')'를 다시 붙입니다.
    4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
    4-5. 생성된 문자열을 반환합니다.
    균형잡힌 괄호 문자열 p가 매개변수로 주어질 때, 주어진 알고리즘을 수행해 올바른 괄호 문자열로 변환한 결과를 return 하도록 solution 함수를 완성해 주세요.

    매개변수 설명
    p는 '(' 와 ')' 로만 이루어진 문자열이며 길이는 2 이상 1,000 이하인 짝수입니다.
    문자열 p를 이루는 '(' 와 ')' 의 개수는 항상 같습니다.
    만약 p가 이미 올바른 괄호 문자열이라면 그대로 return 하면 됩니다.

     **/

    @Test
    fun Test02() {
        var intputs = ""
        var result = ""
        var answer = ""
        intputs = "(()())()"
        result = "(()())()"
        answer = solution02(intputs)
        Assert.assertEquals(result, answer)

        intputs = ")("
        result = "()"
        answer = solution02(intputs)
        Assert.assertEquals(result, answer)

        intputs = "()))((()"
        result = "()(())()"
        answer = solution02(intputs)
        Assert.assertEquals(result, answer)

    }

    fun solution02(str: String): String {
        var answer = StringBuilder()

//        1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (str.isNullOrEmpty()) {
            return ""
        } else if (isPerfectBracket(str)) {
            return str
        }

//        2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        var pair: Pair<String, String> = balancedBracket(str)

        if (isPerfectBracket(pair.first)) {
//        3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
//        3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            answer.append(pair.first).append(solution02(pair.second))
        } else {
//        4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
//        4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            answer.append("(")
//        4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
                    .append(solution02(pair.second))
//        4-3. ')'를 다시 붙입니다.
                    .append(")")
//        4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
                    .append(reversal(pair.first))
//        4-5. 생성된 문자열을 반환합니다.
        }
        return answer.toString()
    }

    //문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
    private fun balancedBracket(str: String): Pair<String, String> {
        var depth = 0
        root@ str.forEachIndexed { index, c ->
            if (c == '(') {
                depth++
            } else {
                depth--
            }
            if (depth == 0) {
                return Pair(str.substring(0, index + 1), if (index == str.length - 1) "" else str.substring(index + 1, str.length))
            }
        }
        return Pair("", str)
    }

    //올바른 괄호 인지 판단
    private fun isPerfectBracket(str: String): Boolean {
        if (str.isNullOrEmpty()) {
            return false
        }
        var depth = 0
        str.forEach { c ->
            if (c == '(') {
                depth++
            } else {
                depth--
            }
            if (depth < 0) {
                return false
            }
        }

        return depth == 0
    }

    // 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 리턴
    private fun reversal(str: String): Any? {
        val stringBuilder = StringBuilder()
        for (i in 1 until str.length - 1) {
            if (str[i] == ')') {
                stringBuilder.append("(")
            } else {
                stringBuilder.append(")")
            }
        }
        return stringBuilder.toString()
    }


//  https://programmers.co.kr/learn/courses/30/lessons/60059
//3. 자물쇠와 열쇠

    /**
    문제 설명
    고고학자인 튜브는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다. 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.

    잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.

    자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다. 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다. 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.

    열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.

    제한사항
    key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
    lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
    M은 항상 N 이하입니다.
    key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
    0은 홈 부분, 1은 돌기 부분을 나타냅니다.

     **/

    @Test
    fun Test03() {
        var key: Array<IntArray>
        var lock: Array<IntArray>
        var answer = false
        key = arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 0, 0), intArrayOf(0, 1, 1))
        lock = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1))

        key.forEach {
            println(Arrays.toString(it))
        }
        println()
        lock.forEach {
            println(Arrays.toString(it))
        }
        println()

        answer = solution03(key, lock)
        Assert.assertEquals(true, answer)


    }

    fun solution03(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        var minX = lock[0].size - 1//홈의 최소 X
        var minY = lock.size - 1//홈의 최소 Y
        var maxX = 0//홈의 최대 X
        var maxY = 0//홈의 최대 Y

        lock.forEachIndexed { indexY, row ->
            row.forEachIndexed { indexX, value ->
                //홈의 최소 최대 x, y index를 구함
                if (value == 0) {
                    if (minX > indexX) {
                        minX = indexX
                    }
                    if (minY > indexY) {
                        minY = indexY
                    }
                    if (maxX < indexX) {
                        maxX = indexX
                    }
                    if (maxY < indexY) {
                        maxY = indexY
                    }
                }
            }
        }

        if (key.size <= maxY - minY || key[0].size <= maxX - minX) {
            //lock의 홈 사이의 거리가 키보다 크면 실패
            return false
        }

        var tempKey = key
        for (i in 0..3) { //90도방향으로 돌려가면서 체크
            if (keyCheck(tempKey, lock, minX, minY, maxX, maxY)) {
                return true
            }
            tempKey = rotate(tempKey)
        }

        return false
    }

    //키가 맞는지 체크
    fun keyCheck(key: Array<IntArray>, lock: Array<IntArray>, minX: Int, minY: Int, maxX: Int, maxY: Int): Boolean {
        for (y in maxY + 1 - key.size..minY) {
            root@ for (x in maxX + 1 - key.size..minX) {
                // key의 x, y 좌표
                for (i in Math.max(0, y)..Math.min(lock.size - 1, Math.max(maxY, y + key.size - 1))) {
                    for (j in Math.max(0, x)..Math.min(lock.size - 1, Math.max(maxX, x + key.size - 1))) {
                        // key의 x, y 좌표에 따라 겹처지는 부분만 체크
                        if (lock[i][j] == 0) {   // lock : 홈
                            if (key[i - y][j - x] == 0) { //key : 홈 이면 실패
                                continue@root
                            }
                        } else {    //lock : 돌기
                            if (key[i - y][j - x] == 1) {//key : 돌기 이면 실패
                                continue@root
                            }
                        }
                    }
                }
                return true
            }
        }
        return false
    }

    //정사각형 시계방향 90도 회전
    fun rotate(array: Array<IntArray>): Array<IntArray> {
        val tempArray = Array<IntArray>(array.size) { IntArray(array.size) }
        val maxIndex = array.size - 1
        for (y in 0 until array.size) {
            for (x in 0 until array.size) {
                tempArray[y][x] = array[maxIndex - x][y]
            }
        }
        return tempArray
    }

//  https://programmers.co.kr/learn/courses/30/lessons/60060
//1. 가사 검색
    /**
    문제 설명

    [본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

    친구들로부터 천재 프로그래머로 불리는 프로도는 음악을 하는 친구로부터 자신이 좋아하는 노래 가사에 사용된 단어들 중에 특정 키워드가 몇 개 포함되어 있는지 궁금하니 프로그램으로 개발해 달라는 제안을 받았습니다.
    그 제안 사항 중, 키워드는 와일드카드 문자중 하나인 '?'가 포함된 패턴 형태의 문자열을 뜻합니다. 와일드카드 문자인 '?'는 글자 하나를 의미하며, 어떤 문자에도 매치된다고 가정합니다. 예를 들어 "fro??"는 "frodo", "front", "frost" 등에 매치되지만 "frame", "frozen"에는 매치되지 않습니다.

    가사에 사용된 모든 단어들이 담긴 배열 words와 찾고자 하는 키워드가 담긴 배열 queries가 주어질 때, 각 키워드 별로 매치된 단어가 몇 개인지 순서대로 배열에 담아 반환하도록 solution 함수를 완성해 주세요.

    가사 단어 제한사항
    words의 길이(가사 단어의 개수)는 2 이상 100,000 이하입니다.
    각 가사 단어의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없습니다.
    전체 가사 단어 길이의 합은 2 이상 1,000,000 이하입니다.
    가사에 동일 단어가 여러 번 나올 경우 중복을 제거하고 words에는 하나로만 제공됩니다.
    각 가사 단어는 오직 알파벳 소문자로만 구성되어 있으며, 특수문자나 숫자는 포함하지 않는 것으로 가정합니다.
    검색 키워드 제한사항
    queries의 길이(검색 키워드 개수)는 2 이상 100,000 이하입니다.
    각 검색 키워드의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없습니다.
    전체 검색 키워드 길이의 합은 2 이상 1,000,000 이하입니다.
    검색 키워드는 중복될 수도 있습니다.
    각 검색 키워드는 오직 알파벳 소문자와 와일드카드 문자인 '?' 로만 구성되어 있으며, 특수문자나 숫자는 포함하지 않는 것으로 가정합니다.
    검색 키워드는 와일드카드 문자인 '?'가 하나 이상 포함돼 있으며, '?'는 각 검색 키워드의 접두사 아니면 접미사 중 하나로만 주어집니다.
    예를 들어 "??odo", "fro??", "?????"는 가능한 키워드입니다.
    반면에 "frodo"('?'가 없음), "fr?do"('?'가 중간에 있음), "?ro??"('?'가 양쪽에 있음)는 불가능한 키워드입니다.

     */
    @Test
    fun Test04() {
        TrieTest().Test01()
    }
}