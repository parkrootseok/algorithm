import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
		rAdjacentMatrix = new int[studentNumber + 1][studentNumber + 1];
		for (int curCompareNumber = 0; curCompareNumber < compareNumber; curCompareNumber++) {
			
			inputs = br.readLine().trim().split(" ");
			int smaller = Integer.parseInt(inputs[0]);
			int bigger = Integer.parseInt(inputs[1]);

			// 자신보다 키 큰 사람을 기록
			adjacentMatrix[smaller][bigger] = 1;
			rAdjacentMatrix[bigger][smaller] = 1;
			
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
			 * DFS / BFS
			 * - 나보다 작은 사람 따라 깊이 우선 탐색
			 * - 나보다 큰 사람 따라 깊이 우선 탐색
			 * - 탐색을 진행한 횟수가 [총 학생 수 - 1]이라면 내 순위를 알 수 있음
			 **/
			int possibleCount = 0;
			for (int curStudent = 1; curStudent <= studentNumber; curStudent++) {
				
				searchCount = 0;
                
				bfs(curStudent);
				
				if (searchCount == studentNumber - 1) {
					possibleCount++;
				}
				
			}
			
			sb.append("#").append(tc).append(" ").append(possibleCount).append("\n");
			
		}
	
		bw.write(sb.toString());
		bw.close();
		
		return;
	
	}
	
	public static void bfs(int start) {
		
		boolean[] isVisited = new boolean[studentNumber + 1];
		Queue<Integer> studentQ = new ArrayDeque<>();
		
		// 3-1. 자신보다 키 큰 모든 사람을 방문
		studentQ.add(start);
		isVisited[start] = true;
		while(!studentQ.isEmpty()) {
			
			int student = studentQ.poll();
			
			for (int next = 1; next <= studentNumber; next++) {
				
				if (adjacentMatrix[student][next] == 1 && !isVisited[next]) {
					
					// 해당 지점으로 이동하여 탐색 시작
					isVisited[next] = true;
					studentQ.add(next);
					searchCount++;
					
				}
			}
			
		}

		// 3-2. 자신보다 키 작은 모든 사람을 방문
		studentQ.add(start);
		while(!studentQ.isEmpty()) {
			
			int student = studentQ.poll();
			
			for (int next = 1; next <= studentNumber; next++) {
				
				if (rAdjacentMatrix[student][next] == 1 && !isVisited[next]) {
					
					// 해당 지점으로 이동하여 탐색 시작
					isVisited[next] = true;
					studentQ.add(next);
					searchCount++;
					
				}
			}
			
		}
		
	}
	
}