import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

/**
 * SWEA_2105_디저트카페
 * @author parkrootseok
 *
 * - 디저트 카페
 *  - 팔고 있는 디저트 숫자
 *  - 카페들은 대각선으로 이동 가능
 *  - 단, 대각선으로 이동할 때 마름모 모양으로 탐색
 *  - 단, 디저트 숫자가 동일한 곳으로 이동 불가능
 *  - 단, 하나의 카페만 들리는 것도 불가능
 *  - 왔던 곳도 다시가면 안됨
 * - 디저트를 가능한 많이 먹을거임
 *  - 가장 많이 먹었을 때를 구해라
 *  - 단, 디저트를 못 먹는다면 -1 출력
 *
 * -> 디저트 카페를 마름모 모양을 그리며 탐색하고 가장 많은 디저트를 먹은 횟수를 출력해라
 *
 * 1. 테스트 케이스 횟수 입력
 * 2. 테스트 케이스에 대한 정보 받기
 *  2-1. 지도 크기를 받아 디저트 카페에 대한 정보를 초기화
 * 3. 현재 위치에서 탐색을 시작
 *  3-1. 현재 상태 등록
 *  3-2. 현재 방향을 포함한 다음 방향에 대해 탐색
 *   3-2-1. 현재 방문한 카페가 3곳이고, 다음 위치가 시작 위치라면 마름모가 완성되었으므로 최대값 갱신
 *   3-2-2. 범위를 벗어나면 스킵
 *   3-2-3. 방문한 곳이라면 스킵
 *   3-2-4. 중복된 디저트 개수라면 스킵
 *   3-2-5. 다음 위치 탐색 시작
 *  3-3. 현재 상태 초기화
 **/

public class Solution {

	// 이동 순서
	// 오른쪽 아래(0) -> 왼쪽 아래(1) -> 왼쪽 위(2) -> 오른쪽 위(3)
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int testNumber;

	static int[][] map;
	static int size;

	static boolean[][] isVisited;
	static HashSet<Integer> dessert;
	static int maxDessertCount;

	public static void input() throws NumberFormatException, IOException {

		// 2-1. 지도 크기를 받아 디저트 카페에 대한 정보를 초기화
		size = Integer.parseInt(br.readLine().trim());
		map = new int[size][size];

		for (int row = 0; row < size; row++) {

			inputs = br.readLine().trim().split(" ");

			for (int col = 0; col < size; col++) {

				map[row][col] = Integer.parseInt(inputs[col]);

			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 입력.
		testNumber = Integer.parseInt(br.readLine().trim());

		for (int curTest = 1; curTest <= testNumber; curTest++) {

			// 2. 테스트 케이스에 대한 정보 받기
			input();

			maxDessertCount = Integer.MIN_VALUE;
			dessert = new HashSet<>();

			for (int row = 0; row < size; row++) {

				for (int col = 0; col < size; col++) {

					// 3. 현재 위치에서 탐색을 시작
					isVisited = new boolean[size][size];
					dessert.clear();

					isVisited[row][col] = true;
					dessert.add(map[row][col]);

					dfs(row, col, row, col, 0);

					isVisited[row][col] = false;
					dessert.remove(map[row][col]);

				}
			}

			// 4. 모든 카페에서 수행을 실패했다면 -1로 초기화
			if (maxDessertCount == Integer.MIN_VALUE) {
				maxDessertCount = -1;
			}

			sb.append("#").append(curTest).append(" ").append(maxDessertCount).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean inRange(int row, int col) {

		if (0 <= row && row < size && 0 <= col && col < size) {
			return true;
		}

		return false;

	}

	// 이동 순서 : 오른쪽 아래(0) -> 왼쪽 아래(1) -> 왼쪽 위(2) -> 오른쪽 위(3)
	public static void dfs(int startRow, int startCol, int curRow, int curCol, int direction) {

		int nextRow;
		int nextCol;

		// 3-2. 현재 방향을 포함한 다음 방향에 대해 탐색
		for (int dir = direction; dir < dr.length; dir++) {

			nextRow = curRow + dr[dir];
			nextCol = curCol + dc[dir];

			// 3-2-1. 현재 방문한 카페가 3곳이고, 다음 위치가 시작 위치라면 마름모가 완성되었으므로 최대값 갱신
			if (3 <= dessert.size() && nextRow == startRow && nextCol == startCol) {
				maxDessertCount = Math.max(maxDessertCount, dessert.size());
				return;
			}

			// 3-2-2. 범위를 벗어나면 스킵
			if (!inRange(nextRow, nextCol)) {
				continue;
			}

			// 3 - 2 - 3. 방문한 곳이라면 스킵
			if (isVisited[nextRow][nextCol]) {
				continue;
			}

			// 3-2-4. 중복된 디저트 개수라면 스킵
			if (dessert.contains(map[nextRow][nextCol])) {
				continue;
			}

			//  3-2-5. 다음 위치 탐색 시작
			isVisited[nextRow][nextCol] = true;
			dessert.add(map[nextRow][nextCol]);

			dfs(startRow, startCol, nextRow, nextCol, dir);

			// 3-3. 현재 상태 초기화
			isVisited[nextRow][nextCol] = false;
			dessert.remove(map[nextRow][nextCol]);
		}


	}

}

// 1
// 4
// 9 8 9 8
// 4 6 9 4
// 8 7 7 8
// 4 5 3 5