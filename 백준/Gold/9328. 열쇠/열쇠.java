import java.io.*;
import java.util.*;

/**
 * BOJ_열쇠
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Node {
		int h;
		int w;

		public Node(int h, int w) {
			this.h = h;
			this.w = w;
		}
	}

	static final char WALL = '*';
	static final char EMPTY = '.';
	static final char DOCUMENT = '$';

	static int T;
	static int H, W;
	static char[][] BUILDING;
	static int key;
	static Queue<Node> entrance;
	static Map<Integer, Queue<Node>> doors;
	static boolean[][] isVisited;
	static int answer;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		while (0 < T--) {

			st = new StringTokenizer(br.readLine().trim());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			BUILDING = new char[H][W];
			entrance = new ArrayDeque<>();
			doors = new HashMap<>();
			key = 0;
			answer = 0;
			for (int h = 0; h < H; h++) {
				BUILDING[h] = br.readLine().toCharArray();
				for (int w = 0; w < W; w++) {
					if (BUILDING[h][w] != WALL && isEdge(h, w)) {
						if (BUILDING[h][w] == DOCUMENT) {
							BUILDING[h][w] = EMPTY;
							answer++;
						} else if (isKey(h, w)) {
							key |= 1 << (BUILDING[h][w] - 'a');
						} 
						entrance.offer(new Node(h, w));
					}
				}
			}

			char[] inputs = br.readLine().toCharArray();
			for (char input : inputs) {
				if (input != '0') {
					key |= (1 << (input - 'a'));
				}
			}

			isVisited = new boolean[H][W];
			for (Node node : entrance) {
				int cH = node.h;
				int cW = node.w;
				if (isDoor(cH, cW)) {
					int doorKey = 1 << (BUILDING[cH][cW] - 'A');
					if (!hasKey(doorKey)) {
						if (doors.containsKey(doorKey)) {
							doors.get(doorKey).offer(new Node(cH, cW));
						} else {
							Queue<Node> queue = new ArrayDeque<>();
							queue.offer(new Node(cH, cW));
							doors.put(doorKey, queue);
						}
						continue;
					}
				}
				bfs(cH, cW);
			}

			sb.append(answer).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
	}

	public static void bfs(int h, int w) {
		int[] dh = new int[]{-1, 1, 0, 0};
		int[] dw = new int[]{0, 0, -1, 1};

		Queue<Node> nodes = new ArrayDeque<>();
		nodes.offer(new Node(h, w));
		isVisited[h][w] = true;

		while (!nodes.isEmpty()) {
			Node node = nodes.poll();

			for (int dir = 0; dir < dh.length; dir++) {
				int nH = node.h + dh[dir];
				int nW = node.w + dw[dir];

				if (outRange(nH, nW) || isVisited[nH][nW] || BUILDING[nH][nW] == WALL) {
					continue;
				}

				if (BUILDING[nH][nW] == DOCUMENT) {
					answer++;
				} else if (isKey(nH, nW)) {
					key |= (1 << BUILDING[nH][nW] - 'a');
					for (int doorKey : doors.keySet()) {
						if (hasKey(doorKey)) {
							Queue<Node> queue = doors.get(doorKey);
							while (!queue.isEmpty()) {
								Node door = queue.poll();
								nodes.offer(new Node(door.h, door.w));
							}
						}
					}
				} else if (isDoor(nH, nW)) {
					int doorKey = 1 << (BUILDING[nH][nW] - 'A');
					if (!hasKey(doorKey)) {
						if (doors.containsKey(doorKey)) {
							doors.get(doorKey).offer(new Node(nH, nW));
						} else {
							Queue<Node> queue = new ArrayDeque<>();
							queue.offer(new Node(nH, nW));
							doors.put(doorKey, queue);
						}
						continue;
					}
				}

				nodes.offer(new Node(nH, nW));
				isVisited[nH][nW] = true;
			}
		}
	}

	public static boolean hasKey(int doorKey) {
		return (key & doorKey) == doorKey;
	}

	public static boolean outRange(int h, int w) {
		return h < 0 || H <= h || w < 0 || W <= w;
	}

	public static boolean isKey(int h, int w) {
		return 'a' <= BUILDING[h][w] && BUILDING[h][w] <= 'z';
	}

	public static boolean isDoor(int h, int w) {
		return 'A' <= BUILDING[h][w] && BUILDING[h][w] <= 'Z';
	}

	public static boolean isEdge(int h, int w) {
		return h == 0 || h == H - 1 || w == 0 || w == W - 1;
	}

}
