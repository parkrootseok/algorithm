import java.io.*;
import java.util.Arrays;

/**
 * BOJ_에너그램
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;
	static char[] letters;
	static boolean[] isUsed;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		for (int count = 0; count < N; count++) {

			letters = br.readLine().trim().toCharArray();
			isUsed = new boolean[letters.length];

			Arrays.sort(letters);
			permutation(new StringBuilder(), 0);

		}

		bw.close();

	}

	public static void permutation(StringBuilder cur, int depth) throws IOException {

		if (depth == letters.length) {

			bw.write(cur.toString());
			bw.write('\n');
			return;

		}

		for (int index = 0; index < letters.length; index++) {

			// 만약, 이전 문자와 동일하다면 이전 문자가 사용되지 않은 경우 패스
			if (isUsed[index] || (0 < index && letters[index] == letters[index - 1] && !isUsed[index - 1])) {
				continue;
			}

			isUsed[index] = true;
			cur.append(letters[index]);
			permutation(cur, depth + 1);
			cur.deleteCharAt(cur.length() - 1);
			isUsed[index] = false;

		}

	}

}