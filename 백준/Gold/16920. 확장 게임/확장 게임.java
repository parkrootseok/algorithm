import java.io.*;
import java.util.*;

/**
 * BOJ_텀 프로젝트
 * @author parkrootseok
 */
public class Main {


	static BufferedReader br;

	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Player {
		int moveCount;
		Queue<int[]> castles;

		public Player(int moveCount) {
			this.moveCount = moveCount;
			this.castles = new ArrayDeque<>();
		}
	}

	static final char EMPTY = '.';
	static final char WALL = '#';
	static final int[] dr = new int[]{-1, 1, 0, 0};
	static final int[] dc = new int[]{0, 0, -1, 1};

	static int N, M, P;
	static Player[] players;
	static char[][] map;
	static int[] counts;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		players = new Player[P + 1];
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 1; index <= P; index++) {
			players[index] = new Player(Integer.parseInt(st.nextToken()));
		}

		map = new char[N][M];
		counts = new int[P + 1];
		for (int r = 0 ; r < N; r++) {
			char[] inputs = br.readLine().toCharArray();
			for (int c = 0 ; c < M; c++) {
				map[r][c] = inputs[c];
				if (map[r][c] != EMPTY && map[r][c] != WALL) {
					players[map[r][c] - '0'].castles.offer(new int[]{r, c});
					counts[map[r][c] - '0']++;
				}
			}
		}

		while (true) {
			boolean isContinue = bfs();
			if (!isContinue) {
				break;
			}
		}

		for (int index = 1; index <= P; index++) {
			sb.append(counts[index]).append(" ");
		}

		bw.write(sb.toString());
		bw.close();
	}

	public static boolean bfs() {
		boolean flag = false;
		for (int index = 1; index <= P; index++) {
			for (int curMoveCount = 0; curMoveCount < players[index].moveCount; curMoveCount++) {
				if (players[index].castles.isEmpty()) {
					break;
				}

				int size = players[index].castles.size();
				while (0 < size--) {
					int[] pos = players[index].castles.poll();
					for (int dir = 0; dir < dr.length; dir++) {
						int nr = pos[0] + dr[dir];
						int nc = pos[1] + dc[dir];
						if (!outRange(nr, nc) && map[nr][nc] == EMPTY) {
							players[index].castles.offer(new int[]{nr, nc});
							map[nr][nc] = (char)(index + '0');
							counts[index]++;
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
