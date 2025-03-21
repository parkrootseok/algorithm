import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
			return this.ability - s.ability;
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

		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		abilities = new PriorityQueue[N];
		candidates = new PriorityQueue<>();
		
		for (int classNum = 0; classNum < N; classNum++) {

			abilities[classNum] = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine(), " ");
			for (int student = 0; student < M; student++) {
				abilities[classNum].add(Integer.parseInt(st.nextToken()));
			}

			int ability = abilities[classNum].poll();
			candidates.add(new Student(classNum, ability));
			min = Math.min(min, ability);
			max = Math.max(max, ability);

		}

		sb.append(solution());
		bw.write(sb.toString());
		bw.close();

	}

	public static int solution() {

		int diff = max - min;
		while (true) {

			// 후보 중 최소 능력치를 가진 학생 추출
			Student student = candidates.poll();
			if (abilities[student.classNumber].isEmpty()) {
				break;
			}

			// 후보 중 가장 최솟값을 가지는 학생이 속한 반에서 다음 후보가 될 학생을 삽입
			int ability = abilities[student.classNumber].poll();
			candidates.offer(new Student(student.classNumber, ability));

			// 후보중 가장 낮은 능력치
			min = candidates.peek().ability;

			// 다음 후보가 될 학생의 능력치가 가장 큰지 확인
			max = Math.max(max, ability);

			// 가장 높은 능력치와 가장 낮은 능력치의 차가 더 작다면 갱신
			diff = Math.min(diff, max - min);

		}

		return diff;

	}

}