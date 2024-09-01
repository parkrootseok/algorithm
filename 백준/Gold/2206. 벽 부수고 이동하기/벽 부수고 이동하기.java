import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_벽부수고이동하기
 * @author parkrootseok
 */
public class Main {

	public static class Node {

		int row;
		int col;
		int count;
		int isBreakable;

		public Node(int row, int col, int count, int isBreakable) {
			this.row = row;
			this.col = col;
			this.count = count;
			this.isBreakable = isBreakable;
		}

	}

	public static int TRUE = 0;
	public static int FALSE = 1;
	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int M;
	public static int[][] map;
	public static int answer;
	public static boolean[][][] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		map = new int[N][M];
		for (int row = 0; row < N; row++) {

			inputs = br.readLine().trim().split("");

			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(inputs[col]);
			}

		}

		answer = Integer.MAX_VALUE;
		isVisited = new boolean[2][N][M];

		sb.append(bfs());
		bw.write(sb.toString());
		bw.close();

	}

	public static int bfs() {

		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, 1, 0));

		while (!queue.isEmpty()) {

			Node node = queue.poll();

			if (node.row == N - 1 && node.col == M - 1) {
				return node.count;
			}

			int nextRow, nextCol;
			for (int dir = 0; dir < dr.length; dir++) {

				nextRow = node.row + dr[dir];
				nextCol = node.col + dc[dir];

				if (!isPossible(nextRow, nextCol)) {
					continue;
				}

				if (isVisited[node.isBreakable][nextRow][nextCol]) {
					continue;
				}

				if (map[nextRow][nextCol] == TRUE) {
					isVisited[node.isBreakable][nextRow][nextCol] = true;
					queue.offer(new Node(nextRow, nextCol, node.count + 1, node.isBreakable));
				}

				else if (map[nextRow][nextCol] == FALSE && node.isBreakable == 0) {
					isVisited[1][nextRow][nextCol] = true;
					queue.offer(new Node(nextRow, nextCol, node.count + 1, 1));
				}

			}

		}

		return -1;

	}

	private static boolean isPossible(int nextRow, int nextCol) {

		if (0 > nextRow || nextRow >= N || 0 > nextCol || nextCol >= M) {
			return false;
		}

		return true;

	}

}