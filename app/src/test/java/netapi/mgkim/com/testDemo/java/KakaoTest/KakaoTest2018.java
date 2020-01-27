package netapi.mgkim.com.testDemo.java.KakaoTest;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class KakaoTest2018 {
    @Test
    public void test01() {
//        System.out.println(new Solution003().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(new Solution003().solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));

    }
//https://programmers.co.kr/learn/courses/30/lessons/17681
    //비밀지도
    class Solution001 {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] answer = new String[n];

            for (int i = 0; i < n; i++) {
                int temp = arr1[i] | arr2[i] | 0b1_0000_0000_0000_0000;
                answer[i] = Integer.toBinaryString(temp);
                answer[i] = answer[i].substring(answer[i].length() - n).replace("1", "#").replace("0", " ");
            }

            return answer;
        }
    }

    //https://programmers.co.kr/learn/courses/30/lessons/17682
    //다트게임
    class Solution002 {
        public int solution(String dartResult) {
            int answer = 0;
            return answer;
        }
    }

    //https://programmers.co.kr/learn/courses/30/lessons/17680
    //캐시
    class Solution003 {
        public int solution(int cacheSize, String[] cities) {
            LRUCahce<String, String> cache = new LRUCahce(cacheSize);
            for (String city : cities) {
                cache.put(city.toLowerCase(), city.toLowerCase());
            }
            return cache.getRunTime();
        }

        private class LRUCahce<K, E> extends LinkedHashMap<K, E> {
            private int cacheSize;
            private int runTime;
            public LRUCahce(int cacheSize) {
                super(cacheSize, 2f, false);
                this.cacheSize = cacheSize;
                runTime = 0;
            }

            public int getRunTime() {
                return runTime;
            }

            @Override
            public E put(K key, E value) {
                if(containsKey(key)) {
                    runTime +=1;
                    remove(key);
                } else {
                    runTime +=5;
                    if(cacheSize < size()) {
                        Iterator<K> iterator = keySet().iterator();
                        if(iterator.hasNext()) {
                            K temp = iterator.next();
                            System.out.println(temp);
                            remove(temp);
                        }
                    }
                }
                return super.put(key, value);
            }

//            @Override
//            protected boolean removeEldestEntry(Entry<K, E> eldest) {
//                return cacheSize > size();
//            }

        }
    }

}
