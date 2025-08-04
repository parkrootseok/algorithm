import java.io.*;
import java.rmi.dgc.VMID;
import java.util.*;

/**
 * BOJ_골목대장호석
 * @author parkrootseok
 */
public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Vertex {
		List<int[]> edges;

		public Vertex() {
			this.edges = new ArrayList<>();
		}
	}

	static class Node {
		int dest;
		long cost;
		long max;

		public Node(int dest, long cost, long max) {
			this.dest = dest;
			this.cost = cost;
			this.max = max;
		}
	}

	static int N, M, A, B;
	static long C;
	static int max;
	static Vertex[] vertices;
	static long[] costs;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());

		vertices = new Vertex[N + 1];
		costs = new long[N + 1];
		for (int n = 0; n <= N; n++) {
			vertices[n] = new Vertex();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int org = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			vertices[org].edges.add(new int[]{dest, cost});
			vertices[dest].edges.add(new int[]{org, cost});
			max = Math.max(max, cost);
		}

		sb.append(parametricSearch());
		bw.write(sb.toString());
		bw.close();
	}

	static int parametricSearch() {
		int left = 1;
		int right = max;
		int answer = -1;

		while (left <= right) {
			int mid = (left + right) >> 1;
			Arrays.fill(costs, Long.MAX_VALUE);
			dijkstra(mid);
			if (costs[B] <= C) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return answer;
	}

	static void dijkstra(int limit) {
		Queue<Node> nodeQ = new ArrayDeque<>();
		nodeQ.offer(new Node(A, 0, Integer.MIN_VALUE));
		costs[A] = 0;

		while (!nodeQ.isEmpty()) {
			Node cNode = nodeQ.poll();
			int cVertex = cNode.dest;
			long cCost = cNode.cost;
			long cMax = cNode.max;
			
			if (costs[cVertex] < cCost) {
				continue;
			}

			for (int[] edge : vertices[cVertex].edges) {
				int nVertex = edge[0];
				long nCost = edge[1];

				if (limit < nCost) {
					continue;
				}

				if (costs[nVertex] > cCost + nCost) {
					costs[nVertex] = cCost + nCost;
					nodeQ.offer(new Node(nVertex, cCost + nCost, Math.max(cMax, nCost)));
				}
			}
		}
	}

}
