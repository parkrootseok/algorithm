import java.util.*;

/**
 * PG_더맵게
 * @author parkrootseok
 *
 * - 모든 음식의 스코빌 지수를 K이상으로 만들자
 *   - 섞은 스코빌 지수 = 가장 작은 + ( 다음으로 작은 * 2 )
 *
 * 1. 스코빌 지수를 PQ에 삽입
 * 2. PQ에서 가장 우선순위가 높은 값이 K보다 작다면 반복 수행
 *  2-1. 가장 스코빌 지수가 낮은 음식을 선택
 *  2-2. PQ에 남은 음식이 없지 않다면 다음으로 낮은 음식을 선택
 *  2-3. 가장 낮은 2개의 음식의 스코빌 지수를 섞어 저장
 *  2-4. 만약 두 개의 음식을 섞은 결과가 K보다 작으면서 더이상 조합할 재료가 없다면 실패
 */
class Solution {

    public static int answer;
    
    public int solution(int[] scoville, int K) {

		// 1. 스코빌 지수를 PQ에 삽입
		PriorityQueue<Integer> scovillePQ = new PriorityQueue<>();
		for (int s : scoville) {
			scovillePQ.offer(s);
		}

		// 2. PQ에서 가장 우선순위가 높은 값이 K보다 작다면 카운팅
		int answer = 0;
		while (scovillePQ.peek() < K) {

			// 2-1. 가장 스코빌 지수가 낮은 음식을 선택
			int first = scovillePQ.poll();
			int second = 0;

			// 2-2. PQ에 남은 음식이 없지 않다면 다음으로 낮은 음식을 선택
			if (!scovillePQ.isEmpty()) {
				second = scovillePQ.poll();
			}

			// 2-3. 가장 낮은 2개의 음식의 스코빌 지수를 섞어 저장
			scovillePQ.offer(first + (second * 2));

			// 2-4. 만약 두 개의 음식을 섞은 결과가 K보다 작으면서 더이상 조합할 재료가 없다면 실패
			if (scovillePQ.size() == 1 && scovillePQ.peek() < K) {
				return -1;
			}

			answer++;

		}

		return answer;

	}
    
}