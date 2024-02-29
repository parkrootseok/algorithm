import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Stack;

/**
 * BOJ_1463_1로만들기
 * @author parkrootseok
 * 
 * - 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
 * - 연산 사용하는 횟수의 최솟값을 출력해라
 * 
 * 1. N을 받는다.
 * 2. dp를 이용하여 해결
 *  2-1. 1을 빼는 경우
 *  2-2. 2로 나누는 경우
 *  2-3. 3으로 나는 경우
 **/

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		
		// 1. N을 받는다.
		N = Integer.parseInt(br.readLine().trim());
		
		dp = new int[N + 1];
		dp[0] = 0;
		dp[1] = 0;
		
		// 2. dp를 이용하여 해결
		for(int number = 2; number <= N; number++) {
			
			// 2-1. 1을 빼는 경우
			// 현재 number는 이전 number에서 -1 연산을 한 번 한 것이므로 다음과 같이 저장할 수 있음.
			dp[number] = dp[number - 1] + 1;
			
			// 2-2. 2로 나누는 경우
			if(number % 2 == 0) {
				// 현재 수를 2로 나눈 숫자의 연산 횟수에 2를 한번 더 나누어야 하므로 + 1
				dp[number] = Math.min(dp[number], dp[number / 2] + 1);
			}
			
			// 2-3. 3으로 나누는 경우
			if(number % 3 == 0) {
				// 현재 수를 3으로 나눈 숫자의 연산 횟수에 3를 한번 더 나누어야 하므로 + 1
				dp[number] = Math.min(dp[number], dp[number / 3] + 1);
			}
					
		}
		
		
		
		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.close();
		
		
	}
	
	
}