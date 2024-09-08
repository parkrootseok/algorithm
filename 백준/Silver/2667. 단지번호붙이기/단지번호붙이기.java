import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BOJ_단지번호붙이기
 * @author parkrootseok
 */
public class Main {

	public static class Node {

		int row;
		int col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};
	public static char EMPTY = '0';

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static int[][] map;
	public static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		map = new int[N][N];
		for (int row = 0; row < N; row++) {
			char[] inputs = br.readLine().trim().toCharArray();
			for (int col = 0; col < N; col++) {
				map[row][col] = inputs[col];
			}
		}

		int groupCount = 0;
		List<Integer> count = new ArrayList<>();
		isVisited = new boolean[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {

				if (map[row][col] == EMPTY || isVisited[row][col]) {
					continue;
				}

				groupCount++;
				count.add(bfs(row, col));

			}
		}

		Collections.sort(count);
		
		sb.append(groupCount).append("\n");

		if (groupCount == 0) {
			sb.append(0).append("\n");
		} else {
			for (int c : count) {
				sb.append(c).append("\n");
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int bfs(int row, int col) {

		Queue<Node> nodeQ = new ArrayDeque<>();
		nodeQ.add(new Node(row, col));
		isVisited[row][col] = true;

		int count = 0;
		while (!nodeQ.isEmpty()) {

			Node node = nodeQ.poll();

			count++;
			int curRow = node.row;
			int curCol = node.col;

			for (int dir = 0; dir < dr.length; dir++) {

				int nextRow = curRow + dr[dir];
				int nextCol = curCol + dc[dir];

				if (nextRow < 0 || N <= nextRow || nextCol < 0 || N <= nextCol) {
					continue;
				}

				if (isVisited[nextRow][nextCol]) {
					continue;
				}

				if (map[nextRow][nextCol] == EMPTY) {
					continue;
				}

				isVisited[nextRow][nextCol] = true;
				nodeQ.offer(new Node(nextRow, nextCol));

			}

		}

		return count;

	}

}