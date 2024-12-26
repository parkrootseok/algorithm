import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ_집합의표현
 * @author parkrootseok
 */
class Main {

	public static final int UNION = 0;
	public static final int FIND = 1;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static int M;

	static int[] unf;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		bw.write(sb.toString());
		bw.close();

	}

	public static int find(int element) {

		if (element == unf[element]) {
			return unf[element];
		}

		return unf[element] = find(unf[element]);

	}


	public static void union(int parent, int child) {

		int A = find(parent);
		int B = find(child);

		if (A == B) {
			return;
		}

		unf[A] = B;

	}

	public static void input() throws IOException {

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		unf = new int[N + 1];
		for (int index = 1; index <= N; index++) {
			unf[index] = index;
		}

		for (int count = 0; count < M; count++) {

			st = new StringTokenizer(br.readLine(), " ");

			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			switch (cmd) {

				case UNION:
					union(a, b);
					break;
				case FIND:
					if (find(a) == find(b)) {
						sb.append("YES").append("\n");
					} else {
						sb.append("NO").append("\n");
					}
					break;

			}

		}

	}

}