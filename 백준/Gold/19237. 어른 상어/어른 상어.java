import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * BOJ_어른상어
 * @author parkrootseok
 */
public class Main {

	static class Shark {
		int id;
		int row;
		int col;
		int direction;
		int[][] priority;

		public Shark(int id, int row, int col) {
			this.id = id;
			this.row = row;
			this.col = col;
			this.priority = new int[5][5];
		}

		int getNextDirection(Set<Integer> candidates) {
			for (int index = 1; index <= 4; index++) {
				if (candidates.contains(priority[this.direction][index])) {
					return priority[this.direction][index];
				}
			}
			return 0;
		}

	}

	static class Smell {
		int owner;
		int second;

		public Smell(int owner, int second) {
			this.owner = owner;
			this.second = second;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int K;
	static Map<Integer, Shark> sharks;
	static int[][] grid;
	static int[][] owner;
	static int[][] times;
	static int second;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정보를 입력 받는다.
		input();

		// 2. 시뮬레이션 수행
		if (simulation()) {
			sb.append(second);
		} else {
			sb.append(-1);
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean simulation() {

		second = 0;
		while (second++ < 1000) {
			moveShark();
			decreaseSmellTime();
			createSmell();

			if (sharks.size() == 1) {
				return true;
			}
		}

		return false;

	}

	public static void moveShark() {

		// 위, 아래, 왼쪽, 오른쪽
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		Queue<Integer> outOfGridSharks = new ArrayDeque<>();

		for (int id : sharks.keySet()) {

			Shark shark = sharks.get(id);
			Set<Integer> noSmell = new HashSet<>();
			Set<Integer> mySmell = new HashSet<>();

			for (int dir = 0; dir < 4; dir++) {

				int nr = shark.row + dr[dir];
				int nc = shark.col + dc[dir];

				if (outRange(nr, nc)) {
					continue;
				}

				if (times[nr][nc] == 0) {
					noSmell.add(dir + 1);
				} else if (owner[nr][nc] == shark.id) {
					mySmell.add(dir + 1);
				}

			}

			int nextDirection = shark.getNextDirection(noSmell);
			if (nextDirection == 0) {
				nextDirection = shark.getNextDirection(mySmell);
			}

			// 상어 이동
			grid[shark.row][shark.col] = 0;
			if (nextDirection == 1) {
				shark.row -= 1;
			} else if (nextDirection == 2) {
				shark.row += 1;
			} else if (nextDirection == 3) {
				shark.col -= 1;
			} else if (nextDirection == 4) {
				shark.col += 1;
			}

			// 이동한 위치에 상어가 없거나, 자신보다 숫자가 크다면
			if (grid[shark.row][shark.col] == 0 || shark.id < grid[shark.row][shark.col]) {
				// 이동 가능
				grid[shark.row][shark.col] = shark.id;
				shark.direction = nextDirection;
			}

			// 그렇지 않다면
			else {
				// 이동 불가능
				outOfGridSharks.offer(id);
			}
		}

		while (!outOfGridSharks.isEmpty()) {
			sharks.remove(outOfGridSharks.poll());
		}

	}

	public static void decreaseSmellTime() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (times[row][col] == 0) {
					continue;
				}

				times[row][col]--;
				if (times[row][col] == 0) {
					owner[row][col] = 0;
				}
			}
		}

	}

	public static void createSmell() {
		for (Shark shark : sharks.values()) {
			owner[shark.row][shark.col] = shark.id;
			times[shark.row][shark.col] = K;
		}
	}

	public static boolean outRange(int row, int col) {
		return row < 0 || N <= row || col < 0 || N <= col;
	}

	public static void input() throws IOException {

		sharks = new HashMap<>();
		grid = new int[21][21];
		owner = new int[21][21];
		times = new int[21][21];

		// 1. N, M, K 입력
		// -> 격자 크기, 상어 갯수, 이동 횟수
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 2. 격자 정보 입력
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; col++) {
				// 2-1. 현재 입력이 0이 아닌 경우, 상어
				grid[row][col] = Integer.parseInt(st.nextToken());
				if (0 < grid[row][col]) {
					sharks.put(grid[row][col], new Shark(grid[row][col], row, col));
					owner[row][col] = grid[row][col];
					times[row][col] = K;
				}
			}
		}

		// 3. 현재 방향 정보 받기
		st = new StringTokenizer(br.readLine(), " ");
		for (int id = 0; id < M; id++) {
			sharks.get(id + 1).direction = Integer.parseInt(st.nextToken());
		}

		// 4. 우선 순위 받기
		for (int id = 0; id < M; id++) {
			Shark shark = sharks.get(id + 1);
			for (int dir = 0; dir < 4; dir++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int index = 0; index < 4; index++) {
					shark.priority[dir + 1][index + 1] = Integer.parseInt(st.nextToken());
				}
			}
		}

	}

}