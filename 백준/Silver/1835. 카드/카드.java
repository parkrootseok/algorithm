import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * BOJ_1835_카드
 * @author parkrootseok
 *
 * - 첫 카드를 뒤로 옮겼을 때 가장 위에 있는 카드는 반복 횟수와 같음
 * - 위와 같은 결과가 나오는 순서를 출력
 *
 *
 * 1. 카드의 개수를 받는다.
 *
 **/
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int cardNumber;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 카드의 개수를 받는다.
		cardNumber = Integer.parseInt(br.readLine().trim());

		// 2. 카드가 1개인 경우 예외처리
		if (cardNumber == 1) {
			sb.append(1);
		} else {

			// 3. 카드가 1개가 아니라면 마지막 과정부터 거꾸로 진행한다.
			Deque<Integer> cardQ = new ArrayDeque<>();

			cardQ.addFirst(cardNumber);
			cardQ.addFirst(cardNumber - 1);

			for (int order = cardNumber - 1; ; order--) {

				// 3-1. N번 뒤에 있는 카드를 앞으로 옮긴다.
				for (int curOrder = order; 1 <= curOrder; curOrder--)  {
					cardQ.addFirst(cardQ.pollLast());
				}

				// 3-2. 마지막 순번이면 종료한다.
				if (order == 1) {
					break;
				}

				// 3-3. 가장 앞에 있는 카드를 책상으로 옮긴다.
				cardQ.addFirst(order - 1);

			}

			for (int card : cardQ) {
				sb.append(card).append(" ");
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

}