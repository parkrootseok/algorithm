import java.io.*;
import java.util.*;

/**
 * BOJ_강의실배정
 * @author parkrootseok
 */
public class Main {

	static class Room implements Comparable<Room> {

		int start;
		int end;

		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Room r) {
			if (this.start == r.start) {
				return Integer.compare(this.end, r.end);
			}
			return Integer.compare(this.start, r.start);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		Queue<Room> rooms = new PriorityQueue<>();
		for (int id = 0; id < N; id++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			rooms.offer(new Room(s, e));
		}

		Queue<Integer> result = new PriorityQueue<>();
		result.offer(rooms.poll().end);
		
		while (!rooms.isEmpty()) {

			Room room = rooms.poll();

			// 연속으로 강의실을 사용할 수 있는 경우
			if (result.peek() <= room.start) {
				// 강의실 사용 시간 연장
				result.poll();
			}

			// 강의실 추가
			result.offer(room.end);

		}

		sb.append(result.size());
		bw.write(sb.toString());
		bw.close();

	}

}