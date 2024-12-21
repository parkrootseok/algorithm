import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * BOJ_퇴사
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int N;
	static int[] T;
	static int[] P;
	static int[] DP;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		T = new int[N];
		P = new int[N];
		for (int idx = 0; idx < N; idx++) {
			String[] inputs = br.readLine().trim().split(" ");
			T[idx] = Integer.parseInt(inputs[0]);
			P[idx] = Integer.parseInt(inputs[1]);
		}

		DP = new int[N];
		Arrays.fill(DP, -1);
		recur(0);

		sb.append(DP[0]);
		bw.write(sb.toString());
		bw.close();

	}

	public static int recur(int day) {

		if (day == N) {
			return 0;
		}

		if (day > N){
			return Integer.MIN_VALUE;
		}

		if (DP[day] != -1) {
			return DP[day];
		}

		DP[day] =  Math.max(recur(day + T[day]) + P[day], recur(day + 1));
		return DP[day];

	}

}