import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_5607_조합
 * @author parkrootseok
 * 
 * - 이항계수를 구한 후 1234567891로 나눈 나머지를 출력
 * 
 * 1. 테스트 케이스 횟수 받기
 * 2. 테스트 케이스에 대한 정보 입력
 *  2-1. N과 R을 받는다.
 * 3. 팩토리얼 테이블 초기화
 * 4. 페르마 소정리를 이용하여 1234567891로 나눈 나머지를 구하자 
 **/
class Solution {
	
	static final int P = 1234567891;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int testCaseNumber;
	static int N, R;
    static long ANSWER;
    static long factorial[] = new long[1000001];

	
	public static void input() throws NumberFormatException, IOException {
		
		inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        R = Integer.parseInt(inputs[1]);
        
	}
	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 받기
		testCaseNumber = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCaseNumber; tc++) {

			// 2. 테스트 케이스에 대한 정보 입력
			input();
			
			// 3. 팩토리얼 테이블 초기화
			factorial();
		
			/**
			 * 4. 페르마 소정리를 이용하여 나머지를 구하자
			 *  - 이항계수 정의(N! / R!(N-R)!)는 분수 형태 꼴에 모듈러 연산을 취해준다면
			 *  - (A % B) mod p = (A mod p % B mod p) mod p와 같지만 모듈러 연산은 나눗셈 꼴에서 허용되지 않아
			 *  - (R!(N-R)!)^p-2와 (R!(N-R)!)^-1 mod p은 합동 관계임을 이용하여 (A * B^p-2) mod p 와 같은 곱셈 형태로 변형하여
			 *  - 최종적으로 (N! mod p * N!(N-R)! mod p) mod p를 도출할 수 있음
			 */
			long numerator = factorial[N] % P;
			long denominator = (factorial[R] * factorial[N - R]) % P;
			long inverseDenominator = getInverse(denominator, P - 2);

			sb.append("#").append(tc).append(" ").append(numerator * inverseDenominator % P).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;	

	}

	
	 public static void factorial() {

		factorial[0] = 1;
		for (int number = 1; number <= 1000000; number++) {
			factorial[number] = factorial[number - 1] * number % P;
		}
	 
	}

	public static long getInverse(long number, long exp) {

		// 지수가 1일 경우 n을 반환
		if (exp == 1) { 
			return number;
		}

		// 문제를 분할
		long divisionResult = getInverse(number, exp / 2) % P; // n^exp/2 형태로 변환

		// 분할된 문제들에 대한 결과를 종합
		if (exp % 2 == 0) { // exp가 짝수이면 n^exp/2 * n^exp/2 = n^exp
			return divisionResult * divisionResult % P;
		} else { // exp가 홀수이면 (n^exp/2 * n^exp/2) * n = n^exp
			return ((divisionResult * divisionResult) % P * number) % P;
		}

	}
		
}