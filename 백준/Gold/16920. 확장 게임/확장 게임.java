import java.io.*;
import java.util.*;

/**
 * BOJ_확장 게임
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Player {
		int moveCount;
		Queue<int[]> continents;

		public Player(int moveCount) {
			this.moveCount = moveCount;
			this.continents = new ArrayDeque<>();
		}
	}

	static final int[] dr = new int[]{-1, 1, 0, 0};
	static final int[] dc = new int[]{0, 0, -1, 1};
	static final char EMPTY = '.';
	static final char WALL = '#';

	static int N, M, P;
	static char[][] map;
	static Player[] players;
	static int[] counts;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		counts = new int[P + 1];
		players = new Player[P + 1];

		st = new StringTokenizer(br.readLine().trim());
		for (int index = 1; index <= P; index++) {
			players[index] = new Player(Integer.parseInt(st.nextToken()));
		}

		for (int row = 0; row < N; row++) {
			String inputs = br.readLine();
			for (int col = 0; col < M; col++) {
				map[row][col] = inputs.charAt(col);
				if (map[row][col] != EMPTY && map[row][col] != WALL) {
					players[map[row][col] - '0'].continents.add(new int[]{row, col});
					counts[map[row][col] - '0']++;
				}
			}
		}


		while (bfs()) {
		}

		for (int index = 1; index <= P; index++) {
			sb.append(counts[index]).append(" ");
		}

		bw.write(sb.toString());
		bw.close();
	}

	public static boolean bfs()  {
		boolean flag = false;
		for (int index = 1; index <= P; index++) {
			Player player = players[index];
			for (int curMoveCount = 0; curMoveCount < player.moveCount; curMoveCount++) {
				if (player.continents.isEmpty()) {
					break;
				}
				int size = player.continents.size();
				while (0 < size--) {
					int[] continent = player.continents.poll();
					int r = continent[0];
					int c = continent[1];
					for (int dir = 0; dir < dr.length; dir++) {
						int nr = r + dr[dir];
						int nc = c + dc[dir];
						if (!outRange(nr, nc) && map[nr][nc] == EMPTY) {
							player.continents.add(new int[]{nr, nc});
							map[nr][nc] = map[r][c];
							counts[map[r][c] - '0']++;
							flag = true;
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean outRange(int r, int c) {
		return r < 0 || N <= r || c < 0 || M <= c;
	}

}
