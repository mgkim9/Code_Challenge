package netapi.mgkim.com.netapitest2.kotlin.data_structure

import java.util.*
import kotlin.collections.HashSet

class TrieList(private val isReversed: Boolean = false) : ITrieList {
    private val root = TrieNode('\u0000')

    override fun add(word: String) {
        val str = if (isReversed) {
            word.reversed()
        } else {
            word
        }
        if (isExist(str)) {
            return
        }
        var current = root
        str.forEach {
            val child = current.findChild(it)
            if (child == null) {
                current.addChild(it)
                current = current.findChild(it)!!
            } else {
                current = child
            }
        }
        current.isWord = true
    }

//    override fun remove(str: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun isExist(str: String): Boolean {
        var current = root

        str.forEach {
            val child = current.findChild(it)
            if (child == null) {
                return false
            } else {
                current = child
            }
        }
        if (current.isWord) {
            return true
        }
        return false
    }

    override fun isPrifxExist(str: String): Boolean {
        var current = root

        str.forEach {
            val child = current.findChild(it)
            if (child == null) {
                return false
            } else {
                current = child
            }
        }
        return true
    }

    override fun getPrifxCnt(word: String): Int {
        val str = word.replace("?", "")
        val qCount = word.length - str.length

        var current = getPreFixNode(str)
        if (current != null) {
            return getChildCnt(current, HashSet<String>(), qCount, 0)
        }
        return 0
    }

    override fun getPrifxSet(word: String): Set<String>? {
        val str = word.replace("?", "")
        val qCount = word.length - str.length
        var current = getPreFixNode(str)
        if (current != null) {
            return getChilds(current, HashSet<String>(), str, qCount, 0)
        }
        return null
    }

    private fun getPreFixNode(str: String): TrieNode? {
        var current = root
        str.forEach {
            val child = current.findChild(it)
            if (child == null) {
                return null
            } else {
                current = child
            }
        }
        return current
    }

    private fun getChildCnt(current: TrieNode, set: HashSet<String>, maxCnt: Int, curCnt: Int): Int {
        if (curCnt > maxCnt) {
            return 0
        } else if (current.isWord && curCnt == maxCnt) {
            return 1
        }
        var total = 0
        current.Iterater().forEach {
            total += getChildCnt(it, set, maxCnt, curCnt + 1)
        }
        return total
    }

    private fun getChilds(current: TrieNode, set: HashSet<String>, key: String, maxCnt: Int, curCnt: Int): Set<String> {
        if (curCnt > maxCnt) {
            return set
        } else if (current.isWord && curCnt == maxCnt) {
            set.add(key)
        }
        current.Iterater().forEach {
            getChilds(it, set, key + it.char, maxCnt, curCnt + 1)
        }
        return set
    }

    override fun show() {
        show(root, "")
    }

    fun show(node: TrieNode, key: String) {
        node.Iterater().forEach {
            show(it, key + it.char)
        }
        if (node.isWord) {
            println(key)
        }
    }

    inner class TrieNode(val char: Char) : Comparable<TrieNode> {

        private val childList: LinkedList<TrieNode> = LinkedList()
        var isWord = false

        fun addChild(char: Char) {
            childList.add(TrieNode(char))
        }

        fun removeChild(child: TrieNode) {
            childList.remove(child)
        }

        fun findChild(char: Char): TrieNode? {
            childList.forEach {
                if (it.char == char) {
                    return it
                }
            }

            return null
        }

        fun Iterater(): Iterator<TrieNode> {
            return childList.iterator()
        }


        fun getChildSize(): Int {
            return childList.size
        }

        override fun compareTo(other: TrieNode): Int {
            return when {
                char > other.char -> 1
                char == other.char -> 0
                else -> -1
            }
        }
    }
}

interface ITrieList {
    fun add(str: String)
    fun isExist(str: String): Boolean   // str 가 있는지
    fun isPrifxExist(str: String): Boolean  //str로 시작하는 단어가 있는지
    fun getPrifxSet(str: String): Set<String>?  // abc??? 모든 list
    fun getPrifxCnt(str: String): Int   //abc??? 갯수
    fun show()
}

