import java.io.*;
import java.util.*;

/**
 * BOJ_타임머신
 * @author parkrootseok
 */
public class Main {

	private static final int INF = 100_000_000;

	static class Route {

		int from;
		int to;
		int time;

		public Route(int from, int to, int time) {
			this.from = from;
			this.to = to;
			this.time = time;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int cityNumber;
	static Route[] routes;
	static int routeNUmber;
	static long[] times;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		cityNumber = Integer.parseInt(st.nextToken());
		routeNUmber = Integer.parseInt(st.nextToken());

		routes = new Route[routeNUmber];
		for (int route = 0; route < routeNUmber; route++) {

			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			routes[route] = new Route(from, to, time);

		}

		if (bellmanFord()) {
			for (int city = 2; city <= cityNumber; city++) {
				if (times[city] != INF) {
					sb.append(times[city]).append("\n");
				} else {
					sb.append("-1").append("\n");
				}
			}
		} else {
			sb.append("-1").append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean bellmanFord() {

		times = new long[cityNumber + 1];
		Arrays.fill(times, INF);
		times[1] = 0;

		for (int city = 0; city <= cityNumber; city++) {

			for (int index = 0; index < routeNUmber; index++) {

				Route curRoute = routes[index];

				int from = curRoute.from;
				int to = curRoute.to;
				long time = curRoute.time;

				if (times[from] != INF && times[to] > times[from] + time) {
					times[to] = times[from] + time;

					if (city == cityNumber) {
						return false;
					}

				}

			}

		}

		return true;

	}

}