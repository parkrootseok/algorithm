import java.io.*;
import java.util.*;

/**
 * BOJ_청소년상어
 * @author parkrootseok
 */
public class Main {

	static class Fish {

		int row;
		int col;
		int direction;
		boolean isAlive;

		public Fish(int row, int col, int direction, boolean isAlive) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.isAlive = isAlive;
		}

	}

	static class Shark {

		int row;
		int col;
		int direction;
		int score;

		public Shark(int row, int col, int direction, int score) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.score = score;
		}

	}

	static final int SIZE = 4;
	static final int EMPTY = 0;
	static final int SHARK = -1;

	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static Fish[] fishes;
	static int[][] map;
	static int answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		map = new int[SIZE][SIZE];
		fishes = new Fish[(SIZE * SIZE) + 1];
		for (int r = 0; r < SIZE; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < SIZE; c++) {
				int id = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken()) - 1;
				fishes[id] = new Fish(r, c, direction, true);
				map[r][c] = id;
			}
		}

		Fish f = fishes[map[0][0]];
		f.isAlive = false;
		Shark shark = new Shark(0, 0, f.direction, map[0][0]);
		map[0][0] = SHARK;

		bruteforce(map, fishes, shark);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bruteforce(int[][] map, Fish[] fishes, Shark cShark) {

		answer = Math.max(answer, cShark.score);
		move(map, fishes);

		int cRow = cShark.row;
		int cCol = cShark.col;
		int cDir = cShark.direction;
		int cScore = cShark.score;

		for (int offset = 1; offset <= 4; offset++) {

			int nRow = cRow + (dr[cDir] * offset);
			int nCol = cCol + (dc[cDir] * offset);

			if (outRange(nRow, nCol) || map[nRow][nCol] <= EMPTY) {
				continue;
			}

			int[][] copiedMap = copy(map);
			Fish[] copiedFishes = copy(fishes);

			Fish killedFish = copiedFishes[copiedMap[nRow][nCol]];
			int nScore = cScore + copiedMap[nRow][nCol];
			copiedMap[cRow][cCol] = EMPTY;
			copiedMap[nRow][nCol] = SHARK;
			killedFish.isAlive = false;

			bruteforce(
				copiedMap,
				copiedFishes,
				new Shark(nRow, nCol, killedFish.direction, nScore)
			);

		}

	}

	public static void move(int[][] map, Fish[] fishes) {

		for (int id = 1; id < fishes.length; id++) {

			if (!fishes[id].isAlive) {
				continue;
			}

			int cDir = fishes[id].direction;
			int cRow = fishes[id].row;
			int cCol = fishes[id].col;

			for (int offset = 0; offset < dr.length; offset++) {

				int nDir = (cDir + offset) % dr.length;
				int nRow = cRow + dr[nDir];
				int nCol = cCol + dc[nDir];

				if (outRange(nRow, nCol) || map[nRow][nCol] == SHARK) {
					continue;
				}

				map[cRow][cCol] = EMPTY;
				
				if (map[nRow][nCol] != EMPTY) {
					Fish nFish = fishes[map[nRow][nCol]];
					nFish.row = cRow;
					nFish.col = cCol;
					map[cRow][cCol] = map[nRow][nCol];
				}

				map[nRow][nCol] = id;
				fishes[id].direction = nDir;
				fishes[id].row = nRow;
				fishes[id].col = nCol;

				break;

			}

		}

	}

	public static boolean outRange(int r, int c) {
		return r < 0 || SIZE <= r || c < 0 || SIZE <= c;
	}

	public static int[][] copy(int[][] map) {
		int[][] copiedMap = new int[SIZE][SIZE];
		for (int r = 0; r < SIZE; r++) {
			copiedMap[r] = map[r].clone();
		}
		return copiedMap;
	}

	public static Fish[] copy(Fish[] fishes) {
		Fish[] copiedFishes = new Fish[fishes.length];
		for (int id = 1; id < fishes.length; id++) {
			Fish f = fishes[id];
			copiedFishes[id] = new Fish(f.row, f.col, f.direction, f.isAlive);
		}
		return copiedFishes;
	}

}