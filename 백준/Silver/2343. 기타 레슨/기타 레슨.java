import java.io.*;
import java.util.*;

/**
 * BOJ_기타레슨
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int lessonCount;
	static int maxBlueLayCount;
	static int[] times;
	static int answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		lessonCount = Integer.parseInt(st.nextToken());
		maxBlueLayCount = Integer.parseInt(st.nextToken());

		int maxTime = Integer.MIN_VALUE;
		int totalTime = 0;
		times = new int[lessonCount];
		st = new StringTokenizer(br.readLine(), " ");
		for (int index = 0; index < lessonCount; ++index) {
			times[index] = Integer.parseInt(st.nextToken());
			maxTime = Math.max(maxTime, times[index]);
			totalTime += times[index];
		}

		binarySearch(maxTime, totalTime);
		sb.append(answer);

		bw.write(sb.toString());
		bw.close();

	}

	public static void binarySearch(int left, int right) {

		while (left <= right) {

			int mid = (left + right) >> 1;
			int blueLayCount = getBlueLayCount(mid);

			// 더 많은 블루레이를 사용했다면
			if (maxBlueLayCount < blueLayCount) {
				// 시간을 더 크게
				left = mid + 1;
			} else {
				// 시간을 더 작게
				right = mid - 1;
				answer = mid;

			}

		}

	}

	public static int getBlueLayCount(int mid) {

		int count = 1;
		int sum = 0;

		for (int index = 0; index < lessonCount; index++) {

			if (mid < sum + times[index]) {
				count++;
				sum = times[index];
			} else {
				sum += times[index];
			}

		}

		return count;

	}

}