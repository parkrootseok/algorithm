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

	static int N, K;
	static int[] W;
	static int[] V;
	static int[] DP;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);

		W = new int[N + 1];
		V = new int[N + 1];
		for (int index = 1; index <= N; index++) {
			inputs = br.readLine().trim().split(" ");
			W[index] = Integer.parseInt(inputs[0]);
			V[index] = Integer.parseInt(inputs[1]);
		}

		DP = new int[K + 1];
		for (int index = 1; index <= N; index++) {
			for (int weight = K; W[index] <= weight; weight--) {
				DP[weight] = Math.max(DP[weight - W[index]] + V[index], DP[weight]);
			}
		}

		sb.append(DP[K]);
		bw.write(sb.toString());
		bw.close();

	}

}