import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_11050_이항계수1
 * @author parkrootseok
 *
 * N과 K가 주어지고 이항 계수를 구해라
 *
 * 1. N과 K를 받는다.
 * 2. N개의 숫자를 입력받고 해당 숫자에 대해 카운팅
 *  2-1. 절대값이 1,000,000보다 작으므로 MAX를 더하여 인덱싱을 한다.
 * 3. 카운팅된 숫자만 출력한다.
 *  3-1. 기존에 인덱싱을 위해 MAX를 더했으므로 뺀 값을 출력
 **/
public class Main {

	public static final int MAX = 1_000_000;
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static String[] inputs;
	public static int[][] memoization;
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 숫자의 개수 N을 받는다.
		inputs = br.readLine().trim().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		memoization = new int[N + 1][K + 1];

		sb.append(binomialCoefficient(N, K));
		bw.write(sb.toString());
		bw.close();

	}

	public static int binomialCoefficient(int N, int K) {

		if (memoization[N][K] > 0) {
			return memoization[N][K];
		}

		if (N == K || K == 0) {
			return  1;
		}

		return memoization[N][K] = binomialCoefficient(N - 1, K - 1) + binomialCoefficient(N - 1, K);
	}

}