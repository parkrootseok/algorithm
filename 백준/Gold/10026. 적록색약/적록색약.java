import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/***
 * BOJ_10026_적록색약
 * @author parkrootseok
 * 
 * - 그림판에 R,G,B 픽셀이 존재
 * - 같은 색상이 상하좌우로 인접해 있다면 같은 구역(적록색약자는 R == G)
 * 1. 픽셀에 대한 크기를 받는다.
 * 2. 픽셀 R, G, B 정보를 받는다.
 * 3. 적록색약이 안닌 사람의 영역 갯수 구하기
 * 4. 녹색인 픽셀을 모두 빨간색으로 변경
 * 5. 적록색약인 사람의 영역 갯수 구하기
 */

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static char[][] pixel;
	static int pixelSize;
	static boolean[][] isVisited;

	static int redEqaulGreen;
	static int redNoneqaulGreen;

	public static boolean inRange(int row, int col) {

		if (0 <= row && row < pixelSize && 0 <= col && col < pixelSize) {
			return true;
		}

		return false;
	}

	public static void colorArea(char color, int row, int col) {

		isVisited[row][col] = true;

		// 탐색하려는 다음 위치가 범위안에 존재하고, 같은 색을 가지고 있고, 방문한 적 없다면 방문

		// 상
		if (inRange(row - 1, col) && pixel[row - 1][col] == color && !isVisited[row - 1][col]) {
			colorArea(color, row - 1, col);
		}

		// 하
		if (inRange(row + 1, col) && pixel[row + 1][col] == color && !isVisited[row + 1][col]) {
			colorArea(color, row + 1, col);
		}

		// 좌
		if (inRange(row, col - 1) && pixel[row][col - 1] == color && !isVisited[row][col - 1]) {
			colorArea(color, row, col - 1);
		}

		// 우
		if (inRange(row, col + 1) && pixel[row][col + 1] == color && !isVisited[row][col + 1]) {
			colorArea(color, row, col + 1);
		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 픽셀에 대한 크기를 받는다.
		inputs = br.readLine().trim().split(" ");
		pixelSize = Integer.parseInt(inputs[0]);

		// 2. 픽셀 R, G, B 정보를 받는다.
		pixel = new char[pixelSize][pixelSize];
		for (int curRow = 0; curRow < pixelSize; curRow++) {
			input = br.readLine().trim();
			for (int curCol = 0; curCol < pixelSize; curCol++) {
				pixel[curRow][curCol] = input.charAt(curCol);
			}
		}

		// 3. 적록색약이 안닌 사람의 영역 갯수 구하기
		redNoneqaulGreen = 0;
		isVisited = new boolean[pixelSize][pixelSize];
		for (int curRow = 0; curRow < pixelSize; curRow++) {

			for (int curCol = 0; curCol < pixelSize; curCol++) {

				// 이미 방문한 곳이라면 패스
				if (isVisited[curRow][curCol]) {
					continue;
				}
				
				colorArea(pixel[curRow][curCol], curRow, curCol);
				redNoneqaulGreen++;
				
			}

		}
		
		// 3. 녹색인 픽셀을 모두 빨간색으로 변경
		for (int curRow = 0; curRow < pixelSize; curRow++) {
			for (int curCol = 0; curCol < pixelSize; curCol++) {
				// 녹색일 경우 레드로 변경
				if(pixel[curRow][curCol] == 'G') {
					pixel[curRow][curCol] = 'R';
				}
			}
		}

		// 4. 적록색약인 사람의 영역 갯수 구하기
		redEqaulGreen = 0;
		isVisited = new boolean[pixelSize][pixelSize];
		for (int curRow = 0; curRow < pixelSize; curRow++) {

			for (int curCol = 0; curCol < pixelSize; curCol++) {

				// 이미 방문한 곳이라면 패스
				if (isVisited[curRow][curCol]) {
					continue;
				}
				
				// 녹색일 경우 레드로 변경
				if(pixel[curRow][curCol] == 'G') {
					pixel[curRow][curCol] = 'R';
				}
				
				colorArea(pixel[curRow][curCol], curRow, curCol);
				redEqaulGreen++;
				
			}

		}

		sb.append(redNoneqaulGreen).append(" ").append(redEqaulGreen);
		bw.write(sb.toString());
		bw.close();

	}

}