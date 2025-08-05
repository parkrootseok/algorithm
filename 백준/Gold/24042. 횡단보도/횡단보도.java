 import java.io.*;
import java.util.*;

/**
 * BOJ_횡단보도
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Vertex {
		int index;
		long openTime;
		Vertex next;

		public Vertex(int index, long openTime, Vertex next) {
			this.index = index;
			this.openTime = openTime;
			this.next = next;
		}
	}


	static class Node implements Comparable<Node> {
		int index;
		long time;

		public Node(int index, long time) {
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.time, o.time);
		}
	}

	static int N;
	static int M;
	static Vertex[] vertices;
	static long[] times;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		vertices = new Vertex[N + 1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			vertices[A] = new Vertex(B, m, vertices[A]);
			vertices[B] = new Vertex(A, m, vertices[B]);
		}

		times = new long[N + 1];
		Arrays.fill(times, Long.MAX_VALUE);
		dijkstra();

		sb.append(times[N]);
		bw.write(sb.toString());
		bw.close();
	}

	static void dijkstra() {
		Queue<Node> nodeQ = new PriorityQueue<>();
		nodeQ.offer(new Node(1, 0));
		times[1] = 0;

		while (!nodeQ.isEmpty()) {
			Node cNode = nodeQ.poll();
			int cIndex = cNode.index;
			long cTime = cNode.time;

			if (times[cIndex] < cTime) {
				continue;
			}

			for (Vertex v = vertices[cIndex]; v != null; v = v.next) {
				int nIndex = v.index;
				long offset = (v.openTime - (cTime % M) + M) % M;
				long needTime = cTime + offset + 1; 

				if (needTime < times[nIndex]) {
					times[nIndex] = needTime;
					nodeQ.offer(new Node(nIndex, needTime));
				}
			}
		}
	}

}
