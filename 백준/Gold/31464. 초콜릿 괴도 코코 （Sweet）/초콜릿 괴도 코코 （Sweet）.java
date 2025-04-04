import java.io.*;
import java.util.*;

public class Main {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static final char CHOCOLATE = '#';
	static final char EMPTY = '.';

	static int size;
	static int hasChocolateCount;
	static char[][] chocolate;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		size = Integer.parseInt(br.readLine());
		chocolate = new char[size][size];

		for (int r = 0; r < size; r++) {
			String line = br.readLine();
			for (int c = 0; c < size; c++) {
				chocolate[r][c] = line.charAt(c);
				if (chocolate[r][c] == CHOCOLATE) {
					hasChocolateCount++;
				}
			}
		}

		List<int[]> result = new ArrayList<>();

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (chocolate[r][c] == CHOCOLATE && isTreeAfterRemove(r, c)) {
					result.add(new int[]{r + 1, c + 1});
				}
			}
		}

		sb.append(result.size()).append("\n");
		for (int[] pos : result) {
			sb.append(pos[0]).append(" ").append(pos[1]).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static boolean isTreeAfterRemove(int bRow, int bCol) {

		boolean[][] visited = new boolean[size][size];
		Queue<int[]> q = new ArrayDeque<>();

		boolean found = false;
		for (int r = 0; r < size && !found; r++) {
			for (int c = 0; c < size && !found; c++) {
				if (chocolate[r][c] == CHOCOLATE && !(r == bRow && c == bCol)) {
					q.offer(new int[]{r, c});
					visited[r][c] = true;
					found = true;
				}
			}
		}

		if (!found) return false;

		int nodeCount = 1;
		int edgeCount = 0;

		while (!q.isEmpty()) {

			int[] cur = q.poll();

			int r = cur[0];
			int c = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (outRange(nr, nc)) {
					continue;
				}

				if (chocolate[nr][nc] != CHOCOLATE) {
					continue;
				}

				if (nr == bRow && nc == bCol) {
					continue;
				}

				edgeCount++;

				if (!visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[]{nr, nc});
					nodeCount++;
				}

			}

		}

		return nodeCount == hasChocolateCount - 1 && edgeCount >> 1 == nodeCount - 1;

	}

	static boolean outRange(int r, int c) {
		return r < 0 || r >= size || c < 0 || c >= size;
	}

}