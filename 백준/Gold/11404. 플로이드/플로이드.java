import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_플로이드
 * @author parkrootseok
 */
public class Main {

	static final int INF = 10_000_000;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int cityNumber;
	static int busNumber;
	static int[][] costs;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		cityNumber = Integer.parseInt(br.readLine());
		busNumber = Integer.parseInt(br.readLine());

		costs = new int[cityNumber + 1][cityNumber + 1];
		for (int from = 1; from <= cityNumber; from++) {
			Arrays.fill(costs[from], INF);
		}

		for (int bus = 0; bus < busNumber; bus++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			costs[from][to] = Math.min(costs[from][to], cost);
		}

		floydWarshall();

		for (int from = 1; from <= cityNumber; from++) {
			for (int to = 1; to <= cityNumber; to++) {
				sb.append(costs[from][to] == INF? 0 : costs[from][to]).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void floydWarshall() {

		for (int stopover = 1; stopover <= cityNumber; stopover++) {

			for (int from = 1; from <= cityNumber; from++) {

				for (int to = 1; to <= cityNumber; to++) {

					if (from == to) {
						continue;
					}

					if (costs[from][to] > costs[from][stopover] + costs[stopover][to]) {
						costs[from][to] = costs[from][stopover] + costs[stopover][to];
					}

				}
			}
		}

	}

}