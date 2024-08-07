import java.util.*;

/**
 * PG_카펫
 * @author parkrootseok
 *
 * - 카펫
 *   - 테두리는 갈색, 중앙은 노란색
 *   - 가로 길이 >= 세로 길이
 */
public class Solution {

	public static int[] answer;
	public static boolean[][] isVisited;
	public boolean isFinished;

	public int[] solution(int brown, int yellow) {

		answer = new int[2];
		isVisited = new boolean[10000][10000];
		isFinished = false;
		bruteforce(brown, yellow, 3, 3);
		return answer;

	}

	public void bruteforce(int brown, int yellow, int col, int row) {

		int targetBrown = (col * 2) + (row - 2) * 2;
		int targetYellow = (row * col) - targetBrown;

		if (isFinished) {
			return;
		}

		if (isVisited[col][row]) {
			return;
		}

		if (targetBrown > brown || targetYellow > yellow) {
			return;
		}

		if (targetBrown == brown && targetYellow == yellow) {
			answer[0] = col;
			answer[1] = row;
			isFinished = true;
			return;
		}

		isVisited[col][row] = true;

		bruteforce(brown, yellow, col + 1, row);

		if (col >= row + 1) {
			bruteforce(brown, yellow, col, row + 1);
		}

	}

}