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


//  https://programmers.co.kr/learn/courses/30/lessons/60061
//5. 기둥과 보 설치
    /**
    문제 설명
    빙하가 깨지면서 스노우타운에 떠내려 온 죠르디는 인생 2막을 위해 주택 건축사업에 뛰어들기로 결심하였습니다. 죠르디는 기둥과 보를 이용하여 벽면 구조물을 자동으로 세우는 로봇을 개발할 계획인데, 그에 앞서 로봇의 동작을 시뮬레이션 할 수 있는 프로그램을 만들고 있습니다.
    프로그램은 2차원 가상 벽면에 기둥과 보를 이용한 구조물을 설치할 수 있는데, 기둥과 보는 길이가 1인 선분으로 표현되며 다음과 같은 규칙을 가지고 있습니다.

    기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
    보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    단, 바닥은 벽면의 맨 아래 지면을 말합니다.

    2차원 벽면은 n x n 크기 정사각 격자 형태이며, 각 격자는 1 x 1 크기입니다. 맨 처음 벽면은 비어있는 상태입니다. 기둥과 보는 격자선의 교차점에 걸치지 않고, 격자 칸의 각 변에 정확히 일치하도록 설치할 수 있습니다. 다음은 기둥과 보를 설치해 구조물을 만든 예시입니다.
    예를 들어, 위 그림은 다음 순서에 따라 구조물을 만들었습니다.

    (1, 0)에서 위쪽으로 기둥을 하나 설치 후, (1, 1)에서 오른쪽으로 보를 하나 만듭니다.
    (2, 1)에서 위쪽으로 기둥을 하나 설치 후, (2, 2)에서 오른쪽으로 보를 하나 만듭니다.
    (5, 0)에서 위쪽으로 기둥을 하나 설치 후, (5, 1)에서 위쪽으로 기둥을 하나 더 설치합니다.
    (4, 2)에서 오른쪽으로 보를 설치 후, (3, 2)에서 오른쪽으로 보를 설치합니다.
    만약 (4, 2)에서 오른쪽으로 보를 먼저 설치하지 않고, (3, 2)에서 오른쪽으로 보를 설치하려 한다면 2번 규칙에 맞지 않으므로 설치가 되지 않습니다. 기둥과 보를 삭제하는 기능도 있는데 기둥과 보를 삭제한 후에 남은 기둥과 보들 또한 위 규칙을 만족해야 합니다. 만약, 작업을 수행한 결과가 조건을 만족하지 않는다면 해당 작업은 무시됩니다.

    벽면의 크기 n, 기둥과 보를 설치하거나 삭제하는 작업이 순서대로 담긴 2차원 배열 build_frame이 매개변수로 주어질 때, 모든 명령어를 수행한 후 구조물의 상태를 return 하도록 solution 함수를 완성해주세요.

    제한사항

    n은 5 이상 100 이하인 자연수입니다.
    build_frame의 세로(행) 길이는 1 이상 1,000 이하입니다.
    build_frame의 가로(열) 길이는 4입니다.
    build_frame의 원소는 [x, y, a, b]형태입니다.
    x, y는 기둥, 보를 설치 또는 삭제할 교차점의 좌표이며, [가로 좌표, 세로 좌표] 형태입니다.
    a는 설치 또는 삭제할 구조물의 종류를 나타내며, 0은 기둥, 1은 보를 나타냅니다.
    b는 구조물을 설치할 지, 혹은 삭제할 지를 나타내며 0은 삭제, 1은 설치를 나타냅니다.
    벽면을 벗어나게 기둥, 보를 설치하는 경우는 없습니다.
    바닥에 보를 설치 하는 경우는 없습니다.
    구조물은 교차점 좌표를 기준으로 보는 오른쪽, 기둥은 위쪽 방향으로 설치 또는 삭제합니다.
    구조물이 겹치도록 설치하는 경우와, 없는 구조물을 삭제하는 경우는 입력으로 주어지지 않습니다.
    최종 구조물의 상태는 아래 규칙에 맞춰 return 해주세요.
    return 하는 배열은 가로(열) 길이가 3인 2차원 배열로, 각 구조물의 좌표를 담고있어야 합니다.
    return 하는 배열의 원소는 [x, y, a] 형식입니다.
    x, y는 기둥, 보의 교차점 좌표이며, [가로 좌표, 세로 좌표] 형태입니다.
    기둥, 보는 교차점 좌표를 기준으로 오른쪽, 또는 위쪽 방향으로 설치되어 있음을 나타냅니다.
    a는 구조물의 종류를 나타내며, 0은 기둥, 1은 보를 나타냅니다.
    return 하는 배열은 x좌표 기준으로 오름차순 정렬하며, x좌표가 같을 경우 y좌표 기준으로 오름차순 정렬해주세요.
    x, y좌표가 모두 같은 경우 기둥이 보보다 앞에 오면 됩니다.
     */
    @Test
    fun Test05() {
        var build_frame: Array<IntArray>
        var results: Array<IntArray>
        var n = 0
        var out: Array<IntArray>

        build_frame = arrayOf(intArrayOf(1, 0, 0, 1), intArrayOf(1, 1, 1, 1), intArrayOf(2, 1, 0, 1), intArrayOf(2, 2, 1, 1), intArrayOf(5, 0, 0, 1), intArrayOf(5, 1, 0, 1), intArrayOf(4, 2, 1, 1), intArrayOf(3, 2, 1, 1))
        results = arrayOf(intArrayOf(1, 0, 0), intArrayOf(1, 1, 1), intArrayOf(2, 1, 0), intArrayOf(2, 2, 1), intArrayOf(3, 2, 1), intArrayOf(4, 2, 1), intArrayOf(5, 0, 0), intArrayOf(5, 1, 0))
        n = 5
        out = solution05(n, build_frame)
        out.forEach {
            println(Arrays.toString(it))
        }
        Assert.assertEquals(results, out)
        println()

        build_frame = arrayOf(intArrayOf(0, 0, 0, 1), intArrayOf(2, 0, 0, 1), intArrayOf(4, 0, 0, 1), intArrayOf(0, 1, 1, 1), intArrayOf(1, 1, 1, 1), intArrayOf(2, 1, 1, 1), intArrayOf(3, 1, 1, 1), intArrayOf(2, 0, 0, 0), intArrayOf(1, 1, 1, 0), intArrayOf(2, 2, 0, 1))
        results = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 1), intArrayOf(1, 1, 1), intArrayOf(2, 1, 1), intArrayOf(3, 1, 1), intArrayOf(4, 0, 0))
        n = 5
        out = solution05(n, build_frame)

        out.forEach {
            println(Arrays.toString(it))
        }

        Assert.assertEquals(results, out)


    }

    val _X = 0   //X 좌표
    val _Y = 1   //Y 좌표
    val FRAME = 2   //구조물
    val METHOD = 3   //동작

    val DELETE = 0  //제거
    val ADD = 1     //추가

    val PILLAR = 0 //기둥
    val ROOF = 1    // 보

    val GRID_NONE = 0b00    // 빈공간
    val GRID_PILLAR = 0b01 //기둥
    val GRID_ROOF = 0b10    // 보

    fun solution05(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        var size = n + 1
        var board = Array<IntArray>(size) { IntArray(size) { GRID_NONE } }

        var count = 0
        build_frame.forEach { build ->
            when (build[METHOD]) {
                ADD -> {
                    var buildingNow = if (build[FRAME] == PILLAR) GRID_PILLAR else GRID_ROOF
                    if (canBuild(board, build[_X], build[_Y], buildingNow)) {
                        board[build[_Y]][build[_X]] = board[build[_Y]][build[_X]] or buildingNow
                        count++
                        println("ADD success : " + Arrays.toString(build))
                    } else {
                        //실패!
                        println("ADD fail : " + Arrays.toString(build))
                    }
                }
                DELETE -> {
                    var rolback: Int = board[build[_Y]][build[_X]]
                    var isRolback = false
                    val checks = ArrayList<IntArray>()
                    if (build[FRAME] == PILLAR) {
                        board[build[_Y]][build[_X]] = board[build[_Y]][build[_X]] and GRID_PILLAR.inv() //선 삭제
                        if (build[_Y] + 2 < size) {  // y축 벗어나는지 체크
                            checks.add(intArrayOf(build[_X], build[_Y] + 1, board[build[_Y] + 1][build[_X]]))
                            if (build[_X] - 1 >= 0) { // x축 벗어나는지 체크
                                checks.add(intArrayOf(build[_X] - 1, build[_Y] + 1, board[build[_Y] + 1][build[_X] - 1]))
                            }
                        }
                    } else {
                        board[build[_Y]][build[_X]] = board[build[_Y]][build[_X]] and GRID_ROOF.inv() //선 삭제
                        checks.add(intArrayOf(build[_X], build[_Y], board[build[_Y]][build[_X]]))
                        if (build[_X] - 1 >= 0) {// x축 벗어나는지 체크
                            checks.add(intArrayOf(build[_X] - 1, build[_Y], board[build[_Y]][build[_X] - 1]))
                        }
                        if (build[_X] + 2 < size) {// x축 벗어나는지 체크
                            checks.add(intArrayOf(build[_X] + 1, build[_Y], board[build[_Y]][build[_X] + 1]))
                        }
                    }

                    checks.forEach { check ->
                        //문제점 체크
                        if (!canBuild(board, check[_X], check[_Y], check[FRAME])) {
                            isRolback = true
                            return@forEach
                        }
                    }

                    if (isRolback) {   //후 롤백
                        board[build[_Y]][build[_X]] = rolback
                        println("DELETE fail : " + Arrays.toString(build) + " rolback " + rolback)
                    } else {
                        count--
                        println("DELETE success : " + Arrays.toString(build))
                    }
                }
            }
        }
        board.forEach {
            println(Arrays.toString(it))
        }
        println()

        var results = Array<IntArray>(count) { IntArray(3) }
        var curCnt = 0

        for (x in 0 until size) {
            for (y in 0 until size) {
                if (board[y][x] and GRID_PILLAR > 0) {
                    results[curCnt][_X] = x
                    results[curCnt][_Y] = y
                    results[curCnt][FRAME] = PILLAR
                    curCnt++
                }
                if (board[y][x] and GRID_ROOF > 0) {
                    results[curCnt][_X] = x
                    results[curCnt][_Y] = y
                    results[curCnt][FRAME] = ROOF
                    curCnt++
                }
            }
        }

        return results
    }

    fun canBuild(board: Array<IntArray>, x: Int, y: Int, gridFrame: Int): Boolean {
        var curGrid = GRID_NONE
        if (gridFrame and GRID_PILLAR > 0) {
            //기둥 설치 가능 case
            //ALL. Y < size - 1
            //1.바닥 인경우
            //2.기둥 위인 경우
            //3.지붕 오른쪽 인경우
            //4.지붕 위 인경우
            if (y < board.size - 1
                    && (y == 0
                            || y >= 1 && board[y - 1][x] and GRID_PILLAR > 0
                            || x >= 1 && board[y][x - 1] and GRID_ROOF > 0
                            || board[y][x] and GRID_ROOF > 0)) {
                curGrid = curGrid or GRID_PILLAR
            }
        }
        if (gridFrame and GRID_ROOF > 0) {
            //지붕 설치 가능 case
            //ALL. Y > 0 && X < size - 1
            //1.밑에 기둥이 있는 경우
            //2.우측 밑에 기둥이 있는 경우
            //3.우측과 좌측에 지붕이 있는 경우
            if (y > 0 && x < board.size - 1
                    && (board[y - 1][x] and GRID_PILLAR > 0
                            || board[y - 1][x + 1] and GRID_PILLAR > 0
                            || x >= 1 && board[y][x - 1] and GRID_ROOF > 0 && board[y][x + 1] and GRID_ROOF > 0)) {
                curGrid = curGrid or GRID_ROOF
            }
        }
        return curGrid == gridFrame
    }


