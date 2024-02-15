import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * SWEA_1247_최적경로
 * @author parkrootseok
 * 
 * - N명의 고객을 방문 후 집에 복귀
 * - 모두 방문하고 집을 돌아가는 경로 중 가장 이동거리가 짧은 경로를 찾는 프로그램을 작성
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 고객의 수와 회사, 집, 고객의 좌표를 받아 초기화한다.
 * 3. N명의 고객들에 대한 수열을 생성
 * 	3-1. 수열이 완성되었다면 총 이동 거리를 구한 후 최소값으로 갱신
 *   3-1-1. 고객의 좌표를 모두 탐색(고객의 수만큼 방문이 이루어져야 함)
 *   3-1-2. 현재위치와 고객 거리를 구하여 누적합
 *   3-1-3. 현재 위치를 갱신
 *   3-1-4. 고객의 좌표를 모두 탐색하여 총 이동 거리를 구했다면 마지막 고객과 집과의 거리를 덧셈
 *   3-1-5. 최소값으로 갱신
 *  3-2. 수열 만들기
 */

class Solution {

	static class Position {

		int row;
		int col;

		Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int tcNumber;
	static int customerNumber;
	
	static Position company;
	static Position home;
	static Position[] customers;
	static int minTotalDistance;
	
	static Position[] perm;
	static boolean[] isVisted;
	
	/**
	 * 두 위치 사이 거리를 구하기 위한 메소드
	 */
	public static int getDistance(int x1, int y1, int x2, int y2) {
		
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
		
	}
	
	public static void permutation(int level) {
		
		// 3-1. 수열이 완성되었다면 총 이동 거리를 구한 후 최소값으로 갱신		
		if(level == customerNumber) {
			
			int curTotalDistance = 0;
			
			// 3-1-1. 고객의 좌표를 모두 탐색(고객의 수만큼 방문이 이루어져야 함)
			// 처음 위치는 회사로 고정
			Position curPosition = company;
			for(int curCustomer = 0; curCustomer < customerNumber; curCustomer++) {
				
				// 3-1-2. 현재위치와 고객 거리를 구하여 누적합
				curTotalDistance += getDistance(curPosition.row, curPosition.col, perm[curCustomer].row, perm[curCustomer].col); 
				
				// 3-1-3. 현재 위치를 갱신
				curPosition = perm[curCustomer];
				
			}
			
			// 3-1-4. 고객의 좌표를 모두 탐색하여 총 이동 거리를 구했다면 마지막 고객과 집과의 거리를 덧셈
			curTotalDistance += getDistance(home.row, home.col, perm[level - 1].row, perm[level - 1].col);
			
			// 3-1-5. 최소값으로 갱신
			minTotalDistance = Math.min(minTotalDistance, curTotalDistance);
			return;
			
		}
		
		// 3-2. 수열 만들기
		for(int curCustomer = 0; curCustomer < customerNumber; curCustomer++) {
			
			if(!isVisted[curCustomer]) {
				
				isVisted[curCustomer] = true;
				perm[level] = customers[curCustomer];
				permutation(level + 1);
				isVisted[curCustomer] = false;
			}
			
		}
		
	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		//1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine().trim());

		for (int curtestCase = 1; curtestCase <= tcNumber; curtestCase++) {

			// 2. 고객의 수와 회사, 집, 고객의 좌표를 받아 초기화한다.
			customerNumber = Integer.parseInt(br.readLine().trim());

			// 회사 좌표, 집 좌표 초기화
			inputs = br.readLine().trim().split(" ");
			company = new Position(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
			home = new Position(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]));

			// N명의 고객 좌표 초기화
			customers = new Position[customerNumber];
			inputs = Arrays.copyOfRange(inputs, 4, inputs.length);
			for (int index = 0; index < inputs.length / 2; index++) {
				customers[index] 
					= new Position(Integer.parseInt(inputs[2 * index]), Integer.parseInt(inputs[2 * index + 1]));
			}
			
			// 3. N명의 고객들에 대한 수열을 생성
			minTotalDistance = Integer.MAX_VALUE;
			perm = new Position[customerNumber];
			isVisted = new boolean[customerNumber];
			permutation(0);

			sb.append("#").append(curtestCase).append(" ").append(minTotalDistance).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}