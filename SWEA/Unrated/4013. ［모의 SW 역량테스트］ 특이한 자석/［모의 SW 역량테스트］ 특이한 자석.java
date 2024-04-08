import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * SWEA_4013_특이한자석
 * @author parkrootseok
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 회전 횟수 입력
 * 3. 톱니바퀴 상태 입력(총 4개의 톱니바퀴 정보, N극(0) / S극(1), 12시 방향부터 시계방향)
 * 4. 회전시킨 방법 입력(톱니바퀴 번호 / 방향[1 : 시계 / -1 : 반시계])
 *  4-1. 좌, 우 톱니바퀴를 확인하여 추가로 회전이 필요한 톱니바퀴 확인
 *  4-2. 톱니바퀴 회전 진행
 *  4-3. 추가로 회전이 필요한 좌, 우 톱니에 대하여 회전
 * 5. 톱니바퀴 점수의 합 출력(12시 방향이 N극 : 0점, S극 : 2^n-1점)
 */

public class Solution {
	
	static class Gear {
		
		int number;
		List<Integer> directions = new ArrayList<>();
		
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
			if(direction == CLOCKWISE) {
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
	
	static final int N = 0;
	static final int S = 1;
	
	static final int CLOCKWISE = 1;
	static final int TOTAL_GEAR_NUMBER = 4;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	
	static int testCaseNumber;
	
	static String[] input;
	static Gear[] gears;
	static int rotationNumber;
	static int totalScore;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		testCaseNumber = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCaseNumber; tc++) {
			
			// 2. 회전 횟수 입력 
			rotationNumber = Integer.parseInt(br.readLine().trim());
						
						
			// 3. 톱니바퀴 상태 입력(총 4개의 톱니바퀴 정보, N극(0) / S극(1), 12시 방향부터 시계방향)
			gears = new Gear[TOTAL_GEAR_NUMBER];
			for(int index = 0 ; index < TOTAL_GEAR_NUMBER ; index++) {
				input = br.readLine().trim().split(" ");
				gears[index] = new Gear(index, input);
			}
			
			
			// 4. 회전시킨 방법 입력(톱니바퀴 번호 / 방향[1 : 시계 / -1 : 반시계])
			int gNumber, direction;
			for (int number = 0; number < rotationNumber ; number++) {
				
				input = br.readLine().trim().split(" ");
				gNumber = Integer.parseInt(input[0]) - 1;
				direction = Integer.parseInt(input[1]);
				
				// 4-1. 좌, 우 톱니바퀴를 확인하여 추가로 회전이 필요한 톱니바퀴 확인
				boolean[] needRotation = new boolean[TOTAL_GEAR_NUMBER];
				needRotation[gNumber] = true;

				// 좌측 톱니 확인
				int leftGearNumber = gNumber - 1;
				while(0 <= leftGearNumber) {
					
					// 오른쪽 톱니바퀴와 맞닿은 면이 같은 경우 종료
					if(gears[leftGearNumber].directions.get(2) == gears[leftGearNumber + 1].directions.get(6)) {
						break;
					}
					
					needRotation[leftGearNumber] = true;
					leftGearNumber--;
					
				}
				
				// 우측 톱니 확인
				int rightGearNumber = gNumber + 1;
				while(rightGearNumber < TOTAL_GEAR_NUMBER) {
					
					// 왼쪽 톱니바퀴와 맞닿은 면이 같은 경우 종료
					if(gears[rightGearNumber - 1].directions.get(2) == gears[rightGearNumber].directions.get(6)) {
						break;
					}
					
					needRotation[rightGearNumber] = true;
					rightGearNumber++;
					
				}
			
				// 4-2. 톱니바퀴 회전 
				gears[gNumber].rotation(direction);
				
				
				// 4-3. 추가로 회전이 필요한 좌, 우 톱니에 대하여 회전
				leftGearNumber = gNumber - 1;
				int lDirection = direction * -1;
				while(0 <= leftGearNumber && needRotation[leftGearNumber]) {
					
					gears[leftGearNumber].rotation(lDirection);
					lDirection *= -1;
					leftGearNumber--;
					
				}
				
				rightGearNumber = gNumber + 1;
				int rDirection = direction * -1;
				while(rightGearNumber < TOTAL_GEAR_NUMBER && needRotation[rightGearNumber]) {
					
					gears[rightGearNumber].rotation(rDirection);
					rDirection *= -1;
					rightGearNumber++;
					
				}
						
			}
			
			// 5. 톱니바퀴 점수의 합 출력
			totalScore = 0;
			for(Gear gear : gears) {
				totalScore += gear.getScore();
			}
			
			sb.append("#").append(tc).append(" ").append(totalScore).append("\n");
			
		}
	
		bw.write(sb.toString());
		bw.close();
		
		return;
	
	}
	
}