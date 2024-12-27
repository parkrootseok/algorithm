import java.io.*;
import java.util.*;

/**
 * BOJ_알고스팟
 * @author parkrootseok
 */
public class Main {

	public static class Node implements Comparable<Node> {

		int row;
		int col;
		int count;

		public Node(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}

		public int compareTo(Node n) {
			return Integer.compare(this.count, n.count);
		}

	}

	public static final int[] dr = {-1,1,0,0};
	public static final int[] dc = {0,0,-1,1};
	public static final int WALL = 1;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static int M;
	public static int[][] map;
	public static int[][] counts;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		map = new int[M][N];
		for (int row = 0; row < M; row++) {
			inputs = br.readLine().trim().split("");
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(inputs[col]);
			}
		}

		counts = new int[M][N];
		for (int row = 0; row < M; row++) {
			Arrays.fill(counts[row], Integer.MAX_VALUE);
		}
		dijkstra();

		sb.append(counts[M - 1][N - 1]);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dijkstra() {

		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0,0, 0));
		counts[0][0] = 0;

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			int cRow = node.row;
			int cCol = node.col;
			int cCount = node.count;

			if (cCount > counts[cRow][cCol]) {
				continue;
			}

			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = cRow + dr[dir];
				int nCol = cCol + dc[dir];

				if (nRow < 0 || M <= nRow || nCol < 0 || N <= nCol) {
					continue;
				}

				if (counts[nRow][nCol] > cCount + map[nRow][nCol]) {
					counts[nRow][nCol] = cCount + map[nRow][nCol];
					queue.add(new Node(nRow, nCol, counts[nRow][nCol]));
				}

			}

		}
	}

}