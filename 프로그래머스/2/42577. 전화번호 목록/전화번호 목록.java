import java.util.Arrays;
import java.util.HashMap;

/**
 * Programmers_전화번호목록
 * @author parkrootseok
 *
 * - 전화번보후에서 한 번호가 다른 번호의 접두어인 경우를 확인
 *   - 있다면 false, 없다면 true를 리턴
 *
 * 1. 모든 전화번호를 해시맵에 저장
 * 2. 해시맵에 저장된 전화번호 중 동일한 부분 문자열이 있는지 확인
 * 	2-1. 현재 문자열의 모든 부분 문자열을 만들어 이를 포함하는 키값이 있는지 확인
 * 		2-1-1. 0 ~ length 길이의 부분 문자열을 생성
 * 		2-1-2. 부분 문자열과 같은 키를 가지는 문자열이 있는지 확인
 **/

public class Solution {

	public boolean solution(String[] phone_book) {

		boolean answer = true;

		// 1. 모든 전화번호를 해시맵에 저장
		HashMap<String, Integer> set = new HashMap<>();
		for (String tel : phone_book) {
			set.put(tel, 0);
		}

		// 2. 해시맵에 저장된 전화번호 중 동일한 부분 문자열이 있는지 확인
		for (int curStringIdx = 0; curStringIdx < phone_book.length; curStringIdx++) {

			// 2-1. 현재 문자열의 모든 부분 문자열을 만들어 이를 포함하는 키값이 있는지 확인
			for (int length = 1; length < phone_book[curStringIdx].length(); length++) {

				// 2-1-1. 0 ~ length 길이의 부분 문자열을 생성
				String prefix = phone_book[curStringIdx].substring(0, length);

				// 2-1-2. 부분 문자열과 같은 키를 가지는 문자열이 있는지 확인
				if (set.containsKey(prefix)) {

					// 있다면 false로 변경 후 반복문 종료
					answer = false;
					break;
				}

			}

		}

		return answer;

	}

}