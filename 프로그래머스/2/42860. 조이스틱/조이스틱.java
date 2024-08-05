import java.util.*;

/**
 * PG_조이스틱
 * @author parkrootseok
 *
 * - A로 이루어진 글자에서 알파벳 이름을 완성해라
 *  - 3글자면 AAA, 4글자면 AAAA
 * - 상(다음 알파벳), 하(이전 알파벳), 좌,우(이동)
 *
 * 1. 위, 아래 중 더 짧은 횟수를 선택
 * 2. A가 아닌 구간까지 이동
 * 3. 오른쪽으로 이동 후 다시 왼쪽으로 가는 경우
 * 4. 왼족으로 이동 후 다시 오른쪽으로 가는 경우
 */
public class Solution {

	public int solution(String name) {

		int move = name.length() - 1;
		int result = 0;

		for (int index = 0; index < name.length(); index++) {

			char targetChar = name.charAt(index);

			// 1. 위, 아래 중 더 짧은 횟수를 선택
			result += Math.min('Z' - targetChar + 1, targetChar - 'A');

			// 2. A가 아닌 구간까지 이동
			int nextIndex = index + 1;
			while (nextIndex < name.length() && name.charAt(nextIndex) == 'A') {
				nextIndex++;
			}

			// 3. 오른쪽으로 이동 후 다시 왼쪽으로 가는 경우
			int right = (index * 2 + (name.length() - nextIndex));
			move = Math.min(move, right);

			// 4. 왼족으로 이동 후 다시 오른쪽으로 가는 경우
			int reverse =  ((name.length() - nextIndex) * 2 + index);
			move = Math.min(move, reverse);

		}

		return result + move;

	}

}
