import java.io.*;
import java.util.*;

/**
 * BOJ_등수구하기
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int S;
	static int P;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		if (N == 0) {
			sb.append(1);
		} else {

			st = new StringTokenizer(br.readLine(), " ");

			int rank = 1;
			int offset = 0;
			for (int index = 0; index < N && index < P; index++) {
				int score = Integer.parseInt(st.nextToken());
				if (S < score) {
					rank++;
				} else if (S == score) {
					offset++;
				}
			}

			if (P < rank + offset) {
				sb.append(-1);
			} else {
				sb.append(rank);
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int upperBound(List<Integer> scores, int target) {

		int left = 0;
		int right = scores.size();

		while (left < right) {

			int mid = (left + right) >> 1;
			if (target <= scores.get(mid)) {
				left = mid + 1;
			} else {
				right = mid;
			}

		}

		return right;

	}

}