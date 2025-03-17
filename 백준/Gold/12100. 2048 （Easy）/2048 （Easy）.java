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

	static final int LIMIT = 5;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;;

	static int size;
	static int[][] board;
	static int[] commands;
	static int answer;

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

		commands = new int[LIMIT];
		answer = 0;
		permutation(0);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static void permutation(int depth) {

		if (depth == LIMIT) {

			int[][] copyBoard = copyBoard();

			for (int command : commands) {
				swipe(copyBoard, command);
			}

			answer = Math.max(answer, getMaxBlock(copyBoard));
			return;

		}

		for (int dir = 0; dir < 5; dir++) {
			commands[depth] = dir;
			permutation(depth + 1);
		}

	}

	public static void swipe(int[][] board, int command) {

		switch (command) {

			case 0:
				for (int col = 0; col < size; col++) {
					int position = 0;
					int block = 0;
					for (int row = 0; row < size; row++) {
						if (0 < board[row][col]) {
							if (board[row][col] == block) {
								board[position - 1][col] = block * 2;
								block = 0;
								board[row][col] = 0;
							} else {
								block = board[row][col];
								board[row][col] = 0;
								board[position++][col] = block;
							}
						}
					}
				}
				break;
			case 1:
				for (int col = 0; col < size; col++) {
					int position = size - 1;
					int block = 0;
					for (int row = size - 1; 0 <= row; row--) {
						if (0 < board[row][col]) {
							if (board[row][col] == block) {
								board[position + 1][col] = block * 2;
								block = 0;
								board[row][col] = 0;
							} else {
								block = board[row][col];
								board[row][col] = 0;
								board[position--][col] = block;
							}
						}
					}
				}
				break;
			case 2:
				for (int row = 0; row < size; row++) {
					int position = 0;
					int block = 0;
					for (int col = 0; col < size; col++) {
						if (0 < board[row][col]) {
							if (board[row][col] == block) {
								board[row][position - 1] = block * 2;
								block = 0;
								board[row][col] = 0;
							} else {
								block = board[row][col];
								board[row][col] = 0;
								board[row][position++] = block;
							}
						}
					}
				}
				break;
			case 3:
				for (int row = 0; row < size; row++) {
					int position = size - 1;
					int block = 0;
					for (int col = size - 1; 0 <= col; col--) {
						if (0 < board[row][col]) {
							if (board[row][col] == block) {
								board[row][position + 1] = block * 2;
								block = 0;
								board[row][col] = 0;
							} else {
								block = board[row][col];
								board[row][col] = 0;
								board[row][position--] = block;
							}
						}
					}
				}
				break;
		}

	}

	public static int getMaxBlock(int[][] board) {
		int maxBlock = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				maxBlock = Math.max(maxBlock, board[row][col]);
			}
		}
		return maxBlock;
	}

	public static int[][] copyBoard() {
		int[][] copyBoard = new int[size][size];
		for (int row = 0; row < size; row++) {
			copyBoard[row] = board[row].clone();
		}
		return copyBoard;
	}

}