import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * BOJ_최단경로
 * @author parkrootseok
 */
class Main {

	public static class Node {

		char value;
		Node lChild;
		Node rChild;

		public Node(char value) {
			this.value = value;
		}

		public void setlChild(Node lChild) {
			this.lChild = lChild;
		}

		public void setrChild(Node rChild) {
			this.rChild = rChild;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static Node[] tree;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		preOrder(tree[0]);
		sb.append("\n");
		inOrder(tree[0]);
		sb.append("\n");
		postOrder(tree[0]);

		bw.write(sb.toString());
		bw.close();

	}

	public static void preOrder(Node parent) {

		if (Objects.isNull(parent)) {
			return;
		}

		sb.append(parent.value);
		preOrder(parent.lChild);
		preOrder(parent.rChild);

	}

	public static void inOrder(Node parent) {

		if (Objects.isNull(parent)) {
			return;
		}

		inOrder(parent.lChild);
		sb.append(parent.value);
		inOrder(parent.rChild);

	}

	public static void postOrder(Node parent) {

		if (Objects.isNull(parent)) {
			return;
		}

		postOrder(parent.lChild);
		postOrder(parent.rChild);
		sb.append(parent.value);

	}

	public static void input() throws IOException {

		N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		tree = new Node[N];
		for (int n = 0; n < N; n++) {
			tree[n] = new Node((char) ('A' + n));
		}

		for (int n = 0; n < N; n++) {

			st = new StringTokenizer(br.readLine(), " ");
			char p = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);

			Node pNode = tree[p - 'A'];

			if (Character.isAlphabetic(l)) {
				pNode.setlChild(tree[l - 'A']);
			}

			if (Character.isAlphabetic(r)) {
				pNode.setrChild(tree[r - 'A']);
			}


		}

	}

}