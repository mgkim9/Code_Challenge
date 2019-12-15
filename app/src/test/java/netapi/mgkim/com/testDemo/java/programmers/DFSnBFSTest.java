package netapi.mgkim.com.testDemo.java.programmers;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.function.Consumer;

public class DFSnBFSTest {


//https://programmers.co.kr/learn/courses/30/lessons/43164
//4.    여행경로

    /**
     문제 설명

     주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
     항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

     제한사항

     •모든 공항은 알파벳 대문자 3글자로 이루어집니다.
     •주어진 공항 수는 3개 이상 10,000개 이하입니다.
     •tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
     •주어진 항공권은 모두 사용해야 합니다.
     •만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
     •모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
     */
    @Test
    public void Test03() {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[] result = {"ICN", "JFK", "HND", "IAD"};
        String[] out = solution03(tickets);
        Assert.assertEquals(result, out);

        tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        result = new String[] {"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"};
        out = solution03(tickets);
        Assert.assertEquals(result, out);

    }
    ArrayList<String> arrayList = new ArrayList<>();

    public String[] solution03(String[][] tickets) {
        Stack<String> st = new Stack<>();
        st.push("ICN");
        boolean[] visiteds = new boolean[tickets.length];
        dfs(tickets, visiteds, st, 0);
        Collections.sort(arrayList);
        return arrayList.get(0).split(" ");
    }

    private void dfs(String[][] tickets, boolean[] visiteds, Stack<String> st, int cnt) {
        if(cnt >= tickets.length) {
//            System.out.println(st.toString());
            StringBuffer buffer = new StringBuffer();
            st.forEach(s -> buffer.append(s).append(" "));
            arrayList.add(buffer.toString().trim());
            return;
        }

        String cur = st.peek();
        for (int i = 0; i < tickets.length; i++) {
            if(!visiteds[i] && tickets[i][0].equals(cur)) {
                st.push(tickets[i][1]);
                visiteds[i] = true;
                dfs(tickets, visiteds, st, cnt+1);
                visiteds[i] = false;
                st.pop();
            }
        }
    }
}
