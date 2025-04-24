import java.io.*;
import java.util.*;

/**
 * BOJ_어른상어
 * @author parkrootseok
 */
public class Main {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static class Shark {

		int row;
		int col;
		int direction;
		boolean isAlive;
		int[][] priority;

		public Shark(int row, int col) {
			this.row = row;
			this.col = col;
			this.isAlive = true;
			this.priority = new int[4][4];
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int K;
	static int[][] map;
	static int[][] smells;
	static int[][] owner;
	static Shark[] sharks;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		init();

		int time = 0;
		do {

			// 1. 냄새 생성
			for (int id = 1; id <= M; id++) {
				if (sharks[id].isAlive) {
					createSmell(id);
				}
			}

			// 2. 이동
			for (int id = 1; id <= M; id++) {
				if (sharks[id].isAlive) {
					move(id);
				}
			}

			// 3. 상어 생존 여부 반영
			kill();

			// 4. 냄새 제거
			deleteSmell();

			// 5. 시간 증가
			if (1000 < ++time) {
				sb.append("-1");
				break;
			}

		} while (!isFinish());

		if (sb.length() == 0) {
			sb.append(time);
		}

		bw.write(sb.toString());
		bw.close();

	}

	private static void createSmell(int id) {
		Shark shark = sharks[id];
		owner[shark.row][shark.col] = id;
		smells[shark.row][shark.col] = K;
	}

	private static void move(int id) {

		Shark shark = sharks[id];
		int r = shark.row;
		int c = shark.col;
		int[] priority = shark.priority[shark.direction];

		boolean isMoved = false;
		for (int dir = 0; dir < 4; dir++) {

			int nr = r + dr[priority[dir]];
			int nc = c + dc[priority[dir]];

			if (outRange(nr, nc)) {
				continue;
			}

			// 아무 냄새가 없는 곳이 있다면 이동
			if (smells[nr][nc] == 0) {
				isMoved = true;
				shark.row = nr;
				shark.col = nc;
				shark.direction = priority[dir];
				break;
			}

		}

		if (!isMoved) {
			for (int dir = 0; dir < 4; dir++) {

				int nr = r + dr[priority[dir]];
				int nc = c + dc[priority[dir]];

				if (outRange(nr, nc)) {
					continue;
				}

				// 자신의 냄새라면 이동
				if (owner[nr][nc] == id) {
					shark.row = nr;
					shark.col = nc;
					shark.direction = priority[dir];
					break;
				}
			}
		}

	}

	private static boolean outRange(int r, int c) {
		return r < 0 || N <= r || c < 0 || N <= c;
	}

	private static void kill() {

		int[][] map = new int[N][N];
		for (int id = 1; id <= M; id++) {
			if (sharks[id].isAlive) {
				if (map[sharks[id].row][sharks[id].col] == 0) {
					map[sharks[id].row][sharks[id].col] = id;
				} else if (id < map[sharks[id].row][sharks[id].col]) {
					sharks[map[sharks[id].row][sharks[id].col]].isAlive = false;
					map[sharks[id].row][sharks[id].col] = id;
				} else if (map[sharks[id].row][sharks[id].col] < id) {
					sharks[id].isAlive = false;
				}
 			}
		}

	}

	private static void deleteSmell() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (0 < smells[r][c]) {
					if (--smells[r][c] == 0) {
						owner[r][c] = 0;
					}
				}
			}
		}
	}

	private static boolean isFinish() {
		for (int id = 2; id <= M; id++) {
			if (sharks[id].isAlive) {
				return false;
			}
		}
		return true;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		smells = new int[N][N];
		owner = new int[N][N];

		sharks = new Shark[M + 1];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				int input = Integer.parseInt(st.nextToken());
				if (0 < input) {
					sharks[input] = new Shark(r, c);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int id = 1; id <= M; id++) {
			sharks[id].direction = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int id = 1; id <= M; id++) {
			for (int r = 0; r < 4; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < 4; c++) {
					sharks[id].priority[r][c] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
	}

	private static void print() {
		int[][] map = new int[N][N];
		for (int id = 1; id <= M; id++) {
			if (sharks[id].isAlive) {
				map[sharks[id].row][sharks[id].col] = id;
			}
		}

		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
    
}