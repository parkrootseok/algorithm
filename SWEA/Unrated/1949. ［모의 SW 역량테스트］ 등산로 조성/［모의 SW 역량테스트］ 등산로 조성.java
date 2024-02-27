import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * SWEA_1949_등산로조성
 * @author parkrootseok
 * 
 * - 최대한 긴 등산로를 만들거야
 * - 등산로는 가장 높은 봉우리에서 시작
 * - 등산로는 낮은 지형 & 가로 & 세로를 만족하는 곳으로 조성
 * - 단, 딱 한 곳을 정해서 K 깊이만큼 지형을 깎는 공사를 할 수 있음
 * 
 * -> 등산로를 조성할 때 가장 높은 봉우리에서 시작하고, 다음 이동할 봉우리는 현재 봉우리보다 낮으면서 가로 또는 세로 방향에 위치해야함.
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 지도 크기와 깍을수 있는 범위에 대해 입력을 받는다.
 * 3. 지도에 대해서 초기화를 진행한다.
 *  3-1. 봉우리 높이에 대한 최대값 기록
 * 4. 최대 높이를 가지는 봉우리에 대해서 리스트를 생성한다.
 * 5. 등산로 조성을 시작한다.	
 *  5-1. 1개의 봉우리를 최대 offset만큼 깎을 수 있으므로 모든 경우를 확인
 *   5-1-1. 현재 봉우리 높이를 curOffset 차감
 *   5-1-2. 최대 높이를 가지는 봉우리 탐색
 *    5-1-2-1. offset을 차감한 봉우리는 제외
 *   5-1-3. 차감했던 offset을 다시 더해준다.
 *  6. 현재 봉우리에서 등산로 길이를 구한다.
 **/

public class Solution {

	static class Peak {

		int row;
		int col;
		int height;

		Peak(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int tcNumber;

	static int[][] map;
	static int mapSize;

	static int offset;
	static int maxHeight;
	static List<Peak> peaks;
	static boolean[][] isVisited;
	static int maxLength;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine().trim());

		for (int curTc = 1; curTc <= tcNumber; curTc++) {

			// 2. 지도 크기와 깍을수 있는 범위에 대해 입력을 받는다.
			inputs = br.readLine().trim().split(" ");
			mapSize = Integer.parseInt(inputs[0]);
			offset = Integer.parseInt(inputs[1]);

			// 3. 지도에 대해서 초기화를 진행한다.
			maxHeight = Integer.MIN_VALUE;
			map = new int[mapSize][mapSize];
			for (int row = 0; row < mapSize; row++) {

				inputs = br.readLine().trim().split(" ");

				for (int col = 0; col < mapSize; col++) {

					map[row][col] = Integer.parseInt(inputs[col]);

					// 3-1. 봉우리 높이에 대한 최대값 기록
					if (maxHeight < map[row][col]) {
						maxHeight = map[row][col];
					}

				}
			}

			// 4. 최대 높이를 가지는 봉우리에 대해서 리스트를 생성한다.
			peaks = new ArrayList<>();
			for (int row = 0; row < mapSize; row++) {

				for (int col = 0; col < mapSize; col++) {

					if (maxHeight == map[row][col]) {
						peaks.add(new Peak(row, col, map[row][col]));
					}

				}
			}

			// 5. 등산로 조성을 시작한다.	
			isVisited = new boolean[mapSize][mapSize];
			maxLength = Integer.MIN_VALUE;
			for (int row = 0; row < mapSize; row++) {

				for (int col = 0; col < mapSize; col++) {
					
					if(map[row][col] == maxHeight) {
						getLength(1, true, map[row][col], row, col);
					}

				}

			}
			
			sb.append("#").append(curTc).append(" ").append(maxLength).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean inRange(int row, int col) {
		
		if(0 <= row && row < mapSize && 0 <= col && col < mapSize) {
			return true;
		}
		
		return false;
		
	}
 	
	public static void getLength(int level, boolean isCutable, int height, int row, int col) {
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		int nextRow;
		int nextCol;
		
		// 방문 처리
		isVisited[row][col] = true;
		
		// 4가지 방향으로 탐색을 시작
		for(int dir = 0; dir < dx.length; dir++) {
			
			nextRow = row + dx[dir];
			nextCol = col + dy[dir];
			
			// 유효한 인덱가 아니라면 스킵
			if (!inRange(nextRow, nextCol)) {
				continue;
			}
			
			// 이미 방문한 봉우리라면 스킵
			if(isVisited[nextRow][nextCol]) {
				continue;
			}
			
			// 현재 봉우리 높이보다 낮다면 바로 진행
			if (height > map[nextRow][nextCol]) {
				getLength(level + 1, isCutable, map[nextRow][nextCol], nextRow, nextCol);				
			} 
			
			// 깎을 수 있고 offset 범위 내에 존재하는 높이를 깎아서 진행할 수 있다면
			else if (isCutable && (map[nextRow][nextCol] - height) < offset) {
					
				getLength(level + 1, false, height - 1, nextRow, nextCol);
				
			}
			
		}
		
		// 방문 처리 무효
		isVisited[row][col] = false;
		
		// 최대 길이 갱신
		maxLength = Math.max(maxLength, level);
		
	}
	
}