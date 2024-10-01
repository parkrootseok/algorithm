import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * BOJ_숨바꼭질2
 * @author parkrootseok
 */
public class Main {

	static class Node {

		int position;

		public Node(int position) {
			this.position = position;
		}

	}

	public static int MAX = 100_000;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static int K;

	public static int[] isVisited;
	public static int min;
	public static int count;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 수빈이가 있는 위치 N과 동생이 있는 위치 K
		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);

		if (N >= K) {
			sb.append(N - K).append("\n").append(1);
			bw.write(sb.toString());
			bw.close();
			return;
		}

		min = Integer.MAX_VALUE;
		count = 0;
		isVisited = new int[MAX + 1];
		bfs();

		sb.append(min).append("\n").append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bfs() {

		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(N));
		isVisited[N] = 1;

		while(!queue.isEmpty()) {

			Node node = queue.poll();

			if (min < isVisited[node.position]) {
				return;
			}

			for (int index = 0; index < 3; index++) {

				int next = node.position;

				switch (index) {
					case 0:
						next += 1;
						break;
					case 1:
						next -= 1;
						break;
					case 2:
						next *= 2;
						break;
				}

				// 범위를 벗어나는 경우
				if (next < 0 || MAX < next) {
					continue;
				}

				// 동생을 찾은 경우 최단 시간 기록 및 카운팅
				if (next == K) {
					min = isVisited[node.position];
					count++;
				}

				// 처음 방문한 곳 or 다음 위치에서 도착 시간이 같은 경우 큐에 추가
				if (isVisited[next] == 0 || isVisited[next] == isVisited[node.position] + 1) {
					isVisited[next] = isVisited[node.position] + 1;
					queue.add(new Node(next));
				}

			}

		}

	}

}