import java.io.*;
import java.util.*;

/**
 * BOJ_청소년상어
 * @author parkrootseok
 */
public class Main {

	static class Shark {
		int row;
		int col;
		int dir;
		int score;

		public Shark(int row, int col, int dir, int score) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.score = score;
		}
	}

	static class Fish {
		int id;
		int row;
		int col;
		int dir;
		boolean isAlive;


		public Fish(int id, int row, int col, int dir, boolean isAlive) {
			this.id = id;
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.isAlive = isAlive;
		}
	}

	public static final int SHARK = -1;
	public static final int EMPTY = 0;

	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1} ;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int SIZE = 4;
	static int answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		int[][] map = new int[SIZE][SIZE];
		Fish[] fishes = new Fish[SIZE * SIZE];
		for (int r = 0; r < SIZE; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < SIZE; c++) {
				int id = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken()) - 1;
				Fish f = new Fish(id, r, c, direction, true);
				fishes[id - 1] = f;
				map[r][c] = f.id;
			}
		}

		Fish f = fishes[map[0][0] - 1];
		f.isAlive = false;
		Shark shark = new Shark(0, 0, f.dir, f.id);
		map[0][0] = SHARK;

		bruteforce(map, shark, fishes);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bruteforce(int[][] map, Shark shark, Fish[] fishes) {

		answer = Math.max(answer, shark.score);
		moveFish(map, fishes);

		for (int offset = 1; offset <= 4; offset++) {

			int nRow = shark.row + (dr[shark.dir] * offset);
			int nCol = shark.col + (dc[shark.dir] * offset);

			if (outRange(nRow, nCol)) {
				continue;
			}

			if (map[nRow][nCol] <= EMPTY) {
				continue;
			}

			// 정보 복사
			int[][] copiedMap = copyMap(map);
			Fish[] copiedFishes = copyFish(fishes);

			// 현재 위치에 존재하는 물고기
			Fish eatFish = copiedFishes[map[nRow][nCol] - 1];

			// 상어 위치 이동
			copiedMap[shark.row][shark.col] = EMPTY;
			copiedMap[eatFish.row][eatFish.col] = SHARK;

			// 물고기 잡아먹기
			eatFish.isAlive = false;
			Shark nShark = new Shark(nRow, nCol, eatFish.dir, shark.score + eatFish.id);
			bruteforce(copiedMap, nShark, copiedFishes);

		}

	}

	public static void moveFish(int[][] map, Fish[] fishes) {

		// 작은 번호를 가진 물고기부터 이동 시작
		for (int id = 0; id < fishes.length; id++) {

			Fish fish = fishes[id];

			if (!fish.isAlive) {
				continue;
			}

			// 현재 방향에서 반시계 방향으로 증가하며 이동 가능한 곳으로 이동
			for (int offset = 0; offset < dr.length; offset++) {

				int nDir = (fish.dir + offset) % dr.length;
				int nRow = fish.row + dr[nDir];
				int nCol = fish.col + dc[nDir];

				if (outRange(nRow, nCol)) {
					continue;
				}

				if (map[nRow][nCol] == SHARK) {
					continue;
				}

				// 현재 위치 초기화
				map[fish.row][fish.col] = EMPTY;

				if (map[nRow][nCol] != EMPTY) {
					// 이동할 위치에 물고기가 존재할 경우 위치 교환
					Fish temp = fishes[map[nRow][nCol] - 1];
					temp.row = fish.row;
					temp.col = fish.col;
					map[fish.row][fish.col] = temp.id;
				}

				fish.row = nRow;
				fish.col = nCol;
				map[nRow][nCol] = fish.id;
				fish.dir = nDir;

				// 이동 완료 후 탈출
				break;

			}

		}

	}

	public static int[][] copyMap(int[][] map) {
		int[][] copyMap = new int[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			copyMap[row] = map[row].clone();
		}
		return copyMap;
	}

	public static Fish[] copyFish(Fish[] fishes) {
		Fish[] copyFishes = new Fish[fishes.length];
		for (int id = 0; id < fishes.length; id++) {
			Fish f = fishes[id];
			copyFishes[f.id - 1] = new Fish(f.id, f.row, f.col, f.dir, f.isAlive);
		}
		return copyFishes;
	}

	public static boolean outRange(int r, int c) {
		return r < 0 || SIZE <= r || c < 0 || SIZE <= c;
	}

}
