/**
 * Programmers_최소직사각형
 * @author parkrootseok
 * 
 * - 지갑
 *   - 모든 명함을 담을 수 있어야함
 *  
 * - 명함
 *  - 회전 가능
 *  - 즉, 가로와 세로의 길이를 바꿀 수 있음 
 *  
 *  - 지갑에 모든 명함을 넣을 수 있고, 가장 작은 크기를 구해라
 *  
 * 1. 모든 변의 길이를 탐색
 *  1-1. 명함의 너비와 높이에서 가장 짧고 긴 부분을 찾는다
 *  1-2. 가장 긴 부분중 최대값과 가장 짧은 부분 중 최대갑을 찾는다
 */
public class Solution {
	
	static int cardNumber;
	static int[][] cards;

    public int solution(int[][] sizes) {
       
    	cardNumber = sizes.length;
    	cards = sizes;
    	
    	int width = Integer.MIN_VALUE;
		int height = Integer.MIN_VALUE;
		
		// 1. 모든 변의 길이를 탐색
		for (int curCard = 0; curCard < cardNumber; curCard++) {
			
			// 1-1. 명함의 너비와 높이에서 가장 길고 짧은 부분을 찾는다
			int curMax = Math.max(cards[curCard][0], cards[curCard][1]);
			int curMin = Math.min(cards[curCard][0], cards[curCard][1]);
	
			// 1-2. 가장 긴 부분 중 최대값과 가장 짧은 부분 중 최대값을 찾는다
			width = Math.max(width, curMax);
			height = Math.max(height, curMin);
			
		}
		
    	return width * height;
        
    }
    
    
}
