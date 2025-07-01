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
	static int[] values;
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		st = new StringTokenizer(br.readLine());
		values = new int[N];
		for (int n = 0; n < N; n++) {
			values[n] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		int answer = Integer.MAX_VALUE;
		int aLeft = 0;
		int aRight = 0;
		while (left < right) {

			int sum = values[left] + values[right];
			if (answer > Math.abs(sum)) {
				answer = Math.abs(sum);
				aLeft = left;
				aRight = right;
			}

			if (sum < 0) {
				left++;
			} else if (0 < sum) {
				right--;
			} else {
				aLeft = left;
				aRight = right;
				break;
			}

		}

		sb.append(values[aLeft]).append(" ").append(values[aRight]);
		bw.write(sb.toString());
		bw.close();

	}

}
