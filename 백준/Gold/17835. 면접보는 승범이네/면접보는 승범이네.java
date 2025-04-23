import java.io.*;
import java.util.*;

/**
 * BOJ_면접보는승범이네
 * @author parkrootseok
 */
public class Main {

	static class City {

		int id;
		List<Edge> edges;

		public City(int id) {
			this.id = id;
			edges = new ArrayList<>();
		}
	}

	static class Edge implements Comparable<Edge> {

		int city;
		long distance;

		public Edge(int city, long distance) {
			this.city = city;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge e) {
			return Long.compare(this.distance, e.distance);
		}

	}

	static final long INF = Long.MAX_VALUE;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int K;
	static City[] cities;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		cities = new City[N + 1];
		for (int id = 0; id <= N; id++) {
			cities[id] = new City(id);
		}

		// 면접장에서 도시 거리를 구하기 위해 역방향으로 추가
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			cities[to].edges.add(new Edge(from, cost));
		}

		// 면접장으로 이동할 수 있는 슈퍼 노드 추가
		st = new StringTokenizer(br.readLine(), " ");
		for (int k = 0; k < K; k++) {
			cities[0].edges.add(new Edge(Integer.parseInt(st.nextToken()), 0));
		}

		long[] distances = dijkstra();
		int maxCity = 0;
		long maxDist = distances[0];
		for (int city = 1; city <= N; city++) {
			if (distances[city] != INF && maxDist < distances[city]) {
				maxDist = distances[city];
				maxCity = city;
			}
		}

		sb.append(maxCity).append("\n").append(maxDist);
		bw.write(sb.toString());
		bw.close();

	}

	public static long[] dijkstra() {

		long[] distance = new long[N + 1];
		Arrays.fill(distance, INF);

		Queue<Edge> edges = new PriorityQueue<>();
		edges.offer(new Edge(0, 0));
		distance[0] = 0;

		while (!edges.isEmpty()) {

			Edge edge = edges.poll();
			int cCity = edge.city;
			long cDist = edge.distance;

			if (distance[cCity] < cDist) {
				continue;
			}

			for (Edge next : cities[cCity].edges) {

				int nCity = next.city;
				long nDist = next.distance;

				if (distance[nCity] > cDist + nDist) {
					distance[nCity] = cDist + nDist;
					edges.offer(new Edge(nCity, distance[nCity]));
				}

			}

		}

		return distance;

	}

}