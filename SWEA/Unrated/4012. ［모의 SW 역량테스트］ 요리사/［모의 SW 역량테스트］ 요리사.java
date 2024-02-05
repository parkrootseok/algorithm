import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * SWEA_4012_요리사
 * @author parkrootseok
 * 
 * - N개의 식재료 존재
 * - N개의 식재료를 반으로 나누어 두 개의 요리
 * - 두 음식 맛의 차이가 최소가 되도록 재료 분배
 * 
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 재료 갯수 입력
 * 3. 재료들의 시너지에 대한 정보 입력
 * 4. 총 N/2개의 식재료를 사용하는 조합을 생성
 *  4-1. 조합이 완성되었으면 시너지의 차이를 계산
 *  
 */

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int tcNumber;
	static int ingredientNumber;
	static int minDifference;
	static int[][] synerge;
	static boolean[] usedIngredients;
	
	
	public static int calcSynnergeDifference() {
		
		int totalASynnerge = 0;
		int totalBSynnerge = 0;
		
		// 분리된 재료들을 탐색
		for(int row = 0; row < ingredientNumber; row++) {
			for(int cols = row + 1; cols < ingredientNumber; cols++) {
				
				// 사용한 재료일 경우
				if(usedIngredients[row] && usedIngredients[cols]) {
					totalASynnerge += synerge[row][cols];
					totalASynnerge += synerge[cols][row];
				} 
				
				// 사용하지 않은 재료일 경우
				else if(!usedIngredients[row] && !usedIngredients[cols]) {
					totalBSynnerge += synerge[row][cols];
					totalBSynnerge += synerge[cols][row];
				}
				
			}
			
		}
		
		return Math.abs(totalASynnerge - totalBSynnerge);
		
	}
	
	public static void combination(int level, int start) {
		
		// 4. 조합이 완성되었으면
		if (level == ingredientNumber / 2) {
			
			// 4-1. 시너지의 차이를 계산
			minDifference = Math.min(minDifference, calcSynnergeDifference());
			
			return;
			
		}
		
		for(int index = start; index < ingredientNumber; index++) {
			
			// 재료를 사용했음을 체크
			usedIngredients[index] = true; 
			
			combination(level + 1, index + 1);
			
			// 사용이 끝난 재료는 반납
			usedIngredients[index] = false; 
			
		}
		
	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 입력
		tcNumber = Integer.parseInt(br.readLine().trim());
		
		for(int curTC = 1; curTC <= tcNumber; curTC++) {
			
			// 2. 재료 갯수 입력
			ingredientNumber = Integer.parseInt(br.readLine().trim());
			
			// 3. 재료들의 시너지에 대한 정보 입력
			synerge = new int[ingredientNumber][ingredientNumber];
			for(int row = 0; row < ingredientNumber; row++) {
				inputs = br.readLine().trim().split(" ");
				for(int cols = 0; cols < ingredientNumber; cols++) {
					synerge[row][cols] = Integer.parseInt(inputs[cols]);
				}
			}
			
			// 4. 총 N/2개의 식재료를 사용하는 조합을 생성
			minDifference = Integer.MAX_VALUE;
			usedIngredients = new boolean[ingredientNumber];
			combination(0, 0);
			
			sb.append("#").append(curTC).append(" ").append(minDifference).append("\n");
		
		}
		
		
		bw.write(sb.toString());
		bw.close();
		return;

	}

}