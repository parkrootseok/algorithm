import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ_최단경로
 * @author parkrootseok
 */
class Main {

	public static final int INF = Integer.MAX_VALUE;

	public static class Vertex {

		int index;
		List<Node> adjacentVertices;

		public Vertex(int index) {
			this.index = index;
			adjacentVertices = new ArrayList<>();
		}

	}

	public static class Node implements Comparable<Node> {

		int vertex;
		int distance;

		public Node(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.distance, n.distance);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int vertexCount;
	static int edgeCount;
	static int startingPoint;

	static Vertex[] graph;
	static int[] distance;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();
		dijkstra();

		for (int vCount = 1; vCount <= vertexCount; vCount++) {
			if (distance[vCount] != INF) {
				sb.append(distance[vCount]).append("\n");
			} else {
				sb.append("INF").append("\n");
			}
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dijkstra() {

		isVisited = new boolean[vertexCount + 1];
		PriorityQueue<Node> queue = new PriorityQueue<>();

		distance[startingPoint] = 0;
		queue.offer(new Node(startingPoint, distance[startingPoint]));

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			int cVertex = node.vertex;
			int cDistance = node.distance;

			if (isVisited[cVertex]) {
				continue;
			}

			isVisited[cVertex] = true;

			for (Node to : graph[cVertex].adjacentVertices) {

				int nVertex = to.vertex;
				int nCost = to.distance;

				if (distance[nVertex] > cDistance + nCost) {
					distance[nVertex] = cDistance + nCost;
					queue.offer(new Node(nVertex, distance[nVertex]));
				}

			}

		}

	}

	public static void input() throws IOException {

		st = new StringTokenizer(br.readLine(), " ");
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		graph = new Vertex[vertexCount + 1];
		distance = new int[vertexCount + 1];
		for (int vCount = 1; vCount <= vertexCount; vCount++) {
			distance[vCount] = INF;
			graph[vCount] = new Vertex(vCount);
		}

		startingPoint = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

		for (int eCount = 0; eCount < edgeCount; eCount++) {

			st = new StringTokenizer(br.readLine(), " ");
			int org = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[org].adjacentVertices.add(new Node(dest, cost));

		}

	}

}