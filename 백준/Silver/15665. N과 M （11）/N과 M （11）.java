import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.TreeSet;

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
	public static TreeSet<Integer> isUsed;
	public static TreeSet<String> isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		numbers = new int[N];
		isUsed = new TreeSet<>();
		inputs = br.readLine().trim().split(" ");
		for (int idx = 0; idx < N; idx++) {

			if (isUsed.contains(Integer.parseInt(inputs[idx]))) {
				continue;
			}

			numbers[idx] = Integer.parseInt(inputs[idx]);

		}

		Arrays.sort(numbers);
		isVisited = new TreeSet<>();
		sequence = new int[M];
		sequence(0);

		bw.write(sb.toString());
		bw.close();

	}

	public static void sequence(int depth) {

		if (depth == M) {

			StringBuilder result = new StringBuilder();

			for (int idx = 0; idx < M; idx++) {
				result.append(sequence[idx]).append(" ");
			}

			if (isVisited.contains(result.toString())) {
				return;
			}

			isVisited.add(result.toString());
			sb.append(result).append("\n");
			return;

		}

		for (int idx = 0; idx < N; idx++) {

			sequence[depth] = numbers[idx];
			sequence(depth + 1);

		}

	}

}