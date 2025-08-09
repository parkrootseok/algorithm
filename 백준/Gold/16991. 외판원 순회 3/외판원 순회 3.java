import java.io.*;
import java.util.*;

/**
 * BOJ_외판원순회3
 * @author parkrootseok
 */
public class Main {

	static class City {
		int x;
		int y;

		public City(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double getDistance(City c) {
			return Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static City[] cities;
	static double[][] DP;
	static int allVisitedBit;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		cities = new City[N];
		DP = new double[N][(int) Math.pow(2, N)];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cities[n] = new City(x, y);
			Arrays.fill(DP[n], -1.0);
		}

		allVisitedBit = (1 << N) - 1;
		sb.append(tps(0, 1));
		bw.write(sb.toString());
		bw.close();
	}

	public static double tps(int to, int path) {
		if(allVisitedBit == path) {
			return cities[to].getDistance(cities[0]);
		}

		if (DP[to][path] != -1.0f) {
			return DP[to][path];
		}

		DP[to][path] = Float.MAX_VALUE;
		for (int from = 0; from < N; from++) {
			if ((path & (1 << from)) != 0) {
				continue;
			}
			DP[to][path] = Math.min(DP[to][path], tps(from, path | (1 << from)) + cities[to].getDistance(cities[from])) ;
		}

		return DP[to][path];
	}

}
