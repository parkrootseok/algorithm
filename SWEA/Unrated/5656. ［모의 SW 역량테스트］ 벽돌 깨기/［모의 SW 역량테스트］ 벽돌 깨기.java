import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * SWEA_5656_벽돌깨기
 * @author parkrootseok
 * 
 * - 구슬을 쏘아 벽을 깨트리자
 * 	- 구슬을 쏠 수 있는 횟수는 제한
 * 	- 구슬은 항상 맨 위에 있는 벽돌만 깰 수 있고, 해당 벽돌에 상하좌우로 숫자 -1 만큼 제거
 *  - 이때 제거된 벽돌들도 마찬가지로 안에 적힌 숫자만큼 상하좌우로 벽돌을 부순다
 *  - 제일 벽돌을 많이 제거한 경우를 구하자
 *  
 * 1. 테스트 케이스 횟수 받기
 * 2. 구슬 횟수와 가로, 세로 크기를 받는다.
 * 3. 맵에 대한 정보를 받는다.
 * 4. dfs 탐색 시작
 * 5. 게임 시작
 *  5-1. 구슬 쏘기
 *  5-2. 벽돌 부수기
 *  5-3. 벽돌 이동하기
 *  5-4. 현재 상태를 유지하고 다음 구슬 쏘기
 *  5-5. 상태 복구
 * 6. 구슬을 모두 사용 후 벽돌의 개수를 구한 후 최소값 갱신
 **/
class Solution {
	
	static int[] dr = new int[] {1, -1, 0, 0};
	static int[] dc = new int[] {0, 0, -1, 1};
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int testCaseNumber;
	static int shootingNumber;
	static int rowSize, colSize;
	
	static int[][] map;
	static int[] perm;
	static int minCount;

	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 받기
		testCaseNumber = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCaseNumber; tc++) {

			// 2. 구슬 횟수와 맵에 대한 가로, 세로 크기를 받는다.
			inputs = br.readLine().trim().split(" ");
			shootingNumber = Integer.parseInt(inputs[0]);
			colSize = Integer.parseInt(inputs[1]);
			rowSize = Integer.parseInt(inputs[2]);

			// 3. 맵에 대한 정보를 받는다.
			map = new int[rowSize][colSize];
			for (int row = 0; row < rowSize; row++) {
			
				inputs = br.readLine().trim().split(" ");
				
				for (int col = 0; col < colSize; col++) {
					
					map[row][col] = Integer.parseInt(inputs[col]); 
					
				}
				
			}
			
			// 4. dfs 탐색 시작
			minCount = Integer.MAX_VALUE;
			perm = new int[shootingNumber];
			dfs(0);
			
			sb.append("#").append(tc).append(" ").append(minCount).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static int[][] copy(int[][] map) {
		
		int[][] copyMap = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {

			copyMap[row] = map[row].clone();

		}
		
		return copyMap;
	}
	
	public static boolean isValid(int row, int col) {
		
		if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
			return true;
		}
	
		return false;
		
	}
	
	public static int shoot(int col) {
		
		
		for (int row = 0; row < rowSize; row++) {
			
			// 가장 처음 만나는 벽돌의 행을 반환
			if (map[row][col] != 0) {
				return row;
			}
			
		}
		
		return rowSize - 1;
		
	}
	
	public static void breakBrick(int row, int col) {
		
		Queue<int[]> positionQ = new ArrayDeque<>();
		positionQ.add(new int[]{row, col, map[row][col]});
				
		while (!positionQ.isEmpty()) {

			int[] position = positionQ.poll();
			int curRow = position[0];
			int curCol = position[1];
			int number = position[2];

			// 4가지 방향에 사정거리 범위안에 존재하는 벽돌 부시기
			for (int dir = 0; dir < dr.length; dir++) {

				for (int range = 0; range < number; range++) {

					int nextRow = curRow + dr[dir] * range;
					int nextCol = curCol + dc[dir] * range;

					// 유효한 범위가 아니라면 스킵
					if (!isValid(nextRow, nextCol)) {
						break;
					}

					if (0 < map[nextRow][nextCol]) {

						if (map[nextRow][nextCol] > 1) {
							positionQ.add(new int[] {nextRow, nextCol, map[nextRow][nextCol]});
						}
						
						map[nextRow][nextCol] = 0;

					}
				}

			}

		}
		
	}
	
	public static void moveBrick() {

		// 모든열을 
		for (int col = 0; col < colSize; col++) {

			// 마지막 행부터 탐색해서
			for (int row = rowSize - 1; 0 <= row; row--) {
				
				// 벽돌이 없는 곳을 만난다면
				if (map[row][col] == 0) {

					int nextRow = row - 1;
					while (0 <= nextRow) {
						
						// 계속 위로 이동하여 처음에 마주치는 벽돌을 가져온다
						if (map[nextRow][col] > 0) {
							map[row][col] = map[nextRow][col];
							map[nextRow][col] = 0;
							break;
						}
						
						// 위로 이동
						nextRow--;
						
					}

				}

			}

		}
		
		return;
		
	}
	
	public static int getBrickCount() {
		
		int count = 0;
		
		for (int row = 0; row < rowSize; row++) {
				
			for (int col = 0; col < colSize; col++) {
				
				if(map[row][col] > 0) {
					count++;
				}
				
			}
			
		}
		
		return count;
		
	}
	
	public static void dfs(int level) {
		
		// 순서 생성 완료
		if (level == shootingNumber) {
			
			// 6. 구슬을 모두 사용 후 벽돌의 개수를 구한 후 최소값 갱신
			minCount = Math.min(minCount, getBrickCount());
			return;
			
		}

		// 5. 게임 시작
		int[][] copyMap = copy(map); 
		
		for (int col = 0; col < colSize; col++) {
			
			// 5-1. 구슬 쏘기
			int row = shoot(col);
			
			// 5-2. 벽돌 부수기
			breakBrick(row, col);
			
			// 5-3. 벽돌 이동하기
			moveBrick();
			
			// 5-4. 현재 상태를 유지하고 다음 구슬 쏘기
			dfs(level + 1);
			
			// 5-5. 상태 복구
			map = copy(copyMap);
			
		}
		
	}
	
}