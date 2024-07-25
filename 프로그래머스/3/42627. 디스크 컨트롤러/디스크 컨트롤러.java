import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * PG_디스크컨트롤러
 * @author parkrootseok
 *
 * - 각 작업에 대해 시작 시점과 작업 소요시간이 주어지고 모든 작업들의 완료 시간 평균값의 최소를 구해라
 *
 * 1. 배열을 시간 오름차순 정렬
 * 2. 우선순위 큐를 활용하여 작업 수행
 *  2-1. 현재 시작 시간보다 전에 도착한 모든 작업을 대기큐에 추가
 *  2-2. 대기큐가 비어있다면 시작 시간 변경
 *  2-3. 대키큐에 작업이 존재하면 대기중인 작업 중 가장 빨리 끝나는 작업을 먼저 수행
 */
class Solution {

    public static class Task implements Comparable<Task> {

		int startAt;
		int runningTime;

		public Task(int startAt, int runningTime) {
			this.startAt = startAt;
			this.runningTime = runningTime;
		}

		@Override
		public int compareTo(Task o) {
			return Integer.compare(this.runningTime, o.runningTime);
		}

	}
    
    public static int answer;
    public static PriorityQueue<Task> waitPQ;

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
	}

	public int solution(int[][] jobs) {

		// 1. 배열을 시간 오름차순 정렬
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);


		waitPQ = new PriorityQueue<>();

		// 2. 우선순위 큐를 활용하여 작업 수행
		int index = 0;
		int nextStartAt = 0;
		int totalNeedTime = 0;
		int finishTaskNumber = 0;
		while (finishTaskNumber < jobs.length) {

			// 2-1. 현재 시작 시간보다 전에 도착한 모든 작업을 대기큐에 추가
			while(index < jobs.length && jobs[index][0] <= nextStartAt) {
				waitPQ.add(new Task(jobs[index][0], jobs[index][1]));
				index++;
			}

			// 2-2. 대기큐가 비어있다면 시작 시간 변경
			if (waitPQ.isEmpty()) {
				nextStartAt = jobs[index][0];
			}

			// 2-3. 대키큐에 작업이 존재하면 대기중인 작업 중 가장 빨리 끝나는 작업을 먼저 수행
			else {

				Task task = waitPQ.poll();

				// 기다린 시간(현재 시간 - 시작 시간) + 작업 시간 -> 현재 작업을 처리하는데 총 걸린 시간
				totalNeedTime += (nextStartAt - task.startAt) + task.runningTime;

				// 현재 시간 + 작업 시간 -> 다음 작업이 실행될 시간
				nextStartAt += task.runningTime;

				// 작업 완료 횟수 증가
				finishTaskNumber++;

			}

		}

		return totalNeedTime / jobs.length;

	}
}