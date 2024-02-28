import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * SWEA_1953_탈주범검거
 * @author parkrootseok
 * 
 * - 탈주범
 *  - 탈출 1시간 뒤 맨홀 뚜껑으로 도주
 *  - 연결된 터널로 도주 가능
 *  - 시간당 1의 거리를 움직임
 * - 터널 종류
 *  - 상하좌우
 *  - 상하
 *  - 좌우
 *  - 상우
 *  - 하우
 *  - 하좌
 *  - 상좌
 * - 일정 시간 뒤에 범인이 위치할 수 있는 경우의 수를 모두 골라라
 * 
 *  1. 테스트 케이스 횟수 입력
 *  2. 지하 가로, 세로 / 맨홀 위치 가로, 세로 / 탈출 후 소요 시간 입력
 *  3. 지하에 대한 정보 입력
 *  4. 이동을 시작
 *   4-1. 초기 사람의 위치를 큐에 삽입
 *   4-2. 현재 시간이 목표한 시간을 지났으면 종료
 *   4-3. 이동했던 모든 터널을 카운트
 *   4-4. 현재 터널에서 갈 수 있는 곳으로 모두 이동
 *  
 **/

public class Solution {

	static class Person {

		int row;
		int col;
		int tunnel;
		int time;

		Person(int row, int col, int tunnel, int time) {
			this.row = row;
			this.col = col;
			this.tunnel = tunnel;
			this.time = time;
		}

	}

	// 상(0), 하(1), 좌(2), 우(3)
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// 각 터널마다 이동
	static int[][] checkPositions = {

		{},
		{0, 1, 2, 3}, // 상하좌우
		{0, 1}, // 상하
		{2, 3}, // 좌우
		{0, 3}, // 상우
		{1, 3}, // 하우
		{1, 2}, // 하좌
		{0, 2}, // 상좌

	};

	// 각 방향별 이동 가능 터널
	static int[][] movableTunnels = {

		{1, 2, 5, 6}, // 상
		{1, 2, 4, 7}, // 하
		{1, 3, 4, 5}, // 좌
		{1, 3, 6, 7}, // 우

	};

	static final int EMPTY = 0;
	static final int UDLR = 1;
	static final int UD = 2;
	static final int LR = 3;
	static final int UR = 4;
	static final int DR = 5;
	static final int DL = 6;
	static final int UL = 7;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int testNumber;

	static int[][] map;
	static int rowSize;
	static int colSize;
	static int manholeRow;
	static int manholeCol;
	static int targetTime;
	static int positionNumber;

	static boolean[][] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 입력.
		testNumber = Integer.parseInt(br.readLine().trim());

		for (int curTest = 1; curTest <= testNumber; curTest++) {

			// 2. 지하 가로, 세로 / 맨홀 위치 가로, 세로 / 탈출 후 소요 시간 입력
			inputs = br.readLine().trim().split(" ");
			rowSize = Integer.parseInt(inputs[0]);
			colSize = Integer.parseInt(inputs[1]);
			manholeRow = Integer.parseInt(inputs[2]);
			manholeCol = Integer.parseInt(inputs[3]);
			targetTime = Integer.parseInt(inputs[4]);

			// 3. 지하에 대한 정보 입력
			map = new int[rowSize][colSize];
			for (int row = 0; row < rowSize; row++) {

				inputs = br.readLine().trim().split(" ");

				for (int col = 0; col < colSize; col++) {

					map[row][col] = Integer.parseInt(inputs[col]);

				}

			}

			// 4. 이동을 시작.
			isVisited = new boolean[rowSize][colSize];
			positionNumber = 0;
			move();

			sb.append("#").append(curTest).append(" ").append(positionNumber).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean inRange(int row, int col) {

		if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
			return true;
		}

		return false;

	}

	public static void move() {

		Queue<Person> personQ = new ArrayDeque<>();

		// 4-1. 초기 사람의 위치를 큐에 삽입
		// 1시간에 맨홀에 사람을 위치해 있다.
		personQ.add(new Person(manholeRow, manholeCol, map[manholeRow][manholeCol], 1));
		isVisited[manholeRow][manholeCol] = true;

		while (!personQ.isEmpty()) {

			Person person = personQ.poll();

			int row = person.row;
			int col = person.col;
			int tunnel = person.tunnel;
			int time = person.time;

			// 4-2. 현재 시간이 목표한 시간을 지났으면 종료
			if (time == targetTime + 1) {
				return;
			}

			// 4-3. 이동했던 모든 터널을 카운트
			positionNumber++;

			// 4-4. 현재 터널에서 갈 수 있는 곳으로 모두 이동
			int nextRow;
			int nextCol;
			for (int dir : checkPositions[tunnel]) {

				nextRow = row + dr[dir];
				nextCol = col + dc[dir];

				// 범위를 벗아난다면 스킵
				if (!inRange(nextRow, nextCol)) {
					continue;
				}

				// 방문한 곳이라면 스킵
				if (isVisited[nextRow][nextCol]) {
					continue;
				}

				// 이동한 곳에 터널이 존재하지 않는다면 스킵
				if (map[nextRow][nextCol] == EMPTY) {
					continue;
				}

				// 현재 조사한 방향에서 갈 수 있는 터널인지 확인
				boolean isMovable = false;
				for (int movableTunnel : movableTunnels[dir]) {
					
					if (movableTunnel == map[nextRow][nextCol]) {
						isMovable = true;
						break;
					}
					
				}
				
				if (!isMovable) {
					// 가능한 터널이 하나도 없다면 스킵
					continue;
				}

				// 위 3조건을 모두 만족하지 않는다면 이동
				// 이동할 때 현재 위치, 현재 위치에 존재하는 터널, 현재 시간 + 1을 넘겨준다.
				isVisited[nextRow][nextCol] = true;
				personQ.add(new Person(nextRow, nextCol, map[nextRow][nextCol], time + 1));

			}

		}

	}

}