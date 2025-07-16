import java.io.*;
import java.util.*;

/**
 * BOJ_로또
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int S;
	static int[] numbers;
	static int[] selected;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		while (true) {

			st = new StringTokenizer(br.readLine().trim());
			int S = Integer.parseInt(st.nextToken());
			if (S == 0) {
				break;
			}

			numbers = new int[S];
			for (int index = 0; index < S; index++) {
				numbers[index] = Integer.parseInt(st.nextToken());
			}

			selected = new int[6];
			combination(0, 0);
			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void combination(int depth, int start) {

		if (depth == selected.length) {
			for (int index : selected) {
				sb.append(numbers[index]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if (numbers.length == depth) {
			return;
		}

		for (int index = start; index < numbers.length; index++) {
			selected[depth] = index;
			combination(depth + 1, index + 1);
		}
	}

}
