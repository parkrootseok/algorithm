import java.io.*;
import java.util.*;

/**
 * BOJ_컵라면
 * @author parkrootseok
 */
public class Main {

	static class Homework implements Comparable<Homework> {

		int deadline;
		int count;

		public Homework(int deadline, int count) {
			this.deadline = deadline;
			this.count = count;
		}

		@Override
		public int compareTo(Homework h) {
			if (this.deadline == h.deadline) {
				return Integer.compare(h.count, this.count);
			}
			return Integer.compare(this.deadline, h.deadline);
		}


	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int size;
	static int limitOfDay;
	static Queue<Homework> homeworks;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		size = Integer.parseInt(br.readLine());
		homeworks = new PriorityQueue<>();
		for (int index = 0; index < size; index++) {
			st = new StringTokenizer(br.readLine(), " ");

			int deadline = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());

			homeworks.offer(new Homework(deadline, count));
			limitOfDay = Math.max(limitOfDay, deadline);
		}

		Queue<Integer> completedHomeworks = new PriorityQueue<>();
		for (int today = 1; today <= limitOfDay; today++) {

			// 오늘까지 끝내야 하는 숙제를 모두 기록
			while (!homeworks.isEmpty() && homeworks.peek().deadline <= today) {
				completedHomeworks.offer(homeworks.poll().count);
			}

			// 오늘까지 끝내야 하는 숙제가 주어진 기간내에 모두 끝낼 수 없다면, 가장 컵라면을 적게 주는 숙제는 버리기
			while (today < completedHomeworks.size()) {
				completedHomeworks.poll();
			}

		}

		int count = 0;
		while (!completedHomeworks.isEmpty()) {
			count += completedHomeworks.poll();
		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

}