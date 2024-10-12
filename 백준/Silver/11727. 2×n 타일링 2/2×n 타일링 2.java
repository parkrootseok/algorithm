import java.io.*;
import java.util.PriorityQueue;

/**
 * BOJ_2xn타일링2
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		dp = new int[1001];
		dp[1] = 1;
		dp[2] = 3;

		if (N > 2) {
			for (int n = 3; n <= N; n++) {
				dp[n] = (dp[n - 1] + (2 * dp[n - 2])) % 10007;
			}
		}

		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.close();

	}

}