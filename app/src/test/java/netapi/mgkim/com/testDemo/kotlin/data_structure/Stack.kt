package netapi.mgkim.com.testDemo.kotlin.data_structure

class Stack<E>(private val maxSize: Int = Int.MAX_VALUE shr 2) : IStack<E> {
    var index = -1
    val array = arrayOfNulls<Any?>(maxSize)

    override fun add(data: E): Boolean {
        if (isFull()) {
            println("add fail isFull!!")
            return false
        }
        println("add " + data)
        array[++index] = data
        return true
    }

    override fun pop(): E {
        println("pop")
        return array[index--] as E
    }

    override fun peek(): E? {
        return array[index] as E?
    }

    override fun isEmpty(): Boolean {
        return index < 0
    }

    override fun size(): Int {
        return index + 1
    }

    private fun isFull(): Boolean {
        return index + 1 >= maxSize
    }

}

interface IStack<E> {
    fun add(data: E): Boolean
    fun pop(): E
    fun peek(): E?
    fun isEmpty(): Boolean
    fun size(): Int
}
