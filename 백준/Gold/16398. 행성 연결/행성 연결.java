import java.io.*;
import java.util.*;

/**
 * BOJ_행성연결
 * @author parkrootseok
 */
public class Main {

	static class Flow implements Comparable<Flow> {

		int from;
		int to;
		int cost;

		public Flow(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Flow f) {
			return Integer.compare(this.cost, f.cost);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int planetNumber;
	static Queue<Flow> flows;
	static int[] unf;
	static long totalCost;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		planetNumber = Integer.parseInt(br.readLine());

		unf = new int[planetNumber + 1];
		flows = new PriorityQueue<>();
		for (int from = 1; from <= planetNumber; from++) {

			unf[from] = from;
			st = new StringTokenizer(br.readLine(), " ");

			for (int to = 1; to <= from; to++) {

				int cost = Integer.parseInt(st.nextToken());
				if (0 < cost) {
					flows.offer(new Flow(from, to, cost));
					flows.offer(new Flow(to, from, cost));
				}

			}

		}

		kruskal();
		sb.append(totalCost);
		bw.write(sb.toString());
		bw.close();

	}

	public static void kruskal() {

		int connectionCount = 0;
		while (!flows.isEmpty() && connectionCount < planetNumber - 1) {

			Flow f = flows.poll();

			if (find(f.from) != find(f.to)) {
				union(f.from, f.to);
				connectionCount++;
				totalCost += f.cost;
			}

		}

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
			return a;
		}

		return unf[a] = find(unf[a]);
	}

}