package netapi.mgkim.com.testDemo.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SortAndSearch {
    @Test
    public void SortTest() {
        int[] array ={9,6,3,8,5,2,7,4,1};
        int[] tempArray = array.clone();
        System.out.println("array : " + Arrays.toString(tempArray));

        sortBySelectSort(tempArray);
        System.out.println("sortBySelectSort : " + Arrays.toString(tempArray));

        tempArray = array.clone();
        sortByInsertSort(tempArray);
        System.out.println("sortByInsertSort : " + Arrays.toString(tempArray));

        tempArray = array.clone();
        sortByQuickSort(tempArray);
        System.out.println("sortByQuickSort : " + Arrays.toString(tempArray));

        tempArray = array.clone();
        sortByMergeSort(tempArray);
        System.out.println("sortByMergeSort : " + Arrays.toString(tempArray));

        System.out.println("binarySearch : " + binarySearch(tempArray, 13));


        Map<String, String> map = new HashMap<>();
        Map<String, String> syncMap = Collections.synchronizedMap(map);

    }

    //2진 검색
    private int binarySearch(int[] array, int n) {
        int start = 0;
        int end = array.length- 1;
        int mid;

        while (start <= end) {
            mid = (start + end) >>1;
            if(array[mid] < n) {
                start = mid + 1;
            } else if(array[mid] > n) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return start * (-1);
    }


    //선택 정렬
    private void sortBySelectSort(int[] array) {
        int max;
        for (int j = array.length - 1; j >= 0 ; j--) {
            max = 0;
            for (int i = 0; i <= j; i++) {
                if(array[i] > array[max]) {
                    max = i;
                }
            }
            swap(array, j, max);
        }
    }

    //삽입 정렬
    private void sortByInsertSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j >= 0 ; j--) {
                if(array[j + 1] < array[j]) {
                    swap(array, j, j+1);
                } else {
                    break;
                }
            }
        }
    }

    //퀵 정렬
    private void sortByQuickSort(int[] array) {
        sortByQuickSort(array, 0, array.length - 1);
    }

    private void sortByQuickSort(int[] array, int start, int end) {
        if(start >= end) {
            return;
        }

        int mid = partition(array, start, end);
        sortByQuickSort(array, start, mid - 1);
        sortByQuickSort(array, mid, end);
    }

    private int partition(int[] array, int start, int end) {
        int midValue = array[(start + end) >> 1];
        while (start <= end) {
            while (array[start] < midValue) start++;
            while (array[end] > midValue) end--;
            if(start <= end) {
                swap(array, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    //병합 정렬
    private void sortByMergeSort(int[] array) {
        sortByMergeSort(array, new int[array.length], 0, array.length - 1);
    }

    private void sortByMergeSort(int[] array, int[] temp, int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        sortByMergeSort(array, temp, start, mid);
        sortByMergeSort(array, temp, mid + 1, end);
        merge(array, temp, start, mid, end);

    }

    private void merge(int[] array, int[] temp, int start, int mid, int end) {
        for (int i = start; i <= end; i++) {
            temp[i] = array[i];
        }
        int rightStart = mid + 1;
        int index = start;
        while (start <= mid && rightStart <= end) {
            if (temp[start] > temp[rightStart]) {
                array[index] = temp[rightStart];
                rightStart++;
            } else {
                array[index] = temp[start];
                start++;
            }
            index++;
        }

        if (start <= mid) {
            for (int i = start; i <= mid; i++) {
                array[index] = temp[i];
                index++;
            }
        }
    }

    private void swap(int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

}
