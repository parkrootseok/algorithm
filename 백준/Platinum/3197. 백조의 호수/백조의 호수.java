import java.io.*;
import java.util.*;

/**
 * BOJ_백조의호수
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int[] dr = new int[]{-1, 1, 0, 0};
	static final int[] dc = new int[]{0, 0, -1, 1};
	static final char WATER = '.';
	static final char ICE = 'X';
	static final char SWAN = 'L';

	static int R, C;
	static char[][] lake;
	static int orgSwanRow, orgSwanCol, destSwanRow, destSwanCol;
	static Queue<int[]> waters;
	static Queue<int[]> swans;
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		orgSwanRow = -1;
		orgSwanCol = -1;
		lake = new char[R][C];
		waters = new ArrayDeque<>();
		for (int r = 0; r < R; r++) {
			lake[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (lake[r][c] == SWAN) {
					lake[r][c] = WATER;
					if (orgSwanRow == -1 && orgSwanCol == -1) {
						orgSwanRow = r;
						orgSwanCol = c;
					} else {
						destSwanRow = r;
						destSwanCol = c;
					}
				}
				if (lake[r][c] == WATER) {
					waters.offer(new int[]{r, c});
				}
 			}
		}
		
		swans = new ArrayDeque<>();
		isVisited = new boolean[R][C];
		swans.offer(new int[]{orgSwanRow, orgSwanCol});
		isVisited[orgSwanRow][orgSwanCol] = true;

		int day = 0;
		while (!move()) {
			melt();
			day++;
		}

		sb.append(day);
		bw.write(sb.toString());
		bw.close();
	}

	private static boolean move() {
		Queue<int[]> candidates = new ArrayDeque<>();

		while (!swans.isEmpty()) {
			int[] node = swans.poll();
			int cR = node[0];
			int cC = node[1];

			if (destSwanRow == cR && destSwanCol == cC) {
				return true;
			}

			for (int dir = 0; dir < dr.length; dir++) {
				int nR = cR + dr[dir];
				int nC = cC + dc[dir];

				if (outRange(nR, nC) || isVisited[nR][nC]) {
					continue;
				}

				if (lake[nR][nC] == WATER) {
					swans.offer(new int[]{nR, nC});
				} else if (lake[nR][nC] == ICE) {
					candidates.offer(new int[]{nR, nC});
				}
				isVisited[nR][nC] = true;
			}
		}

		swans = candidates;
		return false;
	}

	private static void melt() {
		int size = waters.size();
		while (0 < size--) {
			int[] node = waters.poll();
			int cR = node[0];
			int cC = node[1];

			for (int dir = 0; dir < dr.length; dir++) {
				int nR = cR + dr[dir];
				int nC = cC + dc[dir];

				if (outRange(nR, nC)) {
					continue;
				}

				if (lake[nR][nC] == ICE) {
					waters.offer(new int[]{nR, nC});
					lake[nR][nC] = WATER;
				}
			}
		}

	}

	public static boolean outRange(int r, int c) {
		return r < 0 || R <= r || c < 0 || C <= c;
	}

}
