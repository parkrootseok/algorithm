import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * BOJ_11659_구간합구하기4
 * @author parkrootseok
 * 
 * - 수 N개가 주어졌을 때, i ~ j까지 합을 구하자
 * 
 * 1. N과 M을 받는다.
 * 2. N개의 수를 입력 받는다.
 * 		-> 단, 이전 인덱스에서 합을 누적하면서 배열에 저장
 * 3. i ~ j까지 합을 구한다
 * 
 */

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuffer sb = new StringBuffer();
	static String[] inputs;

	static int N;
	static int M;
	static int numbers[];

	public static void main(String args[]) throws Exception {

		// 1. 주어질 테스트 케이스에 대한 수를 입력 받는다.
		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		// 2. N개의 수를 입력 받는다.
		numbers = new int[N + 1];
		inputs = br.readLine().trim().split(" ");
		for(int index = 1; index <= N; index++) {
			
			// 이전 인덱스에서 합을 누적하면서 배열에 저장
			numbers[index] = Integer.parseInt(inputs[index - 1]) + numbers[index - 1];
		}
	
		// 3. i ~ j까지 합을 구한다
		int start, end;
		for(int curM = 0; curM < M; curM++) {
			
			// 시작과 끝 범위를 입력받고
			inputs = br.readLine().trim().split(" ");
			start = Integer.parseInt(inputs[0]);
			end = Integer.parseInt(inputs[1]);
			
			// 끝 인덱스의 값은 [1 ~ 끝 인덱스]의 모든 합
			// 즉, 끝 - (시작-1) = 시작 ~ 끝의 합
			sb.append(numbers[end] - numbers[start - 1]).append("\n");
			
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}