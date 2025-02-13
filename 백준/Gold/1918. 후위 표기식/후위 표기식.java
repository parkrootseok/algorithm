import java.io.*;
import java.util.*;

/**
 * BOJ_후위표기식
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static String expression;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		expression = br.readLine().trim();
		Stack<Character> stack = new Stack<>();
		for (char e : expression.toCharArray()) {

			// 알파벳은 바로 출력
			if (Character.isAlphabetic(e)) {
				sb.append(e);
				continue;
			}
			// 여는 괄호는 스택에 추가
			if (e == '(') {
				stack.push(e);
			}

			// 닫는 괄호가 나오면
			else if (e == ')') {
				// 여는 괄호가 나오기 전까지 모두 출력
				while (!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				// 한 후에, 여는 괄호도 삭제
				if (!stack.isEmpty()) {
					stack.pop();
				}
			}

			// 연산 기호일 경우는
			else  {

				// 자신보다 우선순위가 높은 모든 연산자는 출력
				while (!stack.isEmpty() && (getPriority(stack.peek()) >= getPriority(e))) {
					sb.append(stack.pop());
				}

				stack.push(e);

			}

	

		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		bw.write(sb.toString());
		bw.close();

	}
	
	public static int getPriority(char operation) {
		
		if (operation == '*' || operation == '/') {
			return 2;
		}

		if (operation == '+' || operation == '-') {
			return 1;
		}
		
		return 0;
		
	}
	

}