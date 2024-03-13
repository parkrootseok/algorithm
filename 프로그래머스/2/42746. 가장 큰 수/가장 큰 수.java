import java.util.Arrays;
import java.util.Comparator;

/**
 * Programmers_가장큰수
 * @author parkrootseok
 * 
 * - 주어진 정수를 조합하여 가장 큰 수를 찾아라
 * 
 * 1. 숫자를 모두 문자열로 변환하여 배열에 삽입
 * 2. 문자열 배열을 정렬 수행
 *  2-1. 정렬 기준을 설정
 * 3. 정렬 결과 중 가장 큰 수가 0이라면 0 리턴
 */
class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(new int[] {6, 10, 2});
	}

    public String solution(int[] numbers) {
		
    	StringBuilder sb = new StringBuilder();
    	String[] result = new String[numbers.length];
		
    	// 1. 숫자를 모두 문자열로 변환하여 배열에 삽입
    	for (int idx = 0; idx < numbers.length; idx++) {
    		result[idx] = String.valueOf(numbers[idx]);
    	}
    	
    	// 2. 문자열 배열을 정렬 수행
    	Arrays.sort(result, new Comparator<String>() {
    		
    		// 2-1. 정렬 기준을 설정
    		// o1o2, o2o1 문자열을 내림차순 정렬
    		// o1 : 10, o2 : 4 이면
    		// o1o2 : 104 / o2o1 : 410
    		@Override
    		public int compare(String o1, String o2) {
    			return (o2 + o1).compareTo(o1 + o2);
    		}
    		
		});
    	
    	// 3. 정렬 결과 중 가장 큰 수가 0이라면 0 리턴
    	if (result[0].equals("0")) {
    		return "0";
    	}
    	
    	for (String number : result) {
    		sb.append(number);
    	}
    	
    	return sb.toString();
    	
    }
    
}