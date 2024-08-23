import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
	public static TreeSet<String> isUsed;
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
		isUsed = new TreeSet<>();
		sequence = new int[M];
		isVisited = new boolean[N];
		sequence(0);

		bw.write(sb.toString());
		bw.close();

	}

	public static void sequence(int depth) {

		if (depth == M) {

			StringBuilder sb = new StringBuilder();

			for (int idx = 0; idx < M; idx++) {
				sb.append(sequence[idx]).append(" ");
			}

			if (isUsed.contains(sb.toString())) {
				return;
			}

			isUsed.add(sb.toString());
			System.out.println(sb);

			return;

		}

		for (int idx = 0; idx < N; idx++) {

			if (isVisited[idx]) {
				continue;
			}

			isVisited[idx] = true;
			sequence[depth] = numbers[idx];
			sequence(depth + 1);
			isVisited[idx] = false;

		}

	}

}