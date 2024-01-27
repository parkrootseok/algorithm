import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_10726_이진수 표현
 * @author parkrootseok
 * 
 * - N의 배수 번호인 양을 세기로 함
 * - 배수들 중에서 0-9 모든 숫자롤 보는것 최소 몇 번의 양을 세어야할까?
 * 
 * 1. 테스트 케이스 수 T를 받는다.
 * 2. 각 테스트 케이스마다 N을 받는다.
 * 3. 비트마스킹을 활용해 각 자릿수가 모두 등장하는지 확인
 *  3-1. N의 배수를 구한 후
 *  3-2. 각 자릿수의 bit를 1로 변환
 *  3-3. 종료 조건을 만족하면 반복문 종료
 * 4. 종료 조건을 만족하면 정답을 출력한다.
 *  
 */

public class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int testCaseNumber;
	
	// static int[] count;
	static int completeCondition = (1 << 10) - 1;
	static int visited;
	
	public static boolean isFinish() {
		
//		for (int c : count) {
//			if(c == 0) {
//				return false;
//			}
//		}
//		
//		return true;
		
		if(visited == completeCondition) {
			return true;
		}

		 return false;
		
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		testCaseNumber = Integer.parseInt(br.readLine().trim());
		
		int N;
		for (int curT = 1 ; curT <= testCaseNumber; curT++) {
			
			// 2. 각 테스트 케이스마다 N을 받는다.
			N = Integer.parseInt(br.readLine().trim());
			
//			count = new int[10];	
			int mul = 0;
			String curN = "";
			visited = 0;
			
			
			// 3. 비트마스킹을 활용해 각 자릿수가 모두 등장하는지 확인
			while(true) {	
				
				// 3-1. N의 배수를 구한 후
				curN = String.valueOf(N * (++mul));
				
				// 3-2. 각 자릿수의 bit를 1로 변환
				for(char digit : curN.toCharArray()) {
//					count[digit - '0']++;
					visited |= (1 << (digit - '0'));
				}
				
				// 3-3. 종료 조건을 만족하면 반복문 종료
				if(isFinish()) {
					break;
				}
				
			}
			
			sb.append("#").append(curT).append(" ").append(curN).append("\n");
			
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}
	
}