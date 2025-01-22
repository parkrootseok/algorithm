import java.io.*;
import java.util.*;

/**
 * BOJ_최솟값
 * @author parkrootseok
 */
public class Main {

	static class SegmentTree {

		int[] numbers;
		int[] element;

		public SegmentTree(int size) {
			numbers = new int[size + 1];
			element = new int[(int) Math.pow(2, Math.ceil(Math.log(size) / Math.log(2)) + 1)];
		}

		public int init(int parent, int start, int end) {

			if (start == end) {
				return element[parent] = numbers[start];
			}

			int mid = (start + end) >> 1;
			return element[parent] = Math.min(
				init(parent * 2, start, mid),
				init(parent * 2 + 1, mid + 1, end)
			);

		}

		public int min(int parent, int start, int end, int tStart, int tEnd) {

			if (end < tStart || tEnd < start) {
				return Integer.MAX_VALUE;
			}

			if (tStart <= start && end <= tEnd) {
				return element[parent];
			}

			int mid = (start + end) >> 1;
			return Math.min(
				min(parent * 2, start, mid, tStart, tEnd),
				min(parent * 2 + 1, mid + 1, end, tStart, tEnd)
			);

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;

	static SegmentTree segmentTree;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		segmentTree = new SegmentTree(N);
		for (int n = 1; n <= N; ++n) {
			segmentTree.numbers[n] = Integer.parseInt(br.readLine().trim());
		}

		segmentTree.init(1, 1, N);

		for (int m = 0; m < M; ++m) {

			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(segmentTree.min(1, 1, N, a, b)).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
	}

}