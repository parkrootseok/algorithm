import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_12288_새로운 붋면증 치료
 * @author parkrootseok
 * 
 * - N의 배수 번호인 양을 세기로 함
 * - 배수들 중에서 0-9 모든 숫자롤 보는것 최소 몇 번의 양을 세어야할까?
 * 
 * 1. 테스트 케이스 수 T를 받는다.
 * 2. 각 테스트 케이스마다 N을 받는다.
 * 3. 종료 조건을 만족하는지 확인한다.
 *  3-1. N의 배수를 구한 후
 *  3-2. 각 자릿수를 카운트
 *  3-3. 배수 증가
 *  
 */

public class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int testCaseNumber;
	static int[] count;
	
	public static boolean isFinish() {
		
		for (int c : count) {
			if(c == 0) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		testCaseNumber = Integer.parseInt(br.readLine().trim());
		
		int N;
		for (int curT = 1 ; curT <= testCaseNumber; curT++) {
			
			N = Integer.parseInt(br.readLine().trim());
			count = new int[10];
			
			String curN = "";
			int mul = 1;
			
			// 3. 종료 조건을 만족하는지 확인한다.
			while(!isFinish()) {
				
				// 3-1. N의 배수를 구한 후
				curN = String.valueOf(N * mul);
				
				// 3-2. 각 자릿수를 카운트
				for(char digit : curN.toCharArray()) {
					count[digit - '0']++;
				}
					
				// 3-3. 배수 증가
				mul++;
			}
			
			sb.append("#").append(curT).append(" ").append(curN).append("\n");
			
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}
}
