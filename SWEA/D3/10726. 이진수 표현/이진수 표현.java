import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_10726_이진수 표현
 * @author parkrootseok
 * 
 * - M의 이진수 표현의 마지막 N비트가 모두 1로 켜져 있는지 아닌지를 판별하려 출력
 * 
 * 1. 테스트 케이스 수 T를 받는다.
 * 2. 각 테스트 케이스마다 N과 M을 받는다.
 * 3. N개의 비트가 1인 수를 만든다.
 * 4. N개의 비트가 1로 이루어진 수와 M의 &연산 결과과 이전과 동일한지 확인한다.
 *  
 */

public class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int testCaseNumber;
	static int N, M, lastNBit;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		testCaseNumber = Integer.parseInt(br.readLine().trim());

		for (int curT = 1; curT <= testCaseNumber; curT++) {

			String result = "ON";
			
			// 2. 각 테스트 케이스마다 N과 M을 받는다.
			inputs = br.readLine().trim().split(" ");
			N = Integer.parseInt(inputs[0]);
			M = Integer.parseInt(inputs[1]);
			

			// 3. N개의 비트가 1인 수를 만든다.
			lastNBit = (1 << N) - 1;	

			// 4. N개의 비트가 1로 이루어진 수와 M의 &연산 결과과 이전과 동일한지 확인한다.
			// -> 다르다면 & 연산 성질에 의해 M의 N개의 비트가 모두 1이 아님을 알 수 있음
			if (lastNBit != (M & lastNBit)) {
				result = "OFF";
			}

			sb.append("#").append(curT).append(" ").append(result).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}
