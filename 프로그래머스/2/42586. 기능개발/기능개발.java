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
 * 2. 현재 필요한 기간이 큐에 존재하는 우선순위가 가장 높은 원소보다 크다면 큐 초기화 및 개수를 저장
 */
public class Solution {

	public int[] solution(int[] progresses, int[] speeds) {

		List<Integer> deployCount = new ArrayList<>();
		Queue<Integer> workQ = new ArrayDeque<>();
		for (int idx = 0; idx < progresses.length; idx++) {

			// 1. 기능을 완수하기 위해 걸리는 시간을 계산
			float remainProgress = 100 - progresses[idx];
			int needDay = (int) Math.ceil(remainProgress / speeds[idx]);

			// 2. 현재 필요한 기간이 큐에 존재하는 우선순위가 가장 높은 원소보다 크다면 큐 초기화 및 개수를 저장
			// 현재 우선순위가 가장 높은 기능보다 필요한 기간이 길다는 것은 다른 기간에 배포된다는 것을 의미함
			if (!workQ.isEmpty() && workQ.peek() < needDay) {
				deployCount.add(workQ.size());
				workQ.clear();
			}

			workQ.offer(needDay);

		}

		deployCount.add(workQ.size());

		int[] result = new int[deployCount.size()];
		for (int idx = 0; idx < deployCount.size(); idx++) {
			result[idx] = deployCount.get(idx);
		}

		return result;

	}

}