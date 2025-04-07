import java.io.*;
import java.util.*;

/**
 * BOJ_전력난
 * @author parkrootseok
 */
public class Main {

	static class Edge implements Comparable<Edge> {

		int origin;
		int destination;
		int distance;

		public Edge(int origin, int destination, int distance) {
			this.origin = origin;
			this.destination = destination;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.distance, e.distance);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int M;
	static int N;
	static int total;

	static int[] unf;
	static List<int[]> mns;
	static List<Integer> totals;
	static List<Queue<Edge>> graphs;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		int TC = 0;
		mns = new ArrayList<>();
		totals = new ArrayList<>();
		graphs = new ArrayList<>();
		while (true) {

			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			if (M == 0 && N == 0) {
				break;
			}

			mns.add(new int[]{M, N});
			total = 0;
			graphs.add(new PriorityQueue<>());

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");

				int org = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());

				total += distance;
				graphs.get(TC).offer(new Edge(org, dest, distance));
			}

			totals.add(total);
			TC++;

		}

		for (int tc = 0; tc < TC; tc++) {
			M = mns.get(tc)[0];
			total = totals.get(tc);

			sb.append(total - kruskal(graphs.get(tc))).append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static long kruskal(Queue<Edge> edges) {

		unf = new int[M];
		for (int m = 0; m < M; m++) {
			unf[m] = m;
		}

		int linkedHouseCount = 0;
		long totalDistance = 0;
		while (!edges.isEmpty() && linkedHouseCount < M) {

			Edge cur = edges.poll();

			if (find(cur.origin) != find(cur.destination)) {
				linkedHouseCount++;
				totalDistance += cur.distance;
				union(cur.origin, cur.destination);
			}

		}

		return totalDistance;

	}

	public static void union(int a, int b) {

		int findA = find(a);
		int findB = find(b);

		if (findA == findB) {
			return;
		}

		unf[findB] = findA;

	}

	public static int find(int a) {

		if (unf[a] == a) {
			return unf[a];
		}

		return unf[a] = find(unf[a]);

	}

}