import java.io.*;
import java.util.*;

/**
 * BOJ_상범 빌딩
 * @author parkrootseok
 */
public class Main {

	static int[] dl = {0, 0, 0, 0, 1, -1};
	static int[] dr = {0, 0, 1, -1, 0, 0};
	static int[] dc = {1, -1, 0, 0, 0, 0};
	
	static final char NOT_EMPTY = '#';
	static final char STARTING_POINT = 'S';
	static final char EXIT = 'E';

	static class Position {
		int l;
		int r;
		int c;
		int time;

		public Position(int l, int r, int c, int time) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int L, R, C;
	static char[][][] building;
	static Position startingPoint;
	static Position exit;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		while (true) {

			st = new StringTokenizer(br.readLine().trim());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (L == 0 && R == 0 && C == 0) {
				break;
			}

			building = new char[L][R][C];
			for (int l = 0; l < L; l++) {
				for (int r = 0; r <= R; r++) {
					char[] inputs = br.readLine().toCharArray();
					if (inputs.length != 0) {
						for (int c = 0; c < C; c++) {
							building[l][r][c] = inputs[c];
							if (building[l][r][c] == STARTING_POINT) {
								startingPoint = new Position(l, r, c, 0);
							} else if (building[l][r][c] == EXIT) {
								exit = new Position(l, r, c, 0);
							}
						}
					}
				}
			}

			int answer = bfs();
			if (answer != -1) {
				sb.append("Escaped in ").append(answer).append(" minute(s).");
			} else {
				sb.append("Trapped!");
			}
			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int bfs() {

		boolean[][][] isVisited = new boolean[L][R][C];
		Queue<Position> queue = new ArrayDeque<>();

		queue.add(startingPoint);
		isVisited[startingPoint.l][startingPoint.r][startingPoint.c] = true;

		while (!queue.isEmpty()) {

			Position curPosition = queue.poll();

			if (curPosition.l == exit.l && curPosition.r == exit.r && curPosition.c == exit.c) {
				return curPosition.time;
			}

			for (int dir = 0; dir < dl.length; dir++) {

				int nl = curPosition.l + dl[dir];
				int nr = curPosition.r + dr[dir];
				int nc = curPosition.c + dc[dir];

				if (outRange(nl, nr, nc) || isVisited[nl][nr][nc]) {
					continue;
				}

				if (building[nl][nr][nc] != NOT_EMPTY) {
					queue.add(new Position(nl, nr, nc, curPosition.time + 1));
					isVisited[nl][nr][nc] = true;
				}

			}

		}

		return -1;
	}

	public static boolean outRange(int l, int r, int c) {
		return l < 0 || L <= l || r < 0 || R <= r || c < 0 || C <= c;
	}

}
