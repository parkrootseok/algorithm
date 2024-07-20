import java.io.*;
import java.util.*;

/**
 * PG_소수 찾기
 * @author parkrootseok
 *
 * - 종이 조각으로 만들 수 있는 모든 소수의 개수를 출력
 *
 * 1. 종이 조각들의 부분 집합으로 만들 수 있는 모든 수를 구하고 소수인지 확인하여 카운팅
 */
class Solution {
    
    public static char[] papers;
	public static boolean[] isUsed;
	public static HashSet<Integer> answer;

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution("17"));
	}

	public int solution(String numbers) {

		papers = numbers.toCharArray();

		// 1. 종이 조각으로 만들 수 있는 모든 수 조합을 찾아 소수 판별 후 카운팅
		answer = new HashSet<>();
		isUsed = new boolean[numbers.length()];
		bruteforce(0, "");

		return answer.size();

	}

	public void bruteforce(int curLength, String number) {

		if (curLength == papers.length) {
			return;
		}

		for (int idx = 0; idx < papers.length; idx++) {

			if (!isUsed[idx]) {

				isUsed[idx] = true;

				if (isPrime(Integer.parseInt(number + papers[idx]))) {
					answer.add(Integer.parseInt(number + papers[idx]));
				}

				bruteforce(curLength + 1, number + papers[idx]);
				isUsed[idx] = false;
			}

		}

	}

	public boolean isPrime(int number) {

		if (number < 2) {
			return false;
		}

		for (int curNumber = 2; curNumber <= Math.sqrt(number); curNumber++) {
			if (number % curNumber == 0) {
				return false;
			}
		}

		return true;

	}

}