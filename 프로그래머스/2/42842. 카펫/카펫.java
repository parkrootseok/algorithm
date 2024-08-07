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
	public boolean isFinished;

	public int[] solution(int brown, int yellow) {

		answer = new int[2];
		isFinished = false;

		for (int col = 3; ;col++) {

			for (int row = 3; row <= col; row++) {

				int targetB = (col * 2) + (row - 2) * 2;
				int targetY = col * row - targetB;

				if (targetB == brown && targetY == yellow) {
					answer[0] = col;
					answer[1] = row;
					return answer;
				}

			}

		}

	}


}