import java.util.*;

/**
 * PG_주식가격
 * @author parkrootseok
 *
 * - 주식 가격이 담긴 배열이 주어지고, 떨어지지 않은 기간이 몇초인지 구해라
 *
 * 1. 가장 마지막에 추가된 가격보다 현재 가격이 더 작다면 해당 시점을 기록
 * 2. 현재 가격을 스택에 삽입
 * 3. 스택에 남아있는 가격들에 대해 시점을 기록
 */
public class Solution {

	public static class Node {

		int index;
		int price;

		public Node(int index, int price) {
			this.index = index;
			this.price = price;
		}

	}

	public int[] solution(int[] prices) {

		int[] result = new int[prices.length];
		Stack<Node> nodeStack = new Stack<>();
		
		for (int index = 0; index < prices.length; index++) {

			// 1. 가장 마지막에 추가된 가격보다 현재 가격이 더 작다면 해당 시점을 기록
			while (!nodeStack.isEmpty() && prices[index] < nodeStack.peek().price) {
				Node node = nodeStack.pop();
				result[node.index] = index - node.index;
			}

			// 2. 현재 가격을 스택에 삽입
			nodeStack.push(new Node(index, prices[index]));

		}

		// 3. 스택에 남아있는 가격들에 대해 시점을 기록
		while (!nodeStack.isEmpty()) {
			Node node = nodeStack.pop();
			result[node.index] = (prices.length - 1) - node.index;
		}

		return result;

	}

}
