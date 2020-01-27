package netapi.mgkim.com.testDemo.java.hackerrank;


import java.io.InputStream;
import java.util.ArrayList;

class Scanner<T> {
    private ArrayList<T> array = new ArrayList<>();
    private int index = 0;
    public Scanner(InputStream in) {

    }

    public int nextInt() {
        return (Integer) array.get(index++);
    }

    public void setData(T... data) {
        for (T s : data) {
            array.add(s);
        }
    }

    public void close() {
    }

    public void skip(String s) {
    }

    public String nextLine() {
        StringBuilder stringBuilder = new StringBuilder();

        while (index < array.size()) {
            String input = (String) array.get(index++);
            if(input.trim().isEmpty()) {
                break;
            }
            stringBuilder.append(input);
        }

        return stringBuilder.toString();

//        String split = " ";
//        for (int i = index; i < array.size(); i++) {
//            if(i == array.size() - 1) {
//                stringBuilder.append(array.get(i));
//            } else {
//                stringBuilder.append(array.get(i) + split);
//            }
//        }
//        return stringBuilder.toString();
    }
}