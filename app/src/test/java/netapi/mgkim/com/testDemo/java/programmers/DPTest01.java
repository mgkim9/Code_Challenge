package netapi.mgkim.com.testDemo.java.programmers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DPTest01 {


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
    public void Test03() {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int result = 30;
        int out = solution03(triangle);
        Assert.assertEquals(result, out);

    }

    public int solution03(int[][] triangle) {
        int[][] dp = triangle.clone();

        for (int i = dp.length -2; i >= 0 ; i--) {
            for (int j = 0; j < dp[i].length ; j++) {
                dp[i][j] = Math.max(dp[i+1][j+1], dp[i+1][j]) + dp[i][j];
            }
        }
        return dp[0][0];
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
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        int result = 4;
        int out = solution04(m, n, puddles);
        Assert.assertEquals(result, out);

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
}
