import java.io.*;
import java.util.*;

/**
 * BOJ_중량제한
 * @author parkrootseok
 */
public class Main {

	public static class Vertex {

		int index;
		List<Node> links;

		public Vertex(int index) {
			this.index = index;
			this.links = new ArrayList<>();
		}

	}

	public static class Node implements Comparable<Node> {

		int index;
		int limit;

		public Node(int index, int limit) {
			this.index = index;
			this.limit = limit;
		}

		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.limit, n.limit);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static int M;
	static Vertex[] vertices;
	static int origin;
	static int dest;
	static boolean[] isVisited;
	static int max;
	static int result;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();
		binarySearch(0, max);

		sb.append(result);
		bw.write(sb.toString());
		bw.close();


	}

	public static void binarySearch(int left, int right) {

		while (left <= right) {

			int mid = (left + right) >> 1;

			// 현재 리밋으로 도착가능 하다면, 더 높은 리밋에 대해 탐색 (낮은 가능성 제거)
			if (bfs(origin, mid)) {
				result = mid;
				left = mid + 1;
			}
			// 현재 리밋이 불가능하다면, 더 낮은 리밋에 대해 탐색 (높은 가능성 제거)
			else {
				right = mid - 1;
			}

		}

	}

	public static boolean bfs(int start, int limit) {

		isVisited = new boolean[N + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		isVisited[start] = true;

		while (!queue.isEmpty()) {

			int cVertex = queue.poll();

			if (cVertex == dest) {
				return true;
			}

			for (Node link : vertices[cVertex].links) {

				int nVertex = link.index;

				if (!isVisited[nVertex] && limit <= link.limit) {
					queue.offer(nVertex);
					isVisited[nVertex] = true;
				}

			}

		}

		return false;

	}

	public static void input() throws IOException{

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		vertices = new Vertex[N + 1];
		for (int n = 1; n <= N; n++) {
			vertices[n] = new Vertex(n);
		}

		for (int m = 0; m < M; m++) {

			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			vertices[from].links.add(new Node(to, weight));
			vertices[to].links.add(new Node(from, weight));
			max = Math.max(max, weight);

		}

		st = new StringTokenizer(br.readLine(), " ");
		origin = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());

	}

}