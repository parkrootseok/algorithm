import java.io.*;
import java.util.Stack;

/**
 * BOJ_합분해
 * @author parkrootseok
 */

public class Main {

    static final int MOD_NUM = 1_000_000_000;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);

        dp = new int[N + 1][K + 1];

        // K가 1인 경우
        for (int number = 0; number <= N; number++) {
            // 무조건 1개의 경우의 수
            dp[number][1] = 1;
        }

        // N이 1인 경우
        for (int number = 0; number <= K; number++) {
            // 선택할 수 있는 수와 경우의 수가 동일
            dp[1][number] = number;
        }

        for (int n = 2; n <= N; n++) {
            for (int k = 2; k <= K; k++) {
                dp[n][k] = (dp[n - 1][k] + dp[n][k - 1]) % MOD_NUM;
            }
        }

        sb.append(dp[N][K]);
        bw.write(sb.toString());
        bw.close();

    }

}