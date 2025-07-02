import java.io.*;
import java.util.*;

/**
 * BOJ_용액
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int[] values;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		values = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			values[n] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine().trim());
		for (int m = 0; m < M; m++) {

			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			boolean isPossible = true;
			while (s < e) {

				if (values[s] != values[e]) {
					isPossible = false;
					break;
				}

				s++;
				e--;

			}

			sb.append(isPossible ? "1" : "0").append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}
