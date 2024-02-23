import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_2164_카드2
 * @author parkrootseok
 * 
 * - N장 카드(1 - N 번호)
 * - 1번이 맨 위, N이 맨 아래
 * - 카드 한 장 까지 아래를 반복 수행
 *   - 제일 위를 버린다 후 아래로 이동
 *   - 마지막에 남는 카드를 구해라
 *   
 * 1. 몇 장의 카드를 사용할지 입력
 * 2. 카드를 큐에 저장
 * 3. 카드를 버린다
 * 4. 맨 위에 있는 카드를 다시 아래로 삽입
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int cardNumber;
	static Queue<Integer> cards;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 몇 장의 카드를 사용할지 입력
		cardNumber = Integer.parseInt(br.readLine().trim());

		// 2. 카드를 큐에 저장
		cards = new ArrayDeque<Integer>();
		for (int card = 1; card <= cardNumber; card++) {
			cards.add(card);
		}

		// 카드가 1장이 된다면 종료
		while (cards.size() > 1) {

			// 3. 카드를 버린다
			cards.poll();
			
			// 4. 맨 위에 있는 카드를 다시 아래로 삽입
			cards.offer(cards.poll());

		}

		sb.append(cards.peek()).append("\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}

}