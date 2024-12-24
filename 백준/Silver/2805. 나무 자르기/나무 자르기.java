import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ_나무자르기
 * @author parkrootseok
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int treeCount;
	static int max;
	static int goal;
	static int[] treeHeights;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		sb.append(binarySearch(0, max));
		bw.write(sb.toString());
		bw.close();

	}

	public static int binarySearch(int left, int right) {

		while (left < right) {

			int sawHeight = (left + right) >> 1;
			long hasTreeLength = cut(sawHeight);

			if (hasTreeLength < goal) {
				right = sawHeight;
			} else {
				left = sawHeight + 1;
			}

		}

		return right - 1;

	}

	public static long cut(int mid) {

		long totalLength = 0;

		for (int treeHeight : treeHeights) {
			if (mid < treeHeight) {
				totalLength += (treeHeight - mid);
			}
		}

		return totalLength;

	}

	public static void input() throws IOException {

		st = new StringTokenizer(br.readLine(), " ");
		treeCount = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());

		max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine(), " ");
		treeHeights = new int[treeCount];
		for (int index = 0; index < treeCount; index++) {
			treeHeights[index] = Integer.parseInt(st.nextToken());
			max = Math.max(max, treeHeights[index]);
		}

	}

}