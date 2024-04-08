package algorithm.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Kruscal
 * @author parkrootseok
 *
 * - MST를 구하기 위한 알고리즘
 *
 * 1. 모든 간선을 큐에 삽입
 * 3. 임의의 정점과 연결된 정점들을 탐색하여 최소 비용으로 초기화
 *  3-1. 두 정점이 연결된 상태가 아니라면
 *   3-1-1. mst에 간선 추가
 *   3-1-2. 두 정점을 연결
 *   3-1-3. 연결된 간선 수 증가
 */
public class Kruskal {

	static class Vertex {

		int name;

		public Vertex(int name) {
			this.name = name;
		}

	}

	static class Edge implements Comparable<Edge> {

		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge{" +
				"from=" + from +
				", to=" + to +
				", weight=" + weight +
				'}';
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static class Graph {

		Vertex[] vertices;
		List<Edge> edges;

		public Graph(int vertexNumber) {
			this.vertices = new Vertex[vertexNumber];
			this.edges = new ArrayList<>();
		}
	}

	static final int INF = Integer.MAX_VALUE;
	static final int TOTAL_VERTEX_NUMBER = 5;

	static int[] unf;
	static int[] rank;

	static Graph graph;
	static Graph mst;

	public static void main(String[] args) {

		graph = new Graph(TOTAL_VERTEX_NUMBER + 1);
		mst = new Graph(TOTAL_VERTEX_NUMBER + 1);

		// 1. 정점 초기화
		for(int name = 0; name <= TOTAL_VERTEX_NUMBER; name++) {
			graph.vertices[name] = new Vertex(name);
		}

		// 2. 간선을 추가
		graph.edges.add(new Edge(1, 2, 75));
		graph.edges.add(new Edge(1, 4, 95));
		graph.edges.add(new Edge(1, 3, 51));
		graph.edges.add(new Edge(2, 4, 9));
		graph.edges.add(new Edge(4, 3, 19));
		graph.edges.add(new Edge(4, 5, 42));
		graph.edges.add(new Edge(3, 5, 31));

		// 3. 크루스칼 알고리즘 수행
		make();
		kruskal(1);

		// 4. 알고리즘 수행 결과 확인
		int totalCost = 0;
		for (Edge edge : mst.edges) {
			System.out.println(edge.toString());
			totalCost += edge.weight;
		}

		System.out.println(totalCost);

	}

	public static void kruskal(int randomVertex) {

		// 1. 모든 간선을 큐에 삽입
		PriorityQueue<Edge> edgeQ = new PriorityQueue<>();
		for(Edge edge : graph.edges) {
			edgeQ.add(edge);
		}

 		// 3. 임의의 정점과 연결된 정점들을 탐색하여 최소 비용으로 초기화
		int connectEdgeNumber = 0;
		while (connectEdgeNumber < TOTAL_VERTEX_NUMBER - 1) {

			Edge edge = edgeQ.poll();
			Vertex to = graph.vertices[edge.to];
			Vertex from = graph.vertices[edge.from];
			int weight = edge.weight;

			// 3-1. 두 정점이 연결된 상태가 아니라면
			if (find(to.name) != find(from.name)) {

				// 3-1-1. mst에 간선 추가
				mst.edges.add(new Edge(to.name, from.name, weight));

				// 3-1-2. 두 정점을 연결
				union(to.name, from.name);

				// 3-1-3. 연결된 간선 수 증가
				connectEdgeNumber++;

			}

		}

	}

	public static void make() {

		unf = new int[TOTAL_VERTEX_NUMBER + 1];
		rank = new int[TOTAL_VERTEX_NUMBER + 1];

		// 1. 자기 자신을 대표로 가지는 집합을 생성
		for (int element = 1; element <= TOTAL_VERTEX_NUMBER; element++) {
			unf[element] = element;
		}

	}

	public static int find(int element) {

		// 1. 찾는 원소가 속한 집합의 대표라면 반환
		if (element == unf[element]) {
			return element;
		}

		// 2. 아니라면 자신이 속해있는 대표를 탐색
		// 추가적으로 현재 자신의 대표자를 변경하여 경로 압축을 실행
		return unf[element] = find(unf[element]);

	}

	public static void union(int elementA, int elementB) {

		// 1. 각 원소가 속한 집합의 대표 탐색
		int representationA = find(elementA);
		int representationB = find(elementB);

		// 2. 대표가 같다면 이미 같은 집합이므로 종료
		if (representationA == representationB) {
			return;
		}

		// 3. 대표가 같지 않고 A 원소가 속한 집합의 랭크가 더 높다면 A 밑으로 B를 이동 후 종료
		if (rank[representationA] > unf[representationB]) {
			unf[representationB] = representationA;
			return;
		}

		// 4. B 원소가 속한 랭크다 더 크다면 B가 더 높다면 A를 이동
		unf[representationA] = representationB;

		// 5. 두 집합의 랭크가 같다면 밑으로 들어간 집합의 랭크를 증가
		if (rank[representationA] == rank[representationB]) {
			rank[representationB]++;
		}

	}

}
