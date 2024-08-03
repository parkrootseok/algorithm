import java.lang.reflect.Array;
import java.util.*;

/**
 * PG_체육복
 * @author parkrootseok
 *
 *  1. 잃어버린 학생을 체크
 *  2. 잃어버린 사람 중 여벌옷이 있는 사람 체크
 *  3. 여벌옷도 없고 잃어버린 사람들 체크
 *   3-1. 체육복울 잃어버린 사람이라면 자신의 앞뒤에 빌릴 수 있는지 확인
 */
public class Solution {

	public int solution(int n, int[] lost, int[] reserve) {

		int ans = n;
		int[] isPossible = new int[n];

		// 1. 잃어버린 학생을 체크
		for (int l : lost) {
			isPossible[l - 1]--;
		}

		// 2. 여벌옷이 있는 사람 체크
		for (int r : reserve) {
			isPossible[r - 1]++;
		}

		// 3. 여벌옷도 없고 잃어버린 사람들 체크
		for (int index = 0; index < isPossible.length; index++) {

			// 3-1. 체육복울 잃어버린 사람이라면 자신의 앞뒤에 빌릴 수 있는지 확인
			if (isPossible[index] == -1) {

				if (index - 1 >= 0 && isPossible[index - 1] == 1) {
					isPossible[index]++;
					isPossible[index-1]--;
				}
				else if (index + 1 < isPossible.length && isPossible[index + 1] == 1) {
					isPossible[index]--;
					isPossible[index+1]--;
				} else {
					ans--;
				}

			}

		}

		return ans;

	}

}
