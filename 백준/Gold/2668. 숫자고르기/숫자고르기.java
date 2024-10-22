import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * BOJ_숫자고르기
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int size;
	static int[] numbers;
	static boolean[] isVisited;
	static List<Integer> result;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		size = Integer.parseInt(br.readLine().trim());
		numbers = new int[size + 1];

		for (int idx = 1; idx <= size; idx++) {
			String input = br.readLine().trim();
			numbers[idx] = Integer.parseInt(input);
		}

		result = new ArrayList<>();
		isVisited = new boolean[size + 1];
		for (int idx = 1; idx <= size; idx++) {
			isVisited[idx] = true;
			dfs(1, idx, idx);
			isVisited[idx] = false;
		}

		sb.append(result.size()).append("\n");
		for (Integer num : result) {
			sb.append(num).append("\n");
		}
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int depth, int start, int cur) {

		if (start == numbers[cur]) {
			result.add(start);
			return;
		}

		if (!isVisited[numbers[cur]]) {
			isVisited[numbers[cur]] = true;
			dfs(depth + 1, start, numbers[cur]);
			isVisited[numbers[cur]] = false;
		}

	}

}