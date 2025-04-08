import java.util.*;
import java.io.*;

class Solution {

    static final int SIZE = 8;
	static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
	static boolean[] isUsed;
	static String[] DATA;
	static int answer;

	public int solution(int n, String[] data) {
		answer = 0;
		isUsed = new boolean[SIZE];
		DATA = data;
		dfs(0, new StringBuilder());
		System.out.println(answer);
		return answer;

	}

	public void dfs(int depth, StringBuilder result) {

		if (depth == SIZE) {
			if (isValid(result.toString())) {
				answer++;
			}
			return;
		}

		for (int index = 0; index < SIZE; index++) {

			if (!isUsed[index]) {
				isUsed[index] = true;
				result.append(friends[index]);
				dfs(depth + 1, result);
				result.deleteCharAt(result.length() - 1);
				isUsed[index] = false;
			}

		}

	}

	public boolean isValid(String result) {

		for (String d : DATA) {

			int diff = Math.abs((result.indexOf(d.charAt(0)) - result.indexOf(d.charAt(2)))) - 1;
			char sign = d.charAt(3);
			int target = d.charAt(4) - '0';

			if (sign == '=') {
				if (target != diff) {
					return false;
				}
			} else if (sign == '>') {
				if (diff <= target) {
					return false;
				}
			} else {
				if (diff >= target) {
					return false;
				}
			}

		}

		return true;

	}

    
}