import java.io.*;
import java.util.*;

/**
 * BOJ_1로만들기2
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		int[] prev = new int[N + 1];

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		for (int number = 2; number <= N; number++) {
			dp[number] = dp[number - 1] + 1;
			prev[number] = number - 1;

			if (number % 3 == 0 && dp[number] > dp[number / 3] + 1) {
				dp[number] = dp[number / 3] + 1;
				prev[number] = number / 3;
			}

			if (number % 2 == 0 && dp[number] > dp[number / 2] + 1) {
				dp[number] = dp[number / 2] + 1;
				prev[number] = number / 2;
			}
		}

		if (dp[N] == Integer.MAX_VALUE) {
			sb.append(0).append("\n").append("1");
		} else {
			sb.append(dp[N]).append("\n");
			for (int number = N; number != 0; number = prev[number]) {
				sb.append(number).append(" ");
			}
		}

		bw.write(sb.toString());
		bw.close();
	}

}
