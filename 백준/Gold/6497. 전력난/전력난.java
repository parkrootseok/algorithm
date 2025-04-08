import java.io.*;
import java.util.*;

/**
 * BOJ_전력난
 * @author parkrootseok
 */
public class Main {

	static class Road implements Comparable<Road> {

		int org;
		int dest;
		int meter;

		public Road(int org, int dest, int meter) {
			this.org = org;
			this.dest = dest;
			this.meter = meter;
		}

		@Override
		public int compareTo(Road r) {
			return Integer.compare(this.meter, r.meter);
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int M;
	static int N;
	static int[] unf;
	static Queue<Road> roads;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		while (true) {

			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			if (M == 0 || N == 0) {
				break;
			}

			int totalMeter = 0;
			roads = new PriorityQueue<>();;

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");

				int org = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
				int meter = Integer.parseInt(st.nextToken());

				totalMeter += meter;
				roads.offer(new Road(org, dest, meter));
			}

			sb.append(totalMeter - dijkstra()).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int dijkstra() {

		unf = new int[M];
		for (int parent = 0; parent < M; parent++) {
			unf[parent] = parent;
		}

		int linkedRoadCount = 0;
		int usedMeter = 0;
		while (linkedRoadCount != M - 1) {

			Road road = roads.poll();

			if (find(road.org) != find(road.dest)) {
				linkedRoadCount++;
				usedMeter += road.meter;
				union(road.org, road.dest);
			}

		}

		return usedMeter;

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