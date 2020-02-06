package netapi.mgkim.com.testDemo.java.goorm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://level.goorm.io/exam/49062/%EC%A7%84%EB%B2%95-%EB%B3%80%ED%99%98/quiz/1
//진법 변환
public class Test001 {
    @Test
    public void test001() {
        String input = "";
        int out = 0;
        int result = 0;

        input = "123 323";
        out = 6;
        result = solution001(input);
        Assert.assertEquals(result, out);

        input = "4576 3490";
        out = 11;
        result = solution001(input);
        Assert.assertEquals(result, out);

        input = "1234567890 1001001100101100000001011010010";
        out = 2;
        result = solution001(input);
        Assert.assertEquals(result, out);

        input = "7 7";
        out = 8;
        result = solution001(input);
        Assert.assertEquals(result, out);

        input = "10 A";
        out = 11;
        result = solution001(input);
        Assert.assertEquals(result, out);
    }

    private int solution001(String input) {

        String[] inputArray = input.split(" ");
        if(inputArray != null && inputArray.length > 1) {
            BigInteger input_N = new BigInteger(inputArray[0]);
            if(inputArray[0].length() < inputArray[1].length()) {
                //무조건 10진법 미만
                return check(input_N, inputArray[1], 2, 9);
            } else if(!isPattern(inputArray[1], PATTERN_NUMBER)) {
                // 무조건 11진법 이상
                return check(input_N, inputArray[1], 11, 16);
            } else {
                // 2~16진법
                return check(input_N, inputArray[1], 2, 16);
            }
        } else {
            System.out.println("input error");
        }
        return -1;
    }

    private int check(BigInteger input_N, String input_T, int start, int end) {
        for(int i = start; i <= end; i++) {
            //Math.BigInteger 이용
//            if(input_T.equalsIgnoreCase(input_N.toString(i))) {
//                System.out.println("check : " + i);
//                return i;
//            }
            //진법 변환 (재귀 이용)
            if(input_T.equalsIgnoreCase(digitChange(input_N.intValue(), i, new StringBuffer()))) {
                System.out.println("check : " + i);
                return i;
            }
        }

        System.out.println("check fail ");
        return 0;
    }

    //진법 변환 (재귀 이용)
    private String digitChange(int value, int digit, StringBuffer result) {
        int r = value % digit;
        value /= digit;
        if(value > 0) {
            digitChange(value, digit, result);
        }
        if(r > 9) {
            result.append((char) ('A' + r -10));
        } else {
            result.append(r);
        }
        return result.toString();
    }

    public final String PATTERN_NUMBER = "^[0-9]*$";    //숫자만
    public boolean isPattern(String input, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    //https://level.goorm.io/exam/43086/%EC%95%8C%ED%8C%8C%EB%B2%B3-%EB%B9%88%EB%8F%84-%EA%B5%AC%ED%95%98%EA%B8%B0/quiz/1
    //알파벳 빈도 구하기
    @Test
    public void test002() {
        String input = "";
       input = "edu goorm io";
        solution002(input);
    }

    private void solution002(String input) {
        int[] results = new int[26];
        for (char c : input.toCharArray()) {
            if(c == ' ') {
                continue;
            }

            if(c < 'a') {   //대문자인 경우
                results[c - 'A'] += 1;
            } else {    //소문자인 경우
                results[c - 'a'] += 1;
            }
        }

        for (int i = 0; i < results.length; i++) {
            System.out.printf("%c : %d\n", i+'a', results[i]);
        }
    }
}
