import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_테트로미노
 * @author parkrootseok
 */
public class Main {

	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static final int size = 4;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int rowSize;
	static int colSize;
	static int[][] map;

	static boolean[][] isVisited;
	static int max;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[0]);
		colSize = Integer.parseInt(inputs[1]);

		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(inputs[col]);
			}
		}

		max = Integer.MIN_VALUE;
		isVisited = new boolean[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				isVisited[row][col] = true;
				dfs(1, row, col, map[row][col]);
				isVisited[row][col] = false;
			}
		}

		sb.append(max);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int depth, int row, int col, int sum) {

		if (depth == size) {
			max = Math.max(max, sum);
			return;
		}

		for (int dir = 0; dir < dr.length; dir++) {

			int nRow = row + dr[dir];
			int nCol = col + dc[dir];

			if (outRange(nRow, nCol) || isVisited[nRow][nCol]) {
				continue;
			}

			if (depth == 2) {
				isVisited[nRow][nCol] = true;
				dfs(depth + 1, row, col, sum + map[nRow][nCol]);
				isVisited[nRow][nCol] = false;
			}

			isVisited[nRow][nCol] = true;
			dfs(depth + 1, nRow, nCol, sum + map[nRow][nCol]);
			isVisited[nRow][nCol] = false;

		}


	}

	public static boolean outRange(int row, int col) {

		if (row < 0 || rowSize <= row || col < 0 || colSize <= col) {
			return true;
		}

		return false;

	}

}