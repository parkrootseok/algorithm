import java.io.*;
import java.util.*;

import javax.print.DocFlavor;

/**
 * BOJ_외판원순회2
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[][] W;
	static int min;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		W = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				W[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		min = Integer.MAX_VALUE;
		isVisited = new boolean[N + 1];
        isVisited[1] = true;
		bruteforce(0, 1, 1, 0);
	
		sb.append(min);
		bw.write(sb.toString());
		bw.close();
	}

	static void bruteforce(int depth, int start, int cCity, int sum) {

		if (depth == N - 1) {
			if (0 < W[cCity][start]) {
				min = Math.min(min, sum + W[cCity][start]);
			}
			return;
		}

		if (min < sum) {
			return;
		}

		for (int nCity = 1; nCity <= N; nCity++) {
			if (!isVisited[nCity] && 0 < W[cCity][nCity]) {
				isVisited[nCity] = true;
				bruteforce(depth + 1, start, nCity, sum + W[cCity][nCity]);
				isVisited[nCity] = false;
			}
		}
		
	}

}
