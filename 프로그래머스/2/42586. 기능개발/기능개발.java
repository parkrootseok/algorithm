import java.util.*;

/**
 * Programmers_기능개발
 *
 * - 기능
 *  -> 진도가 100%일 때 서비스 반영 가능
 *  -> 우선 순위가 높은 기능을 배포할 때 우선 순위가 낮은 기능도 같이 배포
 * - 배포가 이루어질 때 마다 몇개의 배포가 이루어지는지 확인
 *
 * 1. 기능을 완수하기 위해 걸리는 시간을 계산
 * 2. 큐에 삽입된 예상 완료 기간을 탐색
 *   2-1. 현재 큐에서 가장 우선순위가 높은 원소가 현재 완료 기간보다 짧다면 배포
 *   2-2. 배포된 기능에 대한 개수를 기록
 */
public class Solution {

	public int[] solution(int[] progresses, int[] speeds) {

		// 1. 기능을 완수하기 위해 걸리는 시간을 계산
		Queue<Integer> workQ = new ArrayDeque<>();
		for (int idx = 0; idx < progresses.length; idx++) {

			int completeProgress = 100;
			float remainProgress = 100 - progresses[idx];
			int needDay = (int) Math.ceil(remainProgress / speeds[idx]);

			workQ.add(needDay);

		}

		// 2. 큐에 삽입된 예상 완료 기간을 탐색
		List<Integer> deployCount = new ArrayList<>();
		while (!workQ.isEmpty()) {

			int curSize = workQ.size();
			int curWorkNeedDay = workQ.poll();

			// 2-1. 현재 큐에서 가장 우선순위가 높은 원소가 현재 완료 기간보다 짧거나 같다면 배포
			while (!workQ.isEmpty() && workQ.peek() <= curWorkNeedDay) {
				workQ.poll();
			}

			// 2-2. 배포된 기능 개수(기존 Queue Size - 현재 Queue Size)를 기록
			deployCount.add(curSize - workQ.size());

		}

		int[] result = new int[deployCount.size()];
		for (int idx = 0; idx < deployCount.size(); idx++) {
			result[idx] = deployCount.get(idx);
		}

		return result;

	}

}