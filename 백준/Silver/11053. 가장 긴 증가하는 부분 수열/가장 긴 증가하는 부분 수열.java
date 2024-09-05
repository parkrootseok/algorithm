import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_가장긴증가하는부분수열
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int[] numbers;
	public static int[] dp;
	public static int max;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		numbers = new int[N];
		inputs = br.readLine().trim().split(" ");
		for (int idx = 0; idx < N; idx++) {
			numbers[idx] = Integer.parseInt(inputs[idx]);
		}

		dp = new int[N];
		for (int row = 0; row < N; row++) {

			dp[row] = 1;

			for (int col = 0; col < row; col++) {

				if (numbers[row] > numbers[col]) {
					dp[row] = Math.max(dp[row], dp[col] + 1);
				}

			}

			max = Math.max(max, dp[row]);

		}

		sb.append(max);
		bw.write(sb.toString());
		bw.close();

	}

}