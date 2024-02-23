import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/***
 * BOJ_1987_알파벳
 * @author parkrootseok
 * 
 * - 보드가 주어지고 각 칸에는 대문자 알파벳이 있다
 * - 말이 주어지고 말은 좌측 상단에 존재
 * - 이때 말은 상하좌우로 이동가능
 * - 새로 이동한 칸에 적혀 있는 알파벳은 처음 등장하는 알파벳
 * - 최대한 몇칸을 지날 수 있을까?(시작점도 1칸 움직인것이라 생각)
 * 
 * 1. 보드의 사이즈가 주어진다.
 * 2. 보드에 대한 정보를 입력 받는다.
 * 3. 말을 4가지 방향으로 이동 시작
 *  3-1. 방문 완료한 알파벳 기록
 *  3-2. 이동할 위치가 유효하고
 *  3-3. 방문한 알파벳이 아니라면
 *  3-4. 말을 이동
 * 
 */

public class Main {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int rowSize;
	static int colSize;
	static int maxMoveCnt;

	static char[][] board;
	static boolean[] isVisited;

	public static boolean isValid(int row, int col) {

		if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
			return true;
		}

		return false;

	}

	public static void move(int level, int row, int col) {

		maxMoveCnt = Math.max(maxMoveCnt, level);

		// 3-1. 방문 완료한 알파벳 기록
		isVisited[board[row][col] - 'A'] = true;

		int nextRow, nextCol;
		for (int direction = 0; direction < dx.length; direction++) {

			nextRow = row + dx[direction];
			nextCol = col + dy[direction];

			// 3-2. 이동할 위치가 유효하고
			if (!isValid(nextRow, nextCol)) {
				continue;
			}

			// 3-3. 방문한 알파벳이 아니라면
			if (isVisited[board[nextRow][nextCol] - 'A']) {
				continue;
			}

			// 3-3. 말을 이동
			move(level + 1, nextRow, nextCol);

			// 사용한 알파벳들은 반납
			isVisited[board[nextRow][nextCol] - 'A'] = false;

		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 보드의 사이즈가 주어진다.
		inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[0]);
		colSize = Integer.parseInt(inputs[1]);

		// 2. 보드에 대한 정보를 입력 받는다.
		board = new char[rowSize][colSize];
		for (int curRow = 0; curRow < rowSize; curRow++) {
			String input = br.readLine().trim();
			for (int curCol = 0; curCol < colSize; curCol++) {
				board[curRow][curCol] = input.charAt(curCol);
			}

		}

		// 3. 말을 4가지 방향으로 이동 시작
		isVisited = new boolean[27];
		maxMoveCnt = Integer.MIN_VALUE;
		move(1, 0, 0);

		sb.append(maxMoveCnt);
		bw.write(sb.toString());
		bw.close();

	}

}