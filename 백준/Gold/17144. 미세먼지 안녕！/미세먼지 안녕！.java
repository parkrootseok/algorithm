import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;

/**
 * BOJ_17144_미세먼지안녕!
 * @author parkrootseok
 * 
 * - 공기청정기
 *  - 항상 1열에 존재
 *  - 크기는 2행
 * 	- 작동
 * 	 - 위쪽 공기청정기 반시계 방향 순환
 * 	 - 아래 공기청정기 시계방향 순환
 * 
 * - 미세먼지
 *  - 인접한 네 방향으로 확산(확산되는 양은 1/5, 남은 양은 날려보낸 양을 뺀 값)
 * 	- 모든 미세먼지는 동시에 확산
 *	- 바람의 방향대로 한 칸씩 이동 
 * 
 * 1. 테스트 케이스 입력
 *  1-1. 격자의 크기와 공기청정기를 작동할 시간을 입력
 *  1-2. 격자에 대한 정보 입력
 * 2. T초 만큼 공기청정기를 가동
 * 3. 미세먼지 확산
 *  3-1. 격자 탐색 시작
 *  3-2. 공기 청정기라면 이를 이동 후 격자에 복사 후 종료
 *  3-3. 공기 청정기가 아니라면 4방향을 탐색하며 미세먼지 확산
 *  3-4. 탐색할 위치의 인덱스가 유효하고 공기 청정기가 아니라면 먼지확산
 *  3-5. 이동한 결과를 반영
 * 4. 먼지 이동
 * 5. 남은 먼지의 개수를 구한 후 출력 
 **/

class Main {
	
	static class AirCleaner {

		int[] row;
		int cols;

		public AirCleaner(int row) {
			this.row = new int[2];
			this.row[0] = row;
			this.row[1] = row + 1;
			this.cols = 0;
		}

	}

	static final int AIR_CLEANER = -1;

	static final int[] dr = {1, -1, 0, 0};
	static final int[] dc = {0, 0, 1, -1};
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int gridRow, gridCol;
	static int runningTime;
	static int remainDustCount;

	static AirCleaner airCleaner;

	static int[][] grid;
	static int[][] gridAfterMove;
	
