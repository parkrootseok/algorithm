import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * BOJ_쉬운최단거리
 * @author parkrootseok
 */
public class Main {

	static class Node {

		int row;
		int col;
		int count;

		public Node(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}

	}

	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int IMPOSSIBLE = 0;
	public static int POSSIBLE = 1;
	public static int TARGET = 2;

	public static int N;
	public static int M;
	public static int tRow;
	public static int tCol;
	public static int[][] map;
	public static boolean[][] isVisited;
	public static int[][] distance;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		map = new int[N][M];
		distance = new int[N][M];
		isVisited = new boolean[N][M];
		for (int row = 0; row < N; row++) {
			inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < M; col++) {

				map[row][col] = Integer.parseInt(inputs[col]);

				if (map[row][col] == TARGET) {
					tRow = row;
					tCol = col;
				}

				if (map[row][col] == IMPOSSIBLE) {
					isVisited[row][col] = true;
				}

			}
		}

		bfs();

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (!isVisited[row][col]) {
					sb.append("-1").append(" ");
				}
				else {
					sb.append(distance[row][col]).append(" ");
				}
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void bfs() {

		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(tRow, tCol, 0));
		isVisited[tRow][tCol] = true;

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			int cRow = node.row;
			int cCol = node.col;
			int cCnt = node.count;

			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = cRow + dr[dir];
				int nCol = cCol + dc[dir];

				if (nRow < 0 || N <= nRow || nCol < 0 || M <= nCol) {
					continue;
				}

				if (!isVisited[nRow][nCol] && map[nRow][nCol] == 1) {
					isVisited[nRow][nCol] = true;
					distance[nRow][nCol] = distance[cRow][cCol] + 1;
					queue.add(new Node(nRow, nCol, cCnt + 1));
				}

			}

		}

	}

}