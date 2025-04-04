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
				if (chocolate[r][c] == CHOCOLATE && isTree(r, c)) {
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

	static boolean isTree(int bRow, int bCol) {

		boolean[][] isVisited = new boolean[size][size];
		Queue<int[]> nodes = new ArrayDeque<>();

		// 1. 떼어낸 초콜릿 위치를 제외한 곳 중 연결성을 확인할 시작점을 구한다.
		boolean found = false;
		for (int r = 0; r < size && !found; r++) {
			for (int c = 0; c < size && !found; c++) {
				if (chocolate[r][c] == EMPTY || r == bRow && c == bCol) {
					continue;
				}

				found = true;
				isVisited[r][c] = true;
				nodes.offer(new int[]{r, c});
			}
		}

		if (!found) return false;

		// 2. 시작점을 기준으로 방문할 수 있는 초콜릿수와 이를 만족할 때 간선수를 카운팅
		int nodeCount = 1;
		int edgeCount = 0;
		while (!nodes.isEmpty()) {

			int[] node = nodes.poll();
			int cRow = node[0];
			int cCol = node[1];

			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = cRow + dr[dir];
				int nCol = cCol + dc[dir];

				if (nRow == bRow && nCol == bCol) {
					continue;
				}

				if (outRange(nRow, nCol) || chocolate[nRow][nCol] != CHOCOLATE) {
					continue;
				}

				// 간선수 카운팅
				edgeCount++;

				if (!isVisited[nRow][nCol]) {
					// 연결된 초콜릿 수 카운팅
					nodeCount++;
					isVisited[nRow][nCol] = true;
					nodes.offer(new int[]{nRow, nCol});
				}

			}

		}

		// 떼어낸 초콜릿을 제외한 모든 초콜릿을 방문할 수 있으며
		// 연결된 초콜릿 조각들에 사이클이 발생하는지 확인
		return nodeCount == hasChocolateCount - 1 && edgeCount >> 1 == nodeCount - 1;

	}

	static boolean outRange(int r, int c) {
		return r < 0 || size <= r || c < 0 || size <= c;
	}

}