import java.io.*;
import java.util.*;

/**
 * BOJ_네트워크복구
 * @author parkrootseok
 */
public class Main {

	public static final int INF = 100_000;

	public static class Computer {

		int name;
		List<Circuit> circuits = new ArrayList<>();

		public Computer(int name) {
			this.name = name;
		}

	}

	public static class Circuit implements Comparable<Circuit> {

		int name;
		int cost;

		public Circuit(int name, int cost) {
			this.name = name;
			this.cost = cost;
		}

		@Override
		public int compareTo(Circuit c) {
			return Integer.compare(this.cost, c.cost);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int computerCount;
	static int circuitCount;

	static Computer[] computers;
	static int[] costs;
	static int[] prev;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		computerCount = Integer.parseInt(st.nextToken());
		circuitCount = Integer.parseInt(st.nextToken());

		computers = new Computer[computerCount + 1];
		for (int computer = 0; computer <= computerCount; computer++) {
			computers[computer] = new Computer(computer);
		}

		for (int circuit = 0; circuit < circuitCount; circuit++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			computers[from].circuits.add(new Circuit(to, cost));
			computers[to].circuits.add(new Circuit(from, cost));

		}

		costs = new int[computerCount + 1];
		prev = new int[computerCount + 1];
		Arrays.fill(costs, INF);

		sb.append(dijkstra()).append("\n");
		for (int computer = 2; computer <= computerCount; computer++) {
			if (prev[computer] != 0) {
				sb.append(computer).append(" ").append(prev[computer]).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.close();

	}

	public static int dijkstra() {

		int repairCount = -1;
		isVisited = new boolean[computerCount + 1];
		Queue<Circuit> queue = new PriorityQueue<>();
		queue.add(new Circuit(1, 0));
		costs[1] = 0;

		while (!queue.isEmpty()) {

			Circuit node = queue.poll();
			int cComputer = node.name;
			int cCost = node.cost;

			if (isVisited[cComputer]) {
				continue;
			}

			isVisited[cComputer] = true;
			repairCount++;

			for (Circuit circuit : computers[cComputer].circuits) {

				int nComputer = circuit.name;
				int nCost = circuit.cost;

				if (costs[nComputer] > cCost + nCost) {
					costs[nComputer] = cCost + nCost;
					queue.offer(new Circuit(nComputer, costs[nComputer]));
					prev[nComputer] = cComputer;
				}

			}

		}

		return repairCount;

	}

}