package netapi.mgkim.com.netapitest2.kotlin.data_structure

import org.junit.Test
import java.util.*
import kotlin.collections.HashMap

class TrieTest {
    @Test
    fun Test01() {
        var words: Array<String> = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao")
        var queries: Array<String> = arrayOf("fro??", "????o", "fr???", "fro???", "pro?", "frodo")
        var answer = solution(words, queries)
        println(Arrays.toString(answer))
    }

    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        var answer = IntArray(queries.size)
        var trieList = TrieList()
        var rTrieList = TrieList(true)
        words.forEach {
            trieList.add(it)
            rTrieList.add(it)
        }

        var cache = HashMap<String, Int>()
        queries.forEachIndexed { index, querie ->
            var count = 0
            if (cache.containsKey(querie)) {
                count = cache.get(querie) ?: 0
            } else {
                count = if (querie.startsWith("?")) {
                    rTrieList.getPrifxCnt(querie.reversed())
                } else {
                    trieList.getPrifxCnt(querie)
                }
                cache.put(querie, count)
            }
            answer[index] = count
        }
        return answer
    }

}