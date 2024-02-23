import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_1158_요세푸스문제
 * @author parkrootseok
 * 
 * - 1번 ~ N번 원을 이루며 앉아있음
 * - 양의 정수가 주어지고, 순서대로 k번 째 사람을 제거
 * - N명의 사람이 모두 제거될 때 까지 진행
 * - 이때, 제거되는 순서는 요세푸스 순열 순
 * 
 * 1. N, K를 입력 받는다.
 * 2. 요세푸스 순열을 만든다.
 * 	2-1. K - 1만큼 이동
 *  2-2. K번 째에 해당하는 원소를 삭제
 *  2-3. 마지막 원소는 따로 처리하여 종료
 *  
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int N, K;
	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		Queue<Integer> numbers = new ArrayDeque<>();
		
		// 1. N, K를 입력 받는다.
		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);
		
		// 2. 요세푸스 순열을 만든다.
		for(int number = 1; number <= N; number++) {
			numbers.add(number);
		}
		
		int sequanceIdx = 0;
		int[] sequance = new int[N];
		while(!numbers.isEmpty()) {
			
			// 2-1. K만큼 이동
			for(int pass = 0; pass < K; pass++) {
				
				// K번째가 아니면 뒤로 다시 넣어주고
				if(pass != K - 1) {
					numbers.offer(numbers.poll());
				} 
				
				// K번 째라면 제거 후 출력 배열에 저장
				else {
					sequance[sequanceIdx++] = numbers.poll();
				}
				
			}
			
		}
	
		sb.append("<");
		for(int idx = 0; idx < N; idx++) {
			if(idx != 0) {
				sb.append(", ").append(sequance[idx]);
			} else {
				sb.append(sequance[idx]);
			}
			
		}
		sb.append(">");
		
		bw.write(sb.toString());
		bw.close();
		return;

	}

}