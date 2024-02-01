import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * BOJ_12891_DNA비밀번호
 * @author parkrootseok
 * 
 * - DNA문자열은 모든 문자가 A, C, G, T
 * - 임의의 DNA 문자열을 만들고 부분 문자열을 비밀번호로 사용
 * - 단, 문자의 개수가 특정 개수 이상
 * - 위치가 다르다면 다른 문자열(즉, 순서에 의미가 있음)
 * - 만들 수 있는 비밀번호 종류의 수를 출력
 * 
 * 1. 임의 문자열 길이와 부분 문자열 길이를 입력
 * 2. 임의 문자열 입력
 * 3. 사용할 문자 갯수에 대한 정보 입력
 * 4. 임의 문자열을 통해 subLength를 가지는 문자열을 생성하여 조건을 만족하는지 확인 후 카운팅
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuffer sb;
	static String[] inputs;

	static int randomLength;
	static int subLength;

	static int subStrCount = 0;
	static char[] dnaChars = {'A', 'C', 'G', 'T'};
	static String randomStr;
	static Map<Character, Integer> useChracterLength;

	public static boolean isDNA(Map<Character, Integer> subStringCount) {

		for (char key : useChracterLength.keySet()) {

			// 카운팅한 결과에 포함하지 않으면 종료
			if(!subStringCount.containsKey(key)) {
				return false;
			}
			
			// 카운팅한 결과가 조건보다 작다면 종료
			if (subStringCount.get(key) < useChracterLength.get(key)) {
				return false;
			}

		}
		
		return true;
		
	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuffer();

		// 1. 임의 문자열 길이와 부분 문자열 길이를 입력
		inputs = br.readLine().trim().split(" ");
		randomLength = Integer.parseInt(inputs[0]);
		subLength = Integer.parseInt(inputs[1]);

		// 2. 임의 문자열 입력
		randomStr = br.readLine().trim();

		// 3. 사용할 문자 갯수에 대한 정보 입력
		inputs = br.readLine().trim().split(" ");
		useChracterLength = new HashMap<>();
		for (int idx = 0; idx < dnaChars.length; idx++) {
			
			// 단, 0인 것은 포함시키지 않는다.
			if (Integer.parseInt(inputs[idx]) != 0) {
				useChracterLength.put(dnaChars[idx], Integer.parseInt(inputs[idx]));
			}
			
		}

		// 4. 임의 문자열을 통해 subLength를 가지는 문자열의 구성 요소를 카운팅
		Map<Character, Integer> subString = new HashMap<>();
		for(int index = 0; index < subLength; index++) {
			subString.put(randomStr.charAt(index), subString.getOrDefault(randomStr.charAt(index), 0) + 1);
		}
		
		if(isDNA(subString)) {
			subStrCount++;
		}
		
		
		for (int curIndex = 1; curIndex < randomLength - subLength + 1; curIndex++) {
		
			// 사용하지 않을 부분을 삭제
			// 이전에 사용한 문자에 대한 카운트를 감소
			char remove = randomStr.charAt(curIndex - 1);
			subString.put(remove, subString.getOrDefault(remove, 0) - 1);
			
			// 사용할 부분 마지막에 추가
			// 사용할 문자에 대한 카운트 증가
			char use = randomStr.charAt(curIndex + subLength - 1);
			subString.put(use, subString.getOrDefault(use, 0) + 1);
			
			// 부분 문자열이 조건을 만족하는지 확인
			if(isDNA(subString)) {
				subStrCount++;
			}
			
		}

		sb.append(subStrCount).append("\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}

}