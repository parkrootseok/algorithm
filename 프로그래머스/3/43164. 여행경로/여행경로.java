import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * PG_여행경로
 * @author parkrootseok
 *
 * - 항공권 정보
 *  - 2차원 배열 : {0 : 출발} = {1 : 도착}
 * - 주어진 모든 항공권을 사용해야 하고, 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 리턴
 *
 * 1.
 */
public class Solution {

	public static boolean[] isVisited;
	public static List<String> result;

	public String[] solution(String[][] tickets) {

		isVisited = new boolean[tickets.length];

		result = new ArrayList<>();
		dfs("ICN", "ICN", 0, tickets);
		Collections.sort(result);

		return result.get(0).split(" ");

	}

	public void dfs(String route, String dest, int depth, String[][] tickets) {

		if (depth == tickets.length) {
			result.add(route);
			return;
		}

		for (int index = 0; index < tickets.length; index++) {

			if (isVisited[index] || !tickets[index][0].equals(dest))  {
				continue;
			}

			isVisited[index] = true;
			dfs(route + " " + tickets[index][1], tickets[index][1], depth + 1, tickets);
			isVisited[index] = false;

		}

	}

}
