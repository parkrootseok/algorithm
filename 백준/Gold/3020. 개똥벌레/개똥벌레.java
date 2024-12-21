import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * BOJ_개똥벌레
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int N;
	static int H;
	static int[] heights;

 	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		H = Integer.parseInt(inputs[1]);

		heights = new int[H + 1];
		for (int n = 1; n <= N; n++)  {

			int height = Integer.parseInt(br.readLine().trim());
			if (n % 2 == 1) {
				heights[1]++;
				heights[height + 1]--;
			} else {
				heights[H - height + 1]++;
			}

		}

		for (int h = 1; h <= H; h++) {
			heights[h] += heights[h - 1];
		}

		Arrays.sort(heights);

		int min = heights[1];
		int count = 0;
		for (int h = 1; h <= H; h++) {
			if (min == heights[h]) {
				count++;
			}
		}

		sb.append(min).append(" ").append(count);
		bw.write(sb.toString());
		bw.close();

	}

}