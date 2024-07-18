import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2342_DanceDanceRevolution
 * @author parkrootseok
 *
 * - 발판 하나의 중점(0)을 기준으로 위(1), 아래(3), 왼쪽(2), 오른쪽(4) 연결
 * - 처음은 중점에 위치
 * - 두 발은 동시에 움직이지 않음
 * - 두 발이 중점을 제외하고 같은 위치에 위치할 수 없음
 * - 드는 힘
 *  - 같은 지점 : 1
 *  - 중앙 -> 다른 위치 : 2
 *  - 인접한 지점 이동 : 3
 *  - 반대편 이동 : 4
 *
 * 1. 지시 사항 입력
 * 2. 지시 사항에 따라 발을 이동하며 사용한 힘을 계산
 */
public class Main {

	public static final int SIZE = 5;
	public static int[][] power = {
		{1, 2, 2, 2, 2},
		{0, 1, 3, 4, 3},
		{0, 3, 1, 3, 4},
		{0, 4, 3, 1, 3},
		{0, 3, 4, 3, 1}
	};

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 지시 사항 입력
		inputs = br.readLine().trim().split(" ");

		// 2. 지시 사항에 따라 발을 이동하며 사용한 힘을 계산
		dp = new int[inputs.length][SIZE][SIZE];

		sb.append(dfs(0, 0, 0));
		bw.write(sb.toString());
		bw.close();

	}

	public static int dfs(int depth, int left, int right) {

		// 모든 지시사항을 수행했다면 종료
		if (depth == inputs.length) {
			return 0;
		}

		// 이전에 이미 밟은 구간이라면 종료
		if (dp[depth][left][right] != 0) {
			return dp[depth][left][right];
		}

		// 왼쪽 발판을 이동했을 때와 오른쪽 발판을 이동했을 때 최소값으로 초기화
		int moveDir = Integer.parseInt(inputs[depth]);
		dp[depth][left][right] = Math.min(
			dfs(depth + 1, moveDir, right) + power[left][moveDir],
			dfs(depth + 1, left, moveDir) + power[right][moveDir]
		);

		return dp[depth][left][right];

	}

}