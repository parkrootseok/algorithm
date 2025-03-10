import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_파티
 * @author parkrootseok
 */
public class Main {

	static class Village {

		int index;
		List<Road> roads = new ArrayList<>();

		public Village(int index) {
			this.index = index;
		}

	}

	static class Road implements Comparable<Road> {

		int index;
		int time;

		public Road(int index, int time) {
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(Road r) {
			return Integer.compare(this. time, r.time);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int villageNumber;
	static int roadNumber;
	static int target;
	static Village[] villages;
	static int[] times;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		villageNumber = Integer.parseInt(st.nextToken());
		roadNumber = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		villages = new Village[villageNumber + 1];
		for (int index = 1; index <= villageNumber; index++) {
			villages[index] = new Village(index);
		}

		for (int road = 0; road < roadNumber; road++) {

			st = new StringTokenizer(br.readLine(), " ");

			int org = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			villages[org].roads.add(new Road(dest, time));

		}

		int[] totalTimes = new int[villageNumber + 1];
		for (int origin = 1; origin <= villageNumber; origin++) {

			if (origin == target) {
				continue;
			}

			dijkstra(origin);
			totalTimes[origin] = times[target];

		}

		int answer = 0;
		dijkstra(target);
		for (int village = 1; village <= villageNumber; village++) {
			answer = Math.max(answer, totalTimes[village] + times[village]);
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dijkstra(int start) {

		times = new int[villageNumber + 1];
		Arrays.fill(times, Integer.MAX_VALUE);

		Queue<int[]> nodes = new ArrayDeque<>();
		nodes.offer(new int[]{start, 0});
		times[start] = 0;

		while (!nodes.isEmpty()) {

			int[] node = nodes.poll();
			int org = node[0];
			int usedTime = node[1];

			if (times[org] < usedTime) {
				continue;
			}

			for (Road road : villages[org].roads) {

				int dest = road.index;
				int time = road.time;

				if (times[dest] > usedTime + time) {
					times[dest] = usedTime + time;
					nodes.offer(new int[]{dest, times[dest]});
				}

			}

		}

	}

}