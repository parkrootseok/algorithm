import java.io.*;
import java.util.*;

/**
 * BOJ_웜홀
 * @author parkrootseok
 */
public class Main {

	static class Vertex {

		int name;
		List<Edge> adjacentVertices;

		public Vertex(int name) {
			this.name = name;
			this.adjacentVertices = new ArrayList<>();
		}

	}

	static class Edge {

		int name;
		int time;

		public Edge(int name, int time) {
			this.name = name;
			this.time = time;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testCount;
	static int N;
	static int M;
	static int W;

	static Vertex[] vertices;
	static int[] distance;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		testCount = Integer.parseInt(br.readLine().trim());
		for (int tCount = 0; tCount < testCount; tCount++) {

			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			vertices = new Vertex[N+1];
			for (int index = 0; index <= N; index++) {
				vertices[index] = new Vertex(index);
			}

			for (int m = 0; m < M ; m++) {

				st = new StringTokenizer(br.readLine(), " ");

				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				vertices[S].adjacentVertices.add(new Edge(E, T));
				vertices[E].adjacentVertices.add(new Edge(S, T));

			}

			for (int w = 0; w < W; w++) {

				st = new StringTokenizer(br.readLine(), " ");

				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				vertices[S].adjacentVertices.add(new Edge(E, T * -1));

			}

			sb.append(bellmanFord()? "YES" : "NO").append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean bellmanFord() {
		
		int INF = 100_000_000;
		distance = new int[N + 1];
		Arrays.fill(distance, INF);
		distance[1] = 0;

		for (int count = 1; count <= N; count++) {

			for (int cVertex = 1; cVertex <= N; cVertex++) {

				for (Edge e : vertices[cVertex].adjacentVertices) {

					int nVertex = e.name;
					int nTime = e.time;

					if (distance[nVertex] > distance[cVertex] + nTime) {

						distance[nVertex] = distance[cVertex] + nTime;

						if (count == N) {
							return true;
						}

					}

				}

			}

		}

		return false;

	}


}