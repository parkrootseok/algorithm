import java.io.*;
import java.util.*;

/**
 * BOJ_보물
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[] A;
	static int[] B;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}

		B = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			B[n] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		Arrays.sort(A);
		Arrays.sort(B);
		for (int n = 0; n < N; n++) {
			sum += (A[n] * B[N  - n - 1]);
		}

		sb.append(sum);
		bw.write(sb.toString());
		bw.close();

	}

}