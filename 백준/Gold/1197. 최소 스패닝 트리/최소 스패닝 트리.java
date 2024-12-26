import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_최소스패닝트리
 * @author parkrootseok
 */
class Main {

	public static class Vertex {

		int index;
		List<Node> adjacentVertices;

		public Vertex(int index) {
			this.index = index;
			adjacentVertices = new ArrayList<>();
		}

	}

	public static class Node implements Comparable<Node> {

		int index;
		int weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.weight, n.weight);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int vertexCount;
	static int edgeCount;

	static Vertex[] vertices;
	static int[] cost;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();
		sb.append(prim());
		
		bw.write(sb.toString());
		bw.close();

	}

	public static long prim() {

		long sum = 0;
		boolean[] isVisited = new boolean[vertexCount + 1];
		Queue<Node> queue = new PriorityQueue<>();

		cost[1] = 0;
		queue.offer(new Node(1, cost[1]));

		while(!queue.isEmpty()) {

			Node cVertex = queue.poll();

			if (isVisited[cVertex.index]) {
				continue;
			}

			sum += cVertex.weight;
			isVisited[cVertex.index] = true;

			for (Node adjVertex : vertices[cVertex.index].adjacentVertices) {

				if (!isVisited[adjVertex.index] && cost[adjVertex.index] > adjVertex.weight) {
					cost[adjVertex.index] = adjVertex.weight;
					queue.offer(new Node(adjVertex.index, cost[adjVertex.index]));
				}

			}

		}
		
		return sum;

	}

	public static void input() throws IOException {

		st = new StringTokenizer(br.readLine(), " ");
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		vertices = new Vertex[vertexCount + 1];
		cost = new int[vertexCount + 1];
		for (int index = 1; index <= vertexCount; index++) {
			vertices[index] = new Vertex(index);
			cost[index] = Integer.MAX_VALUE;
		}

		for (int eCount = 0; eCount < edgeCount; eCount++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			vertices[from].adjacentVertices.add(new Node(to, weight));
			vertices[to].adjacentVertices.add(new Node(from, weight));

		}

	}

}