import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 계단 오르기
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] dp;
    static int[] score;

    public void solution() {

        // N번째 계단을 오르는 방법은 2가지가 존재 (계단 3개는 연속으로 밟을 수 없음)
        // 1. N-2 -> N
        // 2. N-3 -> N-1 -> N
        // 위 2가지 방법의 결과를 비교하여 N번째 계단에서 최대값을 도출
        
        for (int i = 1; i <= N; i++) {

            if (i == 1) {
                dp[1] = score[1];
            } else if (i == 2) {
                dp[2] = score[1] + score[2];
            } else if (i == 3) {
                dp[3] = Math.max(score[1], score[2]) + score[3];
            } else {
                dp[i] = Math.max(dp[i - 2], score[i - 1] + dp[i - 3]) + score[i];
            }

        }

    }

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        score = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        main.solution();

        bw.write(dp[N] + "\n");
        bw.close();

    }

}