import java.io.*;
import java.util.*;

/**
 * BOJ_입국심사
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int min;
	static int max;
	static int[] times;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		times = new int[N];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		for (int n = 0; n < N; n++) {
			times[n] = Integer.parseInt(br.readLine());
			min = Math.min(min, times[n]);
			max = Math.max(max, times[n]);
		}

		sb.append(binarySearch());
		bw.write(sb.toString());
		bw.close();

	}

	public static long binarySearch() {

		long answer = 0;
		long left = 0;
		long right = (long) max * M;

		while (left <= right) {

			long mid = (left + right) >> 1;
			if (M <= getPersonCount(mid)) {
				// 목표를 만족하는 경우 -> 더 작은 시간으로 이동
				answer = mid;
				right = mid - 1;
			} else {
				// 목표를 만족하지 못하는 경우 -> 더 많은 시간으로 이동
				left = mid + 1;
			}

		}

		return answer;

	}

	public static long getPersonCount(long mid) {
		long count = 0;
		for (int n = 0; n < N; n++) {
			count += (mid / times[n]);
			if (M <= count) {
				break;
			}
		}
		return count;
	}

}