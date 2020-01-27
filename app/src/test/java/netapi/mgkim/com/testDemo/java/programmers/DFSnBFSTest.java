package netapi.mgkim.com.testDemo.java.programmers;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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
//        String[] out = solution03(tickets);
//        Assert.assertEquals(result, out);
//
//        tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
//        result = new String[] {"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"};
//        out = solution03(tickets);
//        Assert.assertEquals(result, out);

        String[] out = new Solution003().solution(tickets);
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

    class Solution003 {
        private ArrayList<String> results = new ArrayList();
        public String[] solution(String[][] tickets) {

            Stack<String> stack = new Stack();
            boolean[] visits = new boolean[tickets.length];
            stack.push("ICN");
            findRecursive(tickets, visits, stack, 0);
            Collections.sort(results);

            String[] answer = results.get(0).split(" ");
            return answer;
        }

        public void findRecursive(String[][] tickets, boolean[] visits, Stack<String> stack, int cnt) {
            //종료 조건
            if(cnt >= tickets.length) {
                StringBuilder sb = new StringBuilder();
                stack.forEach( s ->
                        sb.append(s).append(" ")
                );
                results.add(sb.toString().trim());
                return;
            }
            String cur = stack.peek();
            for(int i = 0; i < tickets.length; i++) {
                if(!visits[i] && cur.equals(tickets[i][0])) {
                    stack.push(tickets[i][1]);
                    visits[i] = true;
                    findRecursive(tickets, visits, stack, cnt + 1);
                    stack.pop();
                    visits[i] = false;
                }
            }
        }
    }


    //https://programmers.co.kr/learn/courses/30/lessons/43163?language=java
    //단어 변환

    @Test
    public void Test004() {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        String begin = "hit";
        String target = "cog";

        System.out.println(new Solution004().solution(begin, target, words));
    }
    class Solution004 {
        int minCnt = Integer.MAX_VALUE;
        public int solution(String begin, String target, String[] words) {

            boolean[] visits = new boolean[words.length];
            findResult(words, visits, begin, target, 0);

            int answer = minCnt == Integer.MAX_VALUE ? 0 : minCnt;
            return answer;
        }

        private void findResult(String[] words, boolean[] visits, String begin, String target, int index) {
            if(begin.equals(target)) {
                minCnt = Math.min(minCnt, index);
            }
            if(index > words.length) {
                return;
            }

            root: for (int i = 0; i < words.length; i++) {
                if(!visits[i]) {
                    boolean isDef = false;
                    char[] wordArray = words[i].toCharArray();
                    char[] beginArray = begin.toCharArray();
                    for (int j = 0; j < begin.length(); j++) {
                        if(beginArray[j] != wordArray[j]) {
                            if(isDef) {
                                continue root;
                            }
                            isDef = true;
                        }
                    }
                    visits[i] = true;
                    findResult(words, visits, words[i], target, index + 1);
                    visits[i] = false;
                }
            }
        }
    }

//https://programmers.co.kr/learn/courses/30/lessons/1844
    //게임 맵 최단거리 (BFS)
    //설명
    //https://velog.io/@ptm0304/%EA%B2%8C%EC%9E%84-%EB%A7%B5-%EC%B5%9C%EB%8B%A8%EA%B1%B0%EB%A6%AC-z6k3eq3agp

    @Test
    public void Test005() {
//        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        System.out.println(new Solution005().solution(maps));
    }
    enum PosWay {
        POS_LEFT, POS_RIGHT, POS_UP, POS_DOWN
    }

    class Solution005 {
        public int solution(int[][] maps) {
            Queue<Pos> queue = new LinkedList<>();
            queue.add(new Pos(0,0,1));  // 시작점이 포함이므로 step == 1
            int mapHight = maps.length;
            int mapWidth = maps[0].length;

            while (!queue.isEmpty()) {
                Pos curPos = queue.poll();
                if(curPos.y == mapHight - 1 && curPos.x == mapWidth - 1) {
                    return curPos.step; // BFS는 먼저 도착한 것이 무조건 짧다.
                }

                if(maps[curPos.y][curPos.x] == 0) { // 벽이거나 이미 지나간 자리거나
                    continue;
                }

                maps[curPos.y][curPos.x] = 0;   // 지나간 자리 표시

                for ( PosWay way : PosWay.values()) {
                    if(isPosWay(maps, curPos.y, curPos.x, way)) {
                        queue.add(curPos.newPos(way));
                    }
                }
            }

            return -1;
        }

        private boolean isPosWay(int[][] maps, int y, int x, PosWay way) {
            int newY = y;
            int newX = x;
            switch (way) {
                case POS_LEFT:
                    newX -= 1;
                    if (newX < 0) {
                        return false;
                    }
                    break;
                case POS_RIGHT:
                    newX += 1;
                    if (newX >= maps[0].length) {
                        return false;
                    }
                    break;
                case POS_UP:
                    newY -= 1;
                    if (newY < 0) {
                        return false;
                    }
                    break;
                case POS_DOWN:
                    newY += 1;
                    if (newY >= maps.length) {
                        return false;
                    }
                    break;
            }
            if (maps[newY][newX] == 0) {
                return false;
            }
            return true;
        }

        private class Pos {
            int x;
            int y;
            int step;
            public Pos(int x, int y, int step) {
                this.x = x;
                this.y = y;
                this.step = step;
            }
            public Pos newPos(PosWay way) {
                switch (way) {
                    case POS_LEFT:
                        return new Pos(x - 1, y,step+1);
                    case POS_RIGHT:
                        return new Pos(x + 1, y,step+1);
                    case POS_UP:
                        return new Pos(x, y - 1,step+1);
                    case POS_DOWN:
                        return new Pos(x, y + 1,step+1);
                }
                return null;
            }
        }
    }
}
