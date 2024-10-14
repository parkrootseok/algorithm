import java.io.*;

/**
 * BOJ_FourSquares
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
		dp = new int[N + 1];
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {

			dp[i] = dp[i - 1];

			for (int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i - (j * j)]);
			}

			dp[i]++;

		}

		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.close();

	}

}