import java.io.*;
import java.util.*;

/**
 * BOJ_Gaaaaaaaaaarden
 * @author parkrootseok
 */
public class Main {

	static class Node {
		int row;
		int col;
		int color;
		int time;

		public Node(int row, int col, int color, int time) {
			this.row = row;
			this.col = col;
			this.color = color;
			this.time = time;
		}
	}

	static final int WHITE = 0;
	static final int GREEN = 1;
	static final int RED = 2;
	static final int FLOWER = 4;

	// 0은 호수, 1은 배양액을 뿌릴 수 없는 땅, 2는 배양액을 뿌릴 수 있는 땅을 의미한다.
	static final int LAKE = 0;
	static final int IMPOSSIBLE = 1;
	static final int POSSIBLE = 2;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N, M, G, R;
	static int[][] garden;
	static List<int[]> candidates;

	static int answer;
	static int[] permutation;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		garden = new int[N][M];
		candidates = new ArrayList<>();
		for (int row = 0; row < N; row ++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < M; col ++) {
				garden[row][col] = Integer.parseInt(st.nextToken());
				if (garden[row][col] == POSSIBLE) {
					candidates.add(new int[]{row, col});
				}
			}
		}

		answer = 0;
		permutation = new int[candidates.size()];
		bruteforce(0, 0, 0);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bruteforce(int depth, int green, int red) {
		if (green == G && red == R) {
			answer = Math.max(answer, bfs());
			return;
		}

		if (depth == candidates.size()) {
			return;
		}

		bruteforce(depth + 1, green, red);

		if (green < G) {
			permutation[depth] = GREEN;
			bruteforce(depth + 1, green + 1, red);
			permutation[depth] = WHITE;
		}

		if (red < R) {
			permutation[depth] = RED;
			bruteforce(depth + 1, green, red + 1);
			permutation[depth] = WHITE;
		}
	}

	public static int bfs() {

		int[] dr = new int[]{-1, 1, 0, 0};
		int[] dc = new int[]{0, 0, -1, 1};

		int[][] times = new int[N][M];
		int[][] isVisited = new int[N][M];
		Queue<Node> nodes = new ArrayDeque<>();

		for (int index = 0; index < candidates.size(); index++) {
			if (permutation[index] != WHITE) {
				int[] candidate = candidates.get(index);
				nodes.offer(new Node(candidate[0], candidate[1], permutation[index], 0));
				isVisited[candidate[0]][candidate[1]] = permutation[index];
			}
		}

		int count = 0;
		while (!nodes.isEmpty()) {
			Node node = nodes.poll();
			
			if (isVisited[node.row][node.col] == FLOWER) {
				continue;
			}

			for (int dir = 0; dir < dr.length; dir++) {
				int nr = node.row + dr[dir];
				int nc = node.col + dc[dir];

				if (nr < 0 || N <= nr || nc < 0 || M <= nc) {
					continue;
				}

				if (garden[nr][nc] == LAKE || isVisited[nr][nc] == FLOWER) {
					continue;
				}

				if (isVisited[nr][nc] == WHITE) {
					times[nr][nc] = node.time + 1;
					isVisited[nr][nc] = node.color;
					nodes.offer(new Node(nr, nc, node.color, node.time + 1));
				} else if (node.color == GREEN && isVisited[nr][nc] == RED && times[nr][nc] == node.time + 1) {
					isVisited[nr][nc] = FLOWER;
					count++;
				} else if (node.color == RED && isVisited[nr][nc] == GREEN && times[nr][nc] == node.time + 1) {
					isVisited[nr][nc] = FLOWER;
					count++;
				}
			}
		}

		return count;

	}

}
