import java.io.*;
import java.util.*;

/**
 * BOJ_A->B
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int A;
	static int B;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		sb.append(bfs());
		bw.write(sb.toString());
		bw.close();

	}

	public static int bfs() {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{B, 0});

		while (!queue.isEmpty()) {

			int[] node = queue.poll();
			int number = node[0];
			int cnt = node[1];

			if (number == A) {
				return cnt + 1;
			}

			if (number < A) {
				break;
			}

			if (number % 2 == 0) {
				queue.offer(new int[] {number / 2, cnt + 1});
			}

			if (number % 10 == 1) {
				queue.offer(new int[] {number / 10, cnt + 1});
			}

		}

		return -1;

	}

}