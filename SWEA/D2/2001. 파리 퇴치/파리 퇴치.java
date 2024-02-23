import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_2001_파리퇴치
 * @author parkrootseok
 * 
 * - N by N 배열안에 파리가 존재
 * - M by M 파리채를 한 번 내리쳐 최대한 많은 파리를 사냥
 * - 가장 많이 사냥한 파리의 개수를 구하라
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 배열 크기와 파리채 크기 입력
 * 3. 파리에 대한 정보 입력
 * 4. 파리 잡기 
 * 
 */

class Solution {

	public static int kill(int x, int y) {

		int nextRow;
		int nextCols;
		int sum;

		sum = 0;
		for (int flapperRow = 0; flapperRow < flapperSize; flapperRow++) {

			for (int flapperCols = 0; flapperCols < flapperSize; flapperCols++) {

				nextRow = x + flapperRow;
				nextCols = y + flapperCols;

				if (0 <= nextRow && nextRow < mapSize && 0 <= nextCols && nextCols < mapSize) {
					sum += board[nextRow][nextCols];
				}

			}

		}

		return sum;
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int mapSize, flapperSize;
	static int tcNumber;
	static int[][] board;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 입력
		tcNumber = Integer.parseInt(br.readLine());

		for (int curTC = 1; curTC <= tcNumber; curTC++) {

			// 2. 배열 크기와 파리채 크기 입력
			inputs = br.readLine().split(" ");
			mapSize = Integer.parseInt(inputs[0]);
			flapperSize = Integer.parseInt(inputs[1]);

			// 3. 파리에 대한 정보 입력
			board = new int[mapSize][mapSize];
			for (int mapRow = 0; mapRow < mapSize; mapRow++) {
				inputs = br.readLine().split(" ");
				for (int mapCols = 0; mapCols < mapSize; mapCols++) {
					board[mapRow][mapCols] = Integer.parseInt(inputs[mapCols]);
				}
			}

			// 4. 파리 잡기
			long max = Long.MIN_VALUE;
			for (int mapRow = 0; mapRow < mapSize; mapRow++) {
				for (int mapCols = 0; mapCols < mapSize; mapCols++) {
					max = Math.max(max, kill(mapRow, mapCols));
				}
			}

			sb.append("#").append(curTC).append(" ").append(max).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}