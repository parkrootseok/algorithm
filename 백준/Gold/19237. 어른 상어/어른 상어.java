import java.io.*;
import java.util.*;

/**
 * BOJ_어른상어
 * @author parkrootseok
 */
public class Main {

	// 위, 아래, 왼쪽, 오른쪽
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정보를 입력 받는다.
		input();

		// 2. 시뮬레이션 수행
		boolean isPossible = false;
		for (int second = 1; second <= 1000; second++) {
			// 1. 냄새 수명 감소
			decreaseLifeTime();

			// 2. 상어는 현재 위치에 냄새를 남긴다.
			createSmell();

			// 3. 상어는 이동한다.
			moveShark();

			// 4. 같은 칸에 여러 상어가 있는지 확인하고, 가장 번호가 낮은 상어만 남긴다.
			dead();

			// 5. 1번 상어만 남았는지 확인
			if (!isRunnable()) {
				isPossible = true;
				sb.append(second);
				break;
			}
		}

		if (!isPossible) {
			sb.append(-1);
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void moveShark() {

		for (int m = 1; m <= M; m++) {

			// 이미 죽은 상어는 이동 불가
			if (!sharks[m].isAlive) {
				continue;
			}

			boolean isMoved = false;
			int row = sharks[m].row;
			int col = sharks[m].col;

			// 현재 방향에 따라 움직이는 방향이 변경됨
			int[] priority = sharks[m].priority[sharks[m].direction];

			// 1. 냄새가 없는 칸으로 이동
			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = row + dr[priority[dir]];
				int nCol = col + dc[priority[dir]];

				if (outRange(nRow, nCol)) {
					continue;
				}

				// 냄새가 없는 곳으로 이동
				if (Objects.isNull(smells[nRow][nCol])) {
					sharks[m].row = nRow;
					sharks[m].col = nCol;
					sharks[m].direction = priority[dir];
					isMoved = true;
					break;
				}
			}

			if (isMoved) {
				continue;
			}

			// 2. 이동하지 못했다면, 우선순위에 따라 자신의 냄새가 있는 칸으로 이동
			for (int dir = 0; dir < priority.length; dir++) {

				int nRow = row + dr[priority[dir]];
				int nCol = col + dc[priority[dir]];

				if (outRange(nRow, nCol)) {
					continue;
				}

				// 자신의 냄새인 경우 해당 위치로 이동
				if (!Objects.isNull(smells[nRow][nCol]) && smells[nRow][nCol].index == sharks[m].index) {
					sharks[m].row = nRow;
					sharks[m].col = nCol;
					sharks[m].direction = priority[dir];
					break;
				}
			}

		}

	}

	public static boolean outRange(int row, int col) {
		return row <= 0 || N < row || col <= 0 || N < col;
	}

	public static void dead() {

		Shark[][] existSharks = new Shark[N + 1][N + 1];

		for (int m = 1; m <= M; m++) {
		
			if (!sharks[m].isAlive) {
				continue;
			}
			
			int row = sharks[m].row;
			int col = sharks[m].col;

			// 현재 위치에 이미 상어가 있다면
			if (!Objects.isNull(existSharks[row][col])) {

				Shark curShark = existSharks[row][col];

				// 두 상어의 번호를 비교하여 더 큰 숫자를 가진 상어는 아웃
				if (curShark.index < sharks[m].index) {
					sharks[m].isAlive = false;
				} else {
					curShark.isAlive = false;
					existSharks[row][col] = sharks[m];
				}

			}

			// 없다면 상어를 추가
			else {
				existSharks[row][col] = sharks[m];
			}

		}

	}

	static class Smell {
		int index;
		int limit;

		public Smell(int index, int limit) {
			this.index = index;
			this.limit = limit;
		}
	}

	public static void createSmell() {
		for (int m = 1; m <= M; m++) {
			// 죽은 상어는 스킵
			if (!sharks[m].isAlive) {
				continue;
			}

			// 1. 현재 상어 위치
			int row = sharks[m].row;
			int col = sharks[m].col;

			// 2. 냄새를 남긴다.
			smells[row][col] = new Smell(m, K);
		}
	}

	public static void decreaseLifeTime() {
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				// 현재 위치에 냄새가 있다면
				if (!Objects.isNull(smells[row][col])) {
					// 1. 수명 감소
					smells[row][col].limit--;
					// 2. 남은 수명이 0이라면 삭제
					if (smells[row][col].limit == 0) {
						smells[row][col] = null;
					}
				}
			}
		}
	}

	public static boolean isRunnable() {
		for (int m = 2; m <= M; m++) {
			// 1. 1번 상외를 제외하고 하나라도 살아있는 상어가 있다면,
			if (sharks[m].isAlive) {
				// 진행 가능
				return true;
			}
		}

		// 2. 1번 상어만 살아 있다면, 진행 불가
		return false;
	}

	static class Shark {
		int index;
		int row;
		int col;
		int direction;
		boolean isAlive;
		int[][] priority;

		public Shark(int index, int row, int col) {
			this.index = index;
			this.row = row;
			this.col = col;
			this.isAlive = true;
		}
	}

	static int N;
	static int M;
	static int K;
	static Shark[] sharks;
	static Smell[][] smells;

	public static void input() throws IOException {
		// 1. N, M, K 입력
		// -> 격자 크기, 상어 갯수, 이동 횟수
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 2. 격자 정보 입력
		smells = new Smell[N + 1][N + 1];
		sharks = new Shark[M + 1];
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 1; col <= N; col++) {
				// 2-1. 현재 입력이 0이 아닌 경우, 상어
				int number = Integer.parseInt(st.nextToken());
				if (0 < number) {
					sharks[number] = new Shark(number, row, col);
				}
			}
		}

		// 3. 현재 방향 정보 받기
		st = new StringTokenizer(br.readLine(), " ");
		for (int index = 1; index <= M; index++) {
			sharks[index].direction = Integer.parseInt(st.nextToken()) - 1;
		}

		// 4. 우선 순위 받기
		for (int m = 1; m <= M; m++) {
			sharks[m].priority = new int[4][4];
			for (int dir = 0; dir < 4; dir++) {
				st = new StringTokenizer(br.readLine(), " ");
				int index = 0;
				while (st.hasMoreTokens()) {
					sharks[m].priority[dir][index++] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
	}

}
