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

		public Node(int row, int col, int distance, int count) {
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.count = count;
		}
	}

	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static final int EMPTY = 0;
	static final int WALL = 1;

	static int N, M, K;
	static int[][] map;
	static int[][] counts;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		counts = new int[N][M];
		for (int row = 0; row < N; row++) {
			String inputs = br.readLine();
			Arrays.fill(counts[row], Integer.MAX_VALUE);
			for (int col = 0; col < M; col++) {
				map[row][col] = inputs.charAt(col) - '0';
			}
		}

		sb.append(bfs());
		bw.write(sb.toString());
		bw.close();
	}

	public static int bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, 1, 0));
		counts[0][0] = 0;

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.row == N - 1 && node.col == M - 1) {
				return node.distance;
			}

			for (int dir = 0; dir < dr.length; dir++) {
				int nRow = node.row + dr[dir];
				int nCol = node.col + dc[dir];

				if (outRange(nRow, nCol) || counts[nRow][nCol] <= node.count) {
					continue;
				}

				if (map[nRow][nCol] == WALL) {
					if (K <= node.count || counts[nRow][nCol] <= node.count + 1) {
						continue;
					}
					if (isNight(node.distance)) {
						queue.offer(new Node(node.row, node.col, node.distance + 1, node.count));
					} else {
						queue.offer(new Node(nRow, nCol, node.distance + 1, node.count + 1));
						counts[nRow][nCol] = node.count + 1;
					}
				} else {
					queue.offer(new Node(nRow, nCol, node.distance + 1, node.count));
					counts[nRow][nCol] = node.count;
				}
			}
		}

		return -1;
	}

	public static boolean isNight(int distance) {
		return distance % 2 == 0;
	}

	public static boolean outRange(int r, int c) {
		return r < 0 || N <= r || c < 0 || M <= c;
	}

}
