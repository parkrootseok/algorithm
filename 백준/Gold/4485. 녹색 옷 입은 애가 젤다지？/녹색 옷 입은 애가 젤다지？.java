import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * BOJ_4485_녹색옷입은애가젤다지?
 * @author parkrootseok
 * 
 * 1. 동굴의 크기를 받는다.
 *  1-1. 동굴의 크기가 0이면 종료
 * 2. 동굴의 정보를 받는다.
 * 3. 링크를 움직여 동굴을 탈출한다.
 *  3-1. 시작지점 부터 BFS 탐색 시작
 *  3-2. 끝에 도달했을 경우 minLoss를 갱신
 *  3-3. 4가지 방향으로 이동
 *   3-3-1. 동굴을 벗어난 경우 스킵
 *   3-3-2. 다음 위치에서 손실이 최소일 경우에만 이동
 **/
public class Main {
	
	static class Link implements Comparable<Link> {
		
		int row;
		int col;
		int loss;
		
		public Link(int row, int col, int loss) {
			this.row = row;
			this.col = col;
			this.loss = loss;
		}
		
		@Override
		public int compareTo(Link o) {
			return Integer.compare(this.loss, o.loss);
		}
		
	}
	
	static int[] dr = new int[] {1, -1, 0, 0};
	static int[] dc = new int[] {0, 0, 1, -1};
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int caveSize;
	static int[][] cave;
	static int minLoss;
	
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		int test = 0;
		while (true) {
			
			sb.delete(0, sb.length());
			
			// 1. 동굴의 크기를 받는다.
			caveSize = Integer.parseInt(br.readLine().trim());
			
			// 1-1. 동굴의 크기가 0이면 종료
			if (caveSize == 0) {
				break;
			}
			
			// 2. 동굴의 정보를 받는다.
			cave = new int[caveSize][caveSize];
			for (int row = 0; row < caveSize; row++) {
				
				inputs = br.readLine().trim().split(" ");
				
				for (int col = 0; col < caveSize; col++) {
					cave[row][col] = Integer.parseInt(inputs[col]);
				}
			}
			
			// 3. 링크를 움직여 동굴을 탈출한다.
			minLoss = Integer.MAX_VALUE;
			exit(new Link(0, 0, cave[0][0]));
		
			
			sb.append("Problem ").append(++test).append(": ").append(minLoss).append("\n");
			bw.write(sb.toString());
		
		}
		
		bw.close();
		return;
		
	}
	
	public static boolean isValid(int row, int col) {
		
		if (0 <= row && row < caveSize && 0 <= col && col < caveSize) {
			return true;
		}
		
		return false;
		
	}
	
	public static void exit(Link link) {
		
		int[][] dp = new int[caveSize][caveSize];
		for (int row = 0; row < caveSize; row++) {
			Arrays.fill(dp[row], Integer.MAX_VALUE);
		}

		PriorityQueue<Link> linkQ = new PriorityQueue<>();
		linkQ.add(link);
		
		// 3-1. 시작지점 부터 BFS 탐색 시작
		while(!linkQ.isEmpty()) {
			
			Link curLink = linkQ.poll();
			int curRow = curLink.row;
			int curCol = curLink.col;
			int curLoss = curLink.loss;
			
			// 3-2. 끝에 도달했을 경우 minLoss를 갱신 후 종료
			if (curRow == caveSize -1 && curCol == caveSize - 1) {
				minLoss = Math.min(minLoss, curLoss);
				return; 
			}
			
			// 3-3. 4가지 방향으로 이동
			for (int dir = 0; dir < dr.length; dir++) {
				
				int nextRow = curRow + dr[dir];
				int nextCol = curCol + dc[dir];
				
				// 3-3-1. 동굴을 벗어난 경우 스킵
				// 동굴을 벗어난다면 스킵
				if (!isValid(nextRow, nextCol)) {
					continue;
				}
				
				// 3-3-2. 다음 위치에서 손실이 최소일 경우에만 이동
				// 다음에 이동할 위치로 이동했을 경우 최소 손실을 보장할 수 있다면 이동
				int nextLoss = curLoss + cave[nextRow][nextCol];
				if (nextLoss < dp[nextRow][nextCol]) {
					dp[nextRow][nextCol] = nextLoss;
					linkQ.add(new Link(nextRow, nextCol, nextLoss));
				}
				
			}
			
		}
		
	}
 
}