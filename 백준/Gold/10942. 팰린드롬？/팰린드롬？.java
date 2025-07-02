import java.io.*;
import java.util.*;

/**
 * BOJ_팰린드롬?
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int[] values;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		values = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			values[n] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N + 1][N + 1];
		for (int n = 1; n <= N; n++) {
			Arrays.fill(dp[n], - 1);
		}

		M = Integer.parseInt(br.readLine().trim());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int isPossible = check(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(isPossible).append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int check(int start, int end) {

		if (start >= end) {
			return 1;
		}

		if (dp[start][end] != -1) {
			return dp[start][end];
		}

		if (values[start] == values[end]) {
			return dp[start][end] = check(start + 1, end - 1);
		}

		return 0;

	}

}
