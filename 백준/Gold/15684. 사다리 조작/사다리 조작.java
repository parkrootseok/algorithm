import java.io.*;
import java.util.*;

/**
 * BOJ_내리막길
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int colSize;
	static int rowSize;
	static int lineCount;
	static boolean[][] ladder;
	static int result;
	static int maxCount;
	static boolean isPossible;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		colSize = Integer.parseInt(st.nextToken());
		lineCount = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());

		ladder = new boolean[rowSize + 1][colSize + 1];
		for (int count = 0; count < lineCount; count++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			ladder[from][to] = true;
		}

		if (isPossible()) {
			result = 0;
		} else {
			
			for (int count = 1; count <= 3; count++) {

				maxCount = count;
				simulation(0);

				if (isPossible) {
					result = count;
					break;
				}

			}
			
			if (!isPossible) {
				result = -1;
			}
			
		}

		sb.append(result);
		bw.write(sb.toString());
		bw.close();

	}

	public static void simulation(int count) {

		if (isPossible) {
			return;
		}

		if (count == maxCount) {
			isPossible = isPossible();
			return;
		}

		for (int row = 1; row <= rowSize; row++) {
			for (int col = 1; col <= colSize; col++) {

				if (ladder[row][col]) {
					continue;
				} else if ( 1 < col && ladder[row][col - 1]) {
					continue;
				} else if (col < colSize && ladder[row][col + 1]) {
					continue;
				}

				ladder[row][col] = true;
				simulation(count + 1);
				ladder[row][col] = false;

			}
		}

	}

	public static boolean isPossible() {

		for (int start = 1; start <= colSize; start++) {

			int height = 1;
			int target = start;

			while (height <= rowSize) {

				if (start < colSize && ladder[height][start]) {
					start++;
				} else if (1 < start && ladder[height][start - 1]) {
					start--;
				}

				height++;

			}

			if (start != target) {
				return false;
			}

		}

		return true;

	}

}