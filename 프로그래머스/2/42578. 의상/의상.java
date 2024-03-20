import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Programmers_의상
 *
 * 의상 조합
 *  - 종류별 1가지 의상만 착용
 *  - 일부는 같아도 상관 없음
 *  - 최소 한개 이상의 의상은 입음
 *
 *  1. 종류 별 보유중인 옷의 개수를 카운트
 *  2. 모든 조합수를 계산
 *  3. 하나의 옷도 입지 않은 경우를 예외 처리
 *
 */
public class Solution {

	public int solution(String[][] clothes) {

		// 1. 종류 별 보유중인 옷의 개수를 카운트
		HashMap<String, Integer> map = new HashMap<>();
		for(int idx = 0; idx < clothes.length; idx++) {

			// 단, 안 입는 경우도 존재하므로 초기값은 1로 설정
			map.put(clothes[idx][1], map.getOrDefault(clothes[idx][1], 1) + 1);

		}

		// 2. 모든 조합수를 계산
		int combination = 1;
		for (Entry<String, Integer> entry : map.entrySet()) {

			combination *= entry.getValue();

		}

		// 3. 하나의 옷도 입지 않은 경우를 예외 처리
		combination--;

		return combination;

	}

}