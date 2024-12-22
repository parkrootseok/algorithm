import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_LCS
 */
public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;
    public static String[] inputs;

    public static char[] A;
    public static char[] B;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        A = br.readLine().trim().toCharArray();
        B = br.readLine().trim().toCharArray();

        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int aIdx = 1; aIdx <= A.length; aIdx++) {

            for (int bIdx = 1; bIdx <= B.length; bIdx++) {

                // 두 문자열이 같다면 이전 최장 길이에서 증가
                if (A[aIdx - 1] == B[bIdx - 1]) {
                    dp[aIdx][bIdx] = dp[aIdx - 1][bIdx - 1] + 1;
                }

                // 다르다면 이전 최장 길이를 유지
                else {
                    dp[aIdx][bIdx] = Math.max(dp[aIdx - 1][bIdx], dp[aIdx][bIdx - 1]);
                }

            }

        }

        sb.append(dp[A.length][B.length]);
        bw.write(sb.toString());
        bw.close();

    }

}