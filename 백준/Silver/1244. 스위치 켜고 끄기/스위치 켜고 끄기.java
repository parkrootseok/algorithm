import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1244_스위치켜고끄기
 * @author parkrootseok
 * 
 * - 1부터 연속적인 번호가 붙어있는 스위치가 존재
 * - 스위치 상태는 on[1]/off[0] 둘 중 한가지
 * - 학생을 몇 명 뽑아서, 학생들에게 1 이상이고 스위치 개수 이하인 자연수 제공
 * - 남학생은 배수이면 상태 변경 / 여학생은 자기 자신 번호를 기준으로 좌,우를 확인하여 같다면 모두 변경
 * 
 * 1. 스위치 번호를 받는다.
 * 2. 스위치에 대한 정보를 받는다.
 * 3. 학생수를 받는다.
 * 4. 학생수마다 자신의 숫자를 부여받는다.
 *  4-1. 성별이 남자이면 male()를 실행
 *   4-1-1. 자신이 가진 숫자의 배수인 모든 스위치를 반대로
 *  4-2. 성별이 여자라면 female()를 실행
 *   4-2-1. 자신이 가진 숫자를 기준으로 좌우 대칭을 확인
 *   4-2-2. 대칭 확인이 끝난 후 해당 범위내에 존재하는 스위치의 상태 변경
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuffer sb;
	static String[] inputs;

	static String MALE = "1";
	static String FEMALE = "2";

	static int ON = 1;
	static int OFF = 0;

	static int switchNumber, studentNumber;
	static int[] switches;

	public static void male(int hasNumber) {
		
		// 4-1-1. 자신이 가진 숫자의 배수인 모든 스위치를 반대로
		int mul = 1;
		int next = hasNumber;
		while(next <= switchNumber) {
			
			if(switches[next] == 1) {
				switches[next] = 0;
			} else {
				switches[next] = 1;
			}
			
			next = hasNumber * (++mul);
			
		}
		
	}

	public static void female(int hasNumber) {
		
		int left, right;
		
		// 4-2-1. 자신이 가진 숫자를 기준으로 좌우 대칭을 확인
		left = hasNumber - 1;
		right = hasNumber + 1;
		while(0 < left && right <= switchNumber) {
			
			// 좌, 우측 스위치의 상태가 다르다면 종료
			if(switches[left] != switches[right]) {
				break;
			}
			
			// 같다면 인덱스를 좌,우로 벌리자
			left--;
			right++;
			
		}
		
		// 4-2-2. 대칭 확인이 끝난 후 해당 범위내에 존재하는 스위치의 상태 변경
		for(int start = left + 1; start < right; start++) {
			if(switches[start] == 1) {
				switches[start] = 0;
			} else {
				switches[start] = 1;
			}
		}
		
	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuffer();

		// 1. 스위치 번호를 받는다.
		switchNumber = Integer.parseInt(br.readLine().trim());
		switches = new int[switchNumber + 1];

		// 2. 스위치에 대한 정보를 받는다.
		inputs = br.readLine().trim().split(" ");
		for (int curSwitch = 1; curSwitch <= switchNumber; curSwitch++) {
			switches[curSwitch] = Integer.parseInt(inputs[curSwitch - 1]);
		}

		// 3. 학생수를 받는다.
		studentNumber = Integer.parseInt(br.readLine().trim());

		// 4. 학생수마다 자신의 숫자를 부여받는다.
		for (int curStudent = 0; curStudent < studentNumber; curStudent++) {

			inputs = br.readLine().trim().split(" ");
			String s = inputs[0];
			int hasNumber = Integer.parseInt(inputs[1]);

			// 4-1. 성별이 남자이면 male()를 실행
			if(s.equals(MALE)) {
				male(hasNumber);
			} 
			
			// 4-2. 성별이 여자라면 female()를 실행
			else {
				female(hasNumber);
			}
			
		}

		for(int index = 1; index <= switchNumber; index++) {
			
			sb.append(switches[index]).append(" ");
			
			// 스위치 20개를 출력한 후에 줄바꿈
			if(index % 20 == 0) {	
				sb.append("\n");
			}
			
		}
		
		bw.write(sb.toString());
		bw.close();
		return;

	}

}