import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import javax.swing.text.html.ListView;

/**
 * BOJ_최소비용구하기2
 * @author parkrootseok
 */
public class Main {

	static class City implements Comparable<City> {

		int name;
		int min = Integer.MAX_VALUE;
		List<AdjacentCity> adjacentCities = new ArrayList<>();

		public City(int name) {
			this.name = name;
		}

		@Override
		public int compareTo(City c) {
			return Integer.compare(this.min, c.min);
		}

	}

	static class AdjacentCity {

		int to;
		int weight;

		public AdjacentCity(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int cityCount;
	public static int busCount;
	public static City[] cities;
	public static int[] preCities;

	public static int org;
	public static int dst;


	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		cityCount = Integer.parseInt(br.readLine().trim());
		busCount = Integer.parseInt(br.readLine().trim());

		cities = new City[cityCount + 1];
		for (int count = 1; count <= cityCount; count++) {
			cities[count] = new City(count);
		}

		for (int count = 0; count < busCount; count++) {

			String[] inputs = br.readLine().trim().split(" ");
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			int weight = Integer.parseInt(inputs[2]);

			cities[from].adjacentCities.add(new AdjacentCity(to, weight));

		}

		String[] inputs = br.readLine().trim().split(" ");
		org = Integer.parseInt(inputs[0]);
		dst = Integer.parseInt(inputs[1]);

		preCities = new int[cityCount + 1];
		dijkstra();
		sb.append(cities[dst].min).append("\n");

		int end = dst;
		Stack<Integer> route = new Stack<>();

		route.push(end);
		while (preCities[end] != 0) {
			route.push(preCities[end]);
			end = preCities[end];
		}

		sb.append(route.size()).append("\n");
		while(!route.isEmpty()) {
			sb.append(route.pop()).append(" ");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dijkstra() {

		PriorityQueue<City> queue = new PriorityQueue<>();
		cities[org].min = 0;
		queue.add(cities[org]);

		boolean[] isVisited = new boolean[cityCount + 1];
 		while (!queue.isEmpty()) {

			City city = queue.poll();

			if (isVisited[city.name]) {
				continue;
			}

			isVisited[city.name] = true;

			for (AdjacentCity adj : city.adjacentCities) {

				int to = adj.to;
				int weight = adj.weight;

				if (cities[to].min > city.min + weight) {
					cities[to].min = city.min + weight;
					preCities[to] = city.name;
					queue.add(cities[to]);
				}

			}

		}

	}

}