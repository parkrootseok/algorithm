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
		int isBreakable = 1;

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

		N--;
		M--;
		bfs();

		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bfs() {

		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, 1, 1));

		while (!queue.isEmpty()) {

			Node node = queue.poll();

			if (node.row == N && node.col == M) {
				answer = Math.min(answer, node.count);
			}

			if (isVisited[node.isBreakable][node.row][node.col]) {
				continue;
			}

			isVisited[node.isBreakable][node.row][node.col] = true;

			int nextRow, nextCol;
			for (int dir = 0; dir < dr.length; dir++) {

				nextRow = node.row + dr[dir];
				nextCol = node.col + dc[dir];

				if (!isPossible(nextRow, nextCol)) {
					continue;
				}

				// 이동할 수 없는 곳이면서 벽을 부술수 없는 상태라면 스킵
				if (map[nextRow][nextCol] == FALSE && node.isBreakable != 1) {
					continue;
				}

				// 이동 불가능한 곳 이지만 벽을 부술 수 있는 기회가 남아있다면 이동
				if (map[nextRow][nextCol] == FALSE && node.isBreakable == 1) {
					queue.offer(new Node(nextRow, nextCol, node.count + 1, 0));
				}

				else {
					queue.offer(new Node(nextRow, nextCol, node.count + 1, node.isBreakable));
				}

			}

		}

	}

	private static boolean isPossible(int nextRow, int nextCol) {

		if (0 > nextRow || nextRow > N || 0 > nextCol || nextCol > M) {
			return false;
		}

		return true;

	}

}