package netapi.mgkim.com.testDemo.kotlin.data_structure

import java.lang.StringBuilder
import java.util.*

class HashMap<K, E>(private var initSize:Int = 16) :IMap<K, E>{
    var size = 0
    var digit :Int
    var keys = arrayOfNulls<LinkedList<Any?>>(initSize)
    var values = arrayOfNulls<LinkedList<Any?>>(initSize)

    init {
        if(initSize <= 0) {
            initSize = 16
        }
        digit = Integer.toBinaryString(initSize).length
    }

    private fun sizeUp() {
        val nextSize =  1 shl ++digit
        var newKeys = arrayOfNulls<LinkedList<Any?>>(nextSize)
        var newValues = arrayOfNulls<LinkedList<Any?>>(nextSize)

        keys.forEachIndexed {keyIndex, linkedList ->
            linkedList?.forEachIndexed { index, key ->
                val hashIndex = key.hashCode() % nextSize
                val value = values[keyIndex]?.get(index)
                if(newKeys[hashIndex] == null) {
                    newKeys[hashIndex] = LinkedList<Any?>().apply {
                        add(key)
                    }
                    newValues[hashIndex] = LinkedList<Any?>().apply {
                        add(value)
                    }
                } else {
                    newKeys[hashIndex]?.add(key)
                    newValues[hashIndex]?.add(value)
                }
            }
        }
        keys = newKeys
        values = newValues
        initSize = nextSize
        println("sizeUp initSize $initSize")
    }

    override fun put(key: K, value: E) {
        if(size >= initSize) {
            sizeUp()
        }
        val hashIndex = key.hashCode() % initSize
        val index:Int = keys[hashIndex]?.indexOf(key) ?: -1
        if(index >= 0) {
            values[hashIndex]!!.set(index, value)
        } else {
            if(keys[hashIndex].isNullOrEmpty()) {
                keys[hashIndex] = LinkedList<Any?>().apply {
                    add(key)
                }
                values[hashIndex] = LinkedList<Any?>().apply {
                    add(value)
                }
            } else {
                println("hash Collision!!")
                keys[hashIndex]!!.add(key)
                values[hashIndex]!!.add(value)
            }
            size++
        }
    }

    override fun get(key: K): E? {
        val hashIndex = key.hashCode() % initSize
        val index:Int = keys[hashIndex]?.indexOf(key) ?: -1
        if(index < 0) {
            return null
        }
        return values[hashIndex]?.get(index) as E?
    }

    override fun containsKey(key: K): Boolean {
        val hashIndex = key.hashCode() % initSize
        val index:Int = keys[hashIndex]?.indexOf(key) ?: -1
        return index >= 0
    }

    override fun remove(key: K) :Boolean{
        val hashIndex = key.hashCode() % initSize
        val index:Int = keys[hashIndex]?.indexOf(key) ?: -1
        if(index < 0) {
            return false
        }
        values[hashIndex]?.removeAt(index)
        return true
    }

    override fun size(): Int {
        return size
    }

    override fun getKeys(): List<K> {
        return ArrayList<K>().apply {
            keys.forEach {key->
                key?.forEach {
                    if(it != null) {
                        add(it as K)
                    }
                }
            }
        }
    }

    override fun getValues(): List<E> {
        return ArrayList<E>().apply {
            values.forEach {value->
                value?.forEach {
                    if(it != null) {
                        add(it as E)
                    }
                }
            }
        }
    }

    override fun getEntrys(): List<Entry<K, E>> {
        val values = getValues()
        return ArrayList<Entry<K, E>>().apply {
            getKeys().forEachIndexed { index, k ->
                add(Entry(k, values[index]))
            }
        }
    }

    override fun toString(): String {
        var sb = StringBuilder()
        getEntrys().forEachIndexed() { i, it ->
            sb.append(it.toString())
            if (i != size - 1) {
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    class Entry<K, E>(var key: K, var value: E) {
        override fun toString(): String {
            return "key:$key value:$value"
        }
    }
}

interface IMap<K, E> {
    fun put(key:K, value:E)
    fun get(key:K):E?
    fun containsKey(key:K):Boolean
    fun remove(key: K) : Boolean
    fun size():Int
    fun getKeys():List<K>
    fun getValues():List<E>
    fun getEntrys(): List<HashMap.Entry<K, E>>
}

