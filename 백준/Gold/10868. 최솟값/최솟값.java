import java.io.*;
import java.util.*;

/**
 * BOJ_최솟값
 * @author parkrootseok
 */
public class Main {

	static class SegmentTree {

		int[] numbers;
		int[] elements;

		public SegmentTree(int size) {
			this.numbers = new int[size];
			this.elements = new int[(int) Math.pow(2, Math.ceil(Math.log(size) / Math.log(2)) + 1)];
		}

		int init(int parent, int start, int end) {

			if (start == end) {
				return elements[parent] = numbers[start];
			}

			int mid = (start + end) >> 1;
			return elements[parent] = Math.min(init(parent * 2, start, mid), init(parent * 2 + 1, mid + 1, end));

		}

		int find(int parent, int start, int end, int tStart, int tEnd) {

			if (end < tStart || tEnd < start) {
				return Integer.MAX_VALUE;
			}

			if (tStart <= start && end <= tEnd) {
				return elements[parent];
			}

			int mid = (start + end) >> 1;
			return Math.min(find(parent * 2, start, mid, tStart, tEnd), find(parent * 2 + 1, mid + 1, end, tStart, tEnd));

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int size;
	static int commandCount;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		size = Integer.parseInt(st.nextToken());
		commandCount = Integer.parseInt(st.nextToken());

		SegmentTree segmentTree = new SegmentTree(size);
		for (int index = 0; index < size; index++) {
			segmentTree.numbers[index] = Integer.parseInt(br.readLine());
		}

		segmentTree.init(1, 0, size - 1);
		for (int command = 0; command < commandCount; command++) {

			st = new StringTokenizer(br.readLine(), " ");

			int start = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			sb.append(segmentTree.find(1, 0, size - 1, start - 1, from - 1)).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}