import java.io.*;
import java.util.TreeSet;

/**
 * BOJ_동전1
 * @author parkrootseok
 *
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int size;
	public static int target;

	public static int[] coins;
	public static int count;

	public static int[] combination;
	public static TreeSet<String> isChecked;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		inputs = br.readLine().trim().split(" ");
		size = Integer.parseInt(inputs[0]);
		target = Integer.parseInt(inputs[1]);

		coins = new int[size];
		for (int idx = 0; idx < size; idx++) {
			coins[idx] = Integer.parseInt(br.readLine().trim());
		}

		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int idx = 0; idx < size; idx++) {

			for (int sum = coins[idx]; sum <= target; sum++) {
				dp[sum] += dp[sum - coins[idx]];
			}

		}

		sb.append(dp[target]);
		bw.write(sb.toString());
		bw.close();

	}


}