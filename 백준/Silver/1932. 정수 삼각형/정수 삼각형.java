import java.io.*;
import java.nio.file.WatchEvent;
import java.util.Arrays;

/**
 * BOJ_평범한배낭
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int N;
	static int[][] numbers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		numbers = new int[N + 1][N + 1];
		for (int n = 1; n <= N; n++) {
			String[] inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < n; col++) {
				numbers[n][col + 1] = Integer.parseInt(inputs[col]);
			}
		}

		int[][] DP = new int[N + 1][N + 1];
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= row; col++) {
				DP[row][col] = Math.max(DP[row - 1][col - 1], DP[row - 1][col]) + numbers[row][col];
			}
		}

		int max = Integer.MIN_VALUE;
		for (int col = 1; col <= N; col++) {
			max = Math.max(max, DP[N][col]);
		}

		sb.append(max);
		bw.write(sb.toString());
		bw.close();

	}

}