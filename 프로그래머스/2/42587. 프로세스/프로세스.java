import java.util.*;

/**
 * PG_프로세스
 * @author parkrootseok
 *
 * - 프로세스는 다음과 같은 규칙에 따라 관리된다.
 *  - 대기 큐에서 대기중인 프로세스를 하나 꺼낸다.
 *  - 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 다시 큐에 넣는다.
 *  - 만약 없다면 꺼낸 프로세스를 실행한다.
 * - 중요도 배열과 프로세스 위치 정보가 주어졌을 때 해당 위치에 있는 프로세스가 몇 번째로 실행되는지 찾아라.
 * 
 * 1. 대키큐에서 프로세스를 가져온다.
 * 2. 본인보다 우선순위가 높은 큐가 있다면 다시 큐에 삽입한다.
 * 3. 없다면 실행한다
 */
class Solution {
    
    public static class Process {

		int index;
		int priority;

		public Process(int index, int priority) {
			this.index = index;
			this.priority = priority;
		}

	}
    
    public int solution(int[] priorities, int location) {

		Queue<Process> waitingQ = new ArrayDeque<>();
		for (int index = 0; index < priorities.length; index++) {
			waitingQ.add(new Process(index, priorities[index]));
		}

		Arrays.sort(priorities);

		int order = 0;
		boolean[] isFinished = new boolean[priorities.length];
		while (true) {

			// 1. 대키큐에서 프로세스를 가져온다.
			Process process = waitingQ.poll();

			// 2. 본인보다 우선순위가 높은 큐가 있다면 다시 큐에 삽입한다.
			if (priorities[(priorities.length - 1) - order] != process.priority) {
				waitingQ.offer(process);
				continue;
			}

			// 3. 없다면 실행한다
			isFinished[process.index] = true;
			order++;

			if (process.index == location) {
				break;
			}

		}

		return order;

	}
    
}