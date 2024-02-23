import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_16926_배열돌리기
 * @author parkrootseok
 * 
 * - 2차원 배열을 반시계 방향으로 회전
 * 
 * 1. 배열의 크기와 회전할 횟수를 입력
 * 2. 배열의 정보를 입력
 * 3. 회전을 진행
 *  3-1. (start, start)의 자리에 있는 숫자를 저장
 *  3-2. 맨 위 행 좌로 이동
 *  3-3. 우측 끝 열 위로 이동
 *  3-3. 맨 아래 행 우로 이동
 *  3-4. 좌측 끝 열 아래로 이동
 *  3-5. 저장한 숫자를 (start + 1, start)에 저장
 *  
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int mapRow, mapCols;
	static int rotationNumber;
	static int[][] numbers;

	public static void rotationMap(int start) {

		//  3-1. (start,start)의 자리에 있는 숫자를 저장
		int tmp = numbers[start][start];
		
		// 3-2. 맨 위 행 좌로 이동
		// row = start, cols 이동
		for(int cols = start; cols < mapCols - 1 - start; cols++) {
			numbers[start][cols] = numbers[start][cols + 1];
		}
		
		//  3-3. 우측 끝 열 위로 이동
		// row 이동, cols = mapCols - 1 - start
		for(int row = start; row < mapRow - 1 - start; row++) {
			numbers[row][mapCols - 1 - start] = numbers[row + 1][mapCols - 1 - start];
		}
		
		//  3-3. 맨 아래 행 우로 이동
		// row = mapRow - 1 - start, cols 이동
		for(int cols = mapCols - 1 - start; start < cols; cols--) {
			numbers[mapRow - 1 - start][cols] = numbers[mapRow - 1 - start][cols - 1];
		}
		
		// 3-4. 좌측 끝 열 아래로 이동
		// row 증가, cols = start
		for(int row = mapRow - 1 - start; start < row; row--) {
			numbers[row][start] = numbers[row - 1][start];
		}
		
		//  3-5. 저장한 숫자를 (start + 1, start)에 저장
		numbers[start + 1][start] = tmp;

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 배열의 크기와 회전할 횟수를 입력
		inputs = br.readLine().trim().split(" ");
		mapRow = Integer.parseInt(inputs[0]);
		mapCols = Integer.parseInt(inputs[1]);
		rotationNumber = Integer.parseInt(inputs[2]);

		// 2. 배열의 정보를 입력
		numbers = new int[mapRow][mapCols];
		for (int row = 0; row < mapRow; row++) {

			inputs = br.readLine().trim().split(" ");

			for (int cols = 0; cols < mapCols; cols++) {

				numbers[row][cols] = Integer.parseInt(inputs[cols]);

			}

		}

		// 3. 회전을 진행
		for (int curRotation = 0; curRotation < rotationNumber; curRotation++) {
			
			for(int start = 0; start < Math.min(mapRow, mapCols) / 2; start++) {
				rotationMap(start);
			}
			
		}

		// 결과 출력
		for (int row = 0; row < mapRow; row++) {

			for (int cols = 0; cols < mapCols; cols++) {

				sb.append(numbers[row][cols]).append(" ");

			}

			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}