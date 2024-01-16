import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * BOJ_14891_톱니바퀴
 * @author parkrootseok
 * 
 * 1. 톱니바퀴 상태 입력(총 4개의 톱니바퀴 정보, N극(0) / S극(1), 12시 방향부터 시계방향)
 * 2. 회전 횟수 입력 
 * 3. 회전시킨 방법 입력(톱니바퀴 번호 / 방향[1 : 시계 / -1 : 반시계])
 *  3-1. 좌, 우 톱니바퀴를 확인하여 추가로 회전이 필요한 톱니바퀴 확인
 *  3-2. 톱니바퀴 회전 진행
 * 4. 톱니바퀴 점수의 합 출력(12시 방향이 N극 : 0점, S극 : 2^n-1점)
 */

public class Main {
	
	static class Gear {
		
		int number;
		List<Integer> directions = new LinkedList<>();
		
		Gear(int number, String[] direction) {
			
			this.number = number;
			for(int index = 0 ; index < direction.length ; index++) {
				directions.add(Integer.parseInt(direction[index]));
			}
			
		}
		
		/**
		 * 톱니바퀴 회전
		 * @param direction 회전 방향(시계 / 반시계)
		 */
		public void rotation(int direction) {
			
			// 톱니바퀴 회전
			if(direction == clockwise) {
				rotationClockwise();
			}  else {
				rotationCounterClockwise();
			}
			
		}
		
		/**
		 * 시계 방향 회전
		 */
		private void rotationClockwise( ) {
			
			// 시계 방향 회전(12 -> 1, 1 -> 2, ... , 11 -> 12)
			// 마지막 방향을 맨 앞으로 이동(마지막 원소를 삭제한 후 첫 번째에 추가)
			int lastDirection = directions.get(directions.size() - 1);
			directions.remove(directions.size() - 1);
			directions.add(0, lastDirection);

		}
		
		/**
		 * 반시계 방향 회전
		 */
		private void rotationCounterClockwise( ) {
			
			// 반시계 방향 회전(1 -> 12, 2 -> 1, ... , 12 -> 11)
			// 첫 방향을 맨 뒤로 이동(첫번째 원소를 삭제한 후 마지막에 추가)
			int FirstDirection = directions.get(0);
			directions.remove(0);
			directions.add(directions.size(), FirstDirection);
			
		}
		
	
		/**
		 * 톱니바퀴 점수 얻기(12시 방향이 N극 : 0점, S극 : 2^n-1점)
		 * @return
		 */
		public int getScore() {
			
			if(directions.get(0) == N) {
				return 0;
			}
			
			return (int)Math.pow(2, this.number);
			
		}
		
	
	}
	
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	
	static int N = 0;
	static int S = 1;
	
	static int clockwise = 1;
	static int gearNumber = 4;
	
	static String[] input;
	static Gear[] gears;
	static int rotationNumber;
	static int totalScore;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
//		1. 톱니바퀴 상태 입력(총 4개의 톱니바퀴 정보, N극(0) / S극(1), 12시 방향부터 시계방향)
		gears = new Gear[gearNumber];
		for(int index = 0 ; index < gearNumber ; index++) {
			input = br.readLine().trim().split("");
			gears[index] = new Gear(index, input);
		}
		
		
//		2. 회전 횟수 입력 
		rotationNumber = Integer.parseInt(br.readLine().trim());
		
//		3. 회전시킨 방법 입력(톱니바퀴 번호 / 방향[1 : 시계 / -1 : 반시계])
		int gNumber, direction;
		for (int number = 0; number < rotationNumber ; number++) {
			
			input = br.readLine().trim().split(" ");
			gNumber = Integer.parseInt(input[0]) - 1;
			direction = Integer.parseInt(input[1]);
			
//			3-1. 좌, 우 톱니바퀴를 확인하여 추가로 회전이 필요한 톱니바퀴 확인
			boolean[] needRotation = new boolean[gearNumber];
			needRotation[gNumber] = true;

			// 좌측 탐색
			int left = gNumber - 1;
			while(0 <= left) {
				
				// 오른쪽 톱니바퀴와 맞닿은 면이 다르면서 오른쪽에 존재하는 톱니바퀴가 회전을 진행하는 경우
				if(gears[left].directions.get(2) != gears[left + 1].directions.get(6)) {
					needRotation[left] = true;
				} else {
					break;
				}
				
				left--;
				
			}
			
			// 우측 탐색
			int right = gNumber + 1;
			while(right < gearNumber) {
				
				// 왼쪽 톱니바퀴와 맞닿은 면이 다르면서 왼쪽에 존재하는 톱니바퀴가 회전을 진행하는 경우
				if(gears[right - 1].directions.get(2) != gears[right].directions.get(6)) {
					needRotation[right] = true;
				} else {
					break;
				}
				
				right++;
				
			}
		
//			3-2. 톱니바퀴 회전 진행
			gears[gNumber].rotation(direction);
			
			left = gNumber - 1;
			int lDirection = direction * -1;
			while(0 <= left) {
				
				if(needRotation[left]) {
					gears[left].rotation(lDirection);
					lDirection *= -1;
					left--;
				} else {
					break;
				}
				
				
			}
			
			right = gNumber + 1;
			int rDirection = direction * -1;
			while(right < gearNumber) {
				
				if(needRotation[right]) {
					gears[right].rotation(rDirection);
					rDirection *= -1;
					right++;
				} else {
					break;
				}
				
				
			}
					
		}
		
//		4. 톱니바퀴 점수의 합 출력
		totalScore = 0;
		for(Gear gear : gears) {
			totalScore += gear.getScore();
		}
		
		sb.append(totalScore).append("\n");
		bw.write(sb.toString());
		bw.close();
		
		return;
	
	}
	
}
