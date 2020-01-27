package netapi.mgkim.com.testDemo.java.programmers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DPTest01 {
    //https://programmers.co.kr/learn/courses/30/lessons/43105
    //정수 삼각형

    @Test
    public void Test006() {
        int[][] inputs = {
                {7},
                {3, 7},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}};
        int result = 30;
        int out = new Solution006().solution(inputs);
        System.out.println(out);
        Assert.assertEquals(result, out);
    }

    class Solution006 {
        public int solution(int[][] triangle) {

            for (int i = triangle.length - 2; i >= 0; i--) {
                for (int j = 0; j < triangle[i].length; j++) {
                    triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
                }
            }
            int answer = triangle[0][0];
            return answer;
        }
    }


//https://programmers.co.kr/learn/courses/30/lessons/42898
//4.    등굣길

    /**
     * 문제 설명
     * 계속되는 폭우로 일부 지역이 물에 잠겼습니다. 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.
     * <p>
     * 가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타냅니다.
     * <p>
     * 격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다. 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.
     * <p>
     * 제한사항
     * 격자의 크기 m, n은 1 이상 100 이하인 자연수입니다.
     * m과 n이 모두 1인 경우는 입력으로 주어지지 않습니다.
     * 물에 잠긴 지역은 0개 이상 10개 이하입니다.
     * 집과 학교가 물에 잠긴 경우는 입력으로 주어지지 않습니다.
     */
    @Test
    public void Test04() {
        int m = 100;
        int n = 100;
        int[][] puddles = {{2, 2}};
        int result = 4;
        int out = solution04_2(m, n, puddles);
//        int out = solution04(m, n, puddles);
        Assert.assertEquals(result, out);

    }

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][0]][puddles[i][1]] = -1;
        }
        dp[1][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i != 1) dp[i][j] += dp[i - 1][j] % 1000000007;
                if (j != 1) dp[i][j] += dp[i][j - 1] % 1000000007;
            }
        }
        return dp[m][n] % 1000000007;
    }


    public int solution04_2(int m, int n, int[][] puddles) {
        int[][] board = new int[m + 1][n + 1];
        for (int i = 0; i < puddles.length; i++) {
            board[puddles[i][0]][puddles[i][1]] = -1;
        }
        board[1][1] = 1;

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                    continue;
                }
                if (i != 1) board[i][j] += board[i - 1][j];
                if (j != 1) board[i][j] += board[i][j - 1];
            }
        }

        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }

        return board[m][n] % 1000000007;
    }


    public int solution04(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] board = new int[n + 1][m + 1];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < puddles.length; i++) {//물웅덩이 셋팅
            board[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }
        board[n - 1][m - 1] = 1;    //학교

        for (int i = board.length - 2; i >= 0; i--) {
            for (int j = board[i].length - 2; j >= 0; j--) {
                //학교에서부터 출발
                if (board[i][j] == -1) {
                    //웅덩이
                    continue;
                }
                //[i,j] = [i+1,j] + [i,j+1] 공식
                if (board[i + 1][j] != -1) {
                    board[i][j] += board[i + 1][j];
                }

                if (board[i][j + 1] != -1) {
                    board[i][j] += board[i][j + 1];
                }

                board[i][j] %= 1000000007;
            }
        }
        return board[0][0];
    }

    //https://programmers.co.kr/learn/courses/30/lessons/43104
    //타일 장식물 (피보나치)
    class Solution005 {
        public long solution(int N) {

            long[] results = new long[N + 1];

            results[0] = 0;
            results[1] = 4;
            results[2] = 6;
            for (int i = 3; i <= N; i++) {
                results[i] = results[i - 1] + results[i - 2];
            }


            long answer = results[N];
            return answer;
        }
    }

    //https://programmers.co.kr/learn/courses/30/lessons/42896#qna
    // 카드게임
    //해설
    //https://velog.io/@ptm0304/%EC%B9%B4%EB%93%9C%EA%B2%8C%EC%9E%84
    @Test
    public void Test007() {
        int[] left = {3, 2, 5};
        int[] right = {2, 4, 1};
//        System.out.println(new Solution007().solution(left ,right));
        System.out.println(new Solution007().solution_dfs(left, right));

    }

    class Solution007 {
        //bottom up 방식
        public int solution(int[] left, int[] right) {
            int[][] results = new int[left.length + 1][right.length + 1];

            for (int i = left.length - 1; i >= 0; i--) {
                for (int j = right.length - 1; j >= 0; j--) {
                    if (left[i] > right[j]) {
                        results[i][j] = results[i][j + 1] + right[j];
                    } else {
                        results[i][j] = Math.max(results[i + 1][j + 1], results[i + 1][j]);
                    }
                }
            }

            return results[0][0];
        }

        public int solution_dfs(int[] left, int[] right) {
            int[][] cache = new int[left.length][right.length];

            for (int i = 0; i < cache.length; i++) {
                Arrays.fill(cache[i], -1);
            }

            return findResult(left, right, 0, 0, cache);
        }

        private int findResult(int[] left, int[] right, int leftCnt, int rightCnt, int[][] cache) {
            if (leftCnt >= left.length || rightCnt >= right.length) {
                return 0;
            }

            if (cache[leftCnt][rightCnt] != -1) {
                return cache[leftCnt][rightCnt];
            }

            if (left[leftCnt] > right[rightCnt]) {
                cache[leftCnt][rightCnt] = findResult(left, right, leftCnt, rightCnt + 1, cache) + right[rightCnt];
            } else {
                cache[leftCnt][rightCnt] = Math.max(findResult(left, right, leftCnt + 1, rightCnt + 1, cache),
                        findResult(left, right, leftCnt + 1, rightCnt, cache));
            }
            return cache[leftCnt][rightCnt];
        }
    }

    //https://programmers.co.kr/learn/courses/30/lessons/42897
    //도둑질
    @Test
    public void Test008() {
        int[] money = {1, 2, 3, 1};
        int out = new Solution008().solution(money);
        System.out.println(out);
    }

    class Solution008 {
        public int solution(int[] money) {

            if (money.length == 2) {
                return Math.max(money[0], money[1]);
            } else if (money.length == 3) {
                return Math.max(money[0] + money[2], money[1]);
            }

            int[] results1 = new int[money.length];
            int[] results2 = new int[money.length];

            results1[0] = money[0];
            results1[1] = money[0];
            results2[0] = 0;
            results2[1] = money[1];

            for (int i = 2; i < money.length - 1; i++) {
                results1[i] = Math.max(results1[i - 2] + money[i], results1[i - 1]);
                results2[i] = Math.max(results2[i - 2] + money[i], results2[i - 1]);
            }
            int i = money.length - 1;
            results1[i] = Math.max(results1[i - 2], results1[i - 1]);
            results2[i] = Math.max(results2[i - 2] + money[i], results2[i - 1]);

            return Math.max(results1[i], results2[i]);
        }
    }
