import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * SWEA_1225_암호생성기
 * @author parkrootseok
 * 
 * - 8자리 암호 생성
 * 
 * 1. 테스트 케이스 번호 받고
 * 2. 8자리 숫자 받고
 * 3. 큐에 8자리 숫자 모두 offer
 * 3. 암호 생성 실시
 *  3-1. 큐에서 poll하고 반복 횟수만큼 감소
 *  3-2. 감소한 숫자가 0이하이면 0을 삽입하고 종료
 *  
 */

class Solution {

	public static void generateSecret() {

		int order = 1;
		while (true) {

			// 3-1. 큐에서 poll하고 반복 횟수만큼 감소
			int cur = numbers.poll() - order++;

			// 3-2. 감소한 숫자가 0이하이면 0을 삽입하고 종료
			if (cur <= 0) {
				numbers.offer(0);
				return;
			} else {
				// 그렇지 않다면 삽입
				numbers.offer(cur);
			}

			// 한 싸이클을 수행할 때마다 초기화
			if (order == CYCLE) {
				order = 1;
			}

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int tcNumber;
	static Queue<Integer> numbers;
	static int CYCLE = 6;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		for (int curTC = 1; curTC <= 10; curTC++) {

			sb.append("#").append(curTC);

			// 1. 테스트 케이스 번호 받고
			tcNumber = Integer.parseInt(br.readLine());

			// 2. 8자리 숫자 받고 큐에 8자리 숫자 모두 offer
			numbers = new ArrayDeque<>();
			inputs = br.readLine().trim().split(" ");
			for (String input : inputs) {
				numbers.offer(Integer.parseInt(input));
			}

			// 3. 암호 생성 실시
			generateSecret();

			for (int number : numbers) {
				sb.append(" ").append(number);
			}

			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}
}