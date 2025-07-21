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

	static int T, N;
	static int[] parent;

	static int count;
	static boolean[] isDone;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		while (T-- != 0) {

			N = Integer.parseInt(br.readLine());

			parent = new int[N + 1];
			st = new StringTokenizer(br.readLine().trim());
			for (int student = 1; student <= N; student++) {
				parent[student] = Integer.parseInt(st.nextToken());
			}

			count = 0;
			isVisited = new boolean[N + 1];
			isDone = new boolean[N + 1];
			for (int student = 1; student <= N; student++) {
				if (!isVisited[student]) {
					isVisited[student] = true;
					dfs(student);
				}
			}

			sb.append(N - count).append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int cur) {
		// 다음 방문할 학생 구하기
		int next = parent[cur];

		// 다음 학생을 방문하지 않은 경우 탐색 시작
		if (!isVisited[next]) {
			isVisited[next] = true;
			dfs(next);
		}

		// 다음 학생을 이미 방문했다면,
		else {
			// 사이클 발생 여부를 체크했는지 확인
			if (!isDone[next]) {
				// 역추적하면서 카운팅
				count++;
				while (next != cur) {
					count++;
					next = parent[next];
				}

			}
		}

		// 방문한 학생에 대하여 검사 완료 체크
		isDone[cur] = true;
	}

}
