import java.io.*;
import java.util.*;

/**
 * BOJ_행성연결
 * @author parkrootseok
 */
public class Main {

	static class Flow implements Comparable<Flow> {

		int origin;
		long cost;

		public Flow(int origin, long cost) {
			this.origin = origin;
			this.cost = cost;
		}

		@Override
		public int compareTo(Flow f) {
			return Long.compare(this.cost, f.cost);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int planetNumber;
	static int[][] map;
	static long[] costs;
	static long totalCost;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		planetNumber = Integer.parseInt(br.readLine());

		map = new int[planetNumber][planetNumber];
		costs = new long[planetNumber];
		for (int from = 0; from < planetNumber; from++) {
			costs[from] = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for (int to = 0; to < from; to++) {
				map[from][to] = map[to][from] = Integer.parseInt(st.nextToken());
			}
		}

		prim();
		sb.append(totalCost);
		bw.write(sb.toString());
		bw.close();

	}

	public static void prim() {

		boolean[] isVisited = new boolean[planetNumber + 1];
		Queue<Flow> flows = new PriorityQueue<>();
		flows.offer(new Flow(0, 0));
		costs[0] = 0;

		while (!flows.isEmpty()) {

			Flow f = flows.poll();

			if (isVisited[f.origin]) {
				continue;
			}

			isVisited[f.origin] = true;
			totalCost += costs[f.origin];

			for (int destination = 0; destination < planetNumber; destination++) {

				int needCost = map[f.origin][destination];
				if (needCost == 0 || costs[destination] <= needCost) {
					continue;
				}

				costs[destination] = needCost;
				flows.add(new Flow(destination, costs[destination]));

			}

		}

	}

}