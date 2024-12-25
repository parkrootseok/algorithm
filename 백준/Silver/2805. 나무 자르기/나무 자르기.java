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

		sb.append(binarySearch(max, 0) - 1);
		bw.write(sb.toString());
		bw.close();

	}

	public static int binarySearch(int left, int right) {

		while (right < left) {

			int sawHeight = (left + right) >> 1;
			long hasTreeLength = cut(sawHeight);

			if (hasTreeLength < goal) {
				left = sawHeight;
			}

			else {
				right = sawHeight + 1;
			}

		}

		return right;

	}

	public static long cut(int sawHeight) {

		long totalLength = 0;

		for (int treeHeight : treeHeights) {
			if (sawHeight < treeHeight) {
				totalLength += (treeHeight - sawHeight);
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