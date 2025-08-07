import java.io.*;
import java.util.*;

/**
 * BOJ_쉬운계단수
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[][] DP;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		DP = new int[N + 1][10];
		DP[1][0] = 0;
		for (int i = 1; i <= 9; i++) {
			DP[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			DP[i][0] = DP[i - 1][1];
			for (int j = 1; j <= 8; j++) {
				DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j + 1]) % 1_000_000_000;
			}
			DP[i][9] = DP[i - 1][8];
		}

		long answer = 0;
		for (int i = 0; i <= 9; i++) {
			answer = (answer + DP[N][i]) % 1_000_000_000;
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
	}

}
