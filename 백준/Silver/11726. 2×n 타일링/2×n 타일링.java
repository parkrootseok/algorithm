import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2xn_타일링
 * @author parkrootseok
 */
public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int N;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        
        if (N == 1) {
            System.out.println("1");
            return;
        }
        
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int n = 3; n <= N; n++) {
            dp[n] = (dp[n - 1] + dp[n - 2]) % 10007;
        }

        sb.append(dp[N]);
        bw.write(sb.toString());
        bw.close();

    }

}