import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Programmers_타겟넘버
 * @author parkrootseok
 *
 * 1. 현재 배열의 숫자를 더한 경우를 재귀 호출
 * 2. 현재 배열의 숫자를 뺀 경우를 재귀 호출
 * 3. 배열에 있는 모든 숫자를 사용했을 경우
 *  3-1. 타겟 넘버와 일치하면 카운트
 *  3-2. 재귀호출 종료
 */

public class Solution {
	
	static int count;

	public int solution(int[] numbers, int target) throws NumberFormatException, IOException {

		count = 0;
		dfs(numbers, target, 0, 0);	
		return count;
		
	}
	
	public void dfs(int[] numbers, int target, int level, int sum) {
		
		// 배열에 있는 모든 숫자를 사용했을 경우
		if (level == numbers.length) {
			
			// 결과가 타겟 넘버와 같다면
			if (sum == target) {
				
				// 카운트
				count++;
			}
			
			// 후 종료
			return;
			
		}
		
		// 더한 경우
		dfs(numbers, target, level + 1, sum + numbers[level]);

		// 뺀 경우
		dfs(numbers, target, level + 1, sum - numbers[level]);
		
	}


}
