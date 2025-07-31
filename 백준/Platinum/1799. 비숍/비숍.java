import java.io.*;
import java.util.*;

/**
 * BOJ_비숍
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int[] dr = {-1, -1, 1, 1};
	static final int[] dc = {-1, 1, -1, 1};

	static final int BLACK = 0;
	static final int WHITE = 1;
	static final int EMPTY = 1;
	static final int BISHOP = 2;

	static int N;
	static int[][] board;
	static int[][] color;

	static int white;
	static int black;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		color = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				color[r][c] = (r + c) % 2;
			}
		}

		bruteforce(0, 0, color[0][0], 0);
		bruteforce(0, 1, color[0][1], 0);

		sb.append(white + black);
		bw.write(sb.toString());
		bw.close();
	}

	static void bruteforce(int cRow, int cCol, int cColor, int count) {
		if (N <= cRow) {
			if (cColor == BLACK) {
				black = Math.max(black, count);
			} else {
				white = Math.max(white, count);
			}
			return;
		}
		
		int nCol = cCol + 2;
		int nRow = N <= nCol? cRow + 1 : cRow;
		if (N <= nCol && nRow < N) {
			if (color[nRow][0] == cColor) {
				nCol = 0;
			} else {
				nCol = 1;
			}
		}

		if (board[cRow][cCol] == EMPTY) {
			// 1. 비숍 배치 가능?
			if (isPossible(cRow, cCol)) {
				// 1-1. 비숍 배치 후 재귀 탐색
				board[cRow][cCol] = BISHOP;
				bruteforce(nRow, nCol, cColor, count + 1);
				board[cRow][cCol] = EMPTY;
			}
		}

		bruteforce(nRow, nCol, cColor, count);
	}

	static boolean isPossible(int r, int c) {
		for (int dir = 0; dir < dr.length; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			while(!outRange(nr, nc)) {
				if (board[nr][nc] == BISHOP) {
					return false;
				}
				nr += dr[dir];
				nc += dc[dir];
			}
		}
		return true;
	}

	static boolean outRange(int r, int c) {
		return r < 0 || N <= r || c < 0 || N <= c;
	}

}
