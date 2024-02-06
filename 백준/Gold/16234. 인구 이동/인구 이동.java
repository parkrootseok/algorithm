import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_16234_인구이동
 * @author parkrootseok
 * 
 * - 인구 이동 할거임
 * - 현재 나라와 맞닿은 나라의 인구 차이가 일정 범위 안에 있으면 이동
 * - 국경선이 열린 N개의 국가는 연합을 이룸
 * - N개 국가의 인구수는 연합 인구수 / N
 *
 * 1. 땅의 크기와 하한과 상한을 입력
 * 2. 땅 정보 입력
 * 3. 연합 만들기 진행
 *  3-1. 현재 국가와 연합할 수 있는 나라를 탐색
 *  3-2. 인구 이동
 * 4. 인구 이동이 일어나지 않았다면 종료
 * 5. 일자 증가
 * 
 */

class Main {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int groundSize;
	static int lowerBound, upperBound;

	static int[][] ground;
	static boolean isMove;
	static boolean[][] isVisited;

	public static boolean isValidIndex(int row, int cols) {

		if (!(0 <= row && row < groundSize && 0 <= cols && cols < groundSize)) {
			return false;
		}

		return true;
	}

	public static boolean isPossible(int country1, int country2) {

		int diff = Math.abs(country1 - country2);

		if (!(lowerBound <= diff && diff <= upperBound)) {
			return false;
		}

		return true;

	}

	public static void union(int row, int cols) {

		Queue<int[]> union = new ArrayDeque<>();
		List<int[]> unionCountry = new ArrayList<>();
		int nextRow, nextCols;

		union.add(new int[] {row, cols});
		unionCountry.add(new int[] {row, cols});
		isVisited[row][cols] = true;

		// 4방 탐색을 해서 국경이 열리는 나라를 찾아서 큐에 삽입
		while (!union.isEmpty()) {

			int[] curCountry = union.poll();

			// 3-1. 현재 국가와 연합할 수 있는 나라를 탐색
			for (int idx = 0; idx < dx.length; idx++) {

				nextRow = curCountry[0] + dx[idx];
				nextCols = curCountry[1] + dy[idx];

				// 인덱스가 유효하고 
				if (!isValidIndex(nextRow, nextCols)) {
					continue;
				}

				// 두 국가의 인구 차이가 유효한 범위내이고
				if (!isPossible(ground[curCountry[0]][curCountry[1]], ground[nextRow][nextCols])) {
					continue;
				}

				// 방문하지 않은 국가라면
				if (isVisited[nextRow][nextCols]) {
					continue;
				}

				// 큐에 추가 및 연합에 포함
				isMove = true;
				isVisited[nextRow][nextCols] = true;
				union.add(new int[] {nextRow, nextCols});
				unionCountry.add(new int[] {nextRow, nextCols});

			}

		}

		// 3-2. 인구 이동
		int totalUnionPerson = 0;
		for (int[] country : unionCountry) {
			totalUnionPerson += ground[country[0]][country[1]];
		}

		for (int[] country : unionCountry) {
			ground[country[0]][country[1]] = totalUnionPerson / unionCountry.size();
		}

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 땅의 크기와 하한과 상한을 입력
		inputs = br.readLine().trim().split(" ");
		groundSize = Integer.parseInt(inputs[0]);
		lowerBound = Integer.parseInt(inputs[1]);
		upperBound = Integer.parseInt(inputs[2]);

		// 2. 땅 정보 입력
		ground = new int[groundSize][groundSize];
		for (int row = 0; row < groundSize; row++) {

			inputs = br.readLine().trim().split(" ");

			for (int cols = 0; cols < groundSize; cols++) {

				ground[row][cols] = Integer.parseInt(inputs[cols]);

			}

		}

		int day = 0;
		while (true) {

			isMove = false;
			isVisited = new boolean[groundSize][groundSize];

			for (int row = 0; row < groundSize; row++) {

				for (int cols = 0; cols < groundSize; cols++) {

					// 3. 연합 만들기를 진행
					if (!isVisited[row][cols]) {
						union(row, cols);
					}

				}

			}

			// 4. 인구 이동이 일어나지 않았다면 종료
			if (!isMove) {
				break;
			}

			// 5. 일자 증가
			day++;

		}

		sb.append(day).append("\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}

}