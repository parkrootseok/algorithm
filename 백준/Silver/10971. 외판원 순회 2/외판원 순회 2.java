import java.io.*;
import java.util.*;

/**
 * BOJ_외판원순회2
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[][] W;
	static int min;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		W = new int[N + 1][N + 1];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				W[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		min = Integer.MAX_VALUE;
		isVisited = new boolean[N + 1];
		bruteforce(0, 0, 0);

		sb.append(min);
		bw.write(sb.toString());
		bw.close();
	}

	static void bruteforce(int depth, int to, int sum) {

		if (depth == N - 1) {
			if (0 < W[to][0]) {
				min = Math.min(min, sum + W[to][0]);
			}
			return;
		}

		if (isVisited[to] || min < sum) {
			return;
		}

		isVisited[to] = true;
		for (int from = 1; from <= N; from++) {
			if (!isVisited[from] && W[to][from] != 0) {
				bruteforce(depth + 1, from, sum + W[to][from]);
			}
		}
		isVisited[to] = false;

	}

}
