import java.io.*;
import java.util.*;

/**
 * BOJ_구간 합 구하기
 * @author parkrootseok
 */
public class Main {

	static final int UPDATE = 1;
	static final int SUM = 2;

	static class SegmentTree {

		long[] numbers;
		long[] elements;

		public SegmentTree(int size) {
			numbers = new long[size + 1];
			elements = new long[(int) Math.pow(2, Math.ceil(Math.log(size) / Math.log(2)) + 1)];
		}

		public long init(int parent, int start, int end) {

			if (start == end) {
				return elements[parent] = numbers[start];
			}

			int mid = (start + end) >> 1;
			return elements[parent] = init(parent * 2, start, mid) + init(parent * 2 + 1, mid + 1, end);

		}

		public void update(int parent, int start, int end, int idx, long diff) {

			if (idx < start || end < idx ) {
				return;
			}

			elements[parent] += diff;

			if (start != end) {
				int mid = (start + end) >> 1;
				update(parent * 2, start, mid, idx, diff);
				update(parent * 2 + 1, mid + 1, end, idx, diff);
			}

		}

		public long sum(int parent, int start, int end, int tStart, int tEnd) {

			if (end < tStart || tEnd < start) {
				return 0;
			}

			if (tStart <= start && end <= tEnd) {
				return elements[parent];
			}

			int mid = (start + end) >> 1;
			return sum(parent * 2, start, mid, tStart, tEnd) + sum(parent * 2 + 1, mid + 1, end, tStart, tEnd);

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int K;

	static SegmentTree segmentTree;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		segmentTree = new SegmentTree(N);
		for (int n = 1; n <= N; ++n) {
			segmentTree.numbers[n] = Long.parseLong(br.readLine().trim());
		}

		segmentTree.init(1, 1, N);

		for (int mk = 0; mk < M + K; ++mk) {

			st = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			switch (cmd) {
				case UPDATE:
					segmentTree.update(1, 1, N, b, c - segmentTree.numbers[b]);
					segmentTree.numbers[b] = c;
					break;
				case SUM:
					sb.append(segmentTree.sum(1, 1, N, b, (int) c)).append("\n");
				 	break;
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

}