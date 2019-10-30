package netapi.mgkim.com.testDemo.java;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ArrayOutput {
    //순열과 조합
    @Test
    public void test01() {
        int[] arr = {1, 2, 3, 4, 5};
        int n = arr.length;
        int r = 3;
        int[] combArr = new int[r];
        // n개의 문자중 r개를 골라 정렬하여 출력
        combination(arr, combArr, n, r, 0, 0);

        //arr 로 만들수있는 모든 문자열을 출력
        permutation(arr, 0);
    }

    //조합
    void combination(int[] arr, int[] tempArr, int size, int cnt, int index, int target) {
        if (cnt == 0) {
            permutation(tempArr, index);
        } else if (target == size) {
            return;
        } else {
            tempArr[index] = arr[target];
            combination(arr, tempArr, size, cnt - 1, index + 1, target + 1);
            combination(arr, tempArr, size, cnt, index, target + 1);
        }
    }

    //순열
    void permutation(int[] arr, int index) {
        if (index == arr.length) {
            for (int n : arr) System.out.print(n);
            System.out.println();
        } else {
            for (int i = index; i < arr.length; i++) {
                swap(arr, i, index);
                permutation(arr, index + 1);
                swap(arr, i, index);
            }
        }
    }

    public void swap(int[] arr, int n1, int n2){
        int temp = arr[n1];
        arr[n1] = arr[n2];
        arr[n2] = temp;
    }
}
