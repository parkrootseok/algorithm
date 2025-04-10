import java.util.*;

/**
 * PG_단체사진찍기
 * @author parkrootseok
 */
class Solution {

    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
	static boolean[] isUsed;
	static String[] conditions;
	static int answer;

	public int solution(int n, String[] data) {
		answer = 0;
		isUsed = new boolean[friends.length];
		conditions = data;
		dfs(0, new StringBuilder());
		System.out.println(answer);
		return answer;

	}

	public void dfs(int depth, StringBuilder result) {

		if (depth == friends.length) {
			if (isValid(result.toString())) {
				answer++;
			}
			return;
		}

		for (int index = 0; index < friends.length; index++) {

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

		for (String condition : conditions) {

			int diff = Math.abs((result.indexOf(condition.charAt(0)) - result.indexOf(condition.charAt(2)))) - 1;
			char sign = condition.charAt(3);
			int target = condition.charAt(4) - '0';

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