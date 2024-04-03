import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_11724_연결요소의개수
 * 
 * @author parkrootseok
 * 
 * - 무방향 그래프에서 연결 요소의 개수를 구하라
 * 
 * 1. 테스트 케이스 입력 
 *  1-1. 정점과 간선 개수 받기
 *  1-2. 간선 정보 받기
 * 2. DFS를 활용하여 연결 요소 개수 카운팅
 *  2-1. 현재 정점을 방문하지 않았다면 카운팅 후 탐색 시작
 **/

class Main {

	static class Graph {

		Vertex[] vertices;

		public Graph(int vertexNumber) {
			this.vertices = new Vertex[vertexNumber];
		}

	}
	
	static class Vertex {
		
		int index;
		List<Vertex> adjacentVertices;
		
		public Vertex(int index) {
			this.index = index;
			this.adjacentVertices = new ArrayList<>();
		}
		
	}
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static Graph graph;
	static int vertexNumber;
	static int edgeNumber;
	static int connectedComponentCount;
	static boolean[] isVisited;

	public static void input() throws IOException {

		// 1-1. 정점과 간선 개수 받기
		inputs = br.readLine().trim().split(" ");
		vertexNumber = Integer.parseInt(inputs[0]);
		edgeNumber = Integer.parseInt(inputs[1]);
		
		
		// 1-2. 간선 정보 받기
		graph = new Graph(vertexNumber + 1);
		
		for (int idx = 1; idx <= vertexNumber; idx++) {
			graph.vertices[idx] = new Vertex(idx);
		}
		
		for (int curEdge = 0; curEdge < edgeNumber; curEdge++) {
			
			inputs = br.readLine().trim().split(" ");
			
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			
			graph.vertices[from].adjacentVertices.add(graph.vertices[to]);
			graph.vertices[to].adjacentVertices.add(graph.vertices[from]);
		
		}

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 입력
		input();

		// 2. DFS를 활용하여 연결 요소 개수 카운팅
		isVisited = new boolean[vertexNumber + 1];
		for (int curVertex = 1; curVertex <= vertexNumber; curVertex++) {
			
			// 2-1. 현재 정점을 방문하지 않았다면 카운팅 후 탐색 시작
			if (!isVisited[curVertex]) {
				connectedComponentCount++;
				bfs(graph.vertices[curVertex]);
			}
			
		}
		
		sb.append(connectedComponentCount);
		bw.write(sb.toString());
		bw.close();
		return;

	}
	
	public static void dfs(Vertex curVertex) {
		
		// 현재 정점에 대한 방문 처리
		isVisited[curVertex.index] = true;
		
		for (Vertex vertex : curVertex.adjacentVertices) {
			
			// 이미 방문한 정점일 때 스킵
			if (isVisited[vertex.index]) {
				continue;
			}
			
			// 다음 정점 방문을 위한 재귀 호출
			dfs(vertex);
			
		}
		
	}
	
	public static void bfs(Vertex curVertex) {
		
		Queue<Vertex> vertexQ = new ArrayDeque<>();
		vertexQ.add(curVertex);
		isVisited[curVertex.index] = true; 
		
		while (!vertexQ.isEmpty()) {
			
			Vertex vertex = vertexQ.poll();
					
			for (Vertex nextVertex : vertex.adjacentVertices) {
				
				// 이미 방문한 정점일 때 스킵
				if (isVisited[nextVertex.index]) {
					continue;
				}
				
				// 다음 정점 방문을 위해 큐에 추가
				vertexQ.add(nextVertex);
				isVisited[nextVertex.index] = true;
				
			}
			
		}
		
	}

}