//  https://programmers.co.kr/learn/courses/30/lessons/60062
//6. 외벽 점검
    /**
    문제 설명
    레스토랑을 운영하고 있는 스카피는 레스토랑 내부가 너무 낡아 친구들과 함께 직접 리모델링 하기로 했습니다. 레스토랑이 있는 곳은 스노우타운으로 매우 추운 지역이어서 내부 공사를 하는 도중에 주기적으로 외벽의 상태를 점검해야 할 필요가 있습니다.

    레스토랑의 구조는 완전히 동그란 모양이고 외벽의 총 둘레는 n미터이며, 외벽의 몇몇 지점은 추위가 심할 경우 손상될 수도 있는 취약한 지점들이 있습니다. 따라서 내부 공사 도중에도 외벽의 취약 지점들이 손상되지 않았는 지, 주기적으로 친구들을 보내서 점검을 하기로 했습니다. 다만, 빠른 공사 진행을 위해 점검 시간을 1시간으로 제한했습니다. 친구들이 1시간 동안 이동할 수 있는 거리는 제각각이기 때문에, 최소한의 친구들을 투입해 취약 지점을 점검하고 나머지 친구들은 내부 공사를 돕도록 하려고 합니다. 편의 상 레스토랑의 정북 방향 지점을 0으로 나타내며, 취약 지점의 위치는 정북 방향 지점으로부터 시계 방향으로 떨어진 거리로 나타냅니다. 또, 친구들은 출발 지점부터 시계, 혹은 반시계 방향으로 외벽을 따라서만 이동합니다.

    외벽의 길이 n, 취약 지점의 위치가 담긴 배열 weak, 각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열 dist가 매개변수로 주어질 때, 취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값을 return 하도록 solution 함수를 완성해주세요.

    제한사항
    n은 1 이상 200 이하인 자연수입니다.
    weak의 길이는 1 이상 15 이하입니다.
    서로 다른 두 취약점의 위치가 같은 경우는 주어지지 않습니다.
    취약 지점의 위치는 오름차순으로 정렬되어 주어집니다.
    weak의 원소는 0 이상 n - 1 이하인 정수입니다.
    dist의 길이는 1 이상 8 이하입니다.
    dist의 원소는 1 이상 100 이하인 자연수입니다.
    친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에는 -1을 return 해주세요.

     */
    @Test
    fun Test06() {
        var weak: IntArray
        var dist: IntArray
        var n = 0
        var result = 0
        var out = 0

        weak = intArrayOf(1, 5, 6, 10)
        dist = intArrayOf(1, 2, 3, 4)
        n = 12
        result = 2
        out = solution06(n, weak, dist)
        Assert.assertEquals(result, out)

        weak = intArrayOf(1, 3, 4, 9, 10)
        dist = intArrayOf(2, 5, 7)
        n = 12
        result = 1
        out = solution06(n, weak, dist)
        Assert.assertEquals(result, out)
    }

    fun solution06(n: Int, weak: IntArray, dist: IntArray): Int {
        dist.sortDescending()
        if(weak.size == 1) {
            return 1
        }
        purmutation(n, weak, dist, 0)
        if (minCnt == Integer.MAX_VALUE) {
            return -1
        }
        return minCnt
    }

    var minCnt = Integer.MAX_VALUE
    private fun purmutation(n: Int, weak: IntArray, dist: IntArray, cnt: Int) {
        if (cnt >= dist.size) {
            // dist의 순열
            var curCnt = checkLine(n, weak, dist)
            if (minCnt > curCnt) {
                minCnt = curCnt
            }
            println("curCnt " + curCnt + " " + Arrays.toString(dist))
            return
        }
        for (i in cnt until dist.size) {
            swap(dist, i, cnt)
            purmutation(n, weak, dist, cnt + 1)
            swap(dist, i, cnt)
        }
    }

    private fun checkLine(n: Int, weak: IntArray, dist: IntArray): Int {
        root@ for (cnt in 0 until weak.size) { //시작점을 다르게하여 모든 case 체크
            var index = 0
            var prev = weak[cnt]
            var runner = dist[index]
            root2@ for (i in 1 until weak.size) {
                if (index >= minCnt) {
                    break@root2
                }
                var next = weak[(i + cnt) % weak.size]
                var distance =
                        if (prev > next) {    // 끝 지점 통과
                            next + n - prev
                        } else {
                            next - prev
                        }
                prev = next
                if (runner >= distance) {
                    runner -= distance
                } else {
                    if (index >= dist.size - 1) {
                        continue@root
                    }
                    runner = dist[++index]
                    if(i >= weak.size - 1 ) {
                        break@root2
                    }
                }
            }
            if (minCnt > index + 1) {
                minCnt = index + 1
            }
        }

        return minCnt
    }

    private fun swap(arr: IntArray, a: Int, b: Int) {
        val temp = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }
}