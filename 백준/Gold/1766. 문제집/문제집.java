import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BOJ_문제집
 * @author parkrootseok
 */
public class Main {

	static class Vertex implements Comparable<Vertex> {

		int name;
		int inDegree;
		List<Integer> adjacentVertices = new ArrayList<>();

		public Vertex(int name, int inDegree) {
			this.name = name;
			this.inDegree = inDegree;
		}

		@Override
		public int compareTo(Vertex v) {
			return Integer.compare(this.name, v.name);
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;
	static int M;
	static Vertex[] vertices;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		vertices = new Vertex[N + 1];
		for (int n = 1; n <= N; n++) {
			vertices[n] = new Vertex(n, 0);
		}


		for (int m = 0; m < M; m++) {
			inputs = br.readLine().trim().split(" ");
			int A = Integer.parseInt(inputs[0]);
			int B = Integer.parseInt(inputs[1]);

			vertices[B].inDegree++;
			vertices[A].adjacentVertices.add(B);
		}

		topologySort();

		bw.write(sb.toString());
		bw.close();

	}

	public static void topologySort() {

		Queue<Vertex> q = new PriorityQueue<>();

		for (int v = 1; v <= N; v++) {
			if (vertices[v].inDegree == 0) {
				q.add(vertices[v]);
			}
		}

		while (!q.isEmpty()) {

			Vertex cur = q.poll();
			sb.append(cur.name).append(" ");

			for (Integer next : cur.adjacentVertices) {

				vertices[next].inDegree--;

				if (vertices[next].inDegree == 0) {
					q.add(vertices[next]);
				}


			}

		}

	}

}