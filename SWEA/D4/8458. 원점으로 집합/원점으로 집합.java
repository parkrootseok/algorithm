import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * SWEA_8458_원점으로집합
 * @author parkrootseok
 * 
 * - 점
 *  - N개 존재
 *  - i번째 움직임에서 i만큼의 거리로 이동 가능
 *  - 이동할 수 있는 방향은 상하좌우
 * 
 * - 모든 점을 이동시킬 수 있는 최소 횟수를 구해라
 * 
 * 1. 테스트 케이스 횟수 받기
 * 2. 테스트 케이스에 대한 정보 입력
 *  2-1. 점개수 받기
 *  2-2. 점 위치마다 총 이동횟수를 구한다.
 * 3. 모든 점의 이동 횟수가 모두 짝수이거나 홀수가 아닐 경우 도달 불가능
 * 4. 모든 점의 이동 횟수가 짝수 또는 홀수일 때 모든 점이 도착할 수 최소 시간 구하기
 *  4-1. t초에 이동할 수 있는 횟수를 계산
 *  4-2. 가장 큰 이동횟수가 현재 주어진 이동횟수보다 크다면 스킵
 *  4-3. (이동 가능 횟수 - 가장 큰 이동 횟수)가 홀수인 경우 스킵
 *  4-4. 최소 시간 구하기
 **/
class Solution {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int testCaseNumber;
	static int pointNumber;
	
	static int maxMoveCount;
	static int[] moveCounts;
	
	static int odd;
	static int even;
	static int minTime;
	
	
	public static void input() throws NumberFormatException, IOException {
		
		// 2-1. 점 개수 받기
		pointNumber = Integer.parseInt(br.readLine().trim());
		
		// 2-2. 점 위치마다 총 이동횟수, 최대 이동횟수,홀수와 짝수인 경우의 수를 구한다.
		even = 0;
		odd = 0;
		moveCounts = new int[pointNumber];
		maxMoveCount = Integer.MIN_VALUE;
		for (int idx = 0; idx < pointNumber; idx++) {
			
			inputs = br.readLine().trim().split(" ");
			int row = Math.abs(Integer.parseInt(inputs[0]));
			int col = Math.abs(Integer.parseInt(inputs[1]));
			
			moveCounts[idx] = (row + col);
			
			maxMoveCount = Math.max(maxMoveCount, moveCounts[idx]);
			
			if (moveCounts[idx] % 2 == 0) {
				even++;
			}
			
			else {
				odd++;
			}
			
		}
		
	}
	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 받기
		testCaseNumber = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCaseNumber; tc++) {

			// 2. 테스트 케이스에 대한 정보 입력
			input();
			
			// 3. 모든 점의 이동 횟수가 모두 짝수이거나 홀수가 아닐 경우 도달 불가능
			if (even > 0 && odd > 0) {
				minTime = -1;
			}
			
			// 4. 모든 점의 이동 횟수가 짝수 또는 홀수일 때 모든 점이 도착할 수 최소 시간 구하기
			else {
				
				int movableCount = 0;
				for (int time = 0; ;time++) {
					
					// 4-1. t초에 이동할 수 있는 횟수를 계산
					movableCount += time;
					
					// 4-2. 가장 큰 이동횟수가 현재 주어진 이동횟수보다 크다면 스킵
					if (maxMoveCount > movableCount) {
						continue;
					}
					
					// 4-3. (이동 가능 횟수 - 가장 큰 이동 횟수)가 홀수인 경우 스킵
					if ((movableCount - maxMoveCount) % 2 == 1) {
						continue;
					}
					
					// 4-4. 최소 시간 구하기
					// 위 두 조건을 만족하지 않는다면 멀리 있는 점이 도착 상태이고 이미 도착한 점들이 짝수 거리 범위로 만들어 무조건 마지막 점과 같이 원점 도착 가능함
					minTime = time;
					break;
					
				}
				
			}
			
			sb.append("#").append(tc).append(" ").append(minTime).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}
	
}