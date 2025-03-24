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
	static List<int[]>[][] map;
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				map[row][col] = new ArrayList<>();
			}
		}

		for (int index = 0; index < M; index++) {
			st = new StringTokenizer(br.readLine(), " ");

			int cRow = Integer.parseInt(st.nextToken()) - 1;
			int cCol = Integer.parseInt(st.nextToken()) - 1;
			int nRow = Integer.parseInt(st.nextToken()) - 1;
			int nCol = Integer.parseInt(st.nextToken()) - 1;

			map[cRow][cCol].add(new int[]{nRow, nCol});
		}

		sb.append(bfs(0, 0));
		bw.write(sb.toString());
		bw.close();

	}


	public static int bfs(int row, int col) {

		int count = 1;
		boolean[][] isVisited = new boolean[N][N];
		boolean[][] isTurnOn = new boolean[N][N];
		Queue<int[]> positions = new ArrayDeque<>();
		positions.offer(new int[]{row, col});
		isVisited[row][col] = true;
		isTurnOn[row][col] = true;

		while (!positions.isEmpty()) {

			int[] curPosition = positions.poll();
			int curRow = curPosition[0];
			int curCol = curPosition[1];

			if (!map[curRow][curCol].isEmpty()) {
				isVisited = new boolean[N][N];
				isVisited[curRow][curCol] = true;
				for (int[] infos : map[curRow][curCol]) {
					if (!isTurnOn[infos[0]][infos[1]]) {
						count++;
						isTurnOn[infos[0]][infos[1]] = true;
					}
				}
				map[curRow][curCol].clear();
			}

			for (int dir = 0; dir < dr.length; dir++) {

				int nextRow = curRow + dr[dir];
				int nextCol = curCol + dc[dir];

				if (outRange(nextRow, nextCol)) {
					continue;
				}

				if (isVisited[nextRow][nextCol]) {
					continue;
				}

				if (isTurnOn[nextRow][nextCol]) {
					positions.add(new int[]{nextRow, nextCol});
					isVisited[nextRow][nextCol] = true;
				}

			}

		}

		return count;

	}

	public static boolean outRange(int row, int col) {
		return row < 0 || N <= row || col < 0 || N <= col;
	}

}