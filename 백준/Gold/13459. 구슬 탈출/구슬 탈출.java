import java.io.*;
import java.util.*;

/**
 * BOJ_구슬탈출
 * @author parkrootseok
 */
public class Main {

	static class Node {

		int[] red;
		int[] blue;

		public Node(int[] red, int[] blue) {
			this.red = red;
			this.blue = blue;
		}

	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static char[][] map;
	static Node node;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		node = new Node(new int[2], new int[2]);
		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			char[] inputs = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				map[r][c] = inputs[c];
				if (map[r][c] == 'R') {
					node.red[0] = r;
					node.red[1] = c;
				}
				if (map[r][c] == 'B') {
					node.blue[0] = r;
					node.blue[1] = c;
				}
			}
		}

		sb.append(simulation()? 1 : 0);
		bw.write(sb.toString());
		bw.close();

	}

	public static boolean simulation() {

		Queue<Node> nodes = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N * M][N * M];

		nodes.offer(node);
		isVisited[convertToOneDim(node.red[0], node.red[1])][convertToOneDim(node.blue[0], node.blue[1])] = true;

		int count = 0;
		while (!nodes.isEmpty()) {

			if (10 < ++count) {
				return false;
			}

			int size = nodes.size();
			for (int s = 0; s < size; s++) {

				Node node = nodes.poll();
				int[] red = node.red;
				int[] blue = node.blue;

				for (int dir = 0; dir < dr.length; dir++) {

					int[] rolledRed = rolling(dir, red[0], red[1], blue[0], blue[1]);
					int[] rolledBlue = rolling(dir, blue[0], blue[1], red[0], red[1]);

					// 파란공이 빠진 경우 무조건 실패
					if (rolledBlue[2] == 1) {
						continue;
					}

					// 빨간공만 빠진 경우 성공
					if (rolledRed[2] == 1) {
						return true;
					}

					if (!isVisited[convertToOneDim(rolledRed[0], rolledRed[1])][convertToOneDim(rolledBlue[0], rolledBlue[1])]) {
						isVisited[convertToOneDim(rolledRed[0], rolledRed[1])][convertToOneDim(rolledBlue[0], rolledBlue[1])] = true;
						nodes.offer(new Node(rolledRed, rolledBlue));
					}

				}
			}

		}

		return false;

	}

	public static int[] rolling(int dir, int row, int col, int oRow, int oCol) {

		int count = 0;
		boolean isEncountered = false;
		int cRow = row + dr[dir];
		int cCol = col + dc[dir];
		while (map[cRow][cCol] != '#') {

			// 중간에 다른 구슬을 마주친 경우
			if (cRow == oRow && cCol == oCol) {
				isEncountered = true;
			}

			// 구멍을 만난 경우
			if (map[cRow][cCol] == 'O') {
				count = 1;
				break;
			}

			cRow += dr[dir];
			cCol += dc[dir];
		}

		// 현재 위치는 벽이므로 한칸 반대로 이동
		cRow -= dr[dir];
		cCol -= dc[dir];

		// 움직이는 동안 만났으면, 한칸 더 반대로 이동
		if (isEncountered) {
			cRow -= dr[dir];
			cCol -= dc[dir];
		}

		return new int[] {cRow, cCol, count};

	}

	public static int convertToOneDim(int r, int c) {
		return (r * M) + c;
	}

}