import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * BOJ_15683_감시
 * @author parkrootseok
 * 
 * - 사무실엔 K개의 CCTV 존재
 * - CCTV엔 5종류가 있음(CCTV마다 감시 방향이 다름)
 * - CCTV는 회전이 가능함(90도)
 * - CCTV 방향을 회전해서 사각 지대 크기의 최소를 구해라
 * 
 * 1. 사무실의 크기와 CCTV의 개수를 받음
 * 2. 사무실의 정보를 받음
 *  2-1. CCTV이면 CCTV리스트에 추가
 * 3. DFS
 * 4. 4가지 각도에 따른
 *  4-1. CCTV가 볼 수 있는 모든 방향을 탐색
 * 5. 최종 사각지대 개수를 구하고 최소값으로 초기화
 * 
 */

class Main {

	static class CCTV {

		int index;
		int row;
		int cols;

		public CCTV(int index, int row, int cols) {
			this.index = index;
			this.row = row;
			this.cols = cols;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static final int SAFE = 7;
	static final int EMPTY = 0;
	static final int WALL = 6;

	// 위, 오, 아, 왼
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	// cctv 방향에 대한 정보
	static int[][] cctvDirection = {
		{},
		{1},
		{1, 3},
		{0, 1},
		{0, 1, 3},
		{0, 1, 2, 3},
	};

	static int officeRow;
	static int officeCols;
	static int[][] office;

	static int minSafeField;
	static List<CCTV> cctvs;

	public static int[][] officeCopy(int[][] office) {

		int[][] officeTmp = new int[officeRow][officeCols];

		for (int row = 0; row < officeRow; row++) {
			officeTmp[row] = office[row].clone();
		}

		return officeTmp;
	}

	public static int getSafeFieldCount(int[][] office) {

		int count = 0;

		for (int row = 0; row < officeRow; row++) {

			for (int cols = 0; cols < officeCols; cols++) {

				if (office[row][cols] == EMPTY) {
					count++;
				}

			}

		}

		return count;

	}

	public static boolean isValid(int row, int cols) {

		if (0 <= row && row < officeRow && 0 <= cols && cols < officeCols) {
			return true;
		}


		return false;

	}

	public static void dfs(int cctvCount, int[][] office) {

		// 조합이 완성되었다면
		if (cctvCount == cctvs.size()) {
			// 5. 최종 사각지대 개수를 구하고 최소값으로 초기화
			minSafeField = Math.min(minSafeField, getSafeFieldCount(office));
			return;
		}

		 // 4. 4가지 각도에 따른
		CCTV curCCTV = cctvs.get(cctvCount);

		for (int angle = 0; angle < 4; angle++) {

			int[][] officeTmp = officeCopy(office);

			 //  4-1. CCTV가 볼 수 있는 모든 방향을 탐색
			for (int direction : cctvDirection[curCCTV.index]) {
				
				int nd = (direction + angle) % 4;	// 각도가 증가할 때 마다 감시하는 방향도 회전
				int nextRow = curCCTV.row;
				int nextCols = curCCTV.cols;

				// 인덱스를 증가하면서 유효하면 안전지대로 변경
				while (true) {

					nextRow += dr[nd];
					nextCols += dc[nd];

					// 인덱스가 유효하고 벽이 아니라면
					if (!isValid(nextRow, nextCols)) {
						break;
					}
					
					if (office[nextRow][nextCols] == WALL) {
						break;
					}

					
					// 안전지대로 표시
					officeTmp[nextRow][nextCols] = SAFE;

				}

			}
			
			// 안전지대로 표시한 office를 넘겨 다른 CCTV에 대한 DFS 진행
			dfs(cctvCount + 1, officeTmp);

		}

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 사무실의 크기와 CCTV의 개수를 받음
		inputs = br.readLine().trim().split(" ");
		officeRow = Integer.parseInt(inputs[0]);
		officeCols = Integer.parseInt(inputs[1]);

		// 2. 사무실의 정보를 받음 
		office = new int[officeRow][officeCols];
		cctvs = new LinkedList<>();
		for (int row = 0; row < officeRow; row++) {

			inputs = br.readLine().trim().split(" ");
			for (int cols = 0; cols < officeCols; cols++) {

				int input = Integer.parseInt(inputs[cols]);

				// CCTV라면 목록에 추가
				if (input != WALL && input != EMPTY) {
					cctvs.add(new CCTV(input, row, cols));
				}

				office[row][cols] = input;

			}

		}

		// 3. DFS
		minSafeField = Integer.MAX_VALUE;
		dfs(0, office);

		sb.append(minSafeField).append("\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}

}