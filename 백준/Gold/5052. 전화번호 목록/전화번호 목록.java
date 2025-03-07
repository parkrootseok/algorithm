import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

/**
 * BOJ_전화번호목록
 * @author parkrootseok
 */
public class Main {

	static class Node {

		Map<Character, Node> children = new HashMap<>();
		boolean isEnd;

		public void insert(String word) {

			Node node = this;
			for (int idx = 0; idx < word.length(); idx++) {

				char c = word.charAt(idx);

				node.children.putIfAbsent(c, new Node());
				node = node.children.get(c);

				if (idx == word.length() - 1) {
					node.isEnd = true;
					return;
				}

			}

		}

		public boolean contains(String word) {

			Node node = this;
			for (int idx = 0; idx < word.length(); idx++) {

				char c = word.charAt(idx);
				Node child = node.children.get(c);

				if (child == null) {
					return false;
				}

				node = child;

			}

			if (node.isEnd && node.children.isEmpty()) {
				return false;
			}

			return true;

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StreamTokenizer st;
	static StringBuilder sb;

	static int testcaseNumber;
	static int telNumber;
	static Node trie;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		testcaseNumber = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testcaseNumber; tc++) {

			boolean isPossible = true;
			trie = new Node();
			telNumber = Integer.parseInt(br.readLine().trim());

			String[] words = new String[telNumber];
			for (int tIdx = 0; tIdx < telNumber; tIdx++) {
				words[tIdx] = br.readLine();
				trie.insert(words[tIdx]);
			}

			for (String word : words) {
				if (trie.contains(word)) {
					isPossible = false;
					break;
				}
			}

			sb.append(isPossible ? "YES" : "NO").append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}