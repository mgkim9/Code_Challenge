package netapi.mgkim.com.testDemo.kotlin.data_structure

import org.junit.Test

class DataStructureTest {


    @Test
    fun test01() {

        val queue: IQueue<String> = Queue(10)

        queue.add("1")
        queue.add("2")
        queue.add("3")
        println("peek" + queue.peek())
        queue.add("4")
        println("poll" + queue.poll())
        queue.add("5")
        queue.add("6")
        println("peek" + queue.peek())
        queue.add("7")
        queue.add("8")
        queue.add("9")
        queue.add("10")
        queue.add("11")
        queue.add("12")
        queue.add("13")

        while (!queue.isEmpty()) {
            println("poll" + queue.poll())
        }


        val stack: IStack<String> = Stack(10)

        stack.add("1")
        stack.add("2")
        stack.add("3")
        println("peek" + stack.peek())
        stack.add("4")
        println("pop" + stack.pop())
        stack.add("5")
        stack.add("6")
        println("peek" + stack.peek())
        stack.add("7")
        stack.add("8")
        stack.add("9")
        stack.add("10")
        stack.add("11")
        stack.add("12")
        stack.add("13")

        while (!stack.isEmpty()) {
            println("pop" + stack.pop())
        }


        val map : IMap<Int, String> = HashMap(8)

        map.put(1, "11")
        map.put(2, "22")
        map.put(3, "33")
        map.put(4, "44")
        map.put(5, "55")
        map.put(9, "66")
        map.put(17, "88")
        map.put(1, "88")
        map.put(13, "13")

        println(map.toString())
    }
}