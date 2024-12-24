import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_숫자카드
 * @author parkrootseok
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static int M;

	static int[] my;
	static int[] your;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		Arrays.sort(my);

		for (int number : your) {

			if (binarySearch(number)) {
				sb.append("1"). append(" ");
			} else {
				sb.append("0").append(" ");
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean binarySearch(int target) {

		int left = 0;
		int right = N - 1;

		while (left <= right) {

			int mid = (left + right) >> 1;

			if (target == my[mid]) {
				return true;
			} else if (my[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		return false;

	}

	public static void input() throws IOException {

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		my = new int[N];
		for (int index = 0; index < N; index++) {
			my[index] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		your = new int[M];
		for (int index = 0; index < M; index++) {
			your[index] = Integer.parseInt(st.nextToken());
		}

	}


}