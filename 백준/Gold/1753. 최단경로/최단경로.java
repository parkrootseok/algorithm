import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * BOJ_1753_최단경로
 * @author parkrootseok
 * 
 * - 시작점에서 모든 정점으로의 최단 경로를 구하는 프로그램을 작성해라
 * - 다익스트라해라
 * 
 * 1. 정점과 간선의 개수를 받자
 * 2. 시작 정점의 번호를 받자
 * 3. 간선 정보를 받자
 * 4. 다익스트라 알고리즘을 수행
 *  4-1. 시작 정점은 비용을 0으로 초기화
 *  4-2. 큐를 탐색
 *   4-2-1. from에 대한 인접 정점을 탐색
 *   4-2-2. 현재 정점의 비용이 from으로 부터 오는 간선의 가중치 + from 비용보다 크다면 갱신
 * 5. 수행 후 결과를 출력
 **/

public class Main {

	static class Vertex implements Comparable<Vertex>{

		int name;
		int cost;
		List<AdjacentVertex> adjacentVertices;

		public Vertex(int name) {
			this.name = name;
			this.cost = Integer.MAX_VALUE;
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

		AdjacentVertex(int to, int weight) {
			this.name = to;
			this.weight = weight;
		}

	}

	static class Graph {

		Vertex[] vertices;

		Graph(int vertexNumber) {
			this.vertices = new Vertex[vertexNumber + 1];
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static Graph graph;
	static int vertexNumber;
	static int edgeNumber;
	static int startVertex;
	static boolean[] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정점과 간선의 개수를 받자
		inputs = br.readLine().trim().split(" ");
		vertexNumber = Integer.parseInt(inputs[0]);
		edgeNumber = Integer.parseInt(inputs[1]);

		// 2. 시작 정점의 번호를 받자
		startVertex = Integer.parseInt(br.readLine().trim());

		// 3. 간선 정보를 받자
		graph = new Graph(vertexNumber);
		for (int vertex = 0; vertex <= vertexNumber; vertex++) {
			graph.vertices[vertex] = new Vertex(vertex);
		}

		int from;
		int to;
		int weight;
		for (int edge = 0; edge < edgeNumber; edge++) {

			inputs = br.readLine().trim().split(" ");
			from = Integer.parseInt(inputs[0]);
			to = Integer.parseInt(inputs[1]);
			weight = Integer.parseInt(inputs[2]);

			graph.vertices[from].adjacentVertices.add(new AdjacentVertex(to, weight));

		}

		// 4. 다익스트라 알고리즘을 수행
		dijkstra();

		// 5. 수행 후 결과를 출력
		for (int vertex = 1; vertex <= vertexNumber; vertex++) {

			long cost = graph.vertices[vertex].cost;

			// 5-1. 경로가 존재하는 경우
			if (cost != Integer.MAX_VALUE) {
				sb.append(cost).append("\n");
			}

			// 5-2. 경로가 존재하지 않는 경우
			else {
				sb.append("INF").append("\n");
			}

		}
		
		bw.write(sb.toString());
		bw.close();

	}

	public static void dijkstra() {

		PriorityQueue<Vertex> vertexQ = new PriorityQueue<>();
		
		// 4-1. 시작 정점은 비용을 0으로 초기화
		graph.vertices[startVertex].cost = 0;
		vertexQ.add(graph.vertices[startVertex]);
	
		// 4-2. 큐를 탐색
		while (!vertexQ.isEmpty()) {

			Vertex from = vertexQ.poll();

			// 4-2-1. from에 대한 인접 정점을 탐색
			for (AdjacentVertex adjVertex : from.adjacentVertices) {

				Vertex to = graph.vertices[adjVertex.name];

				// 4-2-2. 현재 정점의 비용이 from으로 부터 오는 간선의 가중치 + from 비용보다 크다면 갱신
				if(to.cost > from.cost + adjVertex.weight) {
					to.cost = from.cost + adjVertex.weight;
					vertexQ.add(to);
				}
				
			}

		}

	}

}