import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * BOJ_미확인도착지
 * @author parkrootseok
 *
 * - 알고 있는 정보
 *  - 출발지
 *  - 최단 거리로 이동함
 *  - g, h의 교차로 사이에 있는 도로를 지나갔음
 * - 양방향 도로
 * - 목적지를 예측
 *  - 주어진 후보 중 가능한 경우만 오름차순 출력
 *
 * 1. T 입력
 * 2. 교차로, 도로, 목적지 후보 개수 입력
 * 3. 예술가들의 출발지, 교차로 시점과 종점
 * 4. 목적지 후보들 입력
 */
public class Main {

	static class City {

		int name;
		ArrayList<Node> nodes;

		public City(int name) {
			this.name = name;
			this.nodes = new ArrayList<>();
		}

	}

	static class Node implements Comparable<Node> {

		int to;
		int weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	public static final int INF = 100_000_000;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int testCount;

	public static int edgeCount, vertexCount, T;
	public static City[] cities;
	public static int[] distance;

	public static int origin;
	public static int g, h;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. T 입력
		testCount = Integer.parseInt(br.readLine().trim());

		for (int tCount = 0; tCount < testCount; tCount++){

			// 2. 교차로, 도로, 목적지 후보 개수 입력
			String[] inputs = br.readLine().trim().split(" ");
			vertexCount = Integer.parseInt(inputs[0]);
			edgeCount = Integer.parseInt(inputs[1]);
			T = Integer.parseInt(inputs[2]);

			cities = new City[vertexCount + 1];
			for (int idx = 1; idx <= vertexCount; idx++) {
				cities[idx] = new City(idx);
			}

			// 3. 예술가들의 출발지, 지나간 교차로
			inputs = br.readLine().trim().split(" ");
			origin = Integer.parseInt(inputs[0]);
			g = Integer.parseInt(inputs[1]);
			h = Integer.parseInt(inputs[2]);

			// 4. 도로 정보
			for (int eCount = 0; eCount < edgeCount; eCount++) {

				inputs = br.readLine().trim().split(" ");

				int from = Integer.parseInt(inputs[0]);
				int to = Integer.parseInt(inputs[1]);
				int weight = Integer.parseInt(inputs[2]) * 2;

				if ((from == g && to == h) || (from == h && to == g)) {
					weight--;
				}

				cities[from].nodes.add(new Node(to, weight));
				cities[to].nodes.add(new Node(from, weight));

			}

			// 5. 목적지 후보들 입력
			List<Integer> targets = new ArrayList<>();
			for (int count = 0; count < T; count++) {
				targets.add(Integer.parseInt(br.readLine().trim()));
			}

			dijkstra(origin);

			// 8. 결과 오름차순 정렬 후 출력
			Collections.sort(targets);
			for (int t : targets) {
				if (distance[t] % 2 == 1) {
					sb.append(t).append(" ");
				}
			}

			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dijkstra(int start) {

		distance = new int[vertexCount + 1];
		Arrays.fill(distance, INF);

		PriorityQueue<Node> queue = new PriorityQueue<>();
		distance[start] = 0;
		queue.add(new Node(start, 0));

		boolean[] isVisited = new boolean[vertexCount + 1];
		while (!queue.isEmpty()) {

			Node cur = queue.poll();

			if (isVisited[cur.to]) {
				continue;
			}

			isVisited[cur.to] = true;

			for (Node next : cities[cur.to].nodes) {

				if (!isVisited[next.to] &&  distance[next.to] > distance[cur.to] + next.weight) {
					distance[next.to] = distance[cur.to] + next.weight;
					queue.add(new Node(next.to, distance[next.to]));
				}

			}

		}

	}

}