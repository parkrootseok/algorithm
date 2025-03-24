import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_대표선수
 * @author parkrootseok
 */
public class Main {

	static class Student implements Comparable<Student> {
		int classNumber;
		int ability;

		public Student(int classNumber, int ability) {
			this.classNumber = classNumber;
			this.ability = ability;
		}

		@Override
		public int compareTo(Student s) {
			return Integer.compare(this.ability, s.ability);
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static Queue<Integer>[] abilities;
	static Queue<Student> candidates;
	static int max, min;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		abilities = new PriorityQueue[N];
		candidates = new PriorityQueue<>();

		for (int n = 0; n < N; n++) {

			abilities[n] = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine(), " ");

			for (int m = 0; m < M; m++) {
				abilities[n].offer(Integer.parseInt(st.nextToken()));
			}

			int ability = abilities[n].poll();
			candidates.offer(new Student(n, ability));
			max = Math.max(max, ability);
			min = Math.min(min, ability);

		}

		sb.append(solution());
		bw.write(sb.toString());
		bw.close();

	}

	public static int solution() {
		
		int diff = max - min;
		while (true) {
			
			Student student = candidates.poll();
			
			if (abilities[student.classNumber].isEmpty()) {
				break;
			}
			
			int ability = abilities[student.classNumber].poll();
			candidates.offer(new Student(student.classNumber, ability));
			
			min = candidates.peek().ability;
			max = Math.max(max, ability);
			diff = Math.min(diff, max - min);
			
		}
		
		return diff;

	}

}