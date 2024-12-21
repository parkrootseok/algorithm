import java.io.*;
import java.util.ArrayList;
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
	static int max;

 	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		T = new int[N + 1];
		P = new int[N + 1];
		for (int idx = 1; idx <= N; idx++) {
			String[] inputs = br.readLine().trim().split(" ");
			T[idx] = Integer.parseInt(inputs[0]);
			P[idx] = Integer.parseInt(inputs[1]);
		}

		max = Integer.MIN_VALUE;
		recur(1, 0);

		sb.append(max);
		bw.write(sb.toString());
		bw.close();

	}

	public static void recur(int day, int profit) {

		 if (day == N + 1) {
			 max = Math.max(max, profit);
			 return;
		 } else if (day > N + 1){
			 return;
		 }

		 recur(day + T[day], profit + P[day]);
		 recur(day + 1, profit);

	}

}