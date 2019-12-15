package netapi.mgkim.com.testDemo.kotlin

import org.junit.Test
import java.util.*
import kotlin.collections.HashMap


class MathTest () {

    //    HashMap에 Capacity값 읽어오기
    @Test
    fun getHashCapacity() {
        var map = HashMap<String, String>(7, 1F)
        map.put("A", "A")

        val tableField = HashMap::class.java.getDeclaredField("table")
        tableField.isAccessible = true
        val table = tableField.get(map) as? Array<Any>
        println("capacity "  + table?.size)
    }


//    https://ko.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/fast-modular-exponentiation
    @Test
    fun powModTest() {
        //모듈러 곱셈 법칙
        // num^digit % mode 를 구하여라
        // ex ) 5^117 % 19
        var num = 5
        var digit = 117
        var mod = 19
        System.out.println(powModSulotion(num, digit, mod))
    }

    private fun powModSulotion(num: Int, digit: Int, mod: Int): Int? {
        var bBin = Integer.toBinaryString(digit)
        val cache = HashMap<Int, Int>(bBin.length, 1F)
        System.out.println("bBin " + bBin + " " + bBin.length)

        var curMode = 1
        bBin.reversed().forEachIndexed { index, c ->
            if (c == '1') {
                curMode *= powMod(num, Math.pow(2.0, index.toDouble()).toInt(), mod, cache)
            }
        }

        return curMode % mod
    }

    fun powMod(num: Int, bDigit: Int, mod: Int, cache: HashMap<Int, Int>): Int {
        System.out.println("powMod num " + num + " bDigit " + bDigit)
        if (cache.containsKey(bDigit)) {
            System.out.println("num " + num + " bDigit " + bDigit + " cache hit! " + cache.get(bDigit))
            return cache.get(bDigit)!!
        }

        if (Math.pow(num.toDouble(), bDigit.toDouble()) > Int.MAX_VALUE) {  //정수범위를 벗어남
            System.out.println("" + num + "의 " + bDigit + "제곱 = " + Math.pow(num.toDouble(), bDigit.toDouble()) + " 정수범위를 벗어남 ")
            return (Math.pow(powMod(num, bDigit / 2, mod, cache).toDouble(), 2.0).toDouble()).toInt() % mod
        } else {
            val value = Math.pow(num.toDouble(), bDigit.toDouble()).toInt() % mod
            System.out.println("" + num + "의 " + bDigit + "제곱 = " + Math.pow(num.toDouble(), bDigit.toDouble()) + " % " + mod + " = " + value)
            cache.put(bDigit, value)
            return Math.pow(num.toDouble(), bDigit.toDouble()).toInt() % mod
        }
    }


    //최대공약수(Greatest Common Measure), 최소공배수(Least Common Multiple)
    @Test
    fun GCM_LCM() {
        var a = 39
        var b = 11
        System.out.println("gcm " + gcm(a,b))
        System.out.println("lcm " + lcm(a,b))
    }

    fun gcm(a:Int, b:Int):Int {
        System.out.println("gcm a " + a + " b " + b)
        if(a%b == 0) {
            return b
        } else {
            return gcm(b, a%b)
        }
    }

    fun lcm(a:Int, b:Int):Int {
        System.out.println("gcm a " + a + " b " + b)
        return a*b/gcm(b, a%b)
    }

    //Combination 조합
    private fun combination(strs: CharArray, temps: CharArray, r: Int, index: Int, target: Int) {
        if (r == 0) {
            System.out.println(Arrays.toString(temps))  // nCr 출력
            //            purmutation(temps, 0); // nPr 출력
        } else if (target >= strs.size) {
            return
        } else {
            temps[index] = strs[target]
            combination(strs, temps, r - 1, index + 1, target + 1)
            combination(strs, temps, r, index, target + 1)
        }

    }

    //Purmutation 순열
    private fun purmutation(strs: CharArray, index: Int) {
        if (index >= strs.size - 1) {
            System.out.println(Arrays.toString(strs))
        } else {
            for (i in index until strs.size) {
                swap(strs, i, index)
                purmutation(strs, index + 1)
                swap(strs, i, index)
            }
        }
    }

    private fun swap(arr: IntArray, a: Int, b: Int) {
        val temp = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }


    private fun swap(arr: CharArray, a: Int, b: Int) {
        val temp = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }


}
