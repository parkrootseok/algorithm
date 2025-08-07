import java.io.*;
import java.util.*;

/**
 * BOJ_이친수
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
		long[] dp = new long[N + 1];

		dp[1] = 1;
		for (int digit = 2; digit <= N; digit++) {
			dp[digit] = dp[digit - 1] + dp[digit - 2];
		}

		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.close();
	}

}
