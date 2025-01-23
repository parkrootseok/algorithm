import java.io.*;
import java.util.*;

/**
 * BOJ_오큰수
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[] numbers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		numbers = new int[N];
		Arrays.fill(numbers, -1);

		Stack<Integer> stack = new Stack<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int index = 0; index < N; ++index) {
			stack.push(Integer.parseInt(st.nextToken()));
		}

		Stack<Integer> NGE = new Stack<>();
		NGE.push(stack.pop());
		for (int index = N - 2; 0 <= index; --index) {

			int curNumber = stack.pop();

			while (!NGE.isEmpty()) {

				int peek = NGE.peek();
				if (curNumber < peek) {
					numbers[index] = peek;
					break;
				} else {
					NGE.pop();
				}

			}

			NGE.push(curNumber);

		}

		for (int number : numbers) {
			sb.append(number).append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int NGE(int start) {

		Stack<Integer> stack = new Stack<>();

		for (int index = N - 1; start < index; --index) {

			if (numbers[start] < numbers[index]) {
				stack.push(numbers[index]);
			}

		}

		return stack.isEmpty()? -1 : stack.pop();

	}

}