import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_ACM Craft
 * @author parkrootseok
 */
public class Main {

	static class Building {

		int name;
		int cost;
		int inDegree;
		List<Integer> rules;

		public Building(int name, int cost) {
			this.name = name;
			this.cost = cost;
			this.inDegree = 0;
			rules = new ArrayList<>();
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testcaseNumber;
	static int buildingNumber;
	static int ruleNumber;
	static int target;

	static Building[] buildings;
	static int[] costs;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		testcaseNumber = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < testcaseNumber; testcase++) {

			st = new StringTokenizer(br.readLine(), " ");
			buildingNumber = Integer.parseInt(st.nextToken());
			ruleNumber = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine(), " ");
			buildings = new Building[buildingNumber + 1];
			for (int building = 1; building <= buildingNumber; building++) {
				buildings[building] = new Building(building, Integer.parseInt(st.nextToken()));
			}

			for (int rule = 0; rule < ruleNumber; rule++) {

				st = new StringTokenizer(br.readLine(), " ");

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				buildings[from].rules.add(to);
				buildings[to].inDegree++;

			}

			target = Integer.parseInt(br.readLine());
			topologySort();
			sb.append(costs[target]).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void topologySort() {

		costs = new int[buildingNumber + 1];
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int index = 1; index <= buildingNumber; index++) {
			if (buildings[index].inDegree == 0) {
				queue.offer(index);
				costs[index] = buildings[index].cost;
			}
		}

		while (!queue.isEmpty()) {
			Building curBuilding = buildings[queue.poll()];

			if (curBuilding.name == target) {
				return;
			}

			for (int next : curBuilding.rules) {
				Building nextBuilding = buildings[next];
				costs[next] = Math.max(costs[next], costs[curBuilding.name] + nextBuilding.cost);

				if (--nextBuilding.inDegree == 0) {
					queue.offer(next);
				}
			}
		}

	}

}