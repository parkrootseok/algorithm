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

	static int count;
	static boolean[] isDone;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		while (T-- != 0) {

			N = Integer.parseInt(br.readLine());

			parent = new int[N + 1];
			st = new StringTokenizer(br.readLine().trim());
			for (int student = 1; student <= N; student++) {
				parent[student] = Integer.parseInt(st.nextToken());
			}

			count = 0;
			isVisited = new boolean[N + 1];
			isDone = new boolean[N + 1];
			for (int student = 1; student <= N; student++) {
				if (!isDone[student]) {
					dfs(student);
				}
			}

			sb.append(N - count).append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int cur) {

		if (isDone[cur]) {
			return;
		}

		if (isVisited[cur]) {
			isDone[cur] = true;
			count++;
		}

		isVisited[cur] = true;
		dfs(parent[cur]);
		isDone[cur] = true;
		isVisited[cur] = false;
		
	}

}
