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

	static final int[] dh = new int[]{-1, 1, 0, 0};
	static final int[] dw = new int[]{0, 0, -1, 1};
	static final char WALL = '*';
	static final char DOCUMENT = '$';

	static int T, H, W;
	static char[][] building;
	static Queue<int[]>[] candidates;
	static int key;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		while (0 < T--) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			building = new char[H + 2][W + 2];
			for (int h = 1; h <= H; h++) {
				char[] inputs = br.readLine().toCharArray();
				System.arraycopy(inputs, 0, building[h], 1, inputs.length);
			}

			key = 0;
			char[] inputs = br.readLine().toCharArray();
			for (char input : inputs) {
				if (input == '0') {
					break;
				}
				key |= (1 << input - 'a');
			}

			candidates = new Queue[26];
			for (int index = 0; index < 26; index++) {
				candidates[index] = new ArrayDeque<>();
			}
			sb.append(bfs()).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
	}

	public static int bfs() {
		int answer = 0;
		boolean[][] isVisited = new boolean[H + 2][W + 2];
		Queue<int[]> nodes = new ArrayDeque<>();

		nodes.offer(new int[]{0, 0});
		isVisited[0][0] = true;
		while (!nodes.isEmpty()) {
			int[] node = nodes.poll();
			int cH = node[0];
			int cW = node[1];

			for (int dir = 0; dir < dh.length; dir++) {
				int nH = cH + dh[dir];
				int nW = cW + dw[dir];

				if (outRange(nH, nW) || isVisited[nH][nW] || building[nH][nW] == WALL) {
					continue;
				}

				if (isKey(nH, nW)) {
					int keyNumber = building[nH][nW] - 'a';
					if (!hasKey(keyNumber)) {
						key |= (1 << keyNumber);
						while (!candidates[keyNumber].isEmpty()) {
							nodes.offer(candidates[keyNumber].poll());
						}
					}
				} else if (isDoor(nH, nW)) {
					int doorNumber = building[nH][nW] - 'A';
					if (!hasKey(doorNumber)) {
						candidates[doorNumber].offer(new int[]{nH, nW});
						continue;
					}
				} else if (building[nH][nW] == DOCUMENT) {
					answer++;
				}

				nodes.offer(new int[]{nH, nW});
				isVisited[nH][nW] = true;
			}
		}

		return answer;
	}

	public static boolean outRange(int h, int w) {
		return h < 0 || H + 2 <= h || w < 0 || W + 2 <= w;
	}

	public static boolean hasKey(int number) {
		int curBit = 1 << number;
		return (key & curBit) == curBit;
	}

	public static boolean isKey(int h, int w) {
		return 'a' <= building[h][w] && building[h][w] <= 'z';
	}

	public static boolean isDoor(int h, int w) {
		return 'A' <= building[h][w] && building[h][w] <= 'Z';
	}

}
