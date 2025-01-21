import java.io.*;
import java.util.*;

/**
 * BOJ_스티커
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T;
	static int n;
	static int[][] scores;
	static boolean[][] isUsed;
	static int max;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine().trim());
		for (int t = 0; t < T; ++t) {

			n = Integer.parseInt(br.readLine().trim());

			max = Integer.MIN_VALUE;
			scores = new int[2][n + 1];
			isUsed = new boolean[2][n + 1];
			for (int row = 0; row < scores.length; ++row) {

				st = new StringTokenizer(br.readLine(), " ");

				int col = 1;
				while (st.hasMoreTokens()) {
					scores[row][col++] = Integer.parseInt(st.nextToken());
				}

			}

			dp = new int[3][n + 1];
			for (int col = 1; col <= n; col++) {

				// 위
				dp[0][col] = Math.max(dp[1][col - 1] + scores[0][col], dp[2][col - 1] + scores[0][col]);

				// 아래
				dp[1][col] = Math.max(dp[0][col - 1] + scores[1][col], dp[2][col - 1] + scores[1][col]);

				// 선택 X
				dp[2][col] = Math.max(dp[0][col - 1], dp[1][col - 1]);

			}

			sb.append(Math.max(dp[0][n], Math.max(dp[1][n], dp[2][n]))).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}