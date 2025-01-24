import java.io.*;
import java.util.*;

/**
 * BOJ_트리의 지름
 * @author parkrootseok
 */
public class Main {

	public static class Vertex {

		int name;
		List<Node> adjustVertices;

		public Vertex(int name) {
			this.name = name;
			this.adjustVertices = new ArrayList<>();
		}
	}

	public static class Node {

		int name;
		int weight;

		public Node(int name, int weight) {
			this.name = name;
			this.weight = weight;
		}

	}

	public static final int INF = 200_000_000;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int V;
	static Vertex[] vertices;

	static int max;
	static int maxVertex;
	static boolean[] isVisited;


	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		V = Integer.parseInt(br.readLine().trim());
		vertices = new Vertex[V + 1];
		for (int v = 1; v <= V; ++v) {
			vertices[v] = new Vertex(v);
		}

		for (int v = 1; v <= V; ++v) {

			st = new StringTokenizer(br.readLine(), " ");

			int org = Integer.parseInt(st.nextToken());

			while (st.hasMoreTokens()) {

				int dst = Integer.parseInt(st.nextToken());

				if (dst == -1) {
					break;
				}

				int weight = Integer.parseInt(st.nextToken());

				vertices[org].adjustVertices.add(new Node(dst, weight));

			}

		}

		max = Integer.MIN_VALUE;
		isVisited = new boolean[V + 1];
		dfs(1, 0);
		dfs(maxVertex, 0);

		sb.append(max);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int cVertex, int distance) {

		if (max < distance) {
			max = distance;
			maxVertex = cVertex;
		}

		isVisited[cVertex] = true;

		for (Node node : vertices[cVertex].adjustVertices) {

			int nVertex = node.name;

			if (isVisited[nVertex]) {
				continue;
			}

			dfs(nVertex, distance + node.weight);

		}

		isVisited[cVertex] = false;

	}

}