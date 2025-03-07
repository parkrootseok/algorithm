import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * BOJ_문자열집합
 * @author parkrootseok
 */
public class Main {

	static class TrieNode {

		HashMap<Character, TrieNode> children = new HashMap<>();
		boolean isEnd;

		public void insert(String word) {

			TrieNode root = this;
			for (int idx = 0; idx < word.length(); idx++) {

				char c = word.charAt(idx);

				root.children.putIfAbsent(c, new TrieNode());
				root = root.children.get(c);

				if (idx == word.length() - 1) {
					root.isEnd = true;
					return;
				}

			}

		}

		public boolean contains(String word) {

			TrieNode root = this;
			for (int idx = 0; idx < word.length(); idx++) {

				char c = word.charAt(idx);
				TrieNode child = root.children.get(c);

				if (child == null) {
					return false;
				}

				root = child;

			}

			return root.isEnd;

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Set<String> words = new HashSet<>();
		for (int n = 0; n < N; n++) {
			String word = br.readLine().trim();
			words.add(word);
		}

		int answer = 0;
		for (int m = 0; m < M; m++) {
			String word = br.readLine().trim();
			if (words.contains(word)) {
				answer++;
			}

		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

}