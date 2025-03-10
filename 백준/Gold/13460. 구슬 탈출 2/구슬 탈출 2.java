import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_구슬탈출2
 * @author parkrootseok
 */
public class Main {

	static final char WALL = '#';
	static final char HALL = 'O';
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	static class Node {

		int[] red = new int[2];
		int[] blue = new int[2];

		public Node(int rRow, int rCol, int bRow, int bCol) {
			this.red[0] = rRow;
			this.red[1] = rCol;
			this.blue[0] = bRow;
			this.blue[1] = bCol;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int rowSize;
	static int colSize;
	static char[][] map;
	static int rRow, rCol;
	static int bRow, bCol;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			char[] inputs = br.readLine().trim().toCharArray();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = inputs[col];

				if (map[row][col] == 'R') {
					rRow = row;
					rCol = col;
				}

				if (map[row][col] == 'B') {
					bRow = row;
					bCol = col;
				}

			}
		}

		sb.append(bfs());
		bw.write(sb.toString());
		bw.close();

	}

	public static int bfs() {

		boolean[][] isVisited = new boolean[rowSize * colSize][rowSize * colSize];
		Queue<Node> nodes = new ArrayDeque<>();
		nodes.offer(new Node(rRow, rCol, bRow, bCol));
		isVisited[convertToOneDimIndex(rRow, rCol)][convertToOneDimIndex(bRow, bCol)] = true;

		int time = 0;
		while (!nodes.isEmpty()) {

			if (10 < ++time) {
				return -1;
			}

			int size = nodes.size();
			while (0 < size--) {

				Node node = nodes.poll();
				int[] red = node.red;
				int[] blue = node.blue;

				for (int dir = 0; dir < dr.length; dir++) {

					int[] rolledRed = roll(dir, red[0], red[1], blue[0], blue[1]);
					int[] rolledBlue = roll(dir, blue[0], blue[1], red[0], red[1]);

					// 파란 구슬이 도착한 경우 불가
					if (rolledBlue[2] == 1) {
						continue;
					}

					// 빨간 구슬이 도착한 경우 종료
					if (rolledRed[2] == 1) {
						return time;
					}

					int redIndex = convertToOneDimIndex(rolledRed[0], rolledRed[1]);
					int blueIndex = convertToOneDimIndex(rolledBlue[0], rolledBlue[1]);

					if (isVisited[redIndex][blueIndex]) {
						continue;
					}

					isVisited[convertToOneDimIndex(rolledRed[0], rolledRed[1])][convertToOneDimIndex(rolledBlue[0], rolledBlue[1])] = true;
					nodes.offer(new Node(rolledRed[0], rolledRed[1], rolledBlue[0], rolledBlue[1]));

				}

			}

		}

		return -1;

	}

	public static int[] roll(int dir, int startRow, int startCol, int otherRow, int otherCol) {

		int curRow = startRow;
		int curCol = startCol;

		int count = 0;
		boolean isEncountered = false;
		while (map[curRow][curCol] != WALL) {

			if (map[curRow][curCol] == HALL) {
				count++;
			}

			if (curRow == otherRow && curCol == otherCol) {
				isEncountered = true;
			}

			curRow += dr[dir];
			curCol += dc[dir];

		}

		if (isEncountered) {
			curRow -= dr[dir];
			curCol -= dc[dir];
		}

		curRow -= dr[dir];
		curCol -= dc[dir];

		return new int[]{curRow, curCol, count};

	}

	public static int convertToOneDimIndex(int row, int col) {
		return row * colSize + col;
	}

}