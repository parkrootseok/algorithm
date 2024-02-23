import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_14503_로봇청소기
 * @author parkrootseok
 * 
 * 1. 방의 크기 입력
 * 2. 로봇 청소기 위치와 방향 입
 * 3. 방 정보 입력(0 : 벽 / 1 : 청소가 필요한 공간)
 * 4. 청소 시작
 *   4-1. 현재 칸 청소
 *   4-2. 현재 칸의 4방향 모두 청소되었는지 확인
 *   4-3. 존재하지 않으면 후진(단, 뒤쪽칸이 벽이라면 작동을 멈춘다) 
 *   4-4. 현재 칸의 4방향 중 청소가 필요한 칸이 있는 경우
 *    	4-4-1. 반시계 방향으로 90도 회전
 *    	4-4-2. 바라보는 방향이 청소되지 않았을 경우 한 칸 전진
 *     
 */

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb; 
	static String[] input;
	
	static int DIRTY = 0;
	static int WALL = 1;
	static int COMPLETE = 2;
	
	static int NORTH = 0;
	static int EAST = 1;
	static int SOUTH = 2;
	static int WEST = 3;
	
	static int N, M; // 방 크기
	static int currentRow, currentCol, currentDirection; // 현재 위치와 방향
	static int[][] room; // 방 정보
	static int cleaningCount; // 청소한 횟수
	
	public static boolean isValid(int nRow, int nCol) {
		
		if(0 <= nRow && nRow < N && 0 <= nCol && nCol < M) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public static boolean checkDirtyRoom( ) {
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		for(int index = 0; index < dx.length; index++) {
			
			int nRow = currentRow + dx[index];
			int nCol = currentCol + dy[index];
			
			if(0 <= nRow && nRow < N && 0 <= nCol && nCol < M) {
				
				if(room[nRow][nCol] == DIRTY) {
					return true;
				}
				
			}
				
		}
		
		return false;
	}
	
	public static void cleaning() {
		
		while(true) {
			
//			4-1. 현재 칸 청소
			if(room[currentRow][currentCol] == DIRTY) {
				room[currentRow][currentCol] = COMPLETE;
				cleaningCount++;
			}
			
//			4-2. 현재 칸의 4방향 모두 청소되었는지 확인
			boolean isExist = checkDirtyRoom();
			
//			4-3. 존재하지 않으면 후진(단, 뒤쪽칸이 벽이라면 작동을 멈춘다) 
			if(!isExist) {
				
				int nRow = currentRow;
				int nCol = currentCol;
				
				// 현재 바라보는 방향 뒤로 후진 
				switch(currentDirection) {
					case 0:
						nRow++;
						break;
					case 1:
						nCol--;
						break;
					case 2:
						nRow--;
						break;
					case 3:
						nCol++;
						break;
				}
				
				// 후진한 곳이 벽이라면 종료
				if(isValid(nRow, nCol)) {
					
					if(room[nRow][nCol] == WALL) {
						return;
					} else {
						currentRow = nRow;
						currentCol = nCol;
					}
					
				}
				
			} 
			
			// 4-4. 존재하면
			else {
				
				// 바라보는 방향 90도 회전
				switch(currentDirection) {
					case 0:
						currentDirection = WEST;
						break;
					case 1:
						currentDirection = NORTH;
						break;
					case 2:
						currentDirection = EAST;
						break;
					case 3:
						currentDirection = SOUTH;
						break;
				}
				
				// 바라보는 방향이 청소되지 않았을 경우 한 칸 전진
				int nRow = currentRow;
				int nCol = currentCol;
				
				switch(currentDirection) {
				case 0:
					nRow--;
					break;
				case 1:
					nCol++;
					break;
				case 2:
					nRow++;
					break;
				case 3:
					nCol--;
					break;
				}
				
				if(isValid(nRow, nCol)) {
					
					if(room[nRow][nCol] == DIRTY) {
						currentRow = nRow;
						currentCol = nCol;
					}
					
				}
				
			}
			
		}

		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
//		1. 방의 크기 입력
		input = br.readLine().trim().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
//		2. 로봇 청소기 위치와 방향 입
		input = br.readLine().trim().split(" ");
		currentRow = Integer.parseInt(input[0]);
		currentCol = Integer.parseInt(input[1]);
		currentDirection = Integer.parseInt(input[2]);
		
//		3. 방 정보 입력(0 : 벽 / 1 : 청소가 필요한 공간)
		room = new int[N][M];
		for(int row = 0 ; row < N ; row++) {
			
			input = br.readLine().trim().split(" ");
			
			for(int col = 0 ; col < M ; col++) {
				
				room[row][col] = Integer.parseInt(input[col]);
				
			}
			
		}
		
//		4. 청소 시작
		cleaningCount = 0;
		cleaning();
		
		sb.append(cleaningCount).append("\n");
		bw.write(sb.toString());
		bw.close();
		
		return;
		
	}

}
