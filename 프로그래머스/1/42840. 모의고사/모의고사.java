import java.io.*;
import java.util.*;

/**
 * PG_모의고사
 * @author parkrootseok
 *
 * - 3명의 수포자 문제 풀이 방식
 *  - 1번: 1 2 3 4 5 반복
 *  - 2번: 2 1 2 3 2 4 2 5 반복
 *  - 3번: 3 3 1 1 2 2 4 4 5 5 반복
 * - 문제에 대한 답이 주어지고 가장 높은 점수를 받은 사람에 대하여 오름차순 반복
 * - 문제는 최대 10,000문제
 *
 * 1. 수포자 3인에 대한 정보를 담은 배열 생성
 * 2. 완전 탐색 진행
 * 3. 최대 점수를 가지는 수포자를 결과 리스트에 추가
 */
class Solution {
    
    public static class Person {

		int[] answers;
		int score;

		public Person(int[] answers) {
			this.answers = answers;
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int problemNumber;
	public static Person[] people;
	public static int max;
    
    public int[] solution(int[] answers) {

        max = Integer.MIN_VALUE;
        problemNumber = answers.length;
        people = new Person[3];
        people[0] = new Person(new int[]{1, 2, 3, 4, 5});
        people[1] = new Person(new int[]{2, 1, 2, 3, 2, 4, 2, 5});
        people[2] = new Person(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

        bruteforce(answers, 0);

        List<Integer> result = new ArrayList<>();
        for (int idx = 0; idx < people.length; idx++) {
            if (people[idx].score == max) {
                result.add(idx + 1);
            }
        }

        return result.stream().mapToInt(idx -> idx).toArray();

    }

	public void bruteforce(int[] answers, int curProblem) {

		if (curProblem == problemNumber) {
			return;
		}

		bruteforce(answers, curProblem + 1);

		for (Person person : people) {

			int idx = curProblem % person.answers.length;
			if(answers[curProblem] == person.answers[curProblem % person.answers.length]) {
				person.score++;
				max = Math.max(max, person.score);
			}

		}

	}

}