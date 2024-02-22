import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * SWEA_3124_최소스패닝트리
 * @author parkrootseok
 * 
 * - 그래프에 대한 정보를 받아 크루스칼 알고리즘을 적용하여 MST의 가중치 출력
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 정점과 간선의 갯수를 받는다.
 * 3. 크루스칼 알고리즘 실행
 * 3-1. 간선의 가중치 오름차순으로 정렬
 * 3-2. 간선을 탐색
 *  3-2-1. 간선의 두 정점이 사이클을 생성하면 패스
 *  3-2-2. 간선의 두 정점이 사이클을 생성하지 않으면 union하고 가중치를 누적
 *  3-2-3. 추가된 간선이 정점의 갯수 - 1을 만족하면 종료
 **/

class Solution {

	static class Graph {

		int[] vertecies;
		int[][] edges;

		Graph() {
			this.vertecies = new int[vertexNumber + 1];
			this.edges = new int[edgeNumber][3];
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int tcNumber;

	static int[] unf;
	static int[] rank;

	static Graph graph;
	static int vertexNumber;
	static int edgeNumber;
	static long totalWeight;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine());

		for (int curTestcase = 1; curTestcase <= tcNumber; curTestcase++) {

			// 2. 정점과 간선의 갯수를 받는다.
			inputs = br.readLine().trim().split(" ");
			vertexNumber = Integer.parseInt(inputs[0]);
			edgeNumber = Integer.parseInt(inputs[1]);

			// 3. 그래프에 대한 정보를 받아 그래프 생성
			graph = new Graph();

			for (int edge = 0; edge < edgeNumber; edge++) {

				inputs = br.readLine().trim().split(" ");
				int from = Integer.parseInt(inputs[0]);
				int to = Integer.parseInt(inputs[1]);
				int weight = Integer.parseInt(inputs[2]);

				graph.edges[edge][0] = from;
				graph.edges[edge][1] = to;
				graph.edges[edge][2] = weight;

			}

			// 3. 집합을 초기화
			make(vertexNumber);

			// 4. 크루스칼 알고리즘 실행
			kruskal();
			
			sb.append("#").append(curTestcase).append(" ").append(totalWeight).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void kruskal() {

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				
				return Integer.compare(o1[2], o2[2]);
			}
			
		});
		
		// 4-1. 간선의 가중치 오름차순으로 정렬
		for(int edge = 0; edge < edgeNumber; edge++) {
			pq.add(graph.edges[edge]);
		}

		// 4-2. 간선을 탐색
		totalWeight = 0;
		int count = 0;
		while(true) {
			
			int[] curEdge = pq.poll();
			
			int from = curEdge[0];
			int to = curEdge[1];
			int weight = curEdge[2];
			
			// 4-2-1. 간선의 두 정점이 사이클을 생성하면 패스
			if(find(from) == find(to)) {
				continue;
			}
			
			// 4-2-2. 간선의 두 정점이 사이클을 생성하지 않으면 union하고 가중치를 누적
			union(from, to);
			totalWeight += weight;
			count++;
			
			// 4-2-3. 추가된 간선이 정점의 갯수 - 1을 만족하면 종료
			if(count == vertexNumber - 1) {
				break;
			}
			
		}

		
	}

	public static void make(int elementNumber) {

		unf = new int[elementNumber + 1];
		rank = new int[elementNumber + 1];
		for (int curElement = 1; curElement <= elementNumber; curElement++) {
			unf[curElement] = curElement;
		}

	}

	public static void union(int elementA, int elementB) {

		int representationA = find(elementA);
		int representationB = find(elementB);

		// 두 원소의 대표가 같다면 이미 같은 집합
		if (representationA == representationB) {
			return;
		}

		// 아니라면 둘을 합집합 수행
		if (rank[representationA] > rank[representationB]) {
			// A가 속한 집합의 랭크가 더 같거나 높은 경우 B를 A 밑으로
			unf[representationB] = unf[representationA];
			return;
		}

		// B가 속한 집합의 랭크가 더 같거나 높은 경우 A를 B 밑으로
		unf[representationA] = unf[representationB];

		// 단, 두 합집합의 랭크가 같다면 밑으로 들어가게 될 집합의 랭크를 상승해야한다.
		if (rank[representationA] == rank[representationB]) {
			rank[representationB]++;
		}

	}

	public static int find(int element) {

		// 대표 노드 라면
		if (element == unf[element]) {
			return element;
		}

		// 대표 노드가 아니라면 찾으러 간다
		return unf[element] = find(unf[element]);

	}

}