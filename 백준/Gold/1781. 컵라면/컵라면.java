import java.io.*;
import java.util.*;

/**
 * BOJ_컵라면
 * @author parkrootseok
 */
public class Main {

	static class Problem implements Comparable<Problem> {

		int deadline;
		int noodleCount;

		public Problem(int deadline, int noodleCount) {
			this.deadline = deadline;
			this.noodleCount = noodleCount;
		}

		@Override
		public int compareTo(Problem p) {
			if (this.deadline == p.deadline) {
				return Integer.compare(p.noodleCount, this.noodleCount);
			}
			return Integer.compare(this.deadline, p.deadline);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static Queue<Problem> problems;
	static int limit;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		problems = new PriorityQueue<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");

			int deadline = Integer.parseInt(st.nextToken());
			int noodleCount = Integer.parseInt(st.nextToken());

			problems.offer(new Problem(deadline, noodleCount));
			limit = Math.max(limit, deadline);
		}

		Queue<Integer> queue = new PriorityQueue<>();
		for (int day = 1; day <= limit; day++) {

			// 현재 날짜에 풀 수 있는 문제를 모두 삽입
			while (!problems.isEmpty() && problems.peek().deadline == day) {
				queue.offer(problems.poll().noodleCount);
			}

			// 풀 수 있는 문제 갯수가 현재 날짜를 초과하면 가장 낮은 숫자를 삭제
			while (day < queue.size()) {
				queue.poll();
			}

		}

		long answer = 0;
		while (!queue.isEmpty()) {
			answer += queue.poll();
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

}