	public static void input() throws IOException {
	
		// 1-1. 격자의 크기와 공기청정기 작동 시간 입력
		inputs = br.readLine().trim().split(" ");
		gridRow = Integer.parseInt(inputs[0]);
		gridCol = Integer.parseInt(inputs[1]);
		runningTime = Integer.parseInt(inputs[2]);
		
		// 1-2. 격자 정보 입력
		grid = new int[gridRow][gridCol];
		for (int row = 0; row < gridRow; row++) {
			
			inputs = br.readLine().trim().split(" ");
			for (int cols = 0; cols < gridCol; cols++) {

				grid[row][cols] = Integer.parseInt(inputs[cols]);
				
				// -1 입력이 들어오면 공기청정기에 대한 객체를 생성
				if (Objects.isNull(airCleaner) && grid[row][cols] == -1) {
					airCleaner = new AirCleaner(row);
				}

			}

		}
		
	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 입력
		input();
		
		// 2. T초 만큼 공기청정기를 가동
		for (int curTime = 0; curTime < runningTime; curTime++) {

			// 3. 미세먼지 확산
			spreadDust();

			// 4. 먼지 이동
			moveCounterClockWise();
			moveClockWise();

		}

		// 5. 남은 먼지의 개수를 구한 후 출력
		sb.append(getDustCount());
		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static boolean isValid(int curRow, int curCols) {

		// 인덱스 범위를 넘어가지 않고
		if (!(0 <= curRow && curRow < gridRow && 0 <= curCols && curCols < gridCol)) {
			return false;
		}

		// 다음 위치에 공기청정기가 존재하지 않으면
		if (grid[curRow][curCols] == AIR_CLEANER) {
			return false;
		}

		// 유효
		return true;

	}

	public static void spreadDust() {
		
		// 이동 결과를 기록할 격자
		gridAfterMove = new int[gridRow][gridCol];

		// 3-1. 격자 탐색 시작
		for (int row = 0; row < gridRow; row++) {

			for (int cols = 0; cols < gridCol; cols++) {

				// 3-2. 공기 청정기라면 이를 이동 후 격자에 복사 후 종료
				if (grid[row][cols] == AIR_CLEANER) {
					gridAfterMove[row][cols] = AIR_CLEANER;
					continue;
				}

				gridAfterMove[row][cols] += grid[row][cols];

				// 3-3. 공기 청정기가 아니라면 4방향을 탐색하며 미세먼지 확산
				int curRow;
				int curCol;
				for (int dir = 0; dir < dr.length; dir++) {

					// 확산을 진행할 좌표
					curRow = row + dr[dir];
					curCol = cols + dc[dir];

					// 3-4. 탐색할 위치의 인덱스가 유효하고 공기 청정기가 아니라면 먼지확산
					if (isValid(curRow, curCol)) {

						// 현재 가지고 있는 (미세먼지 / 5)만큼 확산하고
						gridAfterMove[curRow][curCol] += (grid[row][cols] / 5);

						// 확산한 만큼 제거 
						gridAfterMove[row][cols] -= (grid[row][cols] / 5);
						
					}

				}

			}

		}

		// 3-5. 이동한 결과를 반영
		grid = gridAfterMove;

	}

	/**
	 * 미세먼지 이동(반시계 방향)
	 */
	public static void moveCounterClockWise() {

		// 좌측 모서리 먼지 이동
		for (int move = airCleaner.row[0] - 1; 0 < move; move--) {
			grid[move][0] = grid[move - 1][0];

		}

		// 가장 위에 열에 존재하는 미세먼지 이동
		for (int move = 0; move < gridCol - 1; move++) {
			grid[0][move] = grid[0][move + 1];

		}

		// 우측 아래 모서리 이동
		for (int move = 0; move < airCleaner.row[0]; move++) {
			grid[move][gridCol - 1] = grid[move + 1][gridCol - 1];

		}

		// 가장 아래 열에 존재하는 미세먼지 이동
		for (int move = gridCol - 1; 1 < move; move--) {
			grid[airCleaner.row[0]][move] = grid[airCleaner.row[0]][move - 1];

		}

		// 공기가 나오는 구멍 초기화
		grid[airCleaner.row[0]][1] = 0;

	}

	/**
	 * 미세먼지 이동(시계 방향)
	 */
	public static void moveClockWise() {

		// 좌측 아래 모서리 먼지 위로 이동
		for (int move = airCleaner.row[1] + 1; move < gridRow - 1; move++) {

			grid[move][0] = grid[move + 1][0];

		}

		// 가장 아래 열에 존재하는 미세먼지 이동
		for (int move = 0; move < gridCol - 1; move++) {
			grid[gridRow - 1][move] = grid[gridRow - 1][move + 1];

		}

		grid[gridRow - 1][gridCol - 1] = 0;

		// 우측 위 모서리 아래로 이동
		for (int move = gridRow - 1; airCleaner.row[1] < move; move--) {
			grid[move][gridCol - 1] = grid[move - 1][gridCol - 1];

		}

		// 가장 위에 열에 존재하는 미세먼지 이동
		for (int move = gridCol - 1; 1 < move; move--) {
			grid[airCleaner.row[1]][move] = grid[airCleaner.row[1]][move - 1];
		}

		// 공기가 나오는 구멍 초기화
		grid[airCleaner.row[1]][1] = 0;

	}

	public static int getDustCount() {
		
		int dustCount = 0;
		
		for (int row = 0; row < gridRow; row++) {

			for (int cols = 0; cols < gridCol; cols++) {

				if (grid[row][cols] != AIR_CLEANER) {
					dustCount += grid[row][cols];
				}

			}

		}
		
		return dustCount;
		
	}

}