package netapi.mgkim.com.testDemo.kotlin.data_structure

class Queue<E>(private val maxSize:Int= Int.MAX_VALUE shr 2):IQueue<E> {
    var size = 0
    var headIdex = -1
    var tailIndex = 0
    val array = arrayOfNulls<Any?>(maxSize)

    override fun add(data: E) :Boolean {
        if(isFull()) {
            println("add fail isFull")
            return false
        }
        array[++headIdex] = data
        return true
    }

    override fun poll(): E {
        @Suppress("UNCHECKED_CAST")
        return array[tailIndex++] as E
    }

    override fun peek(): E? {
        @Suppress("UNCHECKED_CAST")
        return array[tailIndex] as E?
    }

    override fun isEmpty(): Boolean {
        return headIdex + 1 == tailIndex
    }

    override fun size(): Int {
        return headIdex + 1 - tailIndex
    }

    private fun isFull(): Boolean {
        return headIdex + 1 >= maxSize
    }
}

interface IQueue<E> {
    fun add(data: E): Boolean
    fun poll():E
    fun peek():E?
    fun isEmpty():Boolean
    fun size():Int
}
