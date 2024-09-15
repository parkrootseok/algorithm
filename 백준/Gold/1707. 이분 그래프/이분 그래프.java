import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_이분그래프
 * @author parkrootseok
 */
public class Main {

	public static final int RED = 1;
	public static final int BLUE = -1;

	public static class Vertex {

		int number;
		List<Integer> adjacentVertices = new ArrayList<>();

		public Vertex(int number) {
			this.number = number;
		}
	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int testCount;
	public static int vertexCount;
	public static int edgeCount;
	public static Vertex[] vertices;
	public static int[] colors;
	public static boolean isPossible;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		testCount = Integer.parseInt(br.readLine().trim());

		for (int curTestCount = 0; curTestCount < testCount; curTestCount++) {

			String[] inputs = br.readLine().trim().split(" ");
			vertexCount = Integer.parseInt(inputs[0]);
			vertices = new Vertex[vertexCount + 1];

			for (int number = 1; number <= vertexCount; number++) {
				vertices[number] = new Vertex(number);
			}

			edgeCount = Integer.parseInt(inputs[1]);
			for (int curEdgeCount = 0; curEdgeCount < edgeCount; curEdgeCount++) {
				inputs = br.readLine().trim().split(" ");
				int from = Integer.parseInt(inputs[0]);
				int to = Integer.parseInt(inputs[1]);

				vertices[from].adjacentVertices.add(to);
				vertices[to].adjacentVertices.add(from);

			}

			isPossible = true;
			colors = new int[vertexCount + 1];
			for (int number = 1; number <= vertexCount; number++) {

				if (!isPossible) {
					break;
				}

				// 방문하지 않은 정점만 탐색
				if (colors[number] == 0) {
					dfs(number, RED);
				}

			}

			if (isPossible) {
				sb.append("YES").append("\n");
			}

			else {
				sb.append("NO").append("\n");
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int curVertex, int color) {

		if (!isPossible) {
			return;
		}

		// 현재 정점에 색깔 할당
		colors[curVertex] = color;

		for (int adjacentVertex : vertices[curVertex].adjacentVertices) {

			// 현재 정점의 색과 동일한 인접한 정점이 있다면 불가능하므로 종료
			if (colors[adjacentVertex] == colors[curVertex]) {
				isPossible = false;
				return;
			}

			// 방문하지 않은 정점만 탐색
			if (colors[adjacentVertex] == 0) {
				dfs(adjacentVertex, color * -1);
			}

		}

	}

}