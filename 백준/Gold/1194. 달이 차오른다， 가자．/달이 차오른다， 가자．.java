import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * BOJ_1194_달이차오른다,가자.
 * @author parkrootseok
 * 
 * - 미로 탈출
 *  - 벽(#) : 이동 불가
 *  - 열쇠(소문자)와 문(대문자)이 존재하고 일치하는 곳만 이동 가능
 * 
 * - 이동
 *   - 수직 또는 수평으로 한 칸
 *   
 *  - 탈출에 걸리는 이동 횟수의 최소값을 구해라
 *  
 * 1. 미로의 세로, 가로 크기를 받는다.
 * 2. 미로의 정보를 받는다.
 * 3. 미로 탈출 시작
 *  3-1.시작지점 부터 BFS 탐색 시작
 *  3-2. 탈출구에 도달했을 경우 minChance를 갱신 후 종료
 *  3-3. 4가지 방향으로 이동
 *   3-3-1. 다음 위치가 문 또는 열쇠일 때
 *    3-3-1-1. 대문자라면 열쇠 보유 여부를 확인 후 없다면 스킵
 *    3-3-1-2. 소문자라면 열쇠이므로 기록
 *  
 **/
public class Main {
	
	static class Minsik implements Comparable<Minsik> {
		
		int row;
		int col;
		int chance;
		int hasKeys;
		
		public Minsik(int hasKeys, int row, int col, int chance) {
			this.row = row;
			this.col = col;
			this.chance = chance;
			this.hasKeys = hasKeys;
		}
		
		@Override
		public int compareTo(Minsik o) {
			return Integer.compare(this.chance, o.chance);
		}
		
	}
	
	static final int KEY_NUMBER = 6;
	static final char WALL = '#';
	static final char EMPTY = '.';
			
	static int[] dr = new int[] {1, -1, 0, 0};
	static int[] dc = new int[] {0, 0, 1, -1};
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;
	
	static int rowSize, colSize;
	
	static char[][] maze;
	static int[][] dp;
	static List<int[]> exits;
	
	static int exitRow, exitCol;
	static int minChance;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		Minsik minsik = null;
		
		// 1. 미로의 세로, 가로 크기를 받는다.
		inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[0]);
		colSize = Integer.parseInt(inputs[1]);
	
		// 2. 미로의 정보를 받는다.
		exits = new ArrayList<>();
		maze = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			
			input = br.readLine().trim();
			
			for (int col = 0; col < colSize; col++) {
				
				maze[row][col] = input.charAt(col);
				
				if (maze[row][col] == '0') {
					minsik = new Minsik(0, row, col, 0);
				}
				
				if (maze[row][col] == '1') {
					exits.add(new int[] {row, col});
				}
				
			}
			
		}
		
		// 3. 미로 탈출 시작
		minChance = Integer.MAX_VALUE;
		dp = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			Arrays.fill(dp[row], Integer.MAX_VALUE);
		}
		
		exit(minsik);
		
		if (minChance == Integer.MAX_VALUE) {
			minChance = -1;
		}
		
		sb.append(minChance);
		bw.write(sb.toString());
		bw.close();
		return;
		
	}
	
	public static boolean isValid(int row, int col) {
		
		if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
			return true;
		}
		
		return false;
		
	}
	
	public static void exit(Minsik minsik) {
		
		PriorityQueue<Minsik> minsikQ = new PriorityQueue<>();
		minsikQ.add(minsik);
		
		// 3-1.시작지점 부터 BFS 탐색 시작
		boolean[][][] isVisited = new boolean[rowSize][colSize][(int) Math.pow(2, KEY_NUMBER)];
		while(!minsikQ.isEmpty()) {
			
			Minsik curMinsik = minsikQ.poll();
			int curRow = curMinsik.row;
			int curCol = curMinsik.col;
			int curChance = curMinsik.chance;
			int curHasKeys = curMinsik.hasKeys;
			
			// 3-2. 탈출구에 도달했을 경우 minChance를 갱신 후 종료
			for (int[] exit : exits) {
				
				if (curRow == exit[0] && curCol == exit[1]) {
					minChance = Math.min(minChance, curChance);
					return; 
				}
				
			}
			
			// 3-3. 4가지 방향으로 이동
			for (int dir = 0; dir < dr.length; dir++) {
				
				int nextRow = curRow + dr[dir];
				int nextCol = curCol + dc[dir];
				
				// 미로를 벗어난다면 스킵
				if (!isValid(nextRow, nextCol)) {
					continue;
				}
				
				// 벽이라면 스킵
				if (maze[nextRow][nextCol] == WALL) {
					continue;
				}
				
				if (isVisited[nextRow][nextCol][curHasKeys]) {
					continue;
				}
				
				int newHasKeys = curHasKeys;
				
				// 3-3-1. 다음 위치가 문 또는 열쇠일 때
				if (Character.isAlphabetic(maze[nextRow][nextCol])) {
					
					// 3-3-1-1. 대문자라면 열쇠 보유 여부를 확인 후 없다면 스킵
					if (Character.isUpperCase(maze[nextRow][nextCol]) && (newHasKeys & 1 << (maze[nextRow][nextCol] - 'A')) == 0) {
						continue;
					}
					
					// 3-3-1-2. 소문자라면 열쇠이므로 기록
					if (Character.isLowerCase(maze[nextRow][nextCol])) {
						newHasKeys |= (1 << maze[nextRow][nextCol] - 'a');
					}
					
				}
	
				isVisited[nextRow][nextCol][newHasKeys] = true;
				minsikQ.add(new Minsik(newHasKeys, nextRow, nextCol, curChance + 1));
				
			}
			
		}
		
	}
 
}