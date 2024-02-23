import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * BOJ_20055_컨베이어벨트위의로봇
 * @author parkrootseok
 * 
 * 1. 컨베이어 벨트에 대한 정보와 목표 갯수(내구도가 0)인 K를 입력 받는다.
 * 2. 내구도에 대한 정보를 받는다.
 * 3. 벨트 회전
 * 4. 로봇 이동 
 * 5. 로봇 올리고 내구도 감소
 * 6. 내구도가 0인 칸이 targetNumber 이상이라면 종료
 */

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] input;
	
	static int answer;
	static int beltLength;
	static int targetNumber;
	static int[] conveyorBelt;
	static boolean[] robot;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		answer = 0;
	
		// 1. 컨베이어 벨트에 대한 정보와 목표 갯수(내구도가 0)인 K를 입력 받는다.
		input = br.readLine().trim().split(" ");
		beltLength = Integer.parseInt(input[0]);
		targetNumber = Integer.parseInt(input[1]);
		
		// 2. 내구도에 대한 정보를 받는다.
		conveyorBelt = new int[beltLength * 2];
		robot = new boolean[beltLength];
		
		input = br.readLine().trim().split(" ");
		for(int index = 0 ; index < input.length ; index++) {
			conveyorBelt[index] = Integer.parseInt(input[index]);
		}
		
		int last;
		while(true) {
			
			answer++;
			
			// 3. 벨트 회전
			last = conveyorBelt[beltLength * 2 - 1];
			for(int index = beltLength * 2 - 1; 0 < index ; index--) {
				conveyorBelt[index] = conveyorBelt[index - 1];
			}
			conveyorBelt[0] = last;
			
			for(int index = beltLength - 1; 0 < index ; index--) {
				robot[index] = robot[index - 1];
			}
			
			// 벨트를 이동하면 첫번째 칸과 마지막 칸 로봇은 사라짐
			robot[0] = false;
			robot[beltLength - 1] = false;
			
			// 4. 로봇 이동
			for(int index = beltLength - 1; 0 < index ; index--) {
				
				// 4-1. 이동할 로봇 존재하고 이동할 위치에 로봇이 없고 내구도가 1 이상이라면 로봇을 이동하고 내구도 감소
				if(robot[index - 1] && !robot[index] && conveyorBelt[index] > 0) {
					
					robot[index - 1] = false;
					robot[index] = true;
					conveyorBelt[index]--;
					robot[beltLength - 1] = false;
					
				}
				
			}
			
			// 5. 로봇 올리고 내구도 감소
			if(conveyorBelt[0] > 0) {
				robot[0] = true;
				conveyorBelt[0]--;
			}
			
			// 6. 내구도가 0인 칸이 targetNumber 이상이라면 종료
			int count = 0;
			for(int index = 0; index < beltLength * 2 ; index++) {
				
				if(conveyorBelt[index] == 0) {
					count++;
				}
				
			}
			
			if (count >= targetNumber) {
				break;
			}
			
		}
		
		sb.append(answer).append("\n");
		bw.write(sb.toString());
		bw.close();

		return;

	}

}