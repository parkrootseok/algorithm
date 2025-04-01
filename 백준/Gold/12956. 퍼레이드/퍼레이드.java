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

	static int cityNumber;
	static int roadNumber;
	static int[][] roads;
	static List<int[]> edges;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		cityNumber = Integer.parseInt(st.nextToken());
		roadNumber = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		roads = new int[cityNumber][cityNumber];
		for (int index = 0; index < roadNumber; index++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			roads[from][to] = roads[to][from] = time;
			edges.add(new int[]{from, to});
		}

		int[][] times = floydWarshall();
		for (int[] edge : edges) {

			int from = edge[0];
			int to = edge[1];
			int temp = roads[from][to];

			roads[from][to] = roads[to][from] = 0;
			int[][] curTimes = floydWarshall();
			roads[from][to] = roads[to][from] = temp;

			int count = 0;
			for (int f = 0; f < cityNumber; f++) {
				for (int t = 0; t < f; t++) {
					if (times[f][t] != curTimes[f][t]) {
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

		int INF = 100_000_000;
		int[][] times = new int[cityNumber][cityNumber];
		for (int from = 0; from < cityNumber; from++) {
			for (int to = 0; to < cityNumber; to++) {

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

		for (int stopover = 0; stopover < cityNumber; stopover++) {
			for (int from = 0; from < cityNumber; from++) {
				for (int to = 0; to < cityNumber; to++) {
					times[from][to] = Math.min(times[from][to], times[from][stopover] + times[stopover][to]);
				}
			}
		}

		return times;

	}

}