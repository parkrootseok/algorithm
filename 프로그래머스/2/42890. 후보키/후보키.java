import java.util.*;
import java.io.*;

/**
 * PG_후보키
 * @author parkrootseok
 */
class Solution {
    
   static String[][] RELATION;
	static boolean[] isVisited;
	static Set<String> candidates;
	static int columnSize;

	public int solution(String[][] relation) {

		RELATION = relation;
		columnSize = relation[0].length;
		candidates = new HashSet<>();

		for (int size = 0; size < columnSize; size++) {
			isVisited = new boolean[columnSize];
			dfs(0, 0, size + 1);
		}

		return candidates.size();

	}

	public void dfs(int depth, int start, int limit) {

		if (depth == limit) {
            String key = createKey();
			if (isUnique() && isMimimal(key)) {
                candidates.add(key);
			}
            return;
		}

		for (int column = start; column < columnSize; column++) {

			if (!isVisited[column]) {
				isVisited[column] = true;
				dfs(depth + 1, start + 1, limit);
				isVisited[column] = false;
			}

		}

	}

	public boolean isUnique() {

		Set<String> combination = new HashSet<>();
		for (int row = 0; row < RELATION.length; row++) {

			StringBuilder sb = new StringBuilder();

			for (int index = 0; index < isVisited.length; index++) {
				if (isVisited[index]) {
					sb.append(RELATION[row][index]);
				}
			}

			if (combination.contains(sb.toString())) {
				return false;
			} else {
				combination.add(sb.toString());
			}

		}

		return true;

	}

	public boolean isMimimal(String key) {

		for (String c : candidates) {

			int count = 0;
			for (int index = 0; index < key.length(); index++) {
				if (c.contains(String.valueOf(key.charAt(index)))) {
					count++;
				}
			}
			
			if (c.length() == count) {
				return false;
			}

		}

		return true;

	}

	public String createKey() {
		StringBuilder key = new StringBuilder();
		for (int index = 0; index < isVisited.length; index++) {
			if (isVisited[index]) {
				key.append(index + 1);
			}
		}
		return key.toString();
	}
    
}