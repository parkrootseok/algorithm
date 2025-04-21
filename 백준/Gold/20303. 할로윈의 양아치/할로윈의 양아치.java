import java.io.*;
import java.util.*;

/**
 * BOJ_할로윈의양아치
 * @author parkrootseok
 */
public class Main {

	static class Group {
		int size;
		int hasCandy;

		public Group(int size, int hasCandy) {
			this.size = size;
			this.hasCandy = hasCandy;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int K;
	static int[] children;
	static int[] unf;
	static int[] rank;
	static List<Group> groups;
	static int answer;
	static int[] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		children = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int id = 1; id <= N; id++) {
			children[id] = Integer.parseInt(st.nextToken());
		}

		unf = new int[N + 1];
		rank = new int[N + 1];
		for (int id = 1; id <= N; id++) {
			unf[id] = id;
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b);
		}

		Map<Integer, Integer> hasCandyNumber = new HashMap<>();
		int[] size = new int[N + 1];
		for (int id = 1; id <= N; id++) {
			int leader = find(id);
			size[leader]++;
			hasCandyNumber.put(leader, hasCandyNumber.getOrDefault(leader, 0) + children[id]);
		}

		groups = new ArrayList<>();
		for (int leader = 1; leader <= N; leader++) {
			if (size[leader] != 0 && size[leader] < K) {
				groups.add(new Group(size[leader], hasCandyNumber.get(leader)));
			}
		}

		dp = new int[K];
		for (Group group : groups) {
			for (int k = K - 1; group.size <= k; k--) {
				dp[k] = Math.max(dp[k], dp[k - group.size] + group.hasCandy);
			}
		}
		
		sb.append(dp[K - 1]);
		bw.write(sb.toString());
		bw.close();

	}
	
	public static void union(int a, int b) {

		int findA = find(a);
		int findB = find(b);

		if (unf[findA] == unf[findB]) {
			return;
		}

		if (rank[findA] > rank[findB]) {
			unf[findB] = findA;
		} else if (rank[findA] < rank[findB]) {
			unf[findA] = findB;
		} else {
			unf[findB] = findA;
			rank[findA]++;
		}

	}

	public static int find(int a) {

		if (unf[a] == a) {
			return a;
		}

		return unf[a] = find(unf[a]);

	}

}