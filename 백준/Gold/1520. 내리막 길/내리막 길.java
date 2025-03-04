import java.io.*;
import java.util.*;

/**
 * BOJ_내리막길
 * @author parkrootseok
 */
public class Main {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int rowSize;
	static int colSize;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		dp = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				dp[row][col] = -1;
			}
		}

		dfs(0, 0);

		sb.append(dp[0][0]);
		bw.write(sb.toString());
		bw.close();

	}

	public static int dfs(int row, int col) {

		if (row == rowSize - 1 && col == colSize - 1) {
			return 1;
		}

		if (dp[row][col] != -1) {
			return dp[row][col];
		}

		dp[row][col] = 0;

		for (int dir = 0; dir < dr.length; dir++) {

			int nRow = row + dr[dir];
			int nCol = col + dc[dir];

			if (outRange(nRow, nCol)) {
				continue;
			}

			if (map[row][col] <= map[nRow][nCol]) {
				continue;
			}

			dp[row][col] += dfs(nRow, nCol);

		}

		return dp[row][col];

	}

	public static boolean outRange(int row, int col) {
		return row < 0 || rowSize <= row || col < 0 || colSize <= col;
	}

}