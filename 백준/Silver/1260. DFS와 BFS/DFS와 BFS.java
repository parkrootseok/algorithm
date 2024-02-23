import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/***
 * BOJ_1260_DFS와BFS
 * @author parkrootseok
 * 
 * - DFS와 BFS로 탐색한 결과를 출력
 * - 단, 정점 번호가 가장 작은 것을 먼저 방문
 * 
 * 1. 정점의 갯수, 간선의 갯수, 탐색을 시작할 정점의 번호를 입력 받는다.
 * 2. 간선이 연결하는 두 정점의 번호를 받는다
 * 3. 작은 정점부터 방문하기 위해 그래프를 정렬 수행
 * 4. DFS 수행
 *  4-1. 방문한 정점에 대한 방문 표시 및 기록
 *  4-2. 현재 정점과 연결된 정점들을 탐색
 * 5. BFS 수행
 *  5-1. 현재 정점과 연결된 정점들을 탐색
 *   5-1-1. 방문했던 정점이면 패스하고
 *   5-1-2. 그렇지 않다면 방문할 정점으로 추가
 * 
 */

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int vertexNumber;
	static int edgeNumber;
	static int originVertex;
	static List<List<Integer>> graph;

	static boolean[] isVistedForDfs;
	static boolean[] isVistedForBfs;

	public static void dfs(int curVertex) {

		// 4-1.방문한 정점에 대한 방문 표시 및 기록
		if (!isVistedForDfs[curVertex]) {
			isVistedForDfs[curVertex] = true;
			sb.append(curVertex).append(" ");
		}

		// 4-2. 현재 정점과 연결된 정점들을 탐색
		for (Integer vertecies : graph.get(curVertex)) {

			// 방문했던 노드라면 패스
			if (isVistedForDfs[vertecies]) {
				continue;
			}

			dfs(vertecies);

		}

	}

	public static void bfs(int originVertex) {

		Queue<Integer> needVisitVertices = new ArrayDeque<>();
		isVistedForBfs[originVertex] = true;
		needVisitVertices.add(originVertex);

		while (!needVisitVertices.isEmpty()) {

			int curVertex = needVisitVertices.poll();

			// 방문한 정점 기록
			sb.append(curVertex).append(" ");

			// 5-1. 현재 정점과 연결된 정점들을 탐색
			for (Integer vertecies : graph.get(curVertex)) {

				// 5-1-1. 방문하지 않았던 정점이면 추가 후 방문 표시
				if (!isVistedForBfs[vertecies]) {
					isVistedForBfs[vertecies] = true;
					needVisitVertices.add(vertecies);

				}

			}

		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정점의 갯수, 간선의 갯수, 탐색을 시작할 정점의 번호를 입력 받는다.
		inputs = br.readLine().trim().split(" ");
		vertexNumber = Integer.parseInt(inputs[0]);
		edgeNumber = Integer.parseInt(inputs[1]);
		originVertex = Integer.parseInt(inputs[2]);

		// 2. 간선이 연결하는 두 정점의 번호를 받는다
		graph = new ArrayList<>();
		for (int curVertex = 0; curVertex <= vertexNumber; curVertex++) {
			graph.add(new ArrayList<>());
		}

		int to, from;
		for (int curEdge = 0; curEdge < edgeNumber; curEdge++) {

			inputs = br.readLine().trim().split(" ");

			to = Integer.parseInt(inputs[0]);
			from = Integer.parseInt(inputs[1]);

			graph.get(to).add(from);
			graph.get(from).add(to);

		}
		
		// 3. 작은 정점부터 방문하기 위해 그래프를 정렬 수행
		for (int curVertex = 1; curVertex <= vertexNumber; curVertex++) {
			Collections.sort(graph.get(curVertex));
		}

		isVistedForDfs = new boolean[vertexNumber + 1];
		isVistedForBfs = new boolean[vertexNumber + 1];

		// 4. DFS 수행
		dfs(originVertex);
		sb.append("\n");

		// 5. BFS 수행
		bfs(originVertex);
		sb.append("\n");

		bw.write(sb.toString());
		bw.close();

	}

}