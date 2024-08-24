import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * BOJ_AC
 * @author parkrootseok
 *
 * - 연산 종류
 *  - R(뒤집기), D(버리기)
 * - 뒤집기
 *  - 배열에 있는 수의 순서를 뒤집는 함수
 * - 버리기
 *  - 첫 번째 인덱스에 있는 수를 버리는 함수
 * 	- 단, 배열이 비어있는 경우 에러
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int testCaseCount;
	public static String[] commands;
	public static int size;
	public static Deque<Integer> numbers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		testCaseCount = Integer.parseInt(br.readLine().trim());

		for (int count = 0; count < testCaseCount; count++) {

			boolean isFront = true;
			boolean isPossible = true;

			commands = br.readLine().trim().split("");
			size = Integer.parseInt(br.readLine().trim());
			inputs = br.readLine().trim()
				.replace("[", "")
				.replace("]", "")
				.split(",");

			numbers = new ArrayDeque<>();

			for (int idx = 0; idx < inputs.length; idx++) {
				if (!inputs[idx].equals("")) {
					numbers.offer(Integer.parseInt(inputs[idx]));
				}
			}

			for (String command : commands) {
				if (command.equals("R")) {

					if (isFront) {
						isFront = false;
					} else {
						isFront = true;
					}

				} else {

					isPossible = delete(isFront);

					if (!isPossible) {
						break;
					}

				}
			}

			if (!isPossible) {
				sb.append("error").append("\n");
				continue;
			}

			sb.append("[");

			if (isFront) {
				int s = numbers.size();
				for(int size = 0; size < s; size++) {

					sb.append(numbers.removeFirst());

					if (size != s - 1) {
						sb.append(",");
					}

				}
			} else {
				int s = numbers.size();
				for(int size = 0; size < s; size++) {

					sb.append(numbers.removeLast());

					if (size != s - 1) {
						sb.append(",");
					}

				}
			}
			sb.append("]").append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean delete(boolean isFront) {

		if (numbers.isEmpty()) {
			return false;
		}

		if (isFront) {
			numbers.removeFirst();
		} else {
			numbers.removeLast();
		}

		return true;

	}

}