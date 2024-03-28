import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_3055_탈출
 * @author parkrootseok
 * 
 * - 고슴도치
 *  - 홍수를 피해 비버굴로 도망
 *  - 매 분마다 인접한 네 칸 중 하나로 이동 가능
 *  - 단, 돌은 통과할 수 없음
 *  - 물이 찰 예정인 곳도 갈 수 없음
 *  
 * - 물
 *  - 매 분마다 비어있는 칸으로 확장
 *  - 단, 돌과 비버의 굴로는 확장 불가
 * 
 * 1. 숲의 크기를 받는다.
 * 2. 숲에 대한 정보를 받는다.
 * 3. BFS를 활용하여 제일 빨리 탈출할 수 있는 시간을 찾자
 **/
public class Main {

	static class Hedgehog {

		int row;
		int col;
		int second;
		
		public Hedgehog(int row, int col, int second) {
			this.row = row;
			this.col = col;
			this.second = second;
		}

	}

	static final char EMTPY = '.';
	static final char WATER = '*';
	static final char ROCK = 'X';
	
	static int[] dr = new int[] {1, -1, 0, 0};
	static int[] dc = new int[] {0, 0, -1, 1};
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int rowSize, colSize;
	static char[][] forest;

	static Hedgehog hedgehog;
	static int exitRow, exitCol;
	static int minSecond;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 숲의 크기를 받는다.
		inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[0]);
		colSize = Integer.parseInt(inputs[1]);
		
		// 2. 숲에 대한 정보를 받는다.
		forest = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			
			input = br.readLine().trim();
			
			for (int col = 0; col < colSize; col++) {
				
				forest[row][col] = input.charAt(col);
				
				if (forest[row][col] == 'S') {
					hedgehog = new Hedgehog(row, col, 0);
				}
				
				if (forest[row][col] == 'D') {
					exitRow = row;
					exitCol = col;
				}

			}
			
		}
		
		// 3. BFS를 활용하여 제일 빨리 탈출할 수 있는 시간을 찾자
		minSecond = Integer.MAX_VALUE;
		bfs(hedgehog);
		
		if (minSecond == Integer.MAX_VALUE) {
			sb.append("KAKTUS");
		}
		
		else {
			sb.append(minSecond);
		}
		
		bw.write(sb.toString());
		bw.close();
		return;

	}
	
	public static char[][] copyOfMap() {
		
		char[][] copy = new char[rowSize][colSize];
		
		for (int row = 0; row < rowSize; row++) {
			copy[row] = forest[row].clone();
		}
		
		return copy;
		
	}

	public static boolean isValid(int row, int col) {
		
		
		if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
			return true;
		}
		
		return false;
		
	}
	
	public static boolean isMoveable(int row, int col) {
		
		// 만약 이동할 위치가 도착지라면 어떤 상황에서라도 이동이 가능
		if (forest[row][col] == 'D') {
			return true;
		}
		
		for (int dir = 0; dir < dr.length; dir++) {
			
			int nextRow = row + dr[dir];
			int nextCol = col + dc[dir];
			
			// 확인할 위치가 유효하지 않다면 스킵
			if (!isValid(nextRow, nextCol)) {
				continue;
			}
			
			if (forest[nextRow][nextCol] == WATER) {
				return false;
			}
			
		}
		
		return true;
	}
	
	public static void expandWater() {
		
		char[][] copy = copyOfMap();

		for (int row = 0; row < rowSize; row++) {
			
			for (int col = 0; col < colSize; col++) {
				
				if (forest[row][col] == WATER) {
					
					for (int dir = 0; dir < dr.length; dir++) {
				
						int nextRow = row + dr[dir];
						int nextCol = col + dc[dir];
						
						// 이동할 위치가 유효하지 않다면 스킵
						if (!isValid(nextRow, nextCol)) {
							continue;
						}
						
						// 이동할 곳이 바위나 비버 굴이라면 확장 불가
						if (forest[nextRow][nextCol] == ROCK || forest[nextRow][nextCol] == 'D') {
							continue;
						}
						
						copy[nextRow][nextCol] = WATER;
						
					}
				
				}
				
			}
				
		}
		
		// 확장한 결과를 기록
		forest = copy;
		
	}
	
	public static void bfs(Hedgehog hedgehog) {
		
		boolean[][] isVisted = new boolean[rowSize][colSize];
		Queue<Hedgehog> hedgehogQ = new ArrayDeque<>();
		hedgehogQ.add(hedgehog);
		
		while (!hedgehogQ.isEmpty()) {
			
			int moveableHedgeHogNumber = hedgehogQ.size();
				
			// 동일한 지도 상황을 유지하기 위해 같은 초에 움직일 수 있는 고슴도치들을 한 번에 이동
			for (int curHedgeHogNumber = 0; curHedgeHogNumber < moveableHedgeHogNumber; curHedgeHogNumber++) {
				
				Hedgehog curHedgehog = hedgehogQ.poll();
				int curRow = curHedgehog.row;
				int curCol = curHedgehog.col;
				int curSecond = curHedgehog.second;
				
				// 고슴도치가 비버굴에 도착했는지 확인
				if (curRow == exitRow && curCol == exitCol) {
					minSecond = curSecond;
					return;
				}
				
				// 고슴도치를 이동
				for (int dir = 0; dir < dr.length; dir++) {
					
					int nextRow = curRow + dr[dir];
					int nextCol = curCol + dc[dir];
					
					// 이동할 위치가 유효하지 않다면 스킵
					if (!isValid(nextRow, nextCol)) {
						continue;
					}
					
					// 방문한적 있다면 스킵
					if (isVisted[nextRow][nextCol]) {
						continue;
					}
					
					// 이동할 곳이 바위나 물이라면 이동 불가
					if (forest[nextRow][nextCol] == ROCK || forest[nextRow][nextCol] == WATER) {
						continue;
					}
					
					// 움직일 위치에 인접한 칸에 물이 있다면 스킵
					// 물이 있다면 찰 예정이므로 이동 불가
					if (!isMoveable(nextRow, nextCol)) {
						continue;
					}
					
					// 두 조건을 모두 만족하지 않는다면 이동 가능
					isVisted[nextRow][nextCol] = true;
					hedgehogQ.add(new Hedgehog(nextRow, nextCol, curSecond + 1));
					
				}
				
			}
			
			// N초에 움직일 수 있는 모든 고슴도치를 움직였다면 물 확장
			expandWater();

		}
		
		return;
		
	}
	
}