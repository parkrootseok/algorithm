import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_1,2,3더하기
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int testCount;
    static int number;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        testCount = Integer.parseInt(br.readLine().trim());
        for (int t = 0; t < testCount; t++) {

            number = Integer.parseInt(br.readLine().trim());
            dp = new int[number + 1];
            
            dp[0] = 1;
            for (int n = 1; n <= number; n++) {
                if (n - 1 >= 0) {
                    dp[n] += dp[n-1];
                }
                if (n - 2 >= 0) {
                    dp[n] += dp[n-2];
                }
                if (n - 3 >= 0) {
                    dp[n] += dp[n-3];
                }
            }

            sb.append(dp[number]).append("\n");

        }

        bw.write(sb.toString());
        bw.close();

    }



}