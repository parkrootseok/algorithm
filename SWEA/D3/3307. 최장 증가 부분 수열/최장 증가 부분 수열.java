import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * SWEA_3307_최장증가부분수열
 * @author parkrootseok
 * 
 * 1. 숫자의 갯수 입력
 * 2. 수열 입력
 * 3. 최장 증가 부분 수열 구하기
 *  3-1. 현재 숫자가 최고 길이의 숫자보다 크다면 길이를 증가시키고 해당 길이에 숫자를 삽입
 *  3-2. 작다면 삽입할 위치를 찾아 삽입한다.
 **/
class Solution {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static String[] inputs;
    
    static int testCaseNumber;
    
    static int number;
    static int[] sequence;
    static int[] dp;

    public static void main(String args[]) throws Exception {

    	br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
        testCaseNumber = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCaseNumber; tc++) {

        	// 1. 숫자의 갯수 입력
            number = Integer.parseInt(br.readLine());
            
            // 2. 수열을 입력받는다.
            sequence = new int[number];
            inputs = br.readLine().split(" ");
            for (int idx = 0; idx < inputs.length; idx++) {
                sequence[idx] = Integer.parseInt(inputs[idx]);
            }

            // 3. 최장 증가 부분 수열 구하기
            dp = new int[number + 1];
            int len = 0;
            for (int idx = 0; idx < number; idx++) {
            	
            	// 3-1. 현재 숫자가 최고 길이의 숫자보다 크다면 길이를 증가시키고 해당 길이에 숫자를 삽입
            	if(dp[len] < sequence[idx]) {
            		
            		dp[++len] = sequence[idx];
            	}
            	
            	// 3-2. 작다면 삽입할 위치를 찾아 삽입한다.
            	else {
            		
            		int insertionIdx = binarySearch(0, len, sequence[idx]);
            		dp[insertionIdx] = sequence[idx];
            		
            	}
                
            }

            sb.append("#").append(tc).append(" ").append(len).append("\n");

        }

        bw.write(sb.toString());
        bw.close();
        return;

    }
    
    public static int binarySearch(int left, int right, int target) {
    
    	while(left < right) {
    		
    		int mid = (left + right) / 2;
    		
    		// 중앙값 보다 크다면(중앙값 인덱스 기준 오른쪽에 위치)
    		// 즉, 목표보다 작은 수 중 하나 이므로 인덱스 + 1
    		// 만약, (인덱스 + 1)위치가 right 위치보다 크게 된다면
    		// right는 목표보다 큰 값 중 첫 값이므로 더이상 작은값이 없다는 것을 알 수 있다.
    		if (dp[mid] < target) {
    			left = mid + 1;
    		}
    		
    		// 중앙값보다 작다면(중앙값 인덱스 기준 왼쪽에 위치)
    		// 즉, 현재 상황에서 목표보다 큰 값 중 첫값이므로 인덱스률 유지
    		else if (target < dp[mid]) {
    			right = mid;
    		}
    		
    	}
    	
    	return right;
    	
    }

}