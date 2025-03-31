import java.io.*;
import java.util.*;

/**
 * BOJ_달빛여우
 * @author parkrootseok
 */
public class Main {

	static class Node implements Comparable<Node>{

		int dest;
		int dist;
		int isTired;

		public Node(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}

		public Node(int dest, int dist, int isTired) {
			this.dest = dest;
			this.dist = dist;
			this.isTired = isTired;
		}

		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.dist, n.dist);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N];
		for (int n = 0; n < N; n++) {
			graph[n] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");

			int org = Integer.parseInt(st.nextToken()) - 1;
			int dest = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());

			graph[org].add(new Node(dest, dist * 2));
			graph[dest].add(new Node(org, dist * 2));

		}

		int[] fox = dijkstraForFox();
		int[][] wolf = dijkstraForWolf();

		int count = 0;
		for (int n = 0; n < N; n++) {
			if (fox[n] < Math.min(wolf[0][n], wolf[1][n])) {
				count++;
			}
		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static int[] dijkstraForFox() {
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);

		Queue<Node> nodes = new PriorityQueue<>();
		nodes.offer(new Node(0, 0));
		dist[0] = 0;

		while (!nodes.isEmpty()) {

			Node cur = nodes.poll();

			if (cur.dist > dist[cur.dest]) {
				continue;
			}

			for (Node next : graph[cur.dest]) {

				int needDist = cur.dist + next.dist;

				if (dist[next.dest] > needDist) {
					dist[next.dest] = needDist;
					nodes.offer(new Node(next.dest, dist[next.dest]));
				}

			}

		}

		return dist;
	}

	public static int[][] dijkstraForWolf() {

		int[][] dist = new int[2][N];
		Arrays.fill(dist[0], Integer.MAX_VALUE);
		Arrays.fill(dist[1], Integer.MAX_VALUE);

		Queue<Node> nodes = new PriorityQueue<>();
		nodes.offer(new Node(0, 0, 0));
		dist[0][0] = 0;

		while (!nodes.isEmpty()) {

			Node cur = nodes.poll();

			if (cur.dist > dist[cur.isTired][cur.dest]) {
				continue;
			}

			for (Node next : graph[cur.dest]) {

				int nDist = cur.dist;
				int nTired = 0;

				if (cur.isTired == 0) {
					nDist += (next.dist / 2);
					nTired = 1;
				} else {
					nDist += (next.dist * 2);
				}

				if (dist[nTired][next.dest] > nDist) {
					dist[nTired][next.dest] = nDist;
					nodes.offer(new Node(next.dest, nDist, nTired));
				}

			}

		}

		return dist;

	}

}