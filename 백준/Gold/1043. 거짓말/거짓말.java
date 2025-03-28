import java.io.*;
import java.util.*;

import javax.swing.*;

/**
 * BOJ_거짓말
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static int[] isKnown;
	static List<Integer>[] parties;
	static int[] unf;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parties = new ArrayList[M];
		unf = new int[N + 1];
		for (int person = 1; person <= N; person++) {
			unf[person] = person;
		}

		st = new StringTokenizer(br.readLine(), " ");
		isKnown = new int[Integer.parseInt(st.nextToken())];
		for (int index = 0 ; index < isKnown.length; index++) {
			isKnown[index] = Integer.parseInt(st.nextToken());
		}

		int count = M;
		if (isKnown.length != 0) {
			for (int pIndex = 0; pIndex < parties.length; pIndex++) {

				parties[pIndex] = new ArrayList<>();
				st = new StringTokenizer(br.readLine(), " ");

				int peopleNumber = Integer.parseInt(st.nextToken());
				for (int index = 0; index < peopleNumber; index++) {
					parties[pIndex].add(Integer.parseInt(st.nextToken()));
				}

				int leader = parties[pIndex].get(0);
				for (int person : parties[pIndex]) {
					union(leader, person);
				}

			}

			for (List<Integer> party : parties) {
				int leader = party.get(0);
				for (int person : isKnown) {
					if (find(leader) == find(person)) {
						count--;
						break;
					}
				}
			}
		}

		sb.append(count);
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

	public static int find(int a) {
		if (unf[a] == a) {
			return a;
		}

		return unf[a] = find(unf[a]);
	}

}