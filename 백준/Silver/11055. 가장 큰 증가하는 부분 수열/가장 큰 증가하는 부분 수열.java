import java.io.*;
import java.util.*;

/**
 * BOJ_가장큰증가하는부분수열
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[] A;
	static int answer;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++) {
			A[index] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = A[i];
			for (int j = 0; j < i; j++) {
				if (A[j] < A[i]) {
					dp[i] = Math.max(dp[i], dp[j] + A[i]);
				}
			}
			answer = Math.max(answer, dp[i]);
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
	}

}
