import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_N과M(3)
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

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		numbers = new int[N];
		for (int number = 1; number <= N; number++) {
			numbers[number - 1] = number;
		}

		sequence = new int[M];
		sequence(0);

		bw.write(sb.toString());
		bw.close();

	}

	public static void sequence(int depth) {

		if (depth == M) {
			for (int idx = 0; idx < M; idx++) {
				sb.append(sequence[idx]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int idx = 0; idx < N; idx++) {
			sequence[depth] = numbers[idx];
			sequence(depth + 1);
		}
		
	}
	
}