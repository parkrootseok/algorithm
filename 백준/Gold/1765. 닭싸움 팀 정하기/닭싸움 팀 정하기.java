import java.io.*;
import java.util.*;

/**
 * BOJ_닭싸움
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N, M;
	static int[] unf;
	static List<Integer>[] enemies;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());

		unf = new int[N + 1];
		enemies = new ArrayList[N + 1];
		for (int index = 1; index <= N; index++) {
			unf[index] = index;
			enemies[index] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine().trim());

			String relation = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			switch (relation) {
				case "F":
					union(a, b);
					break;
				case "E":
					enemies[a].add(b);
					enemies[b].add(a);
					break;
			}
		}

		isVisited = new boolean[N + 1];
		for (int index = 1; index <= N; index++) {
			if (!isVisited[index]) {
				isVisited[index] = true;
				dfs(index, index, 0);
				isVisited[index] = false;
			}
		}

		for (int index = 1; index <= N; index++) {
			find(index);
		}

		Set<Integer> teams = new HashSet<>();
		for (int index = 1; index <= N; index++) {
			teams.add(find(index));
		}

		sb.append(teams.size());
		bw.write(sb.toString());
		bw.close();

	}

	public static void union(int a, int b) {
		int findA = find(a);
		int findB = find(b);

		if (findA == findB) {
			return;
		}

		unf[findB] = findA;
	}

	public static int find(int element) {
		if (unf[element] == element) {
			return unf[element];
		}

		return unf[element] = find(unf[element]);
	}

	public static void dfs(int start, int cur, int count) {

		if (count == 2) {
			union(start, cur);
			return;
		}

		for (Integer index : enemies[cur]) {
			if (!isVisited[index]) {
				isVisited[index] = true;
				dfs(start, index, count + 1);
				isVisited[index] = false;
			}
		}

	}

}
