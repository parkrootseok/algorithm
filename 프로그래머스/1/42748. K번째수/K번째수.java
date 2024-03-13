import java.util.Arrays;

/**
 * Programmers_K번째수
 * @author parkrootseok
 * 
 * - 정렬
 *  - i번 ~ j번 수행
 *  - 정렬 수행 후 k번째 있는 수를 탐색
 * 
 * 1. 시작, 끝 인덱스와 타겟 인덱스를 받는다.
 * 2. 정렬을 수행한다
 * 3. 타겟 인덱스에 있는 숫자를 배열에 추가
 */
class Solution {
	
    public int[] solution(int[] array, int[][] commands) {
    	
    	int[] result = new int[commands.length];
    	
    	for (int row = 0; row < commands.length; row++) {
    		
        	// 1. 시작, 끝 인덱스와 타겟 인덱스를 받는다.	
    		int start = commands[row][0];
    		int end = commands[row][1];
    		int target = commands[row][2];
    		
        	// 2. 정렬을 수행한다
    		int[] temp = Arrays.copyOfRange(array, start - 1, end);
    		Arrays.sort(temp);

        	// 3. 타겟 인덱스에 있는 숫자를 배열에 추가
    		result[row] = temp[target - 1];
    		
    	}
    	
    	System.out.println(Arrays.toString(result));
    	return result;
    	
    }
    
}