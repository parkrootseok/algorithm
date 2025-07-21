import java.io.*;
import java.util.*;

/**
 * BOJ_불!
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int[] dr = new int[]{-1, 1, 0, 0};
	static final int[] dc = new int[]{0, 0, -1, 1};
	static final char EMPTY = '.';

	static int R, C;
	static char[][] building;

	static boolean[][] isVisited;
	static Queue<int[]> positions;
	static Queue<int[]> fires;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		building = new char[R][C];
		isVisited = new boolean[R][C];
		positions = new ArrayDeque<>();
		fires = new ArrayDeque<>();
		for (int row = 0; row < R; row++) {
			char[] inputs =  br.readLine().toCharArray();
			for (int col = 0; col < C; col++) {
				building[row][col] = inputs[col];
				if (building[row][col] == 'J') {
					positions.add(new int[]{row, col, 0});
					isVisited[row][col] = true;
				}
				if (building[row][col] == 'F') {
					fires.add(new int[]{row, col});
				}
			}
		}

		sb.append(bfs());
		bw.write(sb.toString());
		bw.close();

	}

	public static String bfs() {
		while (!positions.isEmpty()) {
			burn();

			int size = positions.size();
			for (int s = 0; s < size; s++) {
				int[] pos = positions.poll();

				for (int dir = 0; dir < dr.length; dir++) {
					int nr = pos[0] + dr[dir];
					int nc = pos[1] + dc[dir];

					if (pos[0] == 0 || pos[0] == R - 1 || pos[1] == 0 || pos[1] == C - 1) {
						return String.valueOf(pos[2] + 1);
					}

					if (!outRange(nr, nc) && !isVisited[nr][nc] && building[nr][nc] == EMPTY) {
						isVisited[nr][nc] = true;
						positions.offer(new int[]{nr, nc, pos[2] + 1});
					}
				}
			}
		}

		return "IMPOSSIBLE";
	}

	public static void burn() {
		int size = fires.size();
		for (int s = 0; s < size; s++) {
			int[] fire = fires.poll();

			for (int dir = 0; dir < dr.length; dir++) {
				int nr = fire[0] + dr[dir];
				int nc = fire[1] + dc[dir];

				if (!outRange(nr, nc) && building[nr][nc] == EMPTY) {
					building[nr][nc] = 'F';
					fires.offer(new int[]{nr, nc});
				}
			}
		}
	}

	public static boolean outRange(int row, int col) {
		return row < 0 || R <= row || col < 0 || C <= col;
	}


}
