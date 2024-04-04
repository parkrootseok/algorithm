import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_5654_키순서
 * @author parkrootseok
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 테스트 케이스 입력
 *  2-1. 학생들의 수 받기
 *  2-2. 키를 비교환 횟수 받기
 *  2-3. 비교 정보 받기
 * 3. 자신의 키 순서가 몇 번째 인지 알 수 학생수의 수를 구한다.
 *  3-1. 자신보다 키 큰 모든 사람을 방문
 *  3-2. 자신보다 키 작은 모든 사람을 방문
 *  3-3. 방문하지 않은 학생이 한 명이라도 있다면 실패
 **/

public class Solution {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int testCaseNumber;
	
	static int[][] adjacentMatrix;
	static int[][] rAdjacentMatrix;
    
	static int studentNumber;
	static int compareNumber;
	static int searchCount;
	
	public static void input() throws NumberFormatException, IOException {
		
		// 2-1. 학생들의 수 받기
		studentNumber = Integer.parseInt(br.readLine().trim());
		
		// 2-2. 키를 비교환 횟수 받기
		compareNumber = Integer.parseInt(br.readLine().trim());
		
		// 2-3. 비교 정보 받기
		adjacentMatrix = new int[studentNumber + 1][studentNumber + 1];
		for (int student = 1; student <= studentNumber; student++) {
			adjacentMatrix[student][0] = -1;
		}

		for (int curCompareNumber = 0; curCompareNumber < compareNumber; curCompareNumber++) {
			
			inputs = br.readLine().trim().split(" ");
			int smaller = Integer.parseInt(inputs[0]);
			int bigger = Integer.parseInt(inputs[1]);

			// 자신보다 키 큰 사람을 기록
			adjacentMatrix[smaller][bigger] = 1;
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 횟수 입력
		testCaseNumber = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCaseNumber; tc++) {
			
			// 2. 테스트 케이스 입력
			input();
			
			// 3. 자신의 키 순서가 몇 번째 인지 알 수 학생수의 수를 구한다.
			/**
			 * DFS + Memoization
			 **/
			int possibleCount = 0;
			for (int curStudent = 1; curStudent <= studentNumber; curStudent++) {
	
				// 탐색이 안된 학생이라면 탐색 시작
				if (adjacentMatrix[curStudent][0] == -1) {
					dfsMem(curStudent);
				}
		
			}
			
			for (int studentA = 1; studentA <= studentNumber; studentA++) {
				
				for (int studentB = 1; studentB <= studentNumber; studentB++) {
					
					adjacentMatrix[0][studentB] += adjacentMatrix[studentA][studentB];
					
				}
				
			}
			
			for (int student = 1; student <= studentNumber; student++) {
				
				if (adjacentMatrix[student][0] + adjacentMatrix[0][student] == studentNumber - 1) {
					possibleCount++;
				}
				
			}
			
			sb.append("#").append(tc).append(" ").append(possibleCount).append("\n");
			
		}
	
		bw.write(sb.toString());
		bw.close();
		
		return;
	
	}
	
	public static void dfsMem(int cur) {
		
		for (int studentA = 1; studentA <= studentNumber; studentA++) {
		
			// 간선이 존재하고 방문하지 않았으면
			if (adjacentMatrix[cur][studentA] == 1) {
				
				// 탐색이 되지 않은 학생이라면 탐색 O
				if (adjacentMatrix[studentA][0] == -1) {
					dfsMem(studentA);
				}
				
				
				// 탐색이 완료된 힉생 탐색 X(현재 학생보다 큰 학생이 있는 경우)
				if (adjacentMatrix[studentA][0] > 0) {
					for (int studentB = 1; studentB <= studentNumber; studentB++) {
						
						// 현재 학생보다 큰 학생을 cur과의 관계로 표현
						if (adjacentMatrix[studentA][studentB] == 1) { 
							adjacentMatrix[cur][studentB] = 1;
						}
						
					}
						
				}
				
			}
			
		}
		
		// 탐색을 모두 진행한 후
		int count = 0;
		for (int student = 1; student <= studentNumber; student++) {
			// 자신보다 큰 학생수 카운팅
			count += adjacentMatrix[cur][student];
		}
		
		adjacentMatrix[cur][0] = count;
		
	}
    
}