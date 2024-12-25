import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * BOJ_최단경로
 * @author parkrootseok
 */
class Main {

	public static class Node {

		int value;
		List<Integer> children;

		public Node(int value) {
			this.value = value;
			children = new ArrayList<>();
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static Node[] tree;
	static int[] parent;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();
		traverse(tree[1]);

		for (int index = 2; index <= N; index++) {
			sb.append(parent[index]).append("\n");
		}
		bw.write(sb.toString());
		bw.close();

	}

	public static void traverse(Node root) {

		for (int child : root.children) {
			if (parent[child] == 0) {
				parent[child] = root.value;
				traverse(tree[child]);
			}

		}

	}

	public static void input() throws IOException {

		N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		tree = new Node[N + 1];
		parent = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			tree[n] = new Node(n);
		}

		for (int n = 0; n < N - 1; n++) {

			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			tree[A].children.add(B);
			tree[B].children.add(A);

		}

	}

}