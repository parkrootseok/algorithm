import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BOJ_특정한최단경로
 * @author parkrootseok
 */
public class Main {

	public static class Vertex implements Comparable<Vertex> {

		int name;
		int cost;
		ArrayList<AdjacentVertex> adjacentVertices = new ArrayList<>();

		public Vertex(int name) {
			this.name = name;
		}

		@Override
		public int compareTo(Vertex v) {
			return Integer.compare(this.cost, v.cost);
		}

	}

	public static class AdjacentVertex {

		int to;
		int weight;

		public AdjacentVertex(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

	}

	public static final int MAX = 200_000_000;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static int E;
	public static int V1;
	public static int V2;
	public static Vertex[] vertices;
	public static boolean[] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		E = Integer.parseInt(inputs[1]);

		vertices = new Vertex[N + 1];
		for (int name = 1; name <= N; name++) {
			vertices[name] =  new Vertex(name);
		}

		for (int edge = 0; edge < E; edge++) {

			inputs = br.readLine().trim().split(" ");
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			int weight = Integer.parseInt(inputs[2]);

			vertices[from].adjacentVertices.add(new AdjacentVertex(to, weight));
			vertices[to].adjacentVertices.add(new AdjacentVertex(from, weight));

		}

		inputs = br.readLine().trim().split(" ");
		V1 = Integer.parseInt(inputs[0]);
		V2 = Integer.parseInt(inputs[1]);

		isVisited = new boolean[N + 1];

		// 1 -> V1 -> V2 -> N
		int v1v2 = 0;
		v1v2 += dijkstra(1, V1);
		v1v2 += dijkstra(V1, V2);
		v1v2 += dijkstra(V2, N);

		// 1 -> V2 -> V1 ->  N
		int v2v1 = 0;
		v2v1 += dijkstra(1, V2);
		v2v1 += dijkstra(V2, V1);
		v2v1 += dijkstra(V1, N);

		int minCost = Math.min(v1v2, v2v1);

		if (minCost >= 200_000_000) {
			System.out.println("-1");
			return;
		}

		sb.append(minCost);
		bw.write(sb.toString());
		bw.close();

	}

	public static int dijkstra(int start, int end) {

		PriorityQueue<Vertex> pq = new PriorityQueue<>();

		for (int name = 1; name <= N; name++) {
			vertices[name].cost = MAX;
		}

		vertices[start].cost = 0;
		pq.add(vertices[start]);

		while (!pq.isEmpty()) {

			Vertex vertex = pq.poll();
			int cost = vertex.cost;

			if (isVisited[vertex.name]) {
				continue;
			}

			isVisited[vertex.name] = true;

			for (AdjacentVertex adjV : vertex.adjacentVertices) {

				int to = adjV.to;
				int weight = adjV.weight;

				if (vertices[to].cost > cost + weight) {
					vertices[to].cost = cost + weight;
					pq.add(vertices[to]);
				}

			}

		}

		Arrays.fill(isVisited, false);
		return vertices[end].cost;

	}

}