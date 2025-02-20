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
		return bfs();
	}

	public static int bfs() {

		int[][] counts = new int[N][M];
		boolean[][] isVisited = new boolean[N][M];

		Deque<int []> queue = new ArrayDeque<>();

		for (int row = 0; row < N; row++) {
			queue.offer(new int[]{row, 0, 0});
			queue.offer(new int[]{row, M - 1, 0});
		}

		for (int col = 0; col < M; col++) {
			queue.offer(new int[]{0, col, 0});
			queue.offer(new int[]{N - 1, col, 0});
		}

		int time = 0;
		while (!queue.isEmpty()) {

			int[] node = queue.poll();

			int row = node[0];
			int col = node[1];
			int cTime = node[2];

			if (isVisited[row][col]) {
				continue;
			}

			isVisited[row][col] = true;
			time = cTime;

			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = row + dr[dir];
				int nCol = col + dc[dir];

				if (outRange(nRow, nCol) || isVisited[nRow][nCol]) {
					continue;
				}

				// 치즈가 아니면 탐색
				if (!hasCheese[nRow][nCol]) {
					queue.addFirst(new int[]{nRow, nCol, cTime});
					continue;
				}

				// 치즈면 카운팅
				if (1 < ++counts[nRow][nCol]) {
					queue.addLast(new int[]{nRow, nCol, cTime + 1});
				}

			}

		}

		return time;

	}

	public static boolean outRange(int row, int col) {
		return row < 0 || N <= row || col < 0 || M <= col;
	}

}