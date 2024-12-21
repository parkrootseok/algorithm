import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BOJ_다이어트
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int N;
	static int mp, mf, ms, mv;
	static int[] p;
	static int[] f;
	static int[] s;
	static int[] v;
	static int[] c;

	static int min;
	static boolean[] isUsed;
	static List<String> results;

 	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		String[] inputs = br.readLine().trim().split(" ");
		mp = Integer.parseInt(inputs[0]);
		mf = Integer.parseInt(inputs[1]);
		ms = Integer.parseInt(inputs[2]);
		mv = Integer.parseInt(inputs[3]);

		p = new int[N + 1];
		f = new int[N + 1];
		s = new int[N + 1];
		v = new int[N + 1];
		c = new int[N + 1];
		for (int idx = 1; idx <= N; idx++) {
			inputs = br.readLine().trim().split(" ");
			p[idx] = Integer.parseInt(inputs[0]);
			f[idx] = Integer.parseInt(inputs[1]);
			s[idx] = Integer.parseInt(inputs[2]);
			v[idx] = Integer.parseInt(inputs[3]);
			c[idx] = Integer.parseInt(inputs[4]);
		}

		results = new ArrayList<>();
		min = Integer.MAX_VALUE;
		isUsed = new boolean[N + 1];
		recur(1);

		if (min == Integer.MAX_VALUE) {
			sb.append("-1");
		} else {
			sb.append(min).append("\n");
			Collections.sort(results);
			sb.append(results.get(0));
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void recur(int depth) {

		 if (depth == N + 1) {

			 boolean isPossible = false;
			 StringBuilder usedIndex = new StringBuilder();
			 int A, B, C, D, E;
			 A = B = C = D = E = 0;
			 for (int index = 1; index <= N; index++) {

				 if (isUsed[index]) {
					 A += p[index];
					 B += f[index];
					 C += s[index];
					 D += v[index];
					 E += c[index];
					 usedIndex.append(index).append(" ");
					 isPossible = true;
				 }

			 }

			 if (isPossible && min >= E) {
				 if (mp <= A && mf <= B && ms <= C && mv <= D) {

					 if (min == E) {
						 results.add(usedIndex.toString());
					 } else {
						 min = E;
						 results.clear();
						 results.add(usedIndex.toString());
					 }
					 
				 }
				 
			 }

			 return;

		 }

		 isUsed[depth] = true;
		 recur(depth + 1);

		 isUsed[depth] = false;
		 recur(depth + 1);

	}

}