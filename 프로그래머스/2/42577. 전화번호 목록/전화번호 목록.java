import java.util.Arrays;
import java.util.HashMap;

/**
 * Programmers_전화번호목록
 * @author parkrootseok
 *
 * - 전화번보후에서 한 번호가 다른 번호의 접두어인 경우를 확인
 *   - 있다면 false, 없다면 true를 리턴
 *
 *
 **/

public class Solution {

	public boolean solution(String[] phone_book) {

		boolean answer = true;

		Arrays.sort(phone_book);
		HashMap<String, Integer> set = new HashMap<>();

		for (int curStringIdx = 0; curStringIdx < phone_book.length - 1; curStringIdx++) {

			if (phone_book[curStringIdx + 1].startsWith(phone_book[curStringIdx])) {
				answer = false;
				break;
			}

		}

		return answer;

	}

}