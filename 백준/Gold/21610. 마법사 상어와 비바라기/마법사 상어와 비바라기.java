import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_21610_마법사상어와비바라기
 * @author parkrootseok
 * 
 * 1. 격자의 크기와 이동할 횟수를 입력 받는다.
 * 2. 격자에 대한 정보를 입력 받는다.
 * 3. 이동활 횟수만큼 정보를 받는다. (d방향으로 s칸 이동)
 * 4. 받은 정보를 통해 이동을 시작한다.
 *  4-1. 모든 구름이 d방향으로 s칸 이동
 *  4-2. 비가 내리고 구름이 존재하는 각 칸의 바구니 물의 양이 1증가
 *  4-3. 물이 증가한 칸에 물복사버그 시전(대각선 방향으로 거리가 1인 칸에 보유한 물의 수만큼 증가)
 * 5. 물의 양이 2개 이상인 칸에 구름이 생기고, 물의 양이 2 줄어듬
 *  
 */

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	// 왼쪽, 왼쪽 위 대각선, 위로, 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선, 아래, 왼쪽 아래 대각선
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

	static int answer;
	static int N, M;
	static int[][] map;
	static boolean[][] isHasCloud;

	public static void moveCloud(int d, int s) {

		boolean[][] afterMove = new boolean[N][N];
		boolean[][] isVisted = new boolean[N][N];
		
		int nx;
		int ny;

		for (int row = 0; row < N; row++) {

			for (int cols = 0; cols < N; cols++) {
				
				// 4-1. 모든 구름이 d방향으로 s칸 이동
				if (isHasCloud[row][cols]) {

					nx = (N + row + dx[d] * s) % N;
					ny = (N + cols + dy[d] * s) % N;

					afterMove[nx][ny] = true;
				
					// 4-2. 각 구름에서 비가 내려 물의 양 1증가
					map[nx][ny]++;

				}

			}

		}
		
		isHasCloud = afterMove;

		// 4-3. 물이 증가한 칸에 물복사버그 시전(대각선 방향으로 거리가 1인 칸에 보유한 물이 있는 바구니 수만큼 증가)
		for (int row = 0; row < N; row++) {

			for (int cols = 0; cols < N; cols++) {

				if (isHasCloud[row][cols]) {

					for (int index = 1; index < dx.length; index += 2) {

						nx = row + dx[index];
						ny = cols + dy[index];

						if ((0 <= nx && nx < N && 0 <= ny && ny < N) && map[nx][ny] > 0) {

							map[row][cols]++;

						}

					}

				}

			}

		}

		// 5. 물의 양이 2개 이상인 칸에 구름이 생기고, 물의 양이 2 줄어듬
		for (int row = 0; row < N; row++) {

			for (int cols = 0; cols < N; cols++) {

				// 구름이 생겼던 자리는 다시 구름을 삭
				if (isHasCloud[row][cols]) {
					isHasCloud[row][cols] = false;
				} else if (map[row][cols] >= 2 && !isVisted[row][cols]) {
					map[row][cols] -= 2;
					isHasCloud[row][cols] = true;
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		//	1. 격자의 크기와 이동할 횟수를 입력 받는다.
		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		map = new int[N][N];

		// 2. 격자에 대한 정보를 입력 받는다.
		for (int row = 0; row < N; row++) {

			inputs = br.readLine().split(" ");

			int cols = 0;
			for (String input : inputs) {
				map[row][cols++] = Integer.parseInt(input);
			}

		}

		// 3. 이동활 횟수만큼 정보를 받는다.
		isHasCloud = new boolean[N][N];
		isHasCloud[N - 1][0] = true;
		isHasCloud[N - 1][1] = true;
		isHasCloud[N - 2][0] = true;
		isHasCloud[N - 2][1] = true;

		int direction, position;
		for (int number = 0; number < M; number++) {

			inputs = br.readLine().split(" ");
			direction = Integer.parseInt(inputs[0]);
			position = Integer.parseInt(inputs[1]);

			// 4. 받은 정보를 통해 이동을 시작한다.
			moveCloud(direction - 1, position % N);

		}

		answer = 0;
		for (int row = 0; row < N; row++) {

			for (int cols = 0; cols < N; cols++) {

				answer += map[row][cols];

			}

		}

		sb.append(answer).append("\n");
		bw.write(sb.toString());
		bw.close();	

		return;

	}

}