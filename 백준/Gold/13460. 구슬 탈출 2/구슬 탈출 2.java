import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ_구슬탈출2
 * @author parkrootseok
 */
public class Main {

	static final char WALL = '#';
	static final char HALL = 'O';

	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	public static class Bead {

		char color;
		int row;
		int col;

		public Bead(char color, int row, int col) {
			this.color = color;
			this.row = row;
			this.col = col;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int rowSize;
	static int colSize;
	static char[][] map;
	static Bead[] beads;
	static int result;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		int index = 0;
		beads = new Bead[2];
		map = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			char[] inputs = br.readLine().trim().toCharArray();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = inputs[col];
				if (map[row][col] == 'R' || map[row][col] == 'B') {
					beads[index++] = new Bead(map[row][col], row, col);
				}

			}
		}

		result = Integer.MAX_VALUE;
		bruteforce(beads, map, 0);

		sb.append(result == Integer.MAX_VALUE ? -1 : result);
		bw.write(sb.toString());
		bw.close();

	}

	public static void print(char[][] map) {

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < rowSize; col++) {
				System.out.print(map[row][col] + " ");

			}
			System.out.println();
		}

		System.out.println();

	}

	public static void bruteforce(Bead[] beads, char[][] map, int count) {

		if (10 < count) {
			return;
		}
	
		// 파란 구슬이 도착한 경우 더이상 불가
		if (!isPossible(beads, map)) {
			return;
		}
		
		// 빨간 구슬만 도착한 경우 횟수 기록
		if (check(beads, map)) {
			result = Math.min(result, count);
			return;
		}

		for (int dir = 0; dir < dr.length; dir++) {

			// 구슬 복사
			Bead[] copyBeads = new Bead[2];
			for (int index = 0; index < 2; index++) {
				copyBeads[index] = new Bead(beads[index].color, beads[index].row, beads[index].col);
			}

			// 맵 복사
			char[][] copyMap = new char[rowSize][colSize];
			for (int row = 0; row < rowSize; row++) {
				copyMap[row] = map[row].clone();
			}

			// 두 구슬을 굴린다
			if (roll(copyBeads, copyMap, dir)) {
				// 재귀 호출
				bruteforce(copyBeads, copyMap, count + 1);
			}

		}

	}

	public static boolean isPossible(Bead[] beads, char[][] map) {
		for (Bead bead : beads) {
			if (map[bead.row][bead.col] == HALL) {
				if (bead.color == 'B') {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean check(Bead[] beads, char[][] map) {
		for (Bead bead : beads) {
			if (map[bead.row][bead.col] == HALL) {
				if (bead.color == 'R') {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * 1. 동시에 같은 위치에 있을 수 없음.
	 * 2. 두 구슬은 동시에 이동.
	 */
	public static boolean roll(Bead[] copyBeads, char[][] copyMap, int direction) {

		List<Bead> sortedBeads = getSortedBeads(copyBeads, direction);

		int totalMoveCount = 0;
		while (true) {

			int moveCount = 0;
			for (Bead bead : sortedBeads) {

				// 이미 도착한 경우 이동하지 않음
				if (copyMap[bead.row][bead.col] == HALL) {
					continue;
				}

				// 이동할 위치
				int nRow = bead.row + dr[direction];
				int nCol = bead.col + dc[direction];

				// 이동 가능하면
				if (isMovable(copyMap, nRow, nCol)) {
					// 이동한 횟수 증가
					moveCount++;
					totalMoveCount++;

					// 이전 위치 초기화
					copyMap[bead.row][bead.col] = '.';

					// 위치 이동
					bead.row = nRow;
					bead.col = nCol;

					if (copyMap[bead.row][bead.col] != HALL) {
						// 현재 위치가 도착 위치가 아닌 경우에만 새로운 위치 기록
						copyMap[bead.row][bead.col] = bead.color;
					}

				}

			}

			// 두 구슬 모두, 이동하지 못한다면 끝
			if (moveCount == 0) {
				break;
			}

		}

		if (totalMoveCount == 0) {
			return false;
		}

		return true;

	}

	public static List<Bead> getSortedBeads(Bead[] beads, int direction) {

		List<Bead> sortedBeads = new ArrayList<>();

		if (direction == 0) {
			// 위로 움직일 경우
			if (beads[0].col == beads[1].col) {
				if (beads[0].row < beads[1].row) {
					sortedBeads.add(beads[0]);
					sortedBeads.add(beads[1]);
				} else {
					sortedBeads.add(beads[1]);
					sortedBeads.add(beads[0]);
				}
			}
		}

		if (direction == 1) {
			// 아래로 움직일 경우
			if (beads[0].col == beads[1].col) {
				if (beads[0].row < beads[1].row) {
					sortedBeads.add(beads[1]);
					sortedBeads.add(beads[0]);
				} else {
					sortedBeads.add(beads[0]);
					sortedBeads.add(beads[1]);
				}
			}
		}

		if (direction == 2) {
			// 좌로 움직일 경우
			if (beads[0].row == beads[1].row) {
				if (beads[0].col < beads[1].col) {
					sortedBeads.add(beads[0]);
					sortedBeads.add(beads[1]);
				} else {
					sortedBeads.add(beads[1]);
					sortedBeads.add(beads[0]);
				}
			}
		}

		if (direction == 3) {
			// 우로 움직일 경우
			if (beads[0].row == beads[1].row) {
				if (beads[0].col < beads[1].col) {
					sortedBeads.add(beads[1]);
					sortedBeads.add(beads[0]);
				} else {
					sortedBeads.add(beads[0]);
					sortedBeads.add(beads[1]);
				}
			}
		}

		// 위 경우에 해당하지 않은 경우 추가하여 반환
		if (sortedBeads.isEmpty()) {
			sortedBeads.add(beads[0]);
			sortedBeads.add(beads[1]);
		}

		return sortedBeads;

	}

	private static boolean isMovable(char[][] map, int row, int col) {

		// 범위 밖으로 나갈 경우 불가
		if (row < 0 || rowSize <= row || col < 0 || colSize <= col) {
			return false;
		}

		// 벽으로 이동 불가, 동시에 같은 위치는 불가능
		if (map[row][col] == WALL || map[row][col] == 'R' || map[row][col] == 'B') {
			return false;
		}

		return true;

	}


}