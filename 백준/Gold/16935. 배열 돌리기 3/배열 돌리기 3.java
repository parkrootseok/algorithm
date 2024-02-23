import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;

/**
 * BOJ_16935_배열돌리기3
 * @author parkrootseok
 * 
 * - 배열에 연산을 적용
 * - 연산은 6가지
 * - 상하반전, 좌우반전, 오른쪽 90도, 왼쪽 90도
 * - 배열을 4개 그룹으로 나누어 이동 
 * 
 * 1. 배열의 크기와 연산의 수 입력
 * 2. 배열의 원소 입력
 * 3. 수행 연산 입력
 * 4. 입력된 연산 수행
 * 
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int rowSize, colSize;
	static int commandNumber;
	static int[][] numbers;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 배열의 크기와 연산의 수 입력
		inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[0]);
		colSize = Integer.parseInt(inputs[1]);
		commandNumber = Integer.parseInt(inputs[2]);

		// 2. 배열의 원소 입력
		numbers = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < colSize; col++) {
				numbers[row][col] = Integer.parseInt(inputs[col]);
			}
		}

		// 3. 수행 연산 입력
		inputs = br.readLine().trim().split(" ");
		for (int command = 0; command < commandNumber; command++) {
			
			int tmp;
			int cmd = Integer.parseInt(inputs[command]);

			// 4. 연산 수행
			switch (cmd) {
				case 1: // 상하 반전
					inversionUpDown();
					break;
				case 2: // 좌우 반전
					inversionLeftRight();
					break;
				case 3: // 오른쪽 90도 회전
					rightRotaion();
					tmp = rowSize;
					rowSize = colSize;
					colSize = tmp;
					break;
				case 4: // 왼쪽 90도 회전
					leftRotaion();
					tmp = rowSize;
					rowSize = colSize;
					colSize = tmp;
					break;
				case 5: // 4부분으로 나누어 시계방향 회전
					clockwise();
					break;
				case 6: // 4부분으로 나누어 반시계방향 회전
					couterClockwise();
					break;

			}
			
		}
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				sb.append(numbers[row][col]).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static void inversionUpDown() {

		int[][] result = new int[rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {

			// 기존 배열을 가장 위에 행부터 접근하면서 새로운 배열에 저장
			for (int col = 0; col < colSize; col++) {
				result[row][col] = numbers[rowSize - row - 1][col];
			}
		}
		
		numbers = result;

	}

	public static void inversionLeftRight() {

		int[][] result = new int[rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {

			// 기존 배열을 가장 마지막 열부터 접근하면서 저장
			for (int col = 0; col < colSize; col++) {
				result[row][col] = numbers[row][colSize - col - 1];
			}
		}

		numbers = result;

	}

	public static void rightRotaion() {
		
		int[][] result = new int[colSize][rowSize];

		for (int row = 0; row < rowSize; row++) {

			// 마지막 열의 가장 첫 행부터 저장
			for (int col = 0; col < colSize; col++) {
				result[col][rowSize - row - 1] = numbers[row][col];
			}
		}

		numbers = result;

	}

	public static void leftRotaion() {

		int[][] result = new int[colSize][rowSize];

		for (int col = 0; col < colSize; col++) {

			// 가장 마지막 행의 첫번째 열부터 저장
			for (int row = 0; row < rowSize; row++) {
				result[colSize - col - 1][row] = numbers[row][col];
			}
		}

		numbers = result;
		
	}

	public static void clockwise() {

		int[][] startPoint = {
			// 첫번째 박스 시작점과 오프셋
			{0, 0, 0, colSize >> 1},

			// 두번째 박스 시작점과 오프셋
			{0, colSize >> 1, rowSize >> 1, 0},

			// 세번째 박스 시작점과 오프셋
			{rowSize >> 1, colSize >> 1, 0, -(colSize >> 1)},

			// 네번째 박스 시작점과 오프셋
			{rowSize >> 1, 0, -(rowSize >> 1), 0}
		};

		int[][] result = new int[rowSize][colSize];

		for (int boxNumber = 0; boxNumber < 4; boxNumber++) {

			int startRow = startPoint[boxNumber][0];
			int startCol = startPoint[boxNumber][1];
			int rowOffset = startPoint[boxNumber][2];
			int colOffset = startPoint[boxNumber][3];

			for (int row = startRow; row < startRow + (rowSize >> 1); row++) {

				for (int col = startCol; col < startCol + (colSize >> 1); col++) {

					result[row + rowOffset][col + colOffset] = numbers[row][col];

				}

			}

		}

		numbers = result;

	}

	public static void couterClockwise() {

		int[][] startPoint = {
			// 첫번째 박스 시작점과 오프셋
			{0, 0, rowSize >> 1, 0},

			// 두번째 박스 시작점과 오프셋
			{0, colSize >> 1, 0, -(colSize >> 1)},

			// 세번째 박스 시작점과 오프셋
			{rowSize >> 1, colSize >> 1, -(rowSize >> 1), 0},

			// 네번째 박스 시작점과 오프셋
			{rowSize >> 1, 0, 0, colSize >> 1}
		};

		int[][] result = new int[rowSize][colSize];

		for (int boxNumber = 0; boxNumber < 4; boxNumber++) {

			int startRow = startPoint[boxNumber][0];
			int startCol = startPoint[boxNumber][1];
			int rowOffset = startPoint[boxNumber][2];
			int colOffset = startPoint[boxNumber][3];

			for (int row = startRow; row < startRow + (rowSize >> 1); row++) {

				for (int col = startCol; col < startCol + (colSize >> 1); col++) {

					result[row + rowOffset][col + colOffset] = numbers[row][col];

				}

			}

		}

		numbers = result;
	}

}