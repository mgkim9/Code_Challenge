package netapi.mgkim.com.testDemo.kotlin.kakaoTest

import netapi.mgkim.com.netapitest2.kotlin.data_structure.TrieTest
import org.junit.Assert
import org.junit.Test

//    https://tech.kakao.com/2019/10/02/kakao-blind-recruitment-2020-round1/
class KakaoTest2020{

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

            if(answer > resultStr.length) {
                answer = resultStr.length
            }
//            strArray.add(resultStr.toString())
        }

//        println(strArray.toString())
        println(answer)
        return answer
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