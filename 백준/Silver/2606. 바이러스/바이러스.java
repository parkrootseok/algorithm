import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ_바이러스
 * @author parkrootseok
 */
class Main {

	public static class Computer {

		List<Integer> adjacentComputer;

		public Computer() {
			this.adjacentComputer = new ArrayList<>();
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int computerCount;
	static Computer[] computers;
	static int linkCount;

	static int count;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		isVisited = new boolean[computerCount + 1];

		dfs(1);

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int curComputer) {

		isVisited[curComputer] = true;

		for (int nextComputer : computers[curComputer].adjacentComputer) {

			if (!isVisited[nextComputer]) {
				count++;
				dfs(nextComputer);
			}

		}

	}

	public static void input() throws IOException {

		computerCount = Integer.parseInt(new StringTokenizer(br.readLine(), " ").nextToken());
		computers = new Computer[computerCount + 1];
		for (int index = 1; index <= computerCount; index++) {
			computers[index] = new Computer();
		}

		linkCount = Integer.parseInt(new StringTokenizer(br.readLine(), " ").nextToken());
		for (int lCount = 0; lCount < linkCount; lCount++) {

			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			computers[from].adjacentComputer.add(to);
			computers[to].adjacentComputer.add(from);

		}

	}

}