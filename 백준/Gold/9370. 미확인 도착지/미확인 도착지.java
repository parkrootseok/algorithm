import java.io.*;
import java.util.*;

/**
 * BOJ_미확인도착지
 * @author parkrootseok
 */
public class Main {

	static final int INF = 10_000_000;

	static class Vertex {

		int name;
		List<Node> nodes;

		public Vertex(int name) {
			this.name = name;
			this.nodes = new ArrayList<>();
		}

	}

	static class Node implements Comparable<Node> {

		int name;
		int distance;

		public Node(int name, int distance) {
			this.name = name;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.distance, n.distance);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;

	static int vertexCount;
	static Vertex[] vertices;
	static int[] distances;

	static int edgeCount;
	static int targetCount;
	static int[] targets;

	static int start;
	static int visitedFrom;
	static int visitedTo;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine().trim());

		for (int t = 0; t < T; ++t) {
			input();
			dijkstra();
			print();
		}

		
		bw.write(sb.toString());
		bw.close();

	}

	public static void input() throws IOException {

		// 1. 교차로, 도로, 목적지 후보 갯수 입력
		st = new StringTokenizer(br.readLine(), " ");

		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		targetCount = Integer.parseInt(st.nextToken());

		// 2. 출발지, 지나간 교차로 정보 입력
		st = new StringTokenizer(br.readLine(), " ");

		start = Integer.parseInt(st.nextToken());
		visitedFrom = Integer.parseInt(st.nextToken());
		visitedTo = Integer.parseInt(st.nextToken());

		// 3. 도로 정보 입력
		vertices = new Vertex[vertexCount + 1];
		for (int vCount = 0; vCount <= vertexCount; vCount++) {
			vertices[vCount] = new Vertex(vCount);
		}

		for (int eCount = 0; eCount < edgeCount; eCount++) {

			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) * 2;

			if (a == visitedFrom && b == visitedTo || a == visitedTo && b == visitedFrom) {
				d--;
			}

			// 양방향 도로
			vertices[a].nodes.add(new Node(b, d));
			vertices[b].nodes.add(new Node(a, d));

		}

		// 4. 목적지 후보 입력
		targets = new int[targetCount];
		for (int tCount = 0; tCount < targetCount; tCount++) {
			targets[tCount] = Integer.parseInt(br.readLine().trim());
		}
		Arrays.sort(targets);

	}

	public static void dijkstra() {

		boolean[] isVisited = new boolean[vertexCount + 1];
		distances = new int[vertexCount + 1];
		Arrays.fill(distances, INF);

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0));
		distances[start] = 0;

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			int cVertex = node.name;
			int cDistance = node.distance;

			if (isVisited[cVertex]) {
				continue;
			}

			isVisited[cVertex] = true;

			for (Node n : vertices[cVertex].nodes) {

				int nVertex = n.name;
				int nDistance = n.distance;

				if (distances[nVertex] > cDistance + nDistance) {
					distances[nVertex] = cDistance + nDistance;
					queue.offer(new Node(nVertex, distances[nVertex]));
				}

			}

		}

	}

	public static void print() {

		for (int target : targets) {

			if (distances[target] % 2 == 1) {
				sb.append(target).append(" ");
			}

		}

		sb.append("\n");

	}

}