import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Programmers_H-index
 * @author parkrootseok
 *
 * - h-index
 *  - 과학자의 생산성과 영향력을 나타내는 지표
 *  - 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었을 때 h의 최대값
 *   -> h랑 같거나 큰 숫자가 h개 이상이고, 나머지 숫자는 h이하를 만족하는 숫자 h를 구해라
 *
 *
 * - 목적 : 논문 인용 횟수가 주어질 때 h-index를 구해라
 *
 * 1. 정렬을 수행
 * 2. 정렬을 수행한 인용 횟수를 탐색
 *  2-1. 남은 논문의 수가 현재 인용 횟수보다
 **/

public class Solution {

	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.solution(new int[]{1});
	}

	public int solution(int[] citations) {

		// 1. 정렬을 수행
		Arrays.sort(citations);
		System.out.println(Arrays.toString(citations));

		// 2. 정렬을 수행한 인용 횟수를 탐색
		for (int curThesis = 0; curThesis < citations.length; curThesis++) {

			int moreCitationThesisNumber = citations.length - curThesis;

			if (moreCitationThesisNumber <= citations[curThesis]) {
				return moreCitationThesisNumber;
			}
			
			
		}
		
		return 0;

	}

}