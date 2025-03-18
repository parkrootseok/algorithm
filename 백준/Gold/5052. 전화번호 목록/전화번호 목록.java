import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * BOJ_전화번호목록
 * @author parkrootseok
 */
public class Main {

	static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
		}
	}

	static class Node {

		Map<Character, Node> children;
		boolean isLast;

		public Node() {
			this.children = new HashMap<>();
			this.isLast = false;
		}

		public void insert(String word) {

			Node node = this;

			for (int index = 0; index < word.length(); index++) {

				char c = word.charAt(index);

				// 현재 Node에 c를 가지는 노드가 없다면, Node를 할당
				node.children.putIfAbsent(c, new Node());

				// 다음 c를 추가할 노드 가져오기
				node = node.children.get(c);

				if (index == word.length() - 1) {
					node.isLast = true;
				}

			}

		}

		public boolean contains(String word) {

			Node node = this;

			for (char c : word.toCharArray()) {

				// c를 가지는 노드를 가져온다.
				Node child = node.children.get(c);

				// 없다면, 포함하지 않는 word
				if (Objects.isNull(child)) {
					return false;
				}

				// 있다면, 다음 자식을 조사
				node = child;

			}

			// 마지막으로 검사한 노드가 어떤 한 문장의 끝인데, 자식이 없다면
			if (node.isLast && !node.children.isEmpty()) {
				return true;
			}

			return false;

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;;

	static int T;
	static int N;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {

			N = Integer.parseInt(br.readLine());

			Trie trie = new Trie();
			String[] words = new String[N];
			for (int n = 0; n < N; n++) {
				words[n] = br.readLine();
				trie.root.insert(words[n]);
			}

			boolean flag = true;
			for (String word : words) {
				if (trie.root.contains(word)) {
					flag = false;
					break;
				}
			}

			sb.append(flag? "YES\n" : "NO\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}