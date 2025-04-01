import java.io.*;
import java.util.*;

/**
 * BOJ_퍼레이드
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int[][] roads;
	static List<int[]> edges;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		roads = new int[N][N];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			roads[from][to] = roads[to][from] = time;
			edges.add(new int[]{from, to});
		}

		int[][] originTimes = floydWarshall();
		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			int temp = roads[from][to];
			roads[from][to] = roads[to][from] = 0;
			int[][] curTimes = floydWarshall();
			roads[from][to] = roads[to][from] = temp;

			int count = 0;
			for (int f = 0; f < N; f++) {
				for (int t = 0; t < f; t++) {
					if (originTimes[f][t] != curTimes[f][t]) {
						count++;
					}
				}
			}

			sb.append(count).append(" ");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int[][] floydWarshall() {

		final int INF = 10_000_000;

		int[][] times = new int[N][N];
		for (int from = 0; from < N; from++) {
			for (int to = 0; to < N; to++) {

				if (from == to) {
					continue;
				}

				if (roads[from][to] != 0) {
					times[from][to] = roads[from][to];
				} else {
					times[from][to] = INF;
				}
			}
		}

		for (int stopover = 0; stopover < N; stopover++) {
			for (int from = 0; from < N; from++) {
				for (int to = 0; to < N; to++) {
					times[from][to] = Math.min(times[from][to], times[from][stopover] + times[stopover][to]);
				}
			}
		}

		return times;

	}

}