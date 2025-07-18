import java.io.*;
import java.util.*;

/**
 * BOJ_불
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static final char EMPTY = '.';
	static final char WALL = '#';
	static final char START = '@';
	static final char FIRE = '*';

	static class Node {
		int h;
		int w;
		int time;

		public Node(int h, int w, int time) {
			this.h = h;
			this.w = w;
			this.time = time;
		}

	}

	static int[] dh = new int[]{-1, 1, 0, 0};
	static int[] dw = new int[]{0, 0, -1, 1};

	static int T;
	static int W, H;

	static char[][] building;
	static Queue<int[]> fires;
	static Queue<Node> node;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine().trim());

		while (T-- != 0) {
			st = new StringTokenizer(br.readLine().trim());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			building = new char[H][W];
			node = new ArrayDeque<>();
			fires = new ArrayDeque<>();

			for (int h = 0; h < H; h++) {
				char[] inputs = br.readLine().toCharArray();
				for (int w = 0; w < W; w++) {
					building[h][w] = inputs[w];
				}
			}

			sb.append(bfs()).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}
	public static String bfs() {

		boolean[][] isVisited = new boolean[H][W];
		int[][] times = new int[H][W];
		for (int h = 0; h < H; h++) {
			Arrays.fill(times[h], -1);
		}

		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (building[h][w] == START) {
					isVisited[h][w] = true;
					node.offer(new Node(h, w, 0));
				}
				if (building[h][w] == FIRE) {
					times[h][w] = 0;
					fires.offer(new int[]{h, w});
				}
			}
		}

		while (!fires.isEmpty()) {
			int[] fire = fires.poll();
			int cH = fire[0];
			int cW = fire[1];
			for (int dir = 0; dir < dh.length; dir++) {
				int nH = cH + dh[dir];
				int nW = cW + dw[dir];
				if (outRange(nH, nW)) {
					continue;
				}
				if (building[nH][nW] == EMPTY && times[nH][nW] == -1) {
					times[nH][nW] = times[cH][cW] + 1;
					fires.offer(new int[]{nH, nW});
				}
			}
		}

		while (!node.isEmpty()) {
			Node cNode = node.poll();
			int cH = cNode.h;
			int cW = cNode.w;
			int cT = cNode.time;

			if (cH == 0 || cH == H - 1 || cW == 0 || cW == W - 1) {
				return String.valueOf(cT + 1);
			}

			for (int dir = 0; dir < dh.length; dir++) {
				int nH = cH + dh[dir];
				int nW = cW + dw[dir];
				if (outRange(nH, nW)) {
					continue;
				}
				if (isVisited[nH][nW]) {
					continue;
				}
				if (building[nH][nW] == EMPTY && (times[nH][nW] == -1 || cT + 1 < times[nH][nW])) {
					isVisited[nH][nW] = true;
					node.offer(new Node(nH, nW, cT + 1));
				}
			}
		}

		return "IMPOSSIBLE";

	}

	public static boolean outRange(int h, int w) {
		return h < 0 || H <= h || w < 0 || W <= w;
	}

}
