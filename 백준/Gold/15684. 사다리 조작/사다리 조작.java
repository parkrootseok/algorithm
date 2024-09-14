import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_사다리조작
 * @author parkrootseok
 *
 * - 시작 지점과 도착 지점의 숫자가 같도록 가로선을 추가
 * - 추가된 가로선의 개수가 3을 초과하거나 불가능하면 -1,
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static int M;
	public static int H;
	public static boolean isPossible;
	public static boolean[][] map;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 세로선 개수, 가로선 개수, 가로선을 놓을 수 있는 위치의 개수를 입력
		String[] inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		H = Integer.parseInt(inputs[2]);

		map = new boolean[H + 1][N + 1];
		for (int m = 0; m < M; m++) {
			inputs = br.readLine().trim().split(" ");

			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);

			// b번 세로선과 b + 1 세로선을 a번 점선 위치에서 연결
			map[a][b] = true;

		}

		if (check()) {
			System.out.println(0);
			return;
		}

		isPossible = false;
		int answer = -1;
		for (int useCount = 1; useCount <= 3; useCount++) {

			bruteforce(0, useCount);

			if (isPossible) {
				answer = useCount;
				break;
			}

		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void bruteforce(int curUseCount, int maxUseCount) {

		if (isPossible) {
			return;
		}

		// 가능한 가로선 개수를 모두 사용한 경우
		if (curUseCount == maxUseCount) {

			// 사다리 결과 확인
			if (check()) {
				isPossible = true;
			}

			return;
		}

		for (int row = 1; row <= H; row++) {

			for (int col = 1; col < N; col++) {

				// 이미 사다리가 있는 경우
				if (map[row][col]) {
					continue;
				}

				// 좌측에 이미 사다리가 있는 경우
				if (1 <= col - 1 && map[row][col - 1]) {
					continue;
				}

				// 우측에 이미 사다리가 있는 경우
				if (col + 1 < N && map[row][col + 1]) {
					continue;
				}

				map[row][col] = true;
				bruteforce(curUseCount + 1, maxUseCount);
				map[row][col] = false;

			}

		}

	}

	public static boolean check() {

		for (int col = 1; col <= N; col++) {

			int row = 1;
			int curCol = col;

			while (row <= H) {

				// 좌측으로 이동가능
				if (1 <= curCol - 1 && map[row][curCol - 1]) {
					curCol = curCol - 1;
				}

				// 우측으로 이동 가능
				else if (curCol < N && map[row][curCol]) {
					curCol = curCol + 1;
				}

				row++;

			}

			if (col != curCol) {
				return false;
			}

		}

		return true;

	}

}