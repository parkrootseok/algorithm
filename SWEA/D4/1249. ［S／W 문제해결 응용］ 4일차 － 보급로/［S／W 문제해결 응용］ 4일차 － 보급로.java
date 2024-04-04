import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

/**
 * SWEA_1249_보급로
 * @author parkrootseok
 * 
 * - 도로 복구 작업
 *  - 도로 깊이에 비례하여 증가
 *  - 깊이가 1이라면 복구시간은 1
 * 
 * - 출발지에서 도착지로 가는 경로 중 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구해라
 *  - 출발지는 (0,0) 도착지는 (N - 1, N - 1)
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 테스트 케이스 입력
 *  2-1. 지도 크기 받기
 *  2-2. 지도에 대한 정보 받기
 * 3. BFS 탐색
 *  3-1. 도착한 경우 최소 복구 시간을 측정
 *  3-2. 도착하지 않았다면 4방 탐색 진행
 *   3-2-1. 현재 이동할 위치의 최소 복구 시간보다 작다면 우선순위 큐에 추가
 **/

public class Solution {
	
	static class Car implements Comparable<Car> {
		
		int row;
		int col;
		int repairTime;
		
		public Car(int row, int col, int repairTime) {
			this.row = row;
			this.col = col;
			this.repairTime = repairTime;
		}
		
		@Override
		public int compareTo(Car o) {
			return Integer.compare(this.repairTime, o.repairTime);
		}
		
	}
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int testCaseNumber;
	
	static int[][] map;
	static int size;
	static int minRepairTime;
	
	static int[][] mem;
	static boolean[][] isVisited;
	
	public static void input() throws NumberFormatException, IOException {

		// 2-1. 지도 크기 받기
		size = Integer.parseInt(br.readLine().trim());
	
		// 2-2. 지도에 대한 정보 받기
		map = new int[size][size];
		mem = new int[size][size];
		for (int row = 0; row < size; row++) {
			inputs = br.readLine().trim().split("");
			for (int col = 0; col < size; col++) {				
				map[row][col] = Integer.parseInt(inputs[col]);
				mem[row][col] = 10_000_000;
			}
			
		}
		
	} 
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 횟수 입력
		testCaseNumber = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCaseNumber; tc++) {
			
			// 2. 테스트 케이스 입력
			input();
			
			// 3. BFS 탐색
			minRepairTime = Integer.MAX_VALUE;
 			isVisited = new boolean[size][size];
			bfs(new Car(0, 0, 0));
			
			sb.append("#").append(tc).append(" ").append(minRepairTime).append("\n");
			
		}
	
		bw.write(sb.toString());
		bw.close();
		
		return;
	
	}
	
	public static void bfs(Car car) {
		
		PriorityQueue<Car> carQ = new PriorityQueue<>();
		carQ.add(car);
		isVisited[car.row][car.col] = true;
		
		while(!carQ.isEmpty()) {
			
			Car curCar = carQ.poll();
			
			// 3-1. 도착한 경우 최소 복구 시간을 측정
			if (curCar.row == size - 1 && curCar.col == size - 1) {
				minRepairTime = curCar.repairTime;
				return;
			}
			
			// 3-2. 도착하지 않았다면 4방 탐색 진행
			for (int dir = 0; dir < dr.length; dir++) {
				
				int nRow = curCar.row + dr[dir];
				int nCol = curCar.col + dc[dir];
				
				// 인덱스가  유효하지 않은 경우 스킵
				if (!(0 <= nRow && nRow < size && 0 <= nCol && nCol < size)) {
					continue;
				}
				
				// 이미 방문한 곳이라면 스킵
				if (isVisited[nRow][nCol]) {
					continue;
				}
				
				// 3-2-1. 현재 이동할 위치의 최소 복구 시간보다 작다면 우선순위 큐에 추가
				int nRepairTime = curCar.repairTime + map[nRow][nCol];
				if (mem[nRow][nCol] > nRepairTime) {
					carQ.add(new Car(nRow, nCol, nRepairTime));
					isVisited[nRow][nCol] = true;
					mem[nRow][nCol] = nRepairTime;
				}				
				
			}
			
		}
		
	}
    
}