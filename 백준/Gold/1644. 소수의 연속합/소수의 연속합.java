import java.io.*;
import java.util.Arrays;

/**
 * BOJ_소수의연속합
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;
	static boolean[] isPrime;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		if (N < 2) {
			System.out.println("0");
			return;
		} else if (N == 2) {
			System.out.println("1");
			return;
		}

		isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		for (int num = 2; num <= Math.sqrt(N); num++) {
			if (isPrime[num]) {
				for (int mul = num * 2; mul <= N; mul+=num) {
					isPrime[mul] = false;
				}
			}
		}

		int count = 0;
		int left = 2;
		int right = 3;
		int sum = left;

		while (left < right && right <= N) {

			// 소수가 아니라면 스킵
			if (!isPrime[left]) {
				left++;
				continue;
			}

			if (!isPrime[right]) {
				right++;
				continue;
			}

			sum += right;

			while (N < sum) {
				if (isPrime[left]) {
					sum -= left;
				}
				left++;
			}

			if (sum == N) {
				count++;
			}

			right++;

		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

}