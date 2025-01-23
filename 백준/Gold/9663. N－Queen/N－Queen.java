import java.io.*;
import java.util.*;

/**
 * BOJ_N-Queen
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[] map;
	static int count;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		map = new int[N];

		count = 0;
		backtracking(0);

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static void backtracking(int col) {

		if (col == N) {
			count++;
			return;
		}

		for (int row = 0; row < N; row++) {

			map[col] = row;

			if (isPossible(col)) {
				backtracking(col + 1);
			}

		}

	}

	public static boolean isPossible(int cCol) {

		for (int col = 0; col < cCol; col++) {

			if (map[col] == map[cCol]) {
				return false;
			}

			if (Math.abs(col - cCol) == Math.abs(map[col] - map[cCol])) {
				return false;
			}

		}

		return true;

	}

}