package netapi.mgkim.com.testDemo.kotlin.kakaoTest

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class KakaoTest2019() {
    //https://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/

//    https://www.welcomekakao.com/learn/courses/30/lessons/42888?language=kotlin
//1. 오픈채팅방
    /**
    문제 설명

    카카오톡 오픈채팅방에서는 친구가 아닌 사람들과 대화를 할 수 있는데, 본래 닉네임이 아닌 가상의 닉네임을 사용하여 채팅방에 들어갈 수 있다.

    신입사원인 김크루는 카카오톡 오픈 채팅방을 개설한 사람을 위해, 다양한 사람들이 들어오고, 나가는 것을 지켜볼 수 있는 관리자창을 만들기로 했다. 채팅방에 누군가 들어오면 다음 메시지가 출력된다.

    [닉네임]님이 들어왔습니다.

    채팅방에서 누군가 나가면 다음 메시지가 출력된다.

    [닉네임]님이 나갔습니다.

    채팅방에서 닉네임을 변경하는 방법은 다음과 같이 두 가지이다.

    채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
    채팅방에서 닉네임을 변경한다.
    닉네임을 변경할 때는 기존에 채팅방에 출력되어 있던 메시지의 닉네임도 전부 변경된다.

    예를 들어, 채팅방에 Muzi와 Prodo라는 닉네임을 사용하는 사람이 순서대로 들어오면 채팅방에는 다음과 같이 메시지가 출력된다.

    Muzi님이 들어왔습니다.
    Prodo님이 들어왔습니다.

    채팅방에 있던 사람이 나가면 채팅방에는 다음과 같이 메시지가 남는다.

    Muzi님이 들어왔습니다.
    Prodo님이 들어왔습니다.
    Muzi님이 나갔습니다.

    Muzi가 나간후 다시 들어올 때, Prodo 라는 닉네임으로 들어올 경우 기존에 채팅방에 남아있던 Muzi도 Prodo로 다음과 같이 변경된다.

    Prodo님이 들어왔습니다.
    Prodo님이 들어왔습니다.
    Prodo님이 나갔습니다.
    Prodo님이 들어왔습니다.

    채팅방은 중복 닉네임을 허용하기 때문에, 현재 채팅방에는 Prodo라는 닉네임을 사용하는 사람이 두 명이 있다. 이제, 채팅방에 두 번째로 들어왔던 Prodo가 Ryan으로 닉네임을 변경하면 채팅방 메시지는 다음과 같이 변경된다.

    Prodo님이 들어왔습니다.
    Ryan님이 들어왔습니다.
    Prodo님이 나갔습니다.
    Prodo님이 들어왔습니다.

    채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record가 매개변수로 주어질 때, 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return 하도록 solution 함수를 완성하라.

    제한사항
    record는 다음과 같은 문자열이 담긴 배열이며, 길이는 1 이상 100,000 이하이다.
    다음은 record에 담긴 문자열에 대한 설명이다.
    모든 유저는 [유저 아이디]로 구분한다.
    [유저 아이디] 사용자가 [닉네임]으로 채팅방에 입장 - Enter [유저 아이디] [닉네임] (ex. Enter uid1234 Muzi)
    [유저 아이디] 사용자가 채팅방에서 퇴장 - Leave [유저 아이디] (ex. Leave uid1234)
    [유저 아이디] 사용자가 닉네임을 [닉네임]으로 변경 - Change [유저 아이디] [닉네임] (ex. Change uid1234 Muzi)
    첫 단어는 Enter, Leave, Change 중 하나이다.
    각 단어는 공백으로 구분되어 있으며, 알파벳 대문자, 소문자, 숫자로만 이루어져있다.
    유저 아이디와 닉네임은 알파벳 대문자, 소문자를 구별한다.
    유저 아이디와 닉네임의 길이는 1 이상 10 이하이다.
    채팅방에서 나간 유저가 닉네임을 변경하는 등 잘못 된 입력은 주어지지 않는다.
     */
    @Test
    fun Test01() {
        var n = 5
        var record = arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan")
        var result = arrayOf("Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다.")
        var out = solution01(record)
        Assert.assertEquals(result, out)

    }

    companion object ChatAction {
        val ACTION_ENTER = "Enter"
        val ACTION_CHANGE = "Change"
        val ACTION_LEAVE = "Leave"

        val ACTION_ENTER_STR = "님이 들어왔습니다."
        val ACTION_LEAVE_STR = "님이 나갔습니다."
    }

    fun solution01(record: Array<String>): Array<String> {
        var idMap = HashMap<String, String>()
        var chats = ArrayList<Pair<String, String>>()
        record.forEach {
            var strs = it.split(" ")
            when (strs[0]) {
                ChatAction.ACTION_ENTER -> {
                    chats.add(Pair(strs[0], strs[1]))
                    idMap.put(strs[1], strs[2])
                }
                ChatAction.ACTION_CHANGE -> {
                    idMap.put(strs[1], strs[2])
                }
                ChatAction.ACTION_LEAVE -> {
                    chats.add(Pair(strs[0], strs[1]))
                }
            }
        }

        var answer = Array<String>(chats.size) {
            idMap.get(chats[it].second) + if (chats[it].first == ChatAction.ACTION_ENTER) {
                ChatAction.ACTION_ENTER_STR
            } else {
                ChatAction.ACTION_LEAVE_STR
            }

        }
//        answer.forEach {
//            println(it)
//        }
        return answer
    }


//    https://www.welcomekakao.com/learn/courses/30/lessons/42889
//2. 실패율
    /**

    문제 설명

    슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌다. 그녀가 만든 프랜즈 오천성이 대성공을 거뒀지만, 요즘 신규 사용자의 수가 급감한 것이다. 원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였다.

    이 문제를 어떻게 할까 고민 한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했다. 역시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만, 실패율을 구하는 부분에서 위기에 빠지고 말았다. 오렐리를 위해 실패율을 구하는 코드를 완성하라.

    실패율은 다음과 같이 정의한다.
    스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
    전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때, 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.

    제한사항
    스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
    stages의 길이는 1 이상 200,000 이하이다.
    stages에는 1 이상 N + 1 이하의 자연수가 담겨있다.
    각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
    단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
    만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
    스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
     */
    @Test
    fun Test02() {
        var n = 5
        var stages = intArrayOf(2, 1, 2, 6, 2, 4, 3, 3)
        var result = intArrayOf(3,4,2,1,5)
        var out = solution02(n, stages)
//        Assert.assertEquals(result, out)

        n = 4
        stages = intArrayOf(4,4,4,4,4)
        result = intArrayOf(4,1,2,3)
        out = solution02(n, stages)
//        Assert.assertEquals(result, out)


        n = 5
        stages = intArrayOf(4,4,4,4,4, 5, 6, 6, 6, 6, 6)
        result = intArrayOf(4,1,2,3)
        out = solution02(n, stages)

    }
    fun solution02(N: Int, stages: IntArray): IntArray {
        stages.sort()
        var curStage = 0
        var curCnt = 0
        var players = Array(N) {
            Pair(0.0, it + 1)
        }
        stages.forEachIndexed { i, num ->
            if (curStage == num) {
                curCnt++
            } else {
                if (curCnt > 0) {
                    players[curStage - 1] = Pair(curCnt / (stages.size - i + curCnt).toDouble(), curStage)
                }
                curCnt = 1
                curStage = num
            }
        }
        if (curCnt > 1 && curStage <= players.size) {
            players[curStage - 1] = Pair(curCnt / (curCnt).toDouble(), curStage)
        }
        players.sortWith(kotlin.Comparator() { o1, o2 ->
            if (o1.first > o2.first) {
                -1
            } else if (o1.first == o2.first && o1.second < o2.second) {
                -1
            } else {
                1
            }
        })
        var answer = IntArray(players.size) {
            players[it].second
        }
//        println(Arrays.toString(players))
//        println(Arrays.toString(answer))
        return answer
    }


//    https://www.welcomekakao.com/learn/courses/30/lessons/42890
//3. 후보키
    /**

    문제 설명

    프렌즈대학교 컴퓨터공학과 조교인 제이지는 네오 학과장님의 지시로, 학생들의 인적사항을 정리하는 업무를 담당하게 되었다.

    그의 학부 시절 프로그래밍 경험을 되살려, 모든 인적사항을 데이터베이스에 넣기로 하였고, 이를 위해 정리를 하던 중에 후보키(Candidate Key)에 대한 고민이 필요하게 되었다.

    후보키에 대한 내용이 잘 기억나지 않던 제이지는, 정확한 내용을 파악하기 위해 데이터베이스 관련 서적을 확인하여 아래와 같은 내용을 확인하였다.

    관계 데이터베이스에서 릴레이션(Relation)의 튜플(Tuple)을 유일하게 식별할 수 있는 속성(Attribute) 또는 속성의 집합 중, 다음 두 성질을 만족하는 것을 후보 키(Candidate Key)라고 한다.
    유일성(uniqueness) : 릴레이션에 있는 모든 튜플에 대해 유일하게 식별되어야 한다.
    최소성(minimality) : 유일성을 가진 키를 구성하는 속성(Attribute) 중 하나라도 제외하는 경우 유일성이 깨지는 것을 의미한다. 즉, 릴레이션의 모든 튜플을 유일하게 식별하는 데 꼭 필요한 속성들로만 구성되어야 한다.
    제이지를 위해, 아래와 같은 학생들의 인적사항이 주어졌을 때, 후보 키의 최대 개수를 구하라.

    제한사항
    relation은 2차원 문자열 배열이다.
    relation의 컬럼(column)의 길이는 1 이상 8 이하이며, 각각의 컬럼은 릴레이션의 속성을 나타낸다.
    relation의 로우(row)의 길이는 1 이상 20 이하이며, 각각의 로우는 릴레이션의 튜플을 나타낸다.
    relation의 모든 문자열의 길이는 1 이상 8 이하이며, 알파벳 소문자와 숫자로만 이루어져 있다.
    relation의 모든 튜플은 유일하게 식별 가능하다.(즉, 중복되는 튜플은 없다.)
     */
    @Test
    fun Test03() {
        var relation = arrayOf<Array<String>>(
                arrayOf("100", "ryan", "music", "2"),
                arrayOf("200", "apeach", "math", "2"),
                arrayOf("300", "tube", "computer", "3"),
                arrayOf("400", "con", "computer", "4"),
                arrayOf("500", "muzi", "music", "3"),
                arrayOf("600", "apeach", "music", "2"))
        var result = 2
        var out = solution03(relation)
        Assert.assertEquals(result, out)
    }
    fun solution03(relation: Array<Array<String>>): Int {

        val masks = IntArray(relation[0].size) {
            /*1 shl */it
        }
        val candidateMasks = ArrayList<Int>()

        val combArr = IntArray(relation[0].size)

        // n개의 문자중 r개를 골라 정렬하여 출력
//        combination(masks, combArr, masks.size, 2, 0, 0)

        println(Arrays.toString(masks))

        var answer = 0
        return answer
    }


    //조합
    //n개 에서 r개를 뽑아 출력 (중복X)
    //(ex : ab, ac, ad, bc, bd...)
    fun combination(arr: IntArray, tempArr: IntArray, size: Int, cnt: Int, index: Int, target: Int) {
        if (cnt == 0) {
            permutation(tempArr, index)
        } else if (target == size) {
            return
        } else {
            tempArr[index] = arr[target]
            combination(arr, tempArr, size, cnt - 1, index + 1, target + 1)
            combination(arr, tempArr, size, cnt, index, target + 1)
        }
    }

    //순열
    //n개 에서 r개를 뽑아 출력 (중복O)
    //(ex : ab, ac, ad, ba, bc...)
    fun permutation(arr: IntArray, index: Int) {
        if (index == arr.size) {
            for (n in arr) print(n)
            println()
        } else {
            for (i in index until arr.size) {
                swap(arr, i, index)
                permutation(arr, index + 1)
                swap(arr, i, index)
            }
        }
    }

    fun swap(arr: IntArray, n1: Int, n2: Int) {
        val temp = arr[n1]
        arr[n1] = arr[n2]
        arr[n2] = temp
    }


}