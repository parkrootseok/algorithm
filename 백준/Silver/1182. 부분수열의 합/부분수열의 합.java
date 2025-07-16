import java.io.*;
import java.util.*;

/**
 * BOJ_부분수열의 합
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N, S;
	static int[] numbers;
	static int answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		numbers = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < N; index++) {
			numbers[index] = Integer.parseInt(st.nextToken());
		}

		bruteforce(0, 0);
		if (S == 0) {
			answer--;
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bruteforce(int depth, int sum) {

		if (depth == N) {
			if (S == sum) {
				answer++;
			}
			return;
		}

		bruteforce(depth + 1, sum + numbers[depth]);
		bruteforce(depth + 1, sum);

	}

}
