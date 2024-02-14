import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * SWEA_1218_괄호 짝짓기
 * @author parkrootseok
 * 
 * - 괄호문자들로 이루어진 문자열이 주어지고
 * - 괄호들의 짝이 맞는지 판별
 * 
 * 1. 괄호 문자열에 대한 길이를 받고
 * 2. 괄호 문자열을 받는다.
 * 3. 괄호 문자열을 탐색하면서
 *  3-1. 여는 괄호는 푸시
 *  3-2. 닫는 괄호라면 피크값을 확인하여 같은 종류의 여는 괄호인지 확인
 *   3-2-1. 스택에 아무 괄호가 없을 경우 종료
 *   3-2-2. 같은 종류의 괄호가 아니라면 유효하지 않으므로 종료
 * 4. 스택에 괄호가 남아있는지 확인
 */

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static Map<Character, Character> brackets = new HashMap<>();
	
	static int tcNumber;
	static int length;
	
	static int isValid;
	static String bracketStr;
	static Stack<Character> stack;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 키 : 여는 괄호 / 값 : 닫는 괄호
		brackets.put('(', ')');
		brackets.put('[', ']');
		brackets.put('{', '}');
		brackets.put('<', '>');
		
		for(int curTC = 1; curTC <= 10; curTC++) {
			
			// 1. 괄호 문자열에 대한 길이를 받고
			length = Integer.parseInt(br.readLine().trim());

			// 2. 괄호 문자열을 받는다.
			bracketStr = br.readLine().trim();

			// 3. 괄호 문자열을 탐색하면서
			isValid = 1;
			stack = new Stack<>();
			for (char bracket : bracketStr.toCharArray()) {

				// 3-1. 여는 괄호는 푸시
				if(brackets.containsKey(bracket)) {
					stack.push(bracket);
				} 
				
				// 3-2. 닫는 괄호라면 피크값을 확인하여 같은 종류의 여는 괄호인지 확인
				else {
					
					
					//  3-2-1. 스택에 아무 괄호가 없을 경우 종료
					if(stack.isEmpty()) {
						isValid = 0;
						break;
					}
					
					//  3-2-2. 같은 종류의 괄호가 아니라면 유효하지 않으므로 종료
					// 닫는 괄호를 
					if(bracket != brackets.get(stack.pop())) {
						isValid = 0;
						break;
					}
					
				}

			}
			
			// 4. 스택에 괄호가 남아있는지 확인
			if(!stack.isEmpty()) {
				isValid = 0;
			}
			
			
			sb.append("#").append(curTC).append(" ").append(isValid).append("\n");
			
		}

		bw.write(sb.toString());
		bw.close();

	}

}