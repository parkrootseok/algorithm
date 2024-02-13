import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/***
 * BOJ_17410_이차원 배열과 연산
 * @author parkrootseok
 * 
 * - 1초 마다 배열에 연산을 적용
 * - 연산은 2가지 종류가 존재
 *  - R연산 : 모든 행에 대해서 정렬 수행(단, 행의 개수가 더 클 경우)
 *  - C연산 : 모든 열에 대해서 정렬 수행(단, 열의 개수가 더 클 경우)
 * - 정렬의 결과는 등장한 수와 등장한 횟수(등장 횟수 오름차순 여러가지면 수 오름차순)
 * - 정렬된 결과를 넣으면 행or열 크기 변경(100을 넘어가면 처음 100개를 제외한 나머지는 버린다)
 * - 정렬을 수행한 결과에서 (r, c)에 존재하는 원소가 k가 되기 위한 최소 시간은?(단, 100초가 넘는다면 -1)
 * 
 * 1. r, c, k를 입력
 * 2. 배열에 대한 정보 입력
 * 3. 100초 동안 배열에 대한 연산을 수행
 *  3-1. (r, c) 자리에 목표 숫자가 있다면 종료
 *  3-2. 행의 크기가 더 크다면 R연산 수행
 *   3-2-1. 행(열)을 탐색하여 배열에 존재하는 숫자에 대한 카운팅
 *   3-2-2. 카운팅한 정보를 리스트에 추가하여 횟수에 대한 오름차순 정렬(만약 동일한 횟수가 존재하다면 숫자에 대한 오름차순 정렬)
 *   3-2-3. 정렬을 마친 숫자와 숫자에 대한 카운팅 정보를 배열에 추가
 *   3-2-4. 계산한 최대 열의 크기가 100을 넘어간다면 100으로 초기화
 *   3-2-5. 기존 배열과 교체
 *  3-3. 열의 크기가 더 크다면 C연산 수행
 *   
 */

public class Main {

	static class NumberCount implements Comparable<NumberCount> {

		int number;
		int count;

		NumberCount(int number, int count) {
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(NumberCount o) {
			return this.count == o.count ? this.number - o.number : this.count - o.count;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static final int LIMIT = 100;

	static int row;
	static int col;

	static int targetRow;
	static int targetCol;
	static int target;
	static int[][] map;

	public static void operationR() {

		int maxColSize = 0;
		int[][] newMap = new int[100][100];
		Map<Integer, Integer> numberCountMap;

		// 배열 탐색 시작
		for (int curRow = 0; curRow < row; curRow++) {

			List<NumberCount> numberCountList = new ArrayList<>();
			numberCountMap = new HashMap<>();

			// 3-2-1. 행을 탐색하여 배열에 존재하는 숫자에 대한 카운팅
			for (int curCol = 0; curCol < col; curCol++) {

				int curNumber = map[curRow][curCol];

				if (curNumber != 0) {
					numberCountMap.put(curNumber, numberCountMap.getOrDefault(curNumber, 0) + 1);
				}

			}

			// 카운팅한 정보를 리스트에 추가하여 횟수에 대한 오름차순 정렬(만약 동일한 횟수가 존재하다면 숫자에 대한 오름차순 정렬)
			for (Entry<Integer, Integer> entries : numberCountMap.entrySet()) {
				numberCountList.add(new NumberCount(entries.getKey(), entries.getValue()));
			}

			Collections.sort(numberCountList);
			maxColSize = Math.max(maxColSize, numberCountList.size() * 2);

			// 정렬을 마친 숫자와 숫자에 대한 카운팅 정보를 배열에 추가
			for (int index = 0; index < numberCountList.size(); index++) {

				// 행 또는 열의 크기가 100을 넘어간다면 종료
				if (index >= 50) {
					break;
				}

				newMap[curRow][2 * index] = numberCountList.get(index).number;
				newMap[curRow][2 * index + 1] = numberCountList.get(index).count;
			}

		}

		// 만약, 계산한 최대 열의 크기가 100을 넘어간다면 100으로 초기화
		col = Math.min(100, maxColSize);

		// 기존 배열과 교체
		map = newMap;

	}

	public static void operationC() {

		int maxRowSize = 0;
		int[][] newMap = new int[100][100];
		Map<Integer, Integer> numberCountMap;

		// 숫자와 등장 횟수를 계산
		for (int curCol = 0; curCol < col; curCol++) {

			List<NumberCount> numberCountList = new ArrayList<>();
			numberCountMap = new HashMap<>();

			for (int curRow = 0; curRow < row; curRow++) {

				int curNumber = map[curRow][curCol];

				if (curNumber != 0) {
					numberCountMap.put(curNumber, numberCountMap.getOrDefault(curNumber, 0) + 1);
				}

			}

			for (Entry<Integer, Integer> entries : numberCountMap.entrySet()) {
				numberCountList.add(new NumberCount(entries.getKey(), entries.getValue()));
			}

			Collections.sort(numberCountList);
			maxRowSize = Math.max(maxRowSize, numberCountList.size() * 2);

			for (int index = 0; index < numberCountList.size(); index++) {

				// 행 또는 열의 크기가 100을 넘어간다면 종료
				if (index >= 50) {
					break;
				}

				newMap[2 * index][curCol] = numberCountList.get(index).number;
				newMap[2 * index + 1][curCol] = numberCountList.get(index).count;
			}

		}

		row = Math.min(100, maxRowSize);

		map = newMap;

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. r, c, k를 입력
		inputs = br.readLine().trim().split(" ");
		targetRow = Integer.parseInt(inputs[0]) - 1;
		targetCol = Integer.parseInt(inputs[1]) - 1;
		target = Integer.parseInt(inputs[2]);

		// 2. 배열에 대한 정보 입력
		map = new int[100][100];
		row = 3;
		col = 3;
		for (int curRow = 0; curRow < row; curRow++) {
			inputs = br.readLine().trim().split(" ");
			for (int curCol = 0; curCol < col; curCol++) {
				map[curRow][curCol] = Integer.parseInt(inputs[curCol]);
			}

		}

		// 3. 100초 동안 배열에 대한 연산을 수행
		int curSecond = 0;
		for (; curSecond <= LIMIT; curSecond++) {

			// 3-1. (r, c) 자리에 목표 숫자가 있다면 종료
			if (map[targetRow][targetCol] == target) {
				break;
			}

			// 3-2. 행의 크기가 더 크다면 R연산 수행
			if (row >= col) {
				operationR();
			}

			// 3-3. 열의 크기가 더 크다면 C연산 수행
			else {
				operationC();
			}

		}

		// 4. 100번 수행후에도 타겟 위치에 타겟 넘버를 찾지 못한다면 -1 출력
		if (curSecond == 101) {
			curSecond = -1;
		}

		sb.append(curSecond);
		bw.write(sb.toString());
		bw.close();

	}

}