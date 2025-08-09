import java.io.*;
import java.util.*;

/**
 * BOJ_외판원순회
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int INF = 1_000_000_000;

	static int N;
	static int[][] W;
	static int[][] DP;
	static int allVisitedBit;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		DP = new int[N][(int) Math.pow(2, N)];
		allVisitedBit = (1 << N) - 1;
		for (int r = 0; r < N; r++) {
			Arrays.fill(DP[r], -1);
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				W[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		sb.append(bruteforce(0, 1));
		bw.write(sb.toString());
		bw.close();
	}

	static int bruteforce(int to, int curVisitedBit) {
		if (allVisitedBit == curVisitedBit) {
			if (W[to][0] == 0) {
				return INF;
			}
			return W[to][0];
		}

		if (DP[to][curVisitedBit] != -1) {
			return DP[to][curVisitedBit];
		}

		DP[to][curVisitedBit] = INF;
		for (int from = 0; from < N; from++) {
			if (W[to][from] == 0 || (curVisitedBit & (1 << from)) != 0) {
				continue;
			}
			DP[to][curVisitedBit] = Math.min(DP[to][curVisitedBit], bruteforce(from, curVisitedBit | (1 << from)) + W[to][from]);
		}

		return DP[to][curVisitedBit];
	}

}
