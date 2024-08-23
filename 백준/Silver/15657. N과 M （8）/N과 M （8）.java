import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_N과M
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int M;
	public static int[] numbers;
	public static int[] sequence;
	public static boolean[] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		numbers = new int[N];
		inputs = br.readLine().trim().split(" ");
		for (int idx = 0; idx < N; idx++) {
			numbers[idx] = Integer.parseInt(inputs[idx]);
		}

		Arrays.sort(numbers);
		isVisited = new boolean[N];
		sequence = new int[M];
		sequence(0, 0);

		bw.write(sb.toString());
		bw.close();

	}

	public static void sequence(int depth, int start) {

		if (depth == M) {
			for (int idx = 0; idx < M; idx++) {
				sb.append(sequence[idx]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int idx = start; idx < N; idx++) {

			if (isVisited[idx]) {
				continue;
			}

			sequence[depth] = numbers[idx];
			sequence(depth + 1, idx);

		}

	}

}