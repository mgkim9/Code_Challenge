package netapi.mgkim.com.testDemo.kotlin.kakaoTest

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class KakaoTest2018 () {

//    https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//1. 비밀 지도(난이도: 하)
    /**
    입력 형식
    입력으로 지도의 한 변 크기 n 과 2개의 정수 배열 arr1, arr2가 들어온다.
    1 ≦ n ≦ 16
    arr1, arr2는 길이 n인 정수 배열로 주어진다.
    정수 배열의 각 원소 x를 이진수로 변환했을 때의 길이는 n 이하이다. 즉, 0 ≦ x ≦ 2^n – 1을 만족한다.
     */
    @Test
    fun Test01() {
        var n = 5
        var arr1 = intArrayOf(9, 20, 28, 18, 11)
        var arr2 = intArrayOf(30, 1, 21, 17, 28)
        var out = arrayOf("#####","# # #", "### #", "#  ##", "#####")
        var curOut = solution01(n, arr1, arr2)
        assertEquals(out, curOut)
        println("test1 성공")

        n = 6
        arr1 = intArrayOf(46, 33, 33 ,22, 31, 50)
        arr2 = intArrayOf(27 ,56, 19, 14, 14, 10)
        out = arrayOf("######", "###  #", "##  ##", "#### ", "#####", "### # ")
        curOut = solution01(n, arr1, arr2)
        assertEquals(out, curOut)
        println("test2 성공")

    }

    private fun solution01(n: Int, arr1: IntArray, arr2: IntArray): Array<String> {
        var outArr: Array<String> = Array<String>(arr1.size) { i ->
            Integer.toBinaryString(arr1[i] or arr2[i]).replace("1", "#").replace("0", " ")
        }
        println(outArr.asList().toString())
        return outArr
    }


//    https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//2. 다트 게임(난이도: 하)
    /**
    입력 형식
    “점수|보너스|[옵션]”으로 이루어진 문자열 3세트.
    예) 1S2D*3T

    점수는 0에서 10 사이의 정수이다.
    보너스는 S, D, T 중 하나이다.
    옵선은 *이나 # 중 하나이며, 없을 수도 있다.
     */
    @Test
    fun Test02() {
        var target = "1S2D*3T"
        var points = splitPoint(target)
        var totalScore = sumScore(points)
        assertEquals(37, totalScore)

        target = "1D2S#10S"
        points = splitPoint(target)
        totalScore = sumScore(points)
        assertEquals(9, totalScore)

        target = "1D2S0T"
        points = splitPoint(target)
        totalScore = sumScore(points)
        assertEquals(3, totalScore)

        target = "1S*2T*3S"
        points = splitPoint(target)
        totalScore = sumScore(points)
        assertEquals(23, totalScore)

        target = "1D#2S*3S"
        points = splitPoint(target)
        totalScore = sumScore(points)
        assertEquals(5, totalScore)

        target = "1T2D3D#"
        points = splitPoint(target)
        totalScore = sumScore(points)
        assertEquals(-4, totalScore)

        target = "1D2S3T*"
        points = splitPoint(target)
        totalScore = sumScore(points)
        assertEquals(59, totalScore)
    }

    val PATTERN_POINT = "[\\d]+"   //점수만
    val PATTERN_BONUS_OPTION = "[a-zA-Z]+[^0-9a-zA-Z]*"   //bonus + option
    class Point(var point: Int, var bonus: Char, var option: Char?){
        fun getScore():Int{
            return when(bonus) {
                'D' -> point * point
                'T' -> point * point * point
                else -> point
            }
        }
        override fun toString(): String {
            return "Point(point=$point, bonus=$bonus, option=$option)"
        }
    }
    private fun splitPoint(target: String): Array<Point> {
        var array : Array<Point> = Array(3) {
            Point(0, 'S', null)
        }

        var pat = Pattern.compile(PATTERN_POINT)
        var match = pat.matcher(target)
        var matchCount = 0
        while (match.find()) {
            match.group().apply {
                array[matchCount].point = this.toInt()
            }
            matchCount++
        }

        pat = Pattern.compile(PATTERN_BONUS_OPTION)
        match = pat.matcher(target)
        matchCount = 0
        while (match.find()) {
            match.group().apply {
                array[matchCount].bonus = this[0]
                if(length > 1) {
                    array[matchCount].option = this[1]
                }
            }
            matchCount++
        }
        return array
    }

    private fun sumScore(points: Array<Point>): Int {
        var totalScore = 0
        for (i in 0 until points.size) {
            points[i].apply {
                var score = getScore()
                when (option) {
                    '*' -> {
                        score *= 2
                    }
                    '#' -> score *= -1
                }
                if (i < points.size - 1 && points[i + 1].option == '*') {
                    score *= 2
                }

                totalScore += score
            }
        }
        println("totalScore : " + totalScore)
        return totalScore
    }



//    https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//3. 캐시(난이도: 하)
    /**
    입력 형식
    캐시 크기(cacheSize)와 도시이름 배열(cities)을 입력받는다.
    cacheSize는 정수이며, 범위는 0 ≦ cacheSize ≦ 30 이다.
    cities는 도시 이름으로 이뤄진 문자열 배열로, 최대 도시 수는 100,000개이다.
    각 도시 이름은 공백, 숫자, 특수문자 등이 없는 영문자로 구성되며, 대소문자 구분을 하지 않는다. 도시 이름은 최대 20자로 이루어져 있다.
    출력 형식
    입력된 도시이름 배열을 순서대로 처리할 때, “총 실행시간”을 출력한다.
     */
    @Test
    fun Test03() {
        var cacheSize = 3
        var cities = arrayOf("Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA")
        var runTime = getRunTime(cacheSize, cities)
        assertEquals(50, runTime)

        cacheSize = 3
        cities = arrayOf("Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul")
        runTime = getRunTime(cacheSize, cities)
        assertEquals(21, runTime)

        cacheSize = 2
        cities = arrayOf("Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome")
        runTime = getRunTime(cacheSize, cities)
        assertEquals(60, runTime)

        cacheSize = 5
        cities = arrayOf("Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome")
        runTime = getRunTime(cacheSize, cities)
        assertEquals(52, runTime)

        cacheSize = 2
        cities = arrayOf("Jeju", "Pangyo", "NewYork", "newyork")
        runTime = getRunTime(cacheSize, cities)
        assertEquals(16, runTime)

        cacheSize = 0
        cities = arrayOf("Jeju", "Pangyo", "Seoul", "NewYork", "LA")
        runTime = getRunTime(cacheSize, cities)
        assertEquals(25, runTime)
    }

    private fun getRunTime(cacheSize: Int, cities: Array<String>): Int {
        var cache = LRUCahce<String, Boolean>(cacheSize)
        cities.forEach {
            cache.put(it, true)
        }
        println("runTime : " + cache.runTime)
        return cache.runTime
    }

    class LRUCahce<K, V>(private val cacheSize:Int) :LinkedHashMap<K, V>(cacheSize, 1F, true) {
        var runTime = 0
        override fun put(key: K, value: V): V? {
            var curKey = if (key is String) key.toLowerCase() else key
            runTime += if(containsKey(curKey)) 1 else 5
            return super.put(curKey as K, value)
        }

        override fun putAll(from: Map<out K, V>) {
            from.forEach{
                var curKey = if (it.key is String) (it.key as String).toLowerCase() else it.key
                runTime += if(containsKey(curKey)) 1 else 5
            }
            super.putAll(from)
        }

        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            return size > cacheSize
        }
    }

