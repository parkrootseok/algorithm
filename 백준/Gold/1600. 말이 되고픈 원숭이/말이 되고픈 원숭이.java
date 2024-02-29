import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_1600_말이되고픈원숭이
 * @author parkrootseok
 * 
 * - 원숭이 3마리가 말이 되고싶어
 * - 말의 움직임을 따라해
 * - 말이 움직이는 방식은 체스의 나이트 말과 같음
 * - 근데, 원숭이는 k번만 따라갈 수 있고 나머지는 인접한 칸으로만 움직일 수 있음
 * - 인접한 칸은 4방(상,하,좌,우)
 * - 어떻게 해야 시작 지점에서 최소 동작으로 (n, n)으로 갈수 있을까?
 * - 도착 지점에 도착했을 때의 최소 동작 수를 구해라
 * 
 * 1. 말처럼 움직일 수 있는 횟수를 받는다.
 * 2. 격자판에 대한 크기를 받는다.
 * 3. 격자판 정보를 받는다.
 * 4. 원숭이 이동을 시작한다.
 *  4-1. 원숭이 초기 위치를 큐에 삽입
 * 	4-2. 큐에 있는 원숭이의 위치가 도착 지점이라면 종료	
 *  4-3. 현재 원숭이 위치에서  갈 수 있는 모든 경로를 큐에 삽입  
 **/

public class Main {

	static class Monkey {

		int row;
		int col;
		int moveCount;
		int useChanceCount;

		Monkey(int row, int col, int moveNumber, int useChanceNumber) {
			this.row = row;
			this.col = col;
			this.moveCount = moveNumber;
			this.useChanceCount = useChanceNumber;
		}

	}

	static final int EMPTY = 0;
	static final int NOT_EMPTY = 1;

	static int[] dxLikeHorse = {-2, -2, 2, 2, 1, -1, 1, -1};
	static int[] dyLikeHorse = {1, -1, 1, -1, -2, -2, 2, 2};
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int chanceNumber;

	static int[][] grid;
	static int gridRow;
	static int gridCol;

	static boolean[][][] isVisited;
	static int totalMoveNumber;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 말처럼 움직일 수 있는 횟수를 받는다.
		chanceNumber = Integer.parseInt(br.readLine().trim());

		// 2. 격자판에 대한 크기를 받는다.
		inputs = br.readLine().trim().split(" ");
		gridRow = Integer.parseInt(inputs[1]);
		gridCol = Integer.parseInt(inputs[0]);

		// 3. 격자판 정보를 받는다.
		grid = new int[gridRow][gridCol];
		for (int row = 0; row < gridRow; row++) {

			inputs = br.readLine().trim().split(" ");

			for (int col = 0; col < gridCol; col++) {

				grid[row][col] = Integer.parseInt(inputs[col]);

			}

		}

		// 4. 원숭이 이동을 시작한다.
		totalMoveNumber = Integer.MAX_VALUE;
		isVisited = new boolean[chanceNumber + 1][gridRow][gridCol];

		if (gridRow == 1 && gridCol == 1) {
			totalMoveNumber = 0;
		} else {
			move();
		}

		// 5. 도착한 경우 없다면 예외 처리
		if (totalMoveNumber == Integer.MAX_VALUE) {
			totalMoveNumber = -1;
		}

		sb.append(totalMoveNumber).append("\n");
		bw.write(sb.toString());
		bw.close();

	}

	public static boolean inRange(int row, int col) {

		if (0 <= row && row < gridRow && 0 <= col && col < gridCol) {
			return true;
		}

		return false;

	}

	public static boolean isExitable(int row, int col) {

		if (row == gridRow - 1 && col == gridCol - 1) {

			return true;
		}

		return false;
	}

	public static boolean isUseableChance(int chanceCount) {

		if (chanceCount < chanceNumber) {
			return true;
		}

		return false;

	}

	
	public static void move() {

		Queue<Monkey> monkeyQ = new ArrayDeque<>();

		// 4-1. 원숭이 초기 위치를 큐에 삽입
		monkeyQ.add(new Monkey(0, 0, 0, 0));

		while (!monkeyQ.isEmpty()) {

			Monkey curMonkey = monkeyQ.poll();
		
			// 4-3. 현재 원숭이 위치에서  갈 수 있는 모든 경로를 큐에 삽입
			int row = curMonkey.row;
			int col = curMonkey.col;
			int curMoveCount = curMonkey.moveCount;
			int curChanceCount = curMonkey.useChanceCount;
			
			// 도착했다면 종료
			if (isExitable(row, col)) {
				totalMoveNumber = Math.min(totalMoveNumber, curMoveCount);
				return;
			}
			
			int nextRow, nextCol;

			// 말처럼 움직일 수 있을 때
			if (isUseableChance(curChanceCount)) {

				for (int dir = 0; dir < dxLikeHorse.length; dir++) {

					nextRow = row + dxLikeHorse[dir];
					nextCol = col + dyLikeHorse[dir];

					// 유효한 인덱스인지 확인
					if (!inRange(nextRow, nextCol)) {
						continue;
					}

					// 방해물이 있는지 확인
					if (grid[nextRow][nextCol] != EMPTY) {
						continue;
					}
					
					// 방문 확인
					if (isVisited[curChanceCount + 1][nextRow][nextCol]) {
						continue;
					}

					isVisited[curChanceCount + 1][nextRow][nextCol] = true;

					monkeyQ.add(new Monkey(nextRow, nextCol, curMonkey.moveCount + 1, curChanceCount + 1));

				}

			}

			for (int dir = 0; dir < dx.length; dir++) {

				nextRow = row + dx[dir];
				nextCol = col + dy[dir];

				// 유효한 인덱스인지 확인
				if (!inRange(nextRow, nextCol)) {
					continue;
				}
				
				// 방해물이 있는지 확인
				if (grid[nextRow][nextCol] != EMPTY) {
					continue;
				}
				
				// 방문 확인
				if (isVisited[curChanceCount][nextRow][nextCol]) {
					continue;
				}

				isVisited[curChanceCount][nextRow][nextCol] = true;
				monkeyQ.add(new Monkey(nextRow, nextCol, curMonkey.moveCount + 1, curChanceCount));

			}

		}
		
	}

}