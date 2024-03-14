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
 *  2-1. 현재 논문의 인용횟수 보다 작은 인용 횟수를 가진 논문의 갯수를 계산
 *  2-2. 더 작은 인용 회수를 가진 논문이 현재 인용 횟수보다 작거나 같다면 종료
 **/

public class Solution {
	
	public int solution(int[] citations) {

		int hIndex = 0;

		// 1. 정렬을 수행
		Arrays.sort(citations);
		System.out.println(Arrays.toString(citations));

		// 2. 정렬을 수행한 인용 횟수를 탐색
		for (int curThesis = 0; curThesis < citations.length; curThesis++) {

			// 2-1. 현재 논문의 인용횟수 보다 작은 인용 횟수를 가진 논문의 갯수를 계산
			int smallerCitationThesisNumber = citations.length - curThesis;

			// 2-2. 더 작은 인용 회수를 가진 논문이 현재 인용 횟수보다 작거나 같다면 종료
			if (smallerCitationThesisNumber <= citations[curThesis]) {
				hIndex = smallerCitationThesisNumber;
				break;
			}

		}

		return hIndex;

	}

}