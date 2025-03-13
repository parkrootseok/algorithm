import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_이분그래프
 * @author parkrootseok
 */
public class Main {

	static class Vertex  {

		int name;
		List<Integer> adjacent;

		public Vertex(int name) {
			this.name = name;
			this.adjacent = new ArrayList<>();
		}
	}

	public static final int RED = 1;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int K;
	static int V;
	static int E;

	static Vertex[] vertices;
	static int[] colors;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {

			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			vertices = new Vertex[V + 1];
			for (int v = 1; v <= V; v++) {
				vertices[v] = new Vertex(v);
			}

			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine(), " ");

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				vertices[from].adjacent.add(to);
				vertices[to].adjacent.add(from);
			}

			colors = new int[V + 1];
			boolean isPossible = false;
			for (int v = 1; v <= V; v++) {

				if (colors[v] == 0) {
					isPossible = isBipartiteGraph(v);
				}

				if (!isPossible) {
					break;
				}

			}

			sb.append(isPossible? "YES\n" : "NO\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean isBipartiteGraph(int start) {

		Queue<Integer> nodes = new ArrayDeque<>();
		nodes.offer(start);
		colors[start] = RED;

		while (!nodes.isEmpty()) {

			int cVertex = nodes.poll();

			for (int nVertex : vertices[cVertex].adjacent) {

				if (colors[cVertex] == colors[nVertex]) {
					return false;
				}

				if (colors[nVertex] == 0) {
					colors[nVertex] = colors[cVertex] * -1;
					nodes.offer(nVertex);
				}

			}

		}

		return true;

	}

}