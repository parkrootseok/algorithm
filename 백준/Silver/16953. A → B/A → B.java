import java.io.*;
import java.util.*;

/**
 * BOJ_A->B
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int A;
	static int B;
	static int answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		answer = Integer.MAX_VALUE;
		bruteforce(A, 0);

		sb.append(answer == Integer.MAX_VALUE ? -1 : answer + 1);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bruteforce(long number, int count) {

		if (B < number) {
			return;
		}

		if (number == B) {
			answer = Math.min(answer, count);
			return;
		}

		bruteforce(number * 2, count + 1);
		bruteforce(number * 10 + 1, count + 1);

	}

}