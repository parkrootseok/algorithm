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

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {

			int N = Integer.parseInt(br.readLine());
			int[][] stickers = new int[2][N];
			for (int index = 0; index < 2; index++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int n = 0; n < N; n++) {
					stickers[index][n] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dp = new int[3][N + 1];
			for (int n = 1; n <= N; n++) {

				// X
				dp[0][n] = Math.max(dp[1][n - 1], dp[2][n - 1]);

				// 아래
				dp[1][n] = Math.max(dp[0][n - 1] + stickers[0][n - 1], dp[2][n - 1] + stickers[0][n - 1]);

				// 위
				dp[2][n] = Math.max(dp[0][n - 1] + stickers[1][n - 1], dp[1][n - 1] + stickers[1][n - 1]);


			}

			sb.append(Math.max(dp[0][N], Math.max(dp[1][N], dp[2][N]))).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}