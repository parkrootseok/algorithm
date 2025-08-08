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
	static int[] A;
	static int[][] DP;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		DP = new int[N + 1][10];

		for (int lastNumber = 1; lastNumber <= 9; lastNumber++) {
			DP[1][lastNumber] = 1;
		}

		for (int i = 2; i <= N; i++) {
			DP[i][0] = DP[i - 1][1];
			for (int lastNumber = 1; lastNumber <= 8; lastNumber++) {
				DP[i][lastNumber] = (DP[i - 1][lastNumber - 1] + DP[i - 1][lastNumber + 1]) % 1_000_000_000;
			}
			DP[i][9] = DP[i - 1][8];
		}

		int answer = 0;
		for (int lastNumber = 0; lastNumber <= 9; lastNumber++) {
			 answer += DP[N][lastNumber] ;
			 answer %= 1_000_000_000;
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
	}

}