//    https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//4. 셔틀버스(난이도: 중)
    /**
    입력 형식
    셔틀 운행 횟수 n, 셔틀 운행 간격 t, 한 셔틀에 탈 수 있는 최대 크루 수 m, 크루가 대기열에 도착하는 시각을 모은 배열 timetable이 입력으로 주어진다.

    0 ＜ n ≦ 10
    0 ＜ t ≦ 60
    0 ＜ m ≦ 45
    timetable은 최소 길이 1이고 최대 길이 2000인 배열로, 하루 동안 크루가 대기열에 도착하는 시각이 HH:MM 형식으로 이루어져 있다.
    크루의 도착 시각 HH:MM은 00:01에서 23:59 사이이다.
    출력 형식
    콘이 무사히 셔틀을 타고 사무실로 갈 수 있는 제일 늦은 도착 시각을 출력한다. 도착 시각은 HH:MM 형식이며, 00:00에서 23:59 사이의 값이 될 수 있다.
     */
    @Test
    fun Test04() {
        var n = 1
        var t = 1
        var m = 5
        var timetable = arrayOf("08:00", "08:01", "08:02", "08:03")
        var lastTime = lastBus(n, t, m, timetable)
        assertEquals("09:00", lastTime)

        n = 2
        t = 10
        m = 2
        timetable = arrayOf("09:10", "09:09", "08:00")
        lastTime = lastBus(n, t, m, timetable)
        assertEquals("09:09", lastTime)

        n = 2
        t = 1
        m = 2
        timetable = arrayOf("09:00", "09:00", "09:00", "09:00")
        lastTime = lastBus(n, t, m, timetable)
        assertEquals("08:59", lastTime)

        n = 1
        t = 1
        m = 5
        timetable = arrayOf("00:01", "00:01", "00:01", "00:01", "00:01")
        lastTime = lastBus(n, t, m, timetable)
        assertEquals("00:00", lastTime)

        n = 1
        t = 1
        m = 1
        timetable = arrayOf("23:59")
        lastTime = lastBus(n, t, m, timetable)
        assertEquals("09:00", lastTime)

        n = 10
        t = 60
        m = 45
        timetable = arrayOf("23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59")
        lastTime = lastBus(n, t, m, timetable)
        assertEquals("18:00", lastTime)

    }

    private val TIME_START_BUS = 9 * 60
    private fun lastBus(n: Int, t: Int, m: Int, timetable: Array<String>): String {
        Arrays.sort(timetable)
        var curTimeStr = "09:00"
        var curIndex = 0
        for (bunNum in 1..n) {
            curTimeStr = valueToTime(TIME_START_BUS + (bunNum - 1) * t)
            for (boardCnt in 1..m) {
                if (curIndex >= timetable.size) {
                    return if (n > 1)
                        valueToTime(TIME_START_BUS + (n - 1) * t)
                    else
                        "09:00"
                }
                if (curTimeStr.compareTo(timetable[curIndex]) > 0) {  //출발시간과 같거나 먼저 온경우
                    curIndex++
                }
            }
        }


        var lastTimeStr = if (curIndex == 0) {
            if (n > 1)
                valueToTime(TIME_START_BUS + (n - 1) * t)
            else
                "09:00"
        } else {
            curIndex--
            if (curIndex != 0 && timetable[curIndex] == timetable[curIndex - 1])  // 같은 시간에 온사람이 2이상 있는경우
                valueToTime(timeToValue(timetable[curIndex]) - 1)
            else
                timetable[curIndex]
        }

        println("lastTimeStr : " + lastTimeStr)
        return lastTimeStr
    }

    private fun timeToValue(time: String) :Int {
        var value = 0
        time.split(":").apply {
            if(size > 1) {
                value = get(0).toInt()*60 + get(1).toInt()
            }
        }
        return value
    }

    private fun valueToTime(value: Int) :String{
        return String.format("%02d:%02d", value/60, value%60)
    }


