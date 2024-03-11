import java.util.HashMap;
import java.util.TreeSet;

/**
 * Programmers_폰켓몬
 * @author parkrootseok
 *
 * - 포켓몬
 *  - 같은 포켓몬은 같은 번호를 가지고 있음
 * - 보유할 수 있는 포켓몬 수
 *   - N / 2
 * - 최대한 많은 종류의 포켓몬을 가지는 방법을 찾아라
 *
 * 1. 현재 가지고 있는 모든 포켓몬을 해시맵에 삽입
 * 2. 해시맵 사이즈가 N/2보다 작다면 출력
 * 3. 해시맵 사이즈가 N/2보다 크다면 N/2 출력
 **/

public class Solution {

	public int solution(int[] nums) {

		// 1. 현재 가지고 있는 모든 포켓몬을 해시맵에 삽입
		HashMap<Integer, Integer> pocketmons = new HashMap<>();
		for (int pocketmon : nums) {
			pocketmons.put(pocketmon, pocketmons.getOrDefault(pocketmon, 0) + 1);
		}

		// 2. 해시맵 사이즈가 N/2보다 작다면 출력
		if (pocketmons.size() < nums.length / 2) {
			return pocketmons.size();
		}

		// 3. 해시맵 사이즈가 N/2보다 크다면 N/2 출력
		else {
			return nums.length / 2;
		}

	}

}