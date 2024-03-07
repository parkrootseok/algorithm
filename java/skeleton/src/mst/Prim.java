package mst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim
 * @author parkrootseok
 *
 * - MST를 구하기 위한 알고리즘
 *
 * 1. 임의의 정점의 비용을 0으로 초기화 후 큐에 모든 정점 삽입
 * 2. 인접 정점들을 탐색하여 최소 비용으로 초기화
 *  2-1. 이미 MST에 포함된 정점은 스킵
 *  2-2. 인접 정점을 탐색
 *   2-2-1. 인접 정점의 비용이 더 크고 MST에 포함되지 않은 정점이라면 갱신 후 추가
 */
public class Prim {

	static class Vertex implements Comparable<Vertex> {

		int name;
		int cost;
		List<AdjacentVertex> adjacentVertices;

		public Vertex(int name) {
			this.name = name;
			this.cost = INF;
			this.adjacentVertices = new ArrayList<>();
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static class AdjacentVertex {

		int name;
		int weight;

		public AdjacentVertex(int name, int weight) {
			this.name = name;
			this.weight = weight;
		}

	}

	public static class Graph {

		Vertex[] vertices;

		public Graph(int vertexNumber) {
			this.vertices = new Vertex[vertexNumber];
		}

	}

	static final int INF = Integer.MAX_VALUE;
	static final int TOTAL_VERTEX_NUMBER = 5;

	static boolean[] isVisited;
	static Graph graph;

	public static void main(String[] args) {

		graph = new Graph(TOTAL_VERTEX_NUMBER + 1);

		// 1. 정점 초기화
		for (int name = 0; name <= TOTAL_VERTEX_NUMBER; name++) {
			graph.vertices[name] = new Vertex(name);
		}

		// 2. 정점을 추가
		graph.vertices[1].adjacentVertices.add(new AdjacentVertex(2, 75));
		graph.vertices[1].adjacentVertices.add(new AdjacentVertex(4, 95));
		graph.vertices[1].adjacentVertices.add(new AdjacentVertex(3, 51));

		graph.vertices[2].adjacentVertices.add(new AdjacentVertex(1, 75));
		graph.vertices[2].adjacentVertices.add(new AdjacentVertex(4, 9));

		graph.vertices[3].adjacentVertices.add(new AdjacentVertex(4, 19));
		graph.vertices[3].adjacentVertices.add(new AdjacentVertex(5, 31));

		graph.vertices[4].adjacentVertices.add(new AdjacentVertex(1, 95));
		graph.vertices[4].adjacentVertices.add(new AdjacentVertex(2, 9));
		graph.vertices[4].adjacentVertices.add(new AdjacentVertex(3, 19));
		graph.vertices[4].adjacentVertices.add(new AdjacentVertex(5, 42));

		graph.vertices[5].adjacentVertices.add(new AdjacentVertex(3, 31));
		graph.vertices[5].adjacentVertices.add(new AdjacentVertex(4, 42));

		// 3. 프림 알고리즘 수행
		prim(1);

		// 4. 알고리즘 수행 결과 확인
		int totalCost = 0;
		for (Vertex vertex : graph.vertices) {
			if(vertex.cost != INF) {
				totalCost += vertex.cost;
			}
		}

		System.out.println(totalCost);

	}

	public static void prim(int randomVertex) {

		isVisited = new boolean[TOTAL_VERTEX_NUMBER + 1];

		// 1. 임의의 정점의 비용을 0으로 초기화 후 큐에 임의의 정점 삽입
		PriorityQueue<Vertex> vertexQ = new PriorityQueue<>();
		graph.vertices[randomVertex].cost = 0;
		vertexQ.add(graph.vertices[randomVertex]);


		// 2. 인접 정점들을 탐색하여 최소 비용으로 초기화
		while (!vertexQ.isEmpty()) {

			Vertex from = vertexQ.poll();

			// 2-1. 이미 MST에 포함된 정점은 스킵
			if (isVisited[from.name]) {
				continue;
			}

			isVisited[from.name] = true;

			// 2-2. 인접 정점을 탐색
			for (AdjacentVertex adjVertex : from.adjacentVertices) {

				Vertex to = graph.vertices[adjVertex.name];
				int weight = adjVertex.weight;

				// 2-2-1. 인접 정점의 비용이 더 크고 MST에 포함되지 않은 정점이라면 갱신 후 추가
				if (!isVisited[to.name] &&  to.cost > weight) {
					to.cost = weight;
					vertexQ.add(to);
				}
			}

		}


	}

}

	// Edge{from=4, to=2, weight=9}
	// Edge{from=3, to=4, weight=19}
	// Edge{from=5, to=3, weight=31}
	// Edge{from=3, to=1, weight=51}
