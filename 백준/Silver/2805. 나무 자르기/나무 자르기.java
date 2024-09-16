import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_나무자르기
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static int M;
	public static int[] heights;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		inputs = br.readLine().trim().split(" ");

		int max = Integer.MIN_VALUE;
		heights = new int[N];
		for (int index = 0; index < N; index++) {
			heights[index] = Integer.parseInt(inputs[index]);
			max = Math.max(max, heights[index]);
		}

		sb.append(upper(0, max));
		bw.write(sb.toString());
		bw.close();

	}

	public static int upper(int low, int high) {

		while (low < high) {

			int mid = (low + high) / 2;
			long height = getHeight(mid);

			// 현재 가져갈 수 있는 나무가 M 이하 (더 많은 나무를 가져가야 하므로 높이를 줄여야 함)
			if (height < M) {
				high = mid;
			} else {
				low = mid + 1;
			}

		}

		return high - 1;

	}

	public static long getHeight(int mid) {

		long height = 0;
		for (int idx = 0; idx < N; idx++) {
			height += (heights[idx] - mid > 0 ? heights[idx] - mid : 0);
		}

		return height;

	}

}