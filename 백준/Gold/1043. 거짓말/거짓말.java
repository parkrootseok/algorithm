import java.io.*;
import java.util.*;

/**
 * BOJ_거짓말
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int personCount;
	
	static int knownCount;
	static int[] knownPeople;
	
	static int partyCount;
	static List<Integer>[] parties;
	
	static int[] unf;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		personCount = Integer.parseInt(st.nextToken());
		partyCount = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		knownCount = Integer.parseInt(st.nextToken());
		knownPeople = new int[knownCount];
		for (int kCount = 0; kCount < knownCount; kCount++) {
			knownPeople[kCount] = Integer.parseInt(st.nextToken());
		}
		
		unf = new int[personCount + 1];
		for (int idx = 0; idx <= personCount; idx++) {
			unf[idx] = idx;
		}
		
		parties = new ArrayList[partyCount];
		for (int pCount = 0; pCount < partyCount; pCount++) {
			
			parties[pCount] = new ArrayList<>();
			st = new StringTokenizer(br.readLine(), " ");
			int invitationPersonCount = Integer.parseInt(st.nextToken());
			for (int iCount = 0; iCount < invitationPersonCount; iCount++) {
				parties[pCount].add(Integer.parseInt(st.nextToken()));
			}
			
			int leader = parties[pCount].get(0);
			for (int idx = 1; idx < parties[pCount].size(); idx++) {
				union(leader, parties[pCount].get(idx));
			}
			
		}
		
		int count = 0;
		for (List<Integer> p : parties) {
			
			int leader = p.get(0);
			boolean isPossible = true;
			
			for (int kCount = 0; kCount < knownCount; kCount++) {
				
				if (find(leader) == find(knownPeople[kCount])) {
					isPossible = false;
					break;
				}
				
			}
			
			if (isPossible) {
				count++;
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