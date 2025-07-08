import java.io.*;
import java.util.*;

/**
 * BOJ_Gaaaaaaaaaarden
 * @author parkrootseok
 */
public class Main {

	static class Position {

		int row;
		int col;
		int color;
		int time;

		public Position(int row, int col, int color, int time) {
			this.row = row;
			this.col = col;
			this.color = color;
			this.time = time;
		}

	}

	public static final int EMPTY = 0;
	public static final int GREEN = 1;
	public static final int RED = 2;
	public static final int FLOWER = 3;

	public static final int LAKE = 0;
	public static final int IMPOSSIBLE = 1;
	public static final int POSSIBLE = 2;

	static int[] dr = new int[] {-1, 1, 0, 0};
	static int[] dc = new int[] {0, 0, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int[][] garden;
	static int N;
	static int M;
	static int G;
	static int R;

	static List<Position> candidates;
	static int answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		garden = new int[N][M];
		candidates = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int m = 0; m < M; m++) {
				int input = Integer.parseInt(st.nextToken());
				garden[n][m] = input;
				if (garden[n][m] == POSSIBLE) {
					candidates.add(new Position(n, m, 0, 0));
				}
			}
		}

		bruteforce(0, 0, 0, new int[candidates.size()]);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bruteforce(int depth, int green, int red, int[] isSelected) {
		if (green == G && red == R) {
			answer = Math.max(answer, bfs(isSelected));
			return;
		}

		if (depth == candidates.size()) {
			return;
		}

		if (green < G) {
			isSelected[depth] = GREEN;
			bruteforce(depth + 1, green + 1, red, isSelected);
			isSelected[depth] = EMPTY;
		}

		if (red < R) {
			isSelected[depth] = RED;
			bruteforce(depth + 1, green, red + 1, isSelected);
			isSelected[depth] = EMPTY;
		}

		bruteforce(depth + 1, green, red, isSelected);
	}

	public static int bfs(int[] isSelected) {

		int[][] times = new int[N][M];
		int[][] isVisited = new int[N][M];
		Queue<Position> queue = new ArrayDeque<>();

		for (int index = 0; index < candidates.size(); index++) {
			if (isSelected[index] != EMPTY) {
				Position pos = candidates.get(index);
				queue.offer(new Position(pos.row, pos.col, isSelected[index], 0));
				isVisited[pos.row][pos.col] = isSelected[index];
			}
		}

		int count = 0;
		while (!queue.isEmpty()) {

			Position cur = queue.poll();

			if (isVisited[cur.row][cur.col] == FLOWER) {
				continue;
			}

			for (int dir = 0; dir < dr.length; dir++) {
				int nr = cur.row + dr[dir];
				int nc = cur.col + dc[dir];

				if (outRange(nr, nc) || garden[nr][nc] == LAKE || isVisited[nr][nc] == FLOWER) {
					continue;
				}

				if (isVisited[nr][nc] == EMPTY) {
					isVisited[nr][nc] = cur.color;
					times[nr][nc] = cur.time + 1;
					queue.add(new Position(nr, nc, cur.color, cur.time + 1));
				} else if (isVisited[nr][nc] == GREEN && cur.color == RED && times[nr][nc] == cur.time + 1) {
					isVisited[nr][nc] = FLOWER;
					count++;
				} else if (isVisited[nr][nc] == RED && cur.color == GREEN && times[nr][nc] == cur.time + 1) {
					isVisited[nr][nc] = FLOWER;
					count++;
				}
			}
		}

		return count;
	}

	public static boolean outRange(int row, int col) {
		return row < 0 || N <= row || col < 0 || M <= col;
	}

}
