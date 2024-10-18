import java.io.*;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * BOJ_에너그램
 * @author parkrootseok
 */
public class Main {

	static final int SIZE = 26;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;
	static char[] inputs;
	static int[] alphabets;
	static Stack<Character> combi;
	static Set<String> result;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().trim());
		for (int count = 0; count < N; count++) {

			inputs = br.readLine().trim().toCharArray();

			alphabets = new int[SIZE];
			for (char input : inputs) {
				alphabets[input - 'a']++;
			}

			result = new TreeSet<>();
			combi = new Stack<>();
			backTracking();

			result.forEach(System.out::println);

		}

		bw.close();

	}

	public static void backTracking() throws IOException {

		if (combi.size() == inputs.length) {

			sb = new StringBuilder();

			for (char c : combi) {
				sb.append(c);
			}

			result.add(sb.toString());
			return;

		}

		for (int index = 0; index < SIZE; index++) {

			if (alphabets[index] > 0) {

				alphabets[index]--;
				combi.push((char)('a' + index));
				backTracking();
				combi.pop();
				alphabets[index]++;

			}

		}

	}

}