package netapi.mgkim.com.testDemo.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class PatternUtils {
    public static final String PATTERN_NUMBER = "^[0-9]*$";    //숫자만
    public static final String PATTERN_WORLD = "^[a-zA-Z]*$";    //영문자만
    public static final String PATTERN_NUMBER_WORLD = "^[a-zA-Z0-9]*$";    //영어 & 숫자만
    public static final String PATTERN_KOREAN = "^[가-힣]*$";    //한글만

    public static Boolean isPattern(String input, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            System.out.println("matching!");
            return true;
        } else {
            System.out.println("no matching!");
            return false;
        }
    }
}
