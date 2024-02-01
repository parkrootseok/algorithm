import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * BOJ_2023_신기한소수찾기
 * @author parkrootseok
 * 
 * - 7331 소수임 근데 733, 73, 7도 소수
 * - 위와 같은 소수를 신기한 소수라 할 거임
 * - N자리 숫자 중 신기한 소수 오름차순 출력
 * 
 * 1. N자리 숫자를 입력
 * 2. 1, 2, 3, 5, 9, 7의 숫자로 N자리 순열 생성
 *  2-1. 현재 순열이 소수가 아니라면 중단
 *  2-2. 완성되었다면 출력
 *
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuffer sb;
	static String[] inputs;
	
	static int length;
	static int[] elements = {1, 2, 3, 5, 7, 9};
	
	public static boolean isPrime(int primeNumber) {
		
		
		// 1이면 소수
		if(primeNumber == 1) {
			return false;
		}
		
		// 1이 아니고 3이하
		// 즉, 2, 3이면 소수가 아니다
		if(primeNumber <= 3) {
			return true;
		}
		
		
		// 현재 primeNumber가 자신을 제외한 약수가 있는지 확인
		for(int number = 2; number * number <= primeNumber; number++) {
			
			if(primeNumber % number == 0) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public static void permutaion(int level, int primeNumber) throws IOException {
		
		// 2-1. 현재 조합된 숫자가 소수가 아니라면 중단
		if(!isPrime(primeNumber)) {
			return;
		}
		
		// 2-2. 완성되었다면 출력
		if(level == length) {
			sb.append(primeNumber).append("\n");
			return;
		}
		
		// 순열을 만든다
		for(int elementIdx = 0; elementIdx < elements.length; elementIdx++) {
			
			// 왼쪽부터 소수인지 판단하므로 기존 숫자에 추가되는 숫자를 이어 붙여준다.
			permutaion(level + 1, (primeNumber * 10) + elements[elementIdx]);
			
		}
		
		
	}
	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuffer();
		
		// 1. N자리 숫자를 입력
		length = Integer.parseInt(br.readLine().trim());
		
		// 2. 1, 2, 3, 5, 9, 7의 숫자로 N자리 순열 생성
		permutaion(0, 0);
		
		bw.write(sb.toString());
		bw.close();
		return;

	}

}