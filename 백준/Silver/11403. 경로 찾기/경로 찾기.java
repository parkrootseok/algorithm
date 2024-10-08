import java.io.*;

/**
 * BOJ_경로찾기
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;
	static int[][] map;
	static int[][] result;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		result = new int[N][N];
		for (int row = 0; row < N; row++) {
			String[] inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < N; col++) {
				// i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재
				map[row][col] = Integer.parseInt(inputs[col]);
			}
		}

		for (int stopover = 0; stopover < N; stopover++) {
			for (int from = 0; from < N; from++) {
				for (int to = 0; to < N; to++) {
					if (map[from][stopover] == 1 && map[stopover][to] == 1) {
						map[from][to] = 1;
					}
				}
			}
		}

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				sb.append(map[row][col]).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

}