//    https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//5. 뉴스 클러스터링(난이도: 중)
    /**
    입력 형식
    입력으로는 str1과 str2의 두 문자열이 들어온다. 각 문자열의 길이는 2 이상, 1,000 이하이다.
    입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만든다. 이때 영문자로 된 글자 쌍만 유효하고,
    기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다. 예를 들어 “ab+”가 입력으로 들어오면, “ab”만 다중집합의 원소로 삼고, “b+”는 버린다.
    다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다. “AB”와 “Ab”, “ab”는 같은 원소로 취급한다.
    출력 형식
    입력으로 들어온 두 문자열의 자카드 유사도를 출력한다. 유사도 값은 0에서 1 사이의 실수이므로, 이를 다루기 쉽도록 65536을 곱한 후에 소수점 아래를 버리고 정수부만 출력한다
     */
    @Test
    fun Test05() {
        var str1 = "FRANCE"
        var str2 = "french"
        var out = clustering(str1, str2)
        assertEquals(16384, out)

        str1 = "handshake"
        str2 = "shake hands"
        out = clustering(str1, str2)
        assertEquals(65536, out)

        str1 = "aa1+aa2"
        str2 = "AAAA12"
        out = clustering(str1, str2)
        assertEquals(43690, out)

        str1 = "E=M*C^2"
        str2 = "e=m*c^2"
        out = clustering(str1, str2)
        assertEquals(65536, out)
    }

    private fun clustering(str1: String, str2: String): Int {
//        var strList1 = ArrayList<String>()
        var strHashList1 = ArrayList<Int>()
        str1.toLowerCase().apply {
            for(i in 1 until length) {
                if(get(i-1) in 'a'..'z' && get(i) in 'a'..'z') {
//                    strList1.add("${get(i-1)}${get(i)}")
                    strHashList1.add("${get(i-1)}${get(i)}".hashCode())
                }
            }
        }

//        var strList2 = ArrayList<String>()
        var strHashList2 = ArrayList<Int>()
        str2.toLowerCase().apply {
            for(i in 1 until length) {
                if(get(i-1) in 'a'..'z' && get(i) in 'a'..'z') {
//                    strList2.add("${get(i-1)}${get(i)}")
                    strHashList2.add("${get(i-1)}${get(i)}".hashCode())
                }
            }
        }

        strHashList1.sort()
        strHashList2.sort()
        return  clusteringCal(strHashList1, strHashList2)
    }

    val NUMBER_65536 = 2 shl 15

    private fun clusteringCal(strHashList1: ArrayList<Int>, strHashList2: ArrayList<Int>): Int {
        var leftStart = 0
        var rightStart = 0
        val leftSize = strHashList1.size
        val rightSize  = strHashList2.size
        var leftValue :Int
        var rightValue : Int
        var intersectionCnt = 0
        var unionCnt = 0

        if(leftSize or rightSize == 0) {
            return NUMBER_65536
        }

        while (leftStart < leftSize && rightStart < rightSize) {
            leftValue = strHashList1.get(leftStart)
            rightValue = strHashList2.get(rightStart)
            if(leftValue > rightValue) {
                rightStart++
            } else if (leftValue < rightValue) {
                leftStart++
            } else {    // 같다 (교집합!!)
                leftStart++
                rightStart++
                intersectionCnt++
            }
        }

        unionCnt = leftSize + rightSize - intersectionCnt
        println("strHashList1 " + strHashList1.toString())
        println("strHashList2 " + strHashList2.toString())
        var out = ((intersectionCnt.toDouble()/unionCnt.toDouble())*(NUMBER_65536)).toInt()
        println("intersectionCnt " + intersectionCnt + " unionCnt " + unionCnt + " out " + out)

        return out
    }

    //    https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//6. 프렌즈4블록(난이도: 상)
    /**
    입력 형식
    입력으로 판의 높이 m, 폭 n과 판의 배치 정보 board가 들어온다.
    2 ≦ n, m ≦ 30
    board는 길이 n인 문자열 m개의 배열로 주어진다. 블록을 나타내는 문자는 대문자 A에서 Z가 사용된다.
    출력 형식
    입력으로 주어진 판 정보를 가지고 몇 개의 블록이 지워질지 출력하라.
    */
    @Test
    fun Test06() {
        var m = 4
        var n = 5
        var board = arrayOf("CCBDE", "AAADE", "AAABF", "CCBBF")
        var score = puzzle(m, n, board)
        assertEquals(14, score)

        m = 6
        n = 6
        board = arrayOf("TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ")
        score = puzzle(m, n, board)
        assertEquals(15, score)
    }

    private fun puzzle(m: Int, n: Int, board: Array<String>): Int {
        var score = 0
        var curBoard : Array<CharArray> = Array(board.size) {
            board[it].toCharArray()
        }
        score = popGame(curBoard)
        println("================= end ==============================")
        println("score : " + score)
        return score
    }

    private fun popGame(curBoard: Array<CharArray>) : Int{
        println("================= input ==============================")
        curBoard.forEach {
            println("curBoard : " + it.toList().toString())
        }

        var tempBoard: Array<IntArray> = Array(curBoard.size) {
            IntArray(curBoard[it].size) {
                1
            }
        }

        //마킹
        var isPop = false
        for (i in 1 until curBoard.size) {
            for (j in 1 until curBoard[i].size) {
                if (curBoard[i][j] != '#' &&
                        curBoard[i][j].hashCode() == curBoard[i - 1][j].hashCode() &&
                        curBoard[i - 1][j].hashCode() == curBoard[i - 1][j - 1].hashCode() &&
                        curBoard[i - 1][j - 1].hashCode() == curBoard[i][j - 1].hashCode()) {
                    tempBoard[i][j] = Math.min(Math.min(tempBoard[i - 1][j], tempBoard[i - 1][j - 1]), tempBoard[i][j - 1]) + 1
                    isPop = true
                }
            }
        }

        var score = 0
        val lastY = tempBoard.size - 1
        val lastX = tempBoard[0].size - 1

        for(y in lastY downTo 0) {
            for(x in lastX downTo 0) {
                val depth = tempBoard[y][x]
                if(depth > 1) {
                    score += blockPop(tempBoard, depth, y, x, curBoard)
                }
            }
        }

        if(isPop) {
            println("================= marking ==============================")
            tempBoard.forEach {
                println("tempBoard : " + it.toList().toString())
            }

            println("================= popGame ==============================")
            curBoard.forEach {
                println("curBoard : " + it.toList().toString())
            }
            gameSort(curBoard)
            score += popGame(curBoard)
        }

        return score
    }

    //정렬
    private fun gameSort(curBoard: Array<CharArray>) {
        for (y in curBoard.size - 1 downTo 0) {
            for (x in 0 until curBoard[y].size) {
                if (curBoard[y][x] == '#') {
                    var depth = 1
                    for(_y in y downTo 0) {
                        if (curBoard[_y][x] == '#') {
                            depth++
                        } else {
                            swap(y, _y, x, curBoard)
                        }
                    }
                }
            }
        }
        println("================= gameSort ==============================")
        curBoard.forEach {
            println("curBoard : " + it.toList().toString())
        }

    }

    // 블록 터트리기
    private fun blockPop(tempBoard: Array<IntArray>, depth: Int, y: Int, x: Int, curBoard: Array<CharArray>) : Int{
        var score = 0
        for (_y in y - depth + 1..y) {
            for (_x in x - depth + 1..x) {
                if (curBoard[_y][_x] != '#' || tempBoard[_y][_x] < depth) {
                    if (curBoard[_y][_x] != '#') {
                        score++
                    }
                    curBoard[_y][_x] = '#'
                }
            }
        }
        return score
    }

    private fun swap(y:Int, _y:Int, x:Int, curBoard: Array<CharArray>) {
        var temp = curBoard[y][x]
        curBoard[y][x] = curBoard[_y][x]
        curBoard[_y][x] = temp
    }

    //    https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//7. 추석 트래픽(난이도: 상)
    /**
    입력 형식
    solution 함수에 전달되는 lines 배열은 N(1 ≦ N ≦ 2,000)개의 로그 문자열로 되어 있으며, 각 로그 문자열마다 요청에 대한 응답완료시간 S와 처리시간 T가 공백으로 구분되어 있다.
    응답완료시간 S는 작년 추석인 2016년 9월 15일만 포함하여 고정 길이 2016-09-15 hh:mm:ss.sss 형식으로 되어 있다.
    처리시간 T는 0.1s, 0.312s, 2s 와 같이 최대 소수점 셋째 자리까지 기록하며 뒤에는 초 단위를 의미하는 s로 끝난다.
    예를 들어, 로그 문자열 2016-09-15 03:10:33.020 0.011s은 “2016년 9월 15일 오전 3시 10분 33.010초”부터 “2016년 9월 15일 오전 3시 10분 33.020초”까지 “0.011초” 동안 처리된 요청을 의미한다. (처리시간은 시작시간과 끝시간을 포함)
    서버에는 타임아웃이 3초로 적용되어 있기 때문에 처리시간은 0.001 ≦ T ≦ 3.000이다.
    lines 배열은 응답완료시간 S를 기준으로 오름차순 정렬되어 있다.
    출력 형식
    solution 함수에서는 로그 데이터 lines 배열에 대해 초당 최대 처리량을 리턴한다.
     */
    @Test
    fun Test07() {
        var inputs = arrayOf("2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s")
        var output = traffic(inputs)
        println(output)
        assertEquals(1, output)

        inputs = arrayOf("2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s")
        output = traffic(inputs)
        println(output)
        assertEquals(2, output)

        inputs = arrayOf("2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s")
        output = traffic(inputs)
        println(output)
        assertEquals(7, output)
    }

    private fun traffic(inputs: Array<String>): Int {
        var regex = "(.+) (.+)s"    // 응답 완료시간, 처리시간 으로 분리
        var timeRegex ="yyyy-MM-dd HH:mm:ss.SSS"
        val fromFormat = SimpleDateFormat(timeRegex)
        val startTimes = LongArray(inputs.size) // 시작 시간들의 모임
        val endTimes = LongArray(inputs.size)   // 종료 시간들의 모임
        inputs.forEachIndexed { index, s ->
            regex.toRegex().matchEntire(s)?.apply {
                endTimes[index] = (fromFormat.parse(groupValues[1])?.time ?: 0L) + 500  //응답 종료 지점 +0.5초
                startTimes[index] = (endTimes[index] + 1L - (groupValues[2].toDouble() * 1000)).toLong() - 1000 //응답 시작 지점 -0.5초
            }
        }

        println("시작 시점 (-500ms): " + Arrays.toString(startTimes))
        println("종료 시점 (+500ms) : " + Arrays.toString(endTimes))

        var maxCnt = 0
        var curCnt = 0
        var curStartIndex = startTimes.size - 1
        var curEndIndex = endTimes.size - 1
        while (curStartIndex >= 0 && curEndIndex >= 0) {    // 마지막 종료시간 부터 역순으로 검사
            if(startTimes[curStartIndex] < endTimes[curEndIndex]) { //다음 종료 시간이 다음 시작 시간보다 큰 경우 중첩 count++
                curCnt++
                if(curCnt > maxCnt) {//maxCnt보다 깊은 중첩인 경우 maxCnt 갱신
                    maxCnt = curCnt
                }
                curEndIndex--
            } else if(startTimes[curStartIndex] > endTimes[curEndIndex]) { // 다음 시작 시간이 다음 종료 시간보다 큰 경우 중첩 count--
                curCnt--
                curStartIndex--
            } else {
                //작업 시작과 종료가 동시에 일어난경우 중첩에 변화는 없음
                curStartIndex--
                curEndIndex--
            }
        }
        return maxCnt
    }
}
