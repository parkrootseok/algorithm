import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_2048
 * @author parkrootseok
 */
public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int size;
	static int[][] board;
	static int answer;
	static int[] commands;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		size = Integer.parseInt(br.readLine());
		board = new int[size][size];
		for (int row = 0; row < size; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < size; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		commands = new int[5];
		answer = Integer.MIN_VALUE;
		simulation(0);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void simulation(int depth) {

		if (depth == 5) {
			int[][] copyBoard = copy(board);

			for (int cmd : commands) {
				swipe(copyBoard, cmd);
			}

			int max = 0;
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					max = Math.max(max, copyBoard[row][col]);
				}
			}

			answer = Math.max(answer, max);
			return;
		}

		for (int dir = 0; dir < 5; dir++) {
			commands[depth] = dir;
			simulation(depth + 1);
		}

	}

	public static int[][] copy(int[][] board) {
		int[][] copyBoard = new int[size][size];
		for (int row = 0; row < size; row++) {
			copyBoard[row] = board[row].clone();
		}
		return copyBoard;
	}

	public static void swipe(int[][] board, int dir) {

		switch (dir) {
			case 0:
				for (int col = 0; col < size; col++) {

					int nextPos = 0;
					int prevBlock = 0;

					for (int row = 0; row < size; row++) {
						if (board[row][col] != 0) {
							if (prevBlock == board[row][col]) {
								board[nextPos - 1][col] = prevBlock * 2;
								prevBlock = 0;
								board[row][col] = 0;
							} else {
								prevBlock = board[row][col];
								board[row][col] = 0;
								board[nextPos][col] = prevBlock;
								nextPos++;
							}
						}
					}
				}
				break;
			case 1:
				for (int col = 0; col < size; col++) {

					int nextPos = size - 1;
					int lastNumber = 0;

					for (int row = size - 1; 0 <= row; row--) {
						if (board[row][col] != 0) {
							if (lastNumber == board[row][col]) {
								board[nextPos + 1][col] = lastNumber * 2;
								lastNumber = 0;
								board[row][col] = 0;
							} else {
								lastNumber = board[row][col];
								board[row][col] = 0;
								board[nextPos][col] = lastNumber;
								nextPos--;
							}
						}
					}

				}
				break;
			case 2:
				for (int row = 0; row < size; row++) {

					int nextPos = 0;
					int prevBlock = 0;

					for (int col = 0; col < size; col++) {
						if (board[row][col] != 0) {
							if (prevBlock == board[row][col]) {
								board[row][nextPos - 1] = prevBlock * 2;
								prevBlock = 0;
								board[row][col] = 0;
							} else {
								prevBlock = board[row][col];
								board[row][col] = 0;
								board[row][nextPos] = prevBlock;
								nextPos++;
							}
						}
					}
				}
				break;
			case 3:
				for (int row = 0; row < size; row++) {

					int nextPos = size - 1;
					int prevBlock = 0;

					for (int col = size - 1; 0 <= col; col--) {
						if (board[row][col] != 0) {
							if (prevBlock == board[row][col]) {
								board[row][nextPos + 1] = prevBlock * 2;
								prevBlock = 0;
								board[row][col] = 0;
							} else {
								prevBlock = board[row][col];
								board[row][col] = 0;
								board[row][nextPos] = prevBlock;
								nextPos--;
							}
						}
					}
				}
				break;
		}

	}

	public static boolean outRange(int row, int col) {
		return row < 0 || size <= row || col < 0 || size <= col;
	}

}