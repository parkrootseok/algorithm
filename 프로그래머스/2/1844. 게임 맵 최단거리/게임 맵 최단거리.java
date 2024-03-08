import java.util.PriorityQueue;

/**
 * Programmers_게임맵최단거리
 * @author parkrootseok
 * 
 * - 캐릭터
 *  - (0, 0)에 위치
 * 
 * - 목표
 *  - (n-1, m-1)에 빨리 도착하는 이동 횟수
 *
 * 1. 큐에 현재 위치를 삽입
 * 2. 현재 위치에서 부터 탐색 시작
 *  2-1. 현재 위치가 도착지점이라면 탐색을 종료
 *  2-2. 현재 위치에서 4방 탐색을 시작
 *   2-2-1. 인덱스 안에 없다면 스킵
 *   2-2-2. 방해물이라면 스킵 
 *   2-2-3. 이미 방문한 지점이라면 스킵
 *   2-2-4. 위 3조건을 모두 만족하지 않는다면 다음 위치를 큐에 삽입
 * 
 */

public class Solution {
	
	static class Position implements Comparable<Position> {
		
		int row;
		int col;
		int count;
		
		Position(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}
		
		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.count, o.count);
		}
		
	}
	
	static final int BLACK = 0;
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int mapRow;
	static int mapCol;
	static int minCount = -1;
	
	public int solution(int[][] maps) {
		
		mapRow = maps.length;
		mapCol =  maps[0].length;
		bfs(maps);
		return minCount;
		
	}
	
	public boolean isValid(int row, int col) {
		
		if (0 <= row && row < mapRow && 0 <= col && col < mapCol) {
			return true;
		}
		
		return false;
		
	}
	
	public void bfs(int[][] maps) {
		
		int goalRow = mapRow - 1;
		int goalCol = mapCol - 1;
		boolean[][] isVisited = new boolean[mapRow][mapCol];
		
		// 1. 큐에 현재 위치를 삽입 
		PriorityQueue<Position> positionQ = new PriorityQueue<>();
		positionQ.add(new Position(0, 0, 1));
		
		// 2. 현재 위치에서 부터 탐색 시작
		while (!positionQ.isEmpty()) {
			
			Position cur = positionQ.poll();
			int curRow = cur.row;
			int curCol = cur.col;
			int curCount = cur.count;
			
			// 2-1. 현재 위치가 도착지점이라면 탐색을 종료
			if (goalRow == curRow && goalCol == curCol) {
				minCount = curCount;
				return;	
			}
			
			// 2-2. 현재 위치에서 4방 탐색을 시작
			int nRow, nCol;
			for (int dir = 0; dir < dr.length; dir++) {
				
				nRow = curRow + dr[dir];
				nCol = curCol + dc[dir];
				
				// 2-2-1. 인덱스 안에 없다면 스킵
				if (!isValid(nRow, nCol)) {
					continue;
				}
				
				// 2-2-2. 방해물이라면 스킵
				if (maps[nRow][nCol] == BLACK) {
					continue;
				}
				
				// 2-2-3. 이미 방문한 지점이라면 스킵
				if (isVisited[nRow][nCol]) {
					continue;
				}
				
				// 2-2-4. 위 3조건을 모두 만족하지 않는다면 다음 위치를 큐에 삽입
				positionQ.add(new Position(nRow, nCol, curCount + 1));
				isVisited[nRow][nCol] = true;
				
			}
			
		}
	
	}

}
