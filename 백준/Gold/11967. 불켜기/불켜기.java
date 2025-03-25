import java.io.*;
import java.util.*;

/**
 * BOJ_불켜기
 * @author parkrootseok
 */
public class Main {

	static int[] dr = new int[]{-1, 1, 0, 0};
	static int[] dc = new int[]{0, 0, -1, 1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;
	static List<int[]>[][] switches;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		switches = new ArrayList[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				switches[row][col] = new ArrayList<>();
			}
		}

		for (int index = 0; index < M; index++) {

			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			switches[x][y].add(new int[]{a, b});

		}

		sb.append(bfs());
		bw.write(sb.toString());
		bw.close();

	}

	public static int bfs() {

		boolean[][] isVisited = new boolean[N][N];
		boolean[][] isTurnOn = new boolean[N][N];

		Queue<int[]> nodes = new ArrayDeque<>();
		nodes.add(new int[]{0, 0});
		isVisited[0][0] = true;
		isTurnOn[0][0] = true;

		int answer = 1;
		while (!nodes.isEmpty()) {

			int[] node = nodes.poll();
			int cRow = node[0];
			int cCol = node[1];

			if (!switches[cRow][cCol].isEmpty()) {

				isVisited = new boolean[N][N];
				isVisited[cRow][cCol] = true;

				for (int[] s : switches[cRow][cCol]) {

					int row = s[0];
					int col = s[1];

					if (!isTurnOn[row][col]) {
						answer++;
						isTurnOn[row][col] = true;
					}

				}

				switches[cRow][cCol].clear();

			}

			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = cRow + dr[dir];
				int nCol = cCol + dc[dir];

				if (outRange(nRow, nCol) || isVisited[nRow][nCol]) {
					continue;
				}

				if (isTurnOn[nRow][nCol]) {
					isVisited[nRow][nCol] = true;
					nodes.add(new int[]{nRow, nCol});
				}

			}

		}

		return answer;
		
	}

	public static boolean outRange(int row, int col) {
		return row < 0 || N <= row || col < 0 || N <= col;
	}

}