import java.io.*;
import java.util.*;

/**
 * BOJ_치즈
 * @author parkrootseok
 */
public class Main {

	public static final int[] dr = {-1, 1, 0, 0};
	public static final int[] dc = {0, 0, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N, M;
	static int totalCheeseCount;
	static boolean[][] hasCheese;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		hasCheese = new boolean[N][M];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < M; ++col) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					totalCheeseCount++;
					hasCheese[row][col] = true;
				}
			}
		}

		sb.append(simulation());
		bw.write(sb.toString());
		bw.close();

	}

	public static int simulation() {

		int time = 0;

		while (0 < totalCheeseCount) {

			melting(bfs());
			time++;

		}

		return time;

	}

	public static int[][] bfs() {

		int[][] counts = new int[N][M];
		boolean[][] isVisited = new boolean[N][M];
		Queue<int []> queue = new ArrayDeque<>();
		queue.offer(new int[]{0, 0});
		isVisited[0][0] = true;

		while (!queue.isEmpty()) {

			int[] node = queue.poll();
			int row = node[0];
			int col = node[1];

			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = row + dr[dir];
				int nCol = col + dc[dir];

				if (outRange(nRow, nCol) || isVisited[nRow][nCol]) {
					continue;
				}

				// 치즈가 아니면 탐색
				if (!hasCheese[nRow][nCol]) {
					isVisited[nRow][nCol] = true;
					queue.offer(new int[]{nRow, nCol});
					continue;
				}

				// 치즈면 카운팅
				counts[nRow][nCol]++;

			}

		}

		return counts;

	}

	public static void melting(int[][] counts) {

		for (int row = 0; row < N; row++) {

			for (int col = 0; col < M; col++) {

				if (1 < counts[row][col]) {
					hasCheese[row][col] = false;
					totalCheeseCount--;
				}

			}

		}

	}

	public static boolean outRange(int row, int col) {
		return row < 0 || N <= row || col < 0 || M <= col;
	}

}