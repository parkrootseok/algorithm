import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_17136_색종이붙이기
 * @author parkrootseok
 * 
 * - 색종이
 *  - 1by1 ~ 5by5 크기 존재
 *  - 종류마다 5개 제한
 *  - 1이 적혀있는 종이에 딱 맞게 붙이자
 * 
 * 1. 테스트 케이스 입력 
 *  1-1. 종이 정보 받기
 * 2. 완전 탐색을 통해 필요한 최소 색종이 개수를 구한다.
 *  2-1. 최소값보다 이미 사용한 색종이 개수가 크다면 종료
 *  2-2. 모든 색종이를 붙였다면 현재 사용한 색종이 개수를 비교하여 최소값 갱신
 *  2-3. 색종이를 붙일 수 없다면 다음 위치로 바로 이동
 *  2-4. 색종이를 붙일 수 있다면 5개의 색종이 중 붙일 수 있는 색종이를 붙여서 다음 위치로 이동
 * 3.색종이를 붙일 수 없는 경우 예외 처리
 **/

class Main {

	static class Position {

		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	static final int SIZE = 10;
	static int[] colorPapers = { 1, 2, 3, 4, 5 };

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static List<Position> positions;
	static int[][] papers;
	static int[] useColorPaperCounts;
	static int minColorPaperCount;

	public static void input() throws IOException {

		// 1-1. 종이 정보 받기
		positions = new ArrayList<>();
		papers = new int[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < SIZE; col++) {
				papers[row][col] = Integer.parseInt(inputs[col]);
				if (papers[row][col] == 1) {
					positions.add(new Position(row, col));
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

		// 2. 완전 탐색을 통해 필요한 최소 색종이 개수를 구한다.
		useColorPaperCounts = new int[5];

		if (positions.isEmpty()) {
			// 붙일 색종이가 하나도 없는 경우
			minColorPaperCount = 0;
		} else {
			// 붙일 색종이가 존재하는 경우
			minColorPaperCount = Integer.MAX_VALUE;
			bruteforce(0);
		}

		// 3.색종이를 붙일 수 없는 경우 예외 처리
		if (minColorPaperCount == Integer.MAX_VALUE) {
			minColorPaperCount = -1;
		}

		sb.append(minColorPaperCount);
		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static int getUseColorPaperCount(int[] colorPaperCount) {

		int totalCount = 0;
		for (int count : colorPaperCount) {
			totalCount += count;
		}

		return totalCount;

	}

	public static boolean isPossible(int row, int col, int size) {

		for (int curRow = row; curRow < row + size; curRow++) {
			for (int curCol = col; curCol < col + size; curCol++) {

				// 색종이가 종이를 벗어나면 불가
				if (!(0 <= curRow && curRow < SIZE && 0 <= curCol && curCol < SIZE)) {
					return false;
				}

				// 붙일 수 없는곳이 존재하지 하면 불가
				if (papers[curRow][curCol] == 0) {
					return false;
				}

			}

		}

		return true;

	}

	public static void bruteforce(int level) {

		int curUsedColorPaperCount = getUseColorPaperCount(useColorPaperCounts);

		// 2-1. 최소값보다 이미 사용한 색종이 개수가 크다면 종료
		if (minColorPaperCount < curUsedColorPaperCount) {
			return;
		}

		// 2-2. 모든 색종이를 붙였다면 현재 사용한 색종이 개수를 비교하여 최소값 갱신
		if (level == positions.size()) {
			minColorPaperCount = Math.min(minColorPaperCount, curUsedColorPaperCount);
			return;
		}

		Position curPosition = positions.get(level);
		int curRow = curPosition.row;
		int curCol = curPosition.col;

		// 2-3. 색종이를 붙일 수 없다면 다음 위치로 바로 이동
		if (papers[curRow][curCol] == 0) {
			bruteforce(level + 1);
		}

		// 2-4. 색종이를 붙일 수 있다면 5개의 색종이 중 붙일 수 있는 색종이를 붙여서 다음 위치로 이동
		else {

			// 붙일 수 있는 색종이를 탐색
			for (int colorPaper : colorPapers) {

				// 이미 모든 색종이를 사용했을 경우 스킵
				if (useColorPaperCounts[colorPaper - 1] == 5) {
					continue;
				}

				// 색종이를 사용할 수 있는지 확인
				if (!isPossible(curRow, curCol, colorPaper)) {
					continue;
				}

				// 해당 색종이를 사용 처리 해주고
				for (int row = curRow; row < curRow + colorPaper; row++) {
					for (int col = curCol; col < curCol + colorPaper; col++) {
						papers[row][col] = 0;
					}
				}
				useColorPaperCounts[colorPaper - 1]++;

				// 재귀 호출
				bruteforce(level + 1);

				// 재귀 호출이 모두 종료했을 경우 상태 복구
				for (int row = curRow; row < curRow + colorPaper; row++) {
					for (int col = curCol; col < curCol + colorPaper; col++) {
						papers[row][col] = 1;
					}
				}
				useColorPaperCounts[colorPaper - 1]--;

			}

		}

		return;

	}

}