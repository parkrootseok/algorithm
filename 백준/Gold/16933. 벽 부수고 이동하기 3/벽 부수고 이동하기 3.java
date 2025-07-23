import java.io.*;
import java.util.*;

/**
 * BOJ_벽부수고이동하기3
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Node {
		int row;
		int col;
		int distance;
		int count;
		int status;

		public Node(int row, int col, int distance, int count, int status) {
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.count = count;
			this.status = status;
		}
	}

	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static final int EMPTY = 0;
	static final int WALL = 1;

	static int N, M, K;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int row = 0; row < N; row++) {
			String inputs = br.readLine();
			for (int col = 0; col < M; col++) {
				map[row][col] = inputs.charAt(col) - '0';
			}
		}

		sb.append(bfs());
		bw.write(sb.toString());
		bw.close();
	}

	public static int bfs() {

		boolean[][][][] isVisited = new boolean[2][K + 1][N][M];
		Queue<Node> queue = new ArrayDeque<>();
		isVisited[0][0][0][0] = true;
		queue.offer(new Node(0, 0, 1, 0,0));

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int nDis = node.distance + 1;
			int nStatus = (node.status + 1) % 2;

			if (node.row == N - 1 && node.col == M - 1) {
				return node.distance;
			}

			if (!isVisited[nStatus][node.count][node.row][node.col]) {
				isVisited[nStatus][node.count][node.row][node.col] = true;
				queue.offer(new Node(node.row, node.col, nDis, node.count, nStatus));
			}

			for (int dir = 0; dir < dr.length; dir++) {
				int nRow = node.row + dr[dir];
				int nCol = node.col + dc[dir];

				if (outRange(nRow, nCol)) {
					continue;
				}

				if (map[nRow][nCol] == EMPTY) {
					if (!isVisited[nStatus][node.count][nRow][nCol]) {
						isVisited[nStatus][node.count][nRow][nCol] = true;
						queue.offer(new Node(nRow, nCol, nDis, node.count, nStatus));
					}
				}
				if (map[nRow][nCol] == WALL && node.status == 0 && node.count < K) {
					if (!isVisited[nStatus][node.count + 1][nRow][nCol]) {
						isVisited[nStatus][node.count + 1][nRow][nCol] = true;
						queue.offer(new Node(nRow, nCol, nDis, node.count + 1, nStatus));
					}
				}
			}
		}

		return -1;
	}

	public static boolean outRange(int r, int c) {
		return r < 0 || N <= r || c < 0 || M <= c;
	}

}
