import java.util.*;

/**
 * Programmers_같은숫자는싫어
 * 
 * - 배열에서 연속적인 수를 제거
 * 
 * 1. 배열에 있는 숫자를 이동
 *  1-1. 스택에 원소가 있다면
 *   1-1-1. 가장 마지막에 삽입된 숫자를 조회
 *   1-1-2. 가장 최근 숫자 정보 갱신
 *   1-1-3. 가장 최근 숫자와 다르다면 큐에 삽입
 *  1-2. 없다면 스택과 큐에 삽입
 * 2. 큐에 존재하는 모든 숫자를 배열로 이동
 */
public class Solution {
	
    public int[] solution(int []arr) {
        
    	// 1. 배열에 있는 숫자를 이동
    	Queue<Integer> queue = new ArrayDeque<>();
    	Stack<Integer> stack = new Stack<>();
    	for (int number : arr) {
    		
    		// 1-1. 스택에 원소가 있다면
    		if (!stack.isEmpty()) {
    			
    			// 1-1-1. 가장 마지막에 삽입된 숫자를 조회
    			int lastInsertionNumber = stack.peek();
    			
    			// 1-1-2. 가장 최근 숫자 정보 갱신
    			stack.push(number);
    			
    			// 1-1-3. 가장 최근 숫자와 다르다면 큐에 삽입
    			if (lastInsertionNumber != number) {
    				queue.add(number);
    			}
    			
    		} 
    		
    		// 1-2. 없다면 스택과 큐에 삽입
    		else {
    			stack.push(number);
        		queue.add(number);
    		}
    		
    	}
    	
    	// 2. 큐에 존재하는 모든 숫자를 배열로 이동
    	int[] answer = new int[queue.size()];
    	int idx = 0;
    	while (!queue.isEmpty()) {
    		answer[idx++] = queue.poll();
    	}
   
        return answer;
        
    }
    
}
