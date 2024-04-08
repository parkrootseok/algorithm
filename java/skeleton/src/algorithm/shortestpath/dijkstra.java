package algorithm.shortestpath;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra
 * @author parkrootseok
 *
 * - 최단경로를 구하기 위한 알고리즘
 *
 * 1. 임의의 정점의 비용을 0으로 초기화 후 큐에 모든 정점 삽입
 * 2. 인접 정점들을 탐색하여 최소 비용으로 초기화
 *  2-1. 이미 최단 경로를 찾은 정점은 스킵
 *  2-2. 인접 정점을 탐색
 *   2-2-1. 현재 정점까지의 비용과 인접 정점으로 향하는 비용을 합한 것이 현재 인접 비용보다 적다면 갱신
 */
public class dijkstra {

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
	static final int TOTAL_VERTEX_NUMBER = 6;
	static boolean[] isVisited;
	static Graph graph;

	public static void main(String[] args) {

		graph = new Graph(TOTAL_VERTEX_NUMBER + 1);

		// 1. 정점 초기화
		for (int name = 0; name <= TOTAL_VERTEX_NUMBER; name++) {
			graph.vertices[name] = new Vertex(name);
		}

		// 2. 정점을 추가
		graph.vertices[1].adjacentVertices.add(new AdjacentVertex(2, 2));
		graph.vertices[1].adjacentVertices.add(new AdjacentVertex(3, 5));
		graph.vertices[1].adjacentVertices.add(new AdjacentVertex(4, 1));

		graph.vertices[2].adjacentVertices.add(new AdjacentVertex(3, 3));
		graph.vertices[2].adjacentVertices.add(new AdjacentVertex(4, 2));

		graph.vertices[3].adjacentVertices.add(new AdjacentVertex(2, 3));
		graph.vertices[3].adjacentVertices.add(new AdjacentVertex(6, 5));

		graph.vertices[4].adjacentVertices.add(new AdjacentVertex(3, 3));
		graph.vertices[4].adjacentVertices.add(new AdjacentVertex(5, 1));

		graph.vertices[5].adjacentVertices.add(new AdjacentVertex(3, 1));
		graph.vertices[5].adjacentVertices.add(new AdjacentVertex(6, 2));

		// 3. 다익스트라 알고리즘 수행
		dijkstra(1);

		// 4. 알고리즘 수행 결과 확인
		for (int idx = 1; idx <= TOTAL_VERTEX_NUMBER; idx++) {
			System.out.println(graph.vertices[idx].cost);
		}

	}

	public static void dijkstra(int randomVertex) {

		isVisited = new boolean[TOTAL_VERTEX_NUMBER + 1];

		// 1. 임의의 정점의 비용을 0으로 초기화 후 큐에 임의의 정점 삽입
		PriorityQueue<Vertex> vertexQ = new PriorityQueue<>();
		graph.vertices[randomVertex].cost = 0;
		vertexQ.add(graph.vertices[randomVertex]);

		// 2. 인접 정점들을 탐색하여 최소 비용으로 초기화
		while (!vertexQ.isEmpty()) {

			Vertex from = vertexQ.poll();

			// 2-1. 이미 최단 경로를 찾은 정점은 스킵
			if (isVisited[from.name]) {
				continue;
			}

			isVisited[from.name] = true;

			// 2-2. 인접 정점을 탐색
			for (AdjacentVertex adjVertex : from.adjacentVertices) {

				Vertex to = graph.vertices[adjVertex.name];
				int weight = adjVertex.weight;

				// 2-2-1. 현재 정점까지의 비용과 인접 정점으로 향하는 비용을 합한 것이 현재 인접 비용보다 적다면 갱신 후 추가
				if (!isVisited[to.name] &&  to.cost > from.cost + weight) {
					to.cost =  from.cost + weight;
					vertexQ.add(to);
				}
			}

		}


	}

}
