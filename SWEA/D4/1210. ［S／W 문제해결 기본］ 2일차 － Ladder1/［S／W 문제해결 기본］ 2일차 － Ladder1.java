import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_1210_Ladder1
 * @author parkrootseok
 * 
 * - 사다리 게임을 통해 누가 아이스크림 살래?
 * - 누가 걸리는지 알고싶음
 * - 아래로 내려가는데 좌,우 이동이 가능하면 방향 전환
 * - 방향 전환했으면 반드시 아래로
 * - 바닥에 도착하면 스탑
 * - 0 : 평면 / 1 : 사다리 / 2 : 도착지점
 * 
 * 1. 주어질 테스트 케이스에 대한 수를 입력 받는다.
 * 2. 사다리 맵에 대한 정보를 받는다.
 * 3. 사다리 게임을 시작
 *  3-1. 좌,우 방향에 사다리가 있는지 확인 후 이동
 *  3-2. 아래로 내려간다.
 *  
 *  
 * 
 */

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuffer sb;
	static String[] inputs;

	static final String PLANE = "0";
	static final String LADDER = "1";
	static final String DESTINATION = "2";
	static int MAP_SIZE = 100;

	static int tcNumber;
	static String[][] map;

	public static int run(int cols) {

		int row, lCols, rCols;

		row = 0;
		while (row < MAP_SIZE - 1) {


			// 3-1. 좌,우 방향에 사다리가 있는지 확인 후 이동
			lCols = cols - 1;
			rCols = cols + 1;

			// 좌측에 사다리가 존재하면
			if (0 < lCols && map[row][lCols].equals(LADDER)) {

				// 사다리가 존재할 때 까지 이동
				while (0 <= lCols && map[row][lCols].equals(LADDER)) {
					lCols--;
				}

				cols = lCols + 1;
				row++;

				// 만약, 좌측 사다리로 이동했다면 우측 사다리 존재 여부는 확인하지 않는다.
				continue;

			}

			// 우측에 사다리가 존재하면 이동
			if (rCols < MAP_SIZE && map[row][rCols].equals(LADDER)) {

				while (rCols < MAP_SIZE && map[row][rCols].equals(LADDER)) {
					// 사다리가 존재할 때 까지 이동
					rCols++;
				}

				cols = rCols - 1;

			}

			// 3-2. 아래로 내려간다.
			row++;
			
		}

		return cols;

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuffer();

		
		for(int curTC = 1; curTC <= 10; curTC++) {
			
			// 1. 주어질 테스트 케이스에 대한 수를 입력 받는다.
			tcNumber = Integer.parseInt(br.readLine().trim());

			// 2. 사다리 맵에 대한 정보를 받는다.
			map = new String[MAP_SIZE][MAP_SIZE];
			for (int row = 0; row < MAP_SIZE; row++) {
				inputs = br.readLine().trim().split(" ");
				for (int cols = 0; cols < MAP_SIZE; cols++) {
					map[row][cols] = inputs[cols];
				}
			}

			// 3. 사다리 게임을 시작
			for (int cols = 0; cols < MAP_SIZE; cols++) {
				
				if(map[0][cols].equals(LADDER)) {
					if (map[MAP_SIZE - 1][run(cols)].equals(DESTINATION)) {
						sb.append("#").append(curTC).append(" ").append(cols).append("\n");
						break;
					}
				}
				
			}
		}
	

		bw.write(sb.toString());
		bw.close();
		return;

	}

}