//https://programmers.co.kr/learn/courses/30/lessons/12983
    //단어 퍼즐
    //설명
    //https://velog.io/@ptm0304/%EB%8B%A8%EC%96%B4%ED%8D%BC%EC%A6%90-DP-BFS
/*
    주어진 스트링 t의 각 인덱스마다 스트링배열 strs와 같은 substring이 있는지 확인한다.
    만약 t.substring(현재 인덱스 - str.length(), 현재 인덱스) 가 str과 같다면 dp[현재 인덱스 -1] (현재 substring이 끝나는 부분)에
        dp[현재 인덱스 - str.length() - 1] (현재 substring 시작점 바로 전 부분) 에 1을 더한 수를 저장해준다.
    만약 현재 substring의 시작점 바로 전 부분의 dp가 0이라면 substring의 끝부분에 해당하는 dp 또한 0으로 유지한다.
    마지막으로 dp의 끝부분이 0이라면 -1, 아니라면 끝부분의 값을 그대로 반환한다
*/

    @Test
    public void Test009() {
        String[] strs = {"ba", "na", "n", "a"};
        String t = "banana";
        int out = new Solution009().solution(strs, t);
        System.out.println(out);
    }

    class Solution009 {
        public int solution(String[] strs, String t) {
            int[] dp = new int[t.length()];

            for (int i = 1; i < t.length() + 1; i++) {
                //t.substring(i-puzzle.length(), i) 검사
                for (int j = 0; j < strs.length; j++) {
                    //strs[j] 퍼즐 검사
                    String puzzle = strs[j];
                    if (i - puzzle.length() < 0) {
                        //퍼즐 길이가 i보다 큰경우
                        continue;
                    }
                    if (puzzle.equals(t.substring(i - puzzle.length(), i))) {
                        if (i - puzzle.length() == 0) {
                            dp[i - 1] = 1;
                            continue;
                        }
                        if (dp[i - puzzle.length() - 1] > 0) {
                            dp[i - 1] = dp[i - 1] == 0 ? dp[i - puzzle.length() - 1] + 1 :
                                    Math.min(dp[i - 1], dp[i - puzzle.length() - 1] + 1);
                        }
                    }
                }
            }
            int answer = dp[dp.length - 1];
            if (answer == 0) return -1;
            return answer;
        }
    }
}
