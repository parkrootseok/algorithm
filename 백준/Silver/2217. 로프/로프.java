import java.io.*;
import java.util.*;

/**
 * BOJ_로프
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[] weights;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		weights = new int[N];
		for (int n = 0; n < N; n++) {
			weights[n] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(weights);
		int answer = 0;
		for (int n = 1; n <= N; n++) {
			answer = Math.max(answer, weights[N - n] * (n));
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

}