import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SWEA_9229_한빈이와SpotMart
 * @author parkrootseok
 * 
 * - 과자 살거임
 * - N개 과자 봉지, A무게
 * - 최대한 많은 양을 가지는 과자
 * - M을 초과하면 안됨
 * - 최대 무게의 합은?
 * - 그리고 과자 2개만 사야함
 * 
 * 1. 테스트 케이스 갯수 입력
 * 2. 과자 갯수랑 제한 무게 입력
 * 3. 과자에 대한 무게 입력
 * 4. N개의 과자 중 2개를 가지는 조합을 생성
 *  4-1. 현재 무게가 한계를 초과하면 종료
 *  4-2. 현재 무게가 한계를 넘지 않고 2봉지의 과자를 모두 담았다면 최대값을 초기화
 *  
 */

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static final int SNACK_LIMIT = 2;
	
	static int tcNumber;
	static int snackNumber;
	static int limitWeight;
	static int[] snacks;
	static int[] combination;
	static int maxPossibleWeight;

	public static void combination(int level, int start, int weight) {
		
		// 4-1. 현재 무게가 한계를 초과하면 종료
		if(weight > limitWeight) {
			return;
		}
		
		// 4-2. 현재 무게가 한계를 넘지 않고 2봉지의 과자를 모두 담았다면
		if(level == SNACK_LIMIT) {
			
			// 최대값을 초기화
			maxPossibleWeight = Math.max(maxPossibleWeight, weight);
			return;
			
		}
		
		
		for(int curSnack = start; curSnack < snackNumber; curSnack++) {
			
			// 현재 과자에 대한 무게를 더하여 재귀 호출
			combination(level + 1, curSnack + 1, weight + snacks[curSnack]);
		}
		
	}
	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 갯수 입력	
		tcNumber = Integer.parseInt(br.readLine().trim());
		// 2. 과자 갯수랑 제한 무게 입력
		// 3. 과자에 대한 무게 입력

		for (int curTC = 1; curTC <= tcNumber; curTC++) {

			// 2. 과자 갯수랑 제한 무게 입력
			inputs = br.readLine().trim().split(" ");
			snackNumber = Integer.parseInt(inputs[0]);
			limitWeight = Integer.parseInt(inputs[1]);
			
			// 3. 과자에 대한 무게 입력
			snacks = new int[snackNumber];
			inputs = br.readLine().trim().split(" ");
			for(int curSnack = 0; curSnack < snackNumber; curSnack++) {
				snacks[curSnack] = Integer.parseInt(inputs[curSnack]);
			}

			// 4. 과자 갯수중 2개를 선택하는 조합 생성
			maxPossibleWeight = 0;
			combination(0, 0, 0);
			
			// 결과 출력
			sb.append("#").append(curTC).append(" ");
			if(maxPossibleWeight > 0) {
				sb.append(maxPossibleWeight).append("\n");
			} else {
				sb.append(-1).append("\n");
			}

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}