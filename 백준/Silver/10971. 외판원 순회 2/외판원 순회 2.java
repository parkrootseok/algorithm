import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/***
 * BOJ_10971_외판원순회2
 * @author parkrootseok
 * 
 * - 1 ~ N번 도시가 존재하고, 도시 사이에는 길이 있음
 * - 모든 도시를 거쳐 다시 제자리로 돌아오는 여행 경로를 계획
 * - 단, 한 번 갔던 도로는 다시 올 수 없음
 * 
 * 1. 도시의 수를 입력
 * 2. 각 도시간의 이동 비용을 받는다.
 * 
 **/

public class Main {
	
	static final int START_CHANNEL = 100;
	static final int TOTAL_BUTTON_NUMBER = 10;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int cityNumber;
	static int[][] cost;
	static int[] minCost;
	static boolean[] isVisited;
	static int minTotalCost;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 도시의 수를 입력
		cityNumber = Integer.parseInt(br.readLine().trim());
		
		// 2. 각 도시간 이동 비용을 받는다.
		cost = new int[cityNumber][cityNumber];
		for(int row = 0; row < cityNumber; row++) {
			
			inputs = br.readLine().trim().split(" ");
			
			for(int col = 0; col < cityNumber; col++) {
				cost[row][col] = Integer.parseInt(inputs[col]);
			}
				
		}
		
		// 3. 최소 비용을 구한다.
		minTotalCost = Integer.MAX_VALUE;
		isVisited = new boolean[cityNumber];		
		for(int start = 0; start < cityNumber; start++) {
			isVisited[start] = true;
			dfs(0, start, start, 0);
		}
		
		
		sb.append(minTotalCost);
		bw.write(sb.toString());
		bw.close();

	}
	
	public static void dfs(int level, int start, int to, int totalCost) {
		
		// 3-4. 모든 도시를 다 돌았다면 최소비용 갱신
		if (level == cityNumber - 1) {
			
			if(cost[to][start] != 0) {
				minTotalCost = Math.min(minTotalCost, totalCost + cost[to][start]);
			}
	
			return;
			
		}
		
		if(totalCost > minTotalCost) {
			return;
		}
		
		
		for(int from = 0; from < cityNumber; from++) {
			
			// 3-1. 이미 방문한 곳이라면 스킵
			if (isVisited[from]) {
				continue;
			}
			
			// 3-2. 갈 수 없는 도시라면 스킵
			if (cost[to][from] == 0) {
				continue;
			}
			
			// 3-3. 갈 수 있는 도시라면 비용을 더하여 재귀 호출
			isVisited[from] = true;
			dfs(level + 1, start, from, totalCost + cost[to][from]);
			isVisited[from] = false;
			
		}
			
	}

}