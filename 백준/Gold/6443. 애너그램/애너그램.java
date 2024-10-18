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

			// 입력받은 문자를 오름차순 정렬
			Arrays.sort(letters);

			do {
				sb.append(letters).append("\n");
			} while (nextPermutation());

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static boolean nextPermutation() {

		int i = letters.length - 1;

		// 가장 오른쪽에 존재하는 증가하는 구간을 탐색
		while (0 < i && letters[i - 1] >= letters[i]) {
			i--;
		}

		// 다음 순열이 존재하지 않음
		if (i == 0) {
			return false;
		}

		// 기준점보다 크면서 가장 작은값을 탐색
		int j = letters.length - 1;
		while (letters[i - 1] >= letters[j]) {
				j--;
		}

		// 기준점과 가장 큰 값중 작은값을 교환
		swap(i - 1, j);

		// 기준점을 기준으로 오름차순 정렬 수행
		revers(i, letters.length - 1);

		return true;

	}

	public static void swap(int i, int j) {
		char tmp = letters[i];
		letters[i] = letters[j];
		letters[j] = tmp;
	}

	public static void revers(int i, int j) {

		int start = i;
		int end = j;
		while (start < end) {
			swap(start, end);
			start++;
			end--;
		}
	}

}