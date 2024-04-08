import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_5604_구간합
 * @author parkrootseok
 * 
 * - 구간 합
 *  - A ~ B 사이에 포함되는 모든 정수의 각 자리를 합한 값
 * 
 * 1. 테스트 케이스 횟수 받기
 * 2. 테스트 케이스에 대한 정보 입력
 *  2-1. 구간 범위를 입력
 * 3. 구간 합 구하기
 *  3-1. 시작점을 (N+1)0 형태로 변환
 *  3-2. 끝점을 (M-1)9 형태로 변환
 *  3-3. (N+1)0 ~ (M-1)9 누적합 계산
 *  3-4. 가중치 증가
 *  3-5. 한 자리씩 우측으로 시프트
 **/
class Solution {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int testCaseNumber;
	
	static long start;
	static long end;
	
	public static void input() throws NumberFormatException, IOException {
		
		inputs = br.readLine().trim().split(" ");
        start = Long.parseLong(inputs[0]);
        end = Long.parseLong(inputs[1]);
        
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
			
			// 3. 구간 합 구하기
			long sum = 0;
			long weight = 1;
			while (start <= end) {
				
				// 시작 지점과 끝 지점이 0이라면 종료
				if (start == 0 && end == 0) {
					break;
				}
				
				// 3-1. 시작점을 (N+1)0 형태로 변환
				// 변환 과정에서 나오는 숫자들도 각 자릿수를 누적합
				// 단, 반복문을 거듭할수록 일의 자리수를 증가하는 것은 (10^반복문 횟수 - 1) 자릿수 증가를 의미
				while (start % 10 != 0 && start <= end) {	// 같은 자리수를 가질 때 시작점이 끝점보다 커질면 안됨
					
					long tmp = start;
					
					while(tmp > 0) {
						// 반복문을 거듭할수록 증가하는 자리수도 증가하므로 이를 맞추기 위해 weight를 곱하여 더해준다
						sum += ((tmp % 10) * weight);
						tmp /= 10;
					}
					
					// 일의 자리수를 하나씩 카운트 증가
					// 33 -> --- -> 38 -> 39
					// 4 -> 5 -> 6 -> 7 -> 8-> 9
					start++;
					
				}
				
				// 시작 지점이 더 커지는 경우도 이미 start ~ end 구간 만큼 자릿 수 누적합을 진행한 경우이므로 종료
				// 시작 지점과 끝 지점이 0이라면 종료
				if (start > end) {
					break;
				}
				
				// 3-2. 끝점을 (M-1)9 형태로 변환
				while (end % 10 != 9 && start <= end) {	// 끝점이 시작점보다 작은 경우는 9 -> 0 일때만 가능(즉, 같은 자리수의 수일 때는 불가능)

					long tmp = end;
					
					while(tmp > 0) {
						// 반복문을 거듭할수록 증가하는 자리수도 증가하므로 이를 맞추기 위해 weight를 곱하여 더해준다
						sum += ((tmp % 10) * weight);
						tmp /= 10;
					}
					
					// 일의 자리수를 하나씩 카운트 증가 카운트 감소
					// 133 -> 132-> 131 -> 130
					// 12 -> 11 -> 10
					end--;
					
				}
				
				// 3-3. (N+1)0 ~ (M-1)9 누적합 계산
				// start ~ end 사이에 0~9 누적합이 발생하는 경우를 모두 덧셈
				// (끝점 / 10 - 시작점 / 10) + 1 만큼 0 ~ 9가 반복됨을 이용 
				sum += (((end / 10) - (start / 10)) + 1) * 45 * weight;
				
				// 3-4. 가중치 증가
				// 십의 자리부터 둘 째 자리 수로 올 수 있는 0 ~ 9의 누적합이 10^(자리수 - 2)만큼 추가 발생
				// 백의 자리에도 0 ~ 9가 올 수 있고 이는 십의 자리에도 0~9가 올수 있으므로 0~9의 누적합이 총 1번(10^(3-2)) 발생
				// 즉, 자릿수가 증가함에 따라 10의 배수만큼 증가하므로 아래와 같이 10을 누적곱
				weight *= 10;
				
				// 3-5. 한 자리씩 우측으로 시프트
				start /= 10;
				end /= 10;

			}

			sb.append("#").append(tc).append(" ").append(sum).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;	

	}

}