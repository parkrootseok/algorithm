import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_1873_상호의배틀필드
 * @author parkrootseok
 * 
 * - 배틀 필드를 진행
 * - 수행할 동작이 주어지고 주어진 동작에 따라 탱크를 작동
 * - 평지만 이동 가능하고 강철로 만든 벽은 부술수 없다
 * - 전차의 방향은 평지와 동일함
 * - 동작으로는 UP, DOWN, LEFT, RIGHT, SHOOT
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 맵의 높이와 너비를 입력 후 맵 초기화 및 탱크 생성
 * 3. 명령을 받아 수행
 */

class Solution {

	static class Tank {

		int row;
		int col;
		String direction;

		public Tank(int x, int y, String direction) {
			this.row = x;
			this.col = y;
			this.direction = direction;
		}

		public void up() {

			this.direction = UP;

			// 이동할 위치가 유효하고 움직일 수 있다면 이동
			if (inRange(this.row - 1, this.col) && isFlat(this.row - 1, this.col)) {
				
				// 이동후 평지로 변경
				map[row][col] = FLAT;
				
				// 탱크 이동
				this.row--;
				
			}

			map[row][col] = this.direction;

		}

		public void down() {

			this.direction = DOWN;
			
			// 이동할 위치가 유효하고 움직일 수 있다면 이동
			if (inRange(this.row + 1, this.col) && isFlat(this.row + 1, this.col)) {
				
				// 이동후 평지로 변경
				map[row][col] = FLAT;
				
				// 탱크 이동
				this.row++;
				
			}

			map[row][col] = this.direction;

		}

		public void left() {

			this.direction = LEFT;

			// 이동할 위치가 유효하고 움직일 수 있다면 이동
			if (inRange(this.row, this.col - 1) && isFlat(this.row, this.col - 1)) {
				
				// 이동후 평지로 변경
				map[row][col] = FLAT;
				
				// 탱크 이동
				this.col--;
				
			}

			map[row][col] = this.direction;

		}

		public void right() {

			this.direction = RIGHT;

			// 이동할 위치가 유효하고 움직일 수 있다면 이동
			if (inRange(this.row, this.col + 1) && isFlat(this.row, this.col + 1)) {
				
				// 이동후 평지로 변경
				map[row][col] = FLAT;
				
				// 탱크 이동
				this.col++;
			
			}

			map[row][col] = this.direction;

		}

		public void shoot() {

			int move = 0;

			// 위로 발사
			if (this.direction.equals(UP)) {

				int nextRow = this.row - 1;
				int nextCol = this.col;
				
				// 위치가 유효하고 철이 아니라면
				while (inRange(nextRow, nextCol) && !map[nextRow][nextCol].equals(IRON)) {
					
					// 벽돌 부수기
					if (map[nextRow][nextCol].equals(BRICK)) {
						map[nextRow][nextCol] = FLAT;
						return;
					}

					nextRow--;
				}

			} 
			
			// 아래로 발사
			else if (this.direction.equals(DOWN)) {

				int nextRow = this.row + 1;
				int nextCol = this.col;
				
				// 위치가 유효하고 철이 아니라면
				while (inRange(nextRow, nextCol) && !map[nextRow][nextCol].equals(IRON)) {
					
					// 벽돌 부수기
					if (map[nextRow][nextCol].equals(BRICK)) {
						map[nextRow][nextCol] = FLAT;
						return;
					}

					nextRow++;

				}

			} 
			
			// 좌로 발사
			else if (this.direction.equals(LEFT)) {

				int nextRow = this.row;
				int nextCol = this.col - 1;
				
				// 위치가 유효하고 철이 아니라면
				while (inRange(nextRow, nextCol) && !map[nextRow][nextCol].equals(IRON)) {

					// 벽돌 부수기
					if (map[nextRow][nextCol].equals(BRICK)) {
						map[nextRow][nextCol] = FLAT;
						return;
					}

					nextCol--;

				}
			} 
			
			// 우로 발사
			else {

				int nextRow = this.row;
				int nextCol = this.col + 1;
				
				// 위치가 유효하고 철이 아니라면
				while (inRange(nextRow, nextCol) && !map[nextRow][nextCol].equals(IRON)) {

					// 벽돌 부수기
					if (map[nextRow][nextCol].equals(BRICK)) {
						map[nextRow][nextCol] = FLAT;
						return;
					}

					nextCol++;

				}

			}

		}

		/**
		 * 위치가 유효한지 확인
		 */
		public boolean inRange(int nx, int ny) {

			if (0 <= nx && nx < mapRow && 0 <= ny && ny < mapCol) {
				return true;
			}

			return false;

		}

		/**
		 * 움직일 수 있는지 확인
		 */
		public boolean isFlat(int nx, int ny) {

			if (!map[nx][ny].equals(FLAT)) {
				return false;
			}

			return true;

		}

	}

	static int[] dx = {0, 1, 1, 1};
	static int[] dy = {1, 0, 1, -1};

	static final String FLAT = ".";
	static final String BRICK = "*";
	static final String IRON = "#";
	static final String WATER = "-";

	static final String UP = "^";
	static final String DOWN = "v";
	static final String LEFT = "<";
	static final String RIGHT = ">";

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int tcNumber;

	static int mapRow;
	static int mapCol;
	static String[][] map;

	static int length;

	static Tank tank;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine());

		for (int curTestCase = 1; curTestCase <= tcNumber; curTestCase++) {

			// 2. 맵의 높이와 너비를 입력 후 맵 초기화
			inputs = br.readLine().split(" ");
			mapRow = Integer.parseInt(inputs[0]);
			mapCol = Integer.parseInt(inputs[1]);

			map = new String[mapRow][mapCol];
			for (int curRow = 0; curRow < mapRow; curRow++) {

				inputs = br.readLine().trim().split("");

				for (int curCol = 0; curCol < mapCol; curCol++) {

					map[curRow][curCol] = inputs[curCol];

					// 탱크를 찾아 생성
					if (map[curRow][curCol].equals(UP)) {
						tank = new Tank(curRow, curCol, map[curRow][curCol]);
					} else if (map[curRow][curCol].equals(DOWN)) {
						tank = new Tank(curRow, curCol, map[curRow][curCol]);
					} else if (map[curRow][curCol].equals(LEFT)) {
						tank = new Tank(curRow, curCol, map[curRow][curCol]);
					} else if (map[curRow][curCol].equals(RIGHT)) {
						tank = new Tank(curRow, curCol, map[curRow][curCol]);
					}

				}

			}

			// 3. 명령을 받아 수행
			length = Integer.parseInt(br.readLine());
			inputs = br.readLine().trim().split("");
			for (String input : inputs) {

				switch (input) {

					case "U":
						tank.up();
						break;
					case "D":
						tank.down();
						break;
					case "L":
						tank.left();
						break;
					case "R":
						tank.right();
						break;
					case "S":
						tank.shoot();
						break;

				}

			}

			sb.append("#").append(curTestCase).append(" ");
			for (int curRow = 0; curRow < mapRow; curRow++) {

				for (int curCol = 0; curCol < mapCol; curCol++) {

					sb.append(map[curRow][curCol]);

				}

				sb.append("\n");

			}
		}

		bw.write(sb.toString());
		bw.close();

	}

}