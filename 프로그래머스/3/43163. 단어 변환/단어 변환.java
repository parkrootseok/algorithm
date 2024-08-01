import java.util.*;

/**
 * PG_단어 변환
 * @author parkrootseok
 *
 * - begin에서 target으로 변환하는 가장 짧은 과정을 찾아라
 *   - 단, 한 번에 한 개의 알파벳만 바꿀 수 있으며, 주어진 단어 안에서만 변환이 가능
 *
 * 1. 변환할 수 있는 모든 단어에 대하여 큐에 삽입
 *  1-1. 현재 단어와 바꿀 단어와 다른 알파벳 갯수를 구하고
 *  1-2. 1-2. 다른 알파벳 개수가 1개 라면 변환 가능
 */
public class Solution {

	public static class Node {

		String string;
		int depth;

		public Node(String string, int depth) {
			this.string = string;
			this.depth = depth;
		}
	}

	public static Set<String> isVisited;

	public int solution(String begin, String target, String[] words) {

		boolean isPossible = false;
		for (String word : words) {

			if (word.equals(target)) {
				isPossible = true;
			}

		}

		if (!isPossible) {
			return 0;
		}

		isVisited = new HashSet<>();

		return bfs(begin, target, words);

	}

	public int bfs(String begin, String target, String[] words) {

		Queue<Node> nodeQ = new ArrayDeque<>();
		nodeQ.add(new Node(begin, 0));
		isVisited.add(begin);

		while (!nodeQ.isEmpty()) {

			Node node = nodeQ.poll();
			String curString = node.string;
			int depth = node.depth;

			if (curString.equals(target)) {
				return depth;
			}

			// 1. 변환할 수 있는 모든 단어에 대하여 큐에 삽입
			for (int index = 0; index < words.length; index++) {

				if (isVisited.contains(words[index])) {
					continue;
				}

				// 1-1. 현재 단어와 바꿀 단어와 다른 알파벳 갯수를 구하고
				int number = 0;
				for (int pos = 0; pos < words[index].length(); pos++) {

					if (curString.charAt(pos) != words[index].charAt(pos)) {
						number++;
					}

				}

				// 1-2. 다른 알파벳 개수가 1개 라면 변환 가능
				if (number == 1) {
					isVisited.add(words[index]);
					nodeQ.offer(new Node(words[index], depth + 1));
				}

			}

		}

		return 0;

	}

}
