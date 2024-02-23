import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * BOJ_15649_N과M(2)
 * @author parkrootseok
 * 
 * - 자연수 N과 M이 주어졌을 때, 길이가 M인 수열을 모두 구하는 프로그램 작성
 * - 수열은 오름차순이며 중복이 존재하면 안된다.
 * - 즉, 중복없는 조합을 만들어라
 * 
 * 1. N과 M을 받는다.
 * 2. 조합을 만든다.
 *  2-1. 현재 level이 M과 같으면 종료
 *  2-2. 그렇지 않으면 조합을 이어서 만든다.
 * 
 */

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuffer sb = new StringBuffer();
	static String[] inputs;

	static int elementNumber;
	static int selectNumber;

	static int combination[];

	public static void combination(int level, int start) {

		// 2-1. 현재 level이 M과 같으면 종료(기저 조건)
		if (level == selectNumber) {

			for (int pElement : combination) {
				sb.append(pElement).append(" ");
			}
			sb.append("\n");

			return;
		}

		// 2-2. 그렇지 않으면 조합을 이어서 만든다.
		for (int curNumber = start; curNumber <= elementNumber; curNumber++) {
			
			// 현재 레벨을 인덱스로 사용하여 원소 삽입
			combination[level] = curNumber;
			
			// 중복되는 원소가 존재하면 안되므로
			// 다음 원소는 현재 사용했던 다음 원소부터 사용
			combination(level + 1, curNumber + 1);
	
		}

	}

	public static void main(String args[]) throws Exception {

		// 1. 주어질 테스트 케이스에 대한 수를 입력 받는다.
		inputs = br.readLine().trim().split(" ");
		elementNumber = Integer.parseInt(inputs[0]);
		selectNumber = Integer.parseInt(inputs[1]);

		// 2. 조합을 만든다.
		combination = new int[selectNumber];
		combination(0, 1);

		bw.write(sb.toString());
		bw.close();
		return;

	}

}