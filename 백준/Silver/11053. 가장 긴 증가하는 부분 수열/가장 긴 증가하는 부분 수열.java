import java.io.*;
import java.util.Arrays;

/**
 * BOJ_LIS
 * @author parkrootseok
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int N;
	static int[] numbers;
	static int[] dp;
	static int max;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		dp = new int[N];
		for (int row = 0; row < N; row++) {

			dp[row] = 1;

			for (int col = 0; col < row; col++) {

				if (numbers[col] < numbers[row]) {
					dp[row] = Math.max(dp[row], dp[col] + 1);
				}

			}

			max = Math.max(max, dp[row]);

		}

		sb.append(max);
		bw.write(sb.toString());
		bw.close();

	}

	public static void input() throws IOException {

		N = Integer.parseInt(br.readLine().trim());
		numbers = new int[N];

		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < N; index++) {
			numbers[index] = Integer.parseInt(inputs[index]);
		}

	}

}