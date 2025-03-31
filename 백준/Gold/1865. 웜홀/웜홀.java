import java.io.*;
import java.util.*;

/**
 * BOJ_웜홀
 * @author parkrootseok
 */
public class Main {

	static class City {
		int index;
		List<Edge> edges;

		public City(int index) {
			this.index = index;
			this.edges = new ArrayList<>();
		}
	}

	static class Edge {
		int dest;
		int time;

		public Edge(int dest, int time) {
			this.dest = dest;
			this.time = time;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int TC;
	static int N;
	static int M;
	static int W;
	static City[] cities;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {

			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			cities = new City[N + 1];
			for (int n = 0; n <= N; n++) {
				cities[n] = new City(n);
			}

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine(), " ");

				int org = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				cities[org].edges.add(new Edge(dest, time));
				cities[dest].edges.add(new Edge(org, time));
			}

			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(br.readLine(), " ");

				int org = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				cities[org].edges.add(new Edge(dest, time * -1));
			}

			sb.append(bellmanFord() ? "YES" : "NO").append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean bellmanFord() {

		int INF = 100_000_000;
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;

		for (int count = 1; count <= N; count++) {
			for (int cur = 1; cur <= N; cur++) {
				for (Edge edge : cities[cur].edges) {
					if (dist[edge.dest] > dist[cur] + edge.time) {
						dist[edge.dest] = dist[cur] + edge.time;

						if (count == N) {
							return true;
						}
					}
				}
			}
		}

		return false;

	}

}