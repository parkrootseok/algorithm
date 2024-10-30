import java.io.*;
import java.util.Arrays;

/**
 * BOJ_가장긴바이토닉부분수열
 * @author parkrootseok
 */

public class Main {

    static final int LR = 0;
    static final int RL = 1;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static int[] numbers;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        numbers = new int[size];

        String[] inputs = br.readLine().trim().split(" ");
        for (int s = 0; s < size; s++) {
            numbers[s] = Integer.parseInt(inputs[s]);
        }

        dp = new int[2][size];

        for (int row = 0; row < size; row++) {

            dp[LR][row] = 1;

            for (int col = 0; col < row; col++) {

                if (numbers[row] > numbers[col]) {
                    dp[LR][row] = Math.max(dp[LR][row], dp[LR][col] + 1);
                }

            }

        }

        for (int row = size - 1; 0 <= row; row--) {

            dp[RL][row] = 1;

            for (int col = size - 1; row < col; col--) {

                if (numbers[row] > numbers[col]) {
                    dp[RL][row] = Math.max(dp[RL][row], dp[RL][col] + 1);
                }

            }

        }

        int ans = 1;
        for (int idx = 0; idx < size; idx++) {
            ans = Math.max(ans, (dp[LR][idx] + dp[RL][idx]) - 1);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();

    }

}