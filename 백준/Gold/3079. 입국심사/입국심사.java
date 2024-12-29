import java.io.*;
import java.util.*;

/**
 * BOJ_입국심사
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static int M;
	static int limit;
	static int[] runningTimes;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		sb.append(binarySearch(0, (long) (Math.pow(10, 9) * Math.pow(10, 9))));
		bw.write(sb.toString());
		bw.close();

	}

	public static long binarySearch(long left, long right) {

		while (left < right) {

			long mid = (left + right) >> 1;

			if (M <= getCompletedPersonCount(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}

		}

		return right;

	}

	public static long getCompletedPersonCount(long mid) {

		long sum = 0;
		for (int n = 0; n < N; n++) {
			sum += (mid / runningTimes[n]);
			if (M < sum) {
				break;
			}
		}

		return sum;

	}

	public static void input() throws IOException{

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		runningTimes = new int[N];
		for (int n = 0; n < N; n++) {
			runningTimes[n] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		}

	}

}