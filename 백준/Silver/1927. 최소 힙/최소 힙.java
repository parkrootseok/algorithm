import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

/**
 * BOJ_1927_최소힙
 * @author parkrootseok
 * 
 * 1. 테스트 케이스 입력 
 *  1-1. 연산 개수 받기
 * 2. 연산 수행
 * 	2-1. 0일 경우 원소 출력
 *  2-2. 0이 아닐 경우 원소 삽입
 **/

class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int operationNumber;

	public static void input() throws IOException {

		// 1-1. 연산 개수 받기
		operationNumber = Integer.parseInt(br.readLine().trim());

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 입력
		input();

		// 2. 연산 수행
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int curOperation = 0; curOperation < operationNumber; curOperation++) {
			
			int number = Integer.parseInt(br.readLine().trim());
			
			// 2-1. 0일 경우 원소 출력
			if (number == 0) {
				
				// pq에 아무 원소가 없다면 0
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
					continue;
				}
				
				// 원소가 있다면 가장 우선순위가 높은 원소
				sb.append(pq.poll()).append("\n");
				
			} 
			
			// 2-2. 0이 아닐 경우 원소 삽입
			else {
				pq.add(number);
			}
			
		}
		
		bw.write(sb.toString());
		bw.close();
		return;

	}
	
}