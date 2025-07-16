import java.io.*;
import java.util.*;

/**
 * BOJ_뉴스 전하기
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static List<Integer>[] children;
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		children = new ArrayList[N];
		for (int index = 0; index < N; index++) {
			children[index] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			if (number != -1) {
				children[number].add(i);
			}
		}

		sb.append(dfs(0));
		bw.write(sb.toString());
		bw.close();

	}

	public static int dfs(int cur) {
		List<Integer> times = new ArrayList<>();
		for (int child : children[cur]) {
			times.add(dfs(child));
		}

		times.sort(Collections.reverseOrder());

		int max = 0;
		for (int index = 0; index < times.size(); index++) {
			max = Math.max(max, times.get(index) + index + 1);
		}

		return max;
	}

}
