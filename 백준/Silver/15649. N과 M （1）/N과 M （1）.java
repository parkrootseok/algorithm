import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * BOJ_15649_N과M(1)
 * @author parkrootseok
 * 
 * - 자연수 N과 M이 주어졌을 때, 길이가 M인 수열을 모두 구하는 프로그램 작성
 * 
 * 1. N과 M을 받는다.
 * 2. 수열을 만든다.
 *  2-1. 현재 level이 M과 같으면 종료
 *  2-2. 그렇지 않으면 수열을 이어서 만든다.
 * 
 */

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuffer sb = new StringBuffer();
	static String[] inputs;

	static int elementNumber, selectNumber;

	static int numbers[];
	static boolean isUsed[];
	static int permutation[];

	public static void permutation(int level) {

		// 2-1. 현재 level이 M과 같으면 종료(기저 조건)
		if (level == selectNumber) {

			for (int pElement : permutation) {
				sb.append(pElement).append(" ");
			}
			sb.append("\n");

			return;
		}

		// 2-2. 그렇지 않으면 수열을 이어서 만든다.
		for (int curNumber = 1; curNumber <= elementNumber; curNumber++) {
			
			// 이전에 사용하지 않은 숫자라면
			if (!isUsed[curNumber]) {
				
				// 현재 level에 원소를 삽입 후 사용 체크(전 처리 조건)
				permutation[level] = curNumber;
				isUsed[curNumber] = true;
				
				// 재귀 호출
				permutation(level + 1);
				
				// 후 처리 조건
				// 사용을 완료한 숫자에 대해 초기화
				isUsed[curNumber] = false;
			}
			
		}

	}

	public static void main(String args[]) throws Exception {

		// 1. 주어질 테스트 케이스에 대한 수를 입력 받는다.
		inputs = br.readLine().trim().split(" ");
		elementNumber = Integer.parseInt(inputs[0]);
		selectNumber = Integer.parseInt(inputs[1]);

		permutation = new int[selectNumber];
		isUsed = new boolean[elementNumber + 1];
		permutation(0);

		bw.write(sb.toString());
		bw.close();
		return;

	}

}