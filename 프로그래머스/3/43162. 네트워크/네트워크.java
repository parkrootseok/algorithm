import java.util.*;

/**
 * PG_네트워크
 * @author parkrootseok
 *
 * - A-B, B-C -> A-c
 * - 네트워크의 개수를 반환해라
 * 
 * 1. dfs를 통해 트리의 개수를 구하자
 */
class Solution {
    
    public static class Computer {

		int name;
		List<Integer> adjacentComputers;

		public Computer(int name) {
			this.name = name;
			this.adjacentComputers = new ArrayList<>();
		}

	}

	public static int answer;
	public static Computer[] nodes;
	public static boolean[] isVisited;
    
    public int solution(int n, int[][] computers) {

		answer = 0;
		nodes = new Computer[n];
		for (int idx = 1; idx <= n; idx++) {
			nodes[idx - 1] = new Computer(idx);
		}

		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if (row == col) {
					continue;
				}

				if (computers[row][col] == 1) {
					nodes[row].adjacentComputers.add(col);
					nodes[col].adjacentComputers.add(row);
				}

			}
		}

		// 1. dfs를 통해 트리의 개수를 구하자
		isVisited = new boolean[n];
		for (int idx = 0; idx < n; idx++) {

			if (isVisited[idx]) {
				continue;
			}

			dfs(idx);
			answer++;

		}

		return answer;

	}

	public void dfs(int curNode) {

		isVisited[curNode] = true;

		for (int nextNode : nodes[curNode].adjacentComputers) {

			if (isVisited[nextNode]) {
				continue;
			}

			dfs(nextNode);

		}

	}
    
}