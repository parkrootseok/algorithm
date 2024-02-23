import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/***
 * BOJ_15683_감시
 * @author parkrootseok
 * 
 * - 5종류의 cctv가 존재하는데 각각 감시할 수 있는 방향이 다름
 * - 감시를 수행할 때 벽은 감시할 수 없는 영역
 * - cctv는 회전이 가능함(90도 회전만 가능)
 * - cctv 각도를 조절하여 사각지대의 최소 크기를 구해라
 * 
 * 1. 사무실에 대한 정보를 받는다
 *  1-1. cctv라면 cctv list에 삽입
 * 2. 완전탐색을 수행
 *   2-1. 모든 cctv를 작동시켰다면 사각지대를 카운트
 *   2-2. 모든 방향에 대하여 cctv 동작 수행
 *   2-3. 다음 cctv를 수행하기 위해 재귀 호출
 ***/

class CCTV {

	int number;
	int row;
	int col;

	CCTV(int number, int row, int col) {
		this.number = number;
		this.row = row;
		this.col = col;
	}

	// cctv 수행
	void run(int[][] office) {

	}

}

public class Main {

	// 상, 우, 하, 좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	// cctv 방향 정보에 대한 배열
	static int[][] cctvDirections = {

		{}, // 미사용
		{1}, // 1
		{1, 3}, // 2
		{0, 1}, // 3
		{0, 1, 3}, // 4
		{0, 1, 2, 3} // 5

	};

	static final int UNSAFETYZONE = 0;
	static final int SAFETYZONE = 9;
	static final int WALL = 6;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int[][] office;
	static int officeRow;
	static int officeCol;

	static List<CCTV> cctvs;
	static int minUnSafetyZoneCount;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 사무실에 대한 정보를 받는다
		inputs = br.readLine().trim().split(" ");
		officeRow = Integer.parseInt(inputs[0]);
		officeCol = Integer.parseInt(inputs[1]);

		cctvs = new ArrayList<>();
		office = new int[officeRow][officeCol];
		for (int curRow = 0; curRow < officeRow; curRow++) {

			inputs = br.readLine().trim().split(" ");

			for (int curCol = 0; curCol < officeCol; curCol++) {

				office[curRow][curCol] = Integer.parseInt(inputs[curCol]);

				//  1-1. cctv라면 cctv list에 삽입
				if (1 <= office[curRow][curCol] && office[curRow][curCol] <= 5) {
					cctvs.add(new CCTV(office[curRow][curCol], curRow, curCol));
				}

			}

		}

		// 2. 완전탐색을 수행하여 사각지대 최소 크기를 구한다.
		minUnSafetyZoneCount = Integer.MAX_VALUE;
		bruteForce(0, office);

		sb.append(minUnSafetyZoneCount).append("\n");
		bw.write(sb.toString());
		bw.close();

		return;

	}

	public static void bruteForce(int level, int[][] curOffice) {

		// 2-1. 모든 cctv를 작동시켰다면 사각지대를 카운트
		if (level == cctvs.size()) {
			minUnSafetyZoneCount = Math.min(minUnSafetyZoneCount, getUnsafetyZoneCount(curOffice));
			return;
		}

		// 2-2. 모든 방향에 대하여 cctv 동작 수행
		CCTV curCCTV = cctvs.get(level);
		for (int angle = 0; angle < 4; angle++) {

			int[][] officeTmp = copyOfOffice(curOffice);

			// 초기 방향 배열을 참고하여 각도에 따라 이동 방향을 변경한다.
//			int nextRow = curCCTV.row;
//			int nextCol = curCCTV.col;

			//  4-1. CCTV가 볼 수 있는 모든 방향을 탐색
			for (int direction : cctvDirections[curCCTV.number]) {

				int nd = (direction + angle) % 4; // 각도가 증가할 때 마다 감시하는 방향도 회전
				int nextRow = curCCTV.row;
				int nextCols = curCCTV.col;

				// 인덱스를 증가하면서 유효하면 안전지대로 변경
				while (true) {

					nextRow += dx[nd];
					nextCols += dy[nd];

					// 인덱스가 유효하고 벽이 아니라면
					if (!isValid(nextRow, nextCols)) {
						break;
					}

					if (office[nextRow][nextCols] == WALL) {
						break;
					}

					// 안전지대로 표시
					officeTmp[nextRow][nextCols] = SAFETYZONE;

				}

			}

			// 2-3. 다음 cctv를 수행하기 위해 재귀 호출
			bruteForce(level + 1, officeTmp);

		}

	}
	
	public static boolean isValid(int row, int col) {

		if (0 <= row && row < officeRow && 0 <= col && col < officeCol) {
			return true;
		}

		return false;

	}

	public static int getUnsafetyZoneCount(int[][] office) {

		int count = 0;
		for (int curRow = 0; curRow < officeRow; curRow++) {

			for (int curCol = 0; curCol < officeCol; curCol++) {

				if (office[curRow][curCol] == 0) {
					count++;
				}
			}

		}

		return count;

	}

	public static int[][] copyOfOffice(int[][] office) {

		int[][] officeTmp = new int[officeRow][officeCol];

		for (int curRow = 0; curRow < officeRow; curRow++) {

			officeTmp[curRow] = office[curRow].clone();

		}

		return officeTmp;

	}

}