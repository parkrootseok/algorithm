import java.io.*;
import java.util.*;

/**
 * BOJ_텀 프로젝트
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T, N;
	static int[] parent;
	static boolean[] isVisited;
	static boolean[] hasTeam;
	static int count;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		while (0 < T--) {

			N = Integer.parseInt(br.readLine());
			parent = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int index = 1; index <= N; index++) {
				parent[index] = Integer.parseInt(st.nextToken());
			}

			isVisited = new boolean[N + 1];
			hasTeam = new boolean[N + 1];
			count = 0;
			for (int index = 1; index <= N; index++) {
				if (!hasTeam[index]) {
					dfs(index);
				}
			}

			sb.append(N - count).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
	}

	public static void dfs(int curStudent) {
		if (hasTeam[curStudent]) {
			// 이미 팀이 있다면 종료
			return;
		}

		if (isVisited[curStudent]) {
			// 사이클이 발생한 경우 카운팅
			hasTeam[curStudent] = true;
			count++;
		}

		isVisited[curStudent] = true;
		dfs(parent[curStudent]);
		isVisited[curStudent] = false;
		hasTeam[curStudent] = true;
	}

}
