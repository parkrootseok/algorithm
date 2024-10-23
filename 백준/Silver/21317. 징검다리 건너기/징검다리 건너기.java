import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BOJ_징검다리건너기
 * @author parkrootseok
 */
public class Main {

	static final int SMALL = 0;
	static final int BIG = 1;
	static final int MAX = 100_000;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int stoneCount;
	static int[][] jump;
	static int K;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		stoneCount = Integer.parseInt(br.readLine().trim());

		jump = new int[2][Math.max(4, stoneCount)];
		for (int s = 1; s <= stoneCount - 1; s++) {
			String[] inputs = br.readLine().trim().split(" ");
			jump[SMALL][s] = Integer.parseInt(inputs[0]);
			jump[BIG][s] = Integer.parseInt(inputs[1]);
		}
		K = Integer.parseInt(br.readLine().trim());

		int[][] dp = new int[2][Math.max(4, stoneCount + 1)];
		for (int row = 0; row < dp.length; row++) {
			Arrays.fill(dp[row], MAX);
		}

		dp[0][0] = dp[0][1] = dp[1][0] = dp[1][1] =  0;
		dp[0][2] = jump[SMALL][1];
		dp[0][3] = Math.min(dp[0][2] + jump[SMALL][2], dp[0][1] + jump[BIG][1]);
		for (int stone = 4; stone <= stoneCount; stone++) {

			// 매우 큰 점프를 사용하지 않는 경우
			dp[0][stone] = Math.min(dp[0][stone - 1] + jump[SMALL][stone - 1], dp[0][stone - 2] + jump[BIG][stone - 2]);

			// 매우 큰 점프를 사용하는 경우
			dp[1][stone] = Math.min(Math.min(dp[1][stone - 1] + jump[SMALL][stone - 1], dp[1][stone - 2] + jump[BIG][stone - 2]), dp[0][stone - 3] + K);

		}

		sb.append(Math.min(dp[0][stoneCount], dp[1][stoneCount]));
		bw.write(sb.toString());
		bw.close();

	}

}