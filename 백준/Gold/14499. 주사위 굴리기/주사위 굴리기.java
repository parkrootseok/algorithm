import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * BOJ_14499_주사위굴리기
 * @author parkrootseok
 * 
 * - 크기가 N by M 인 지도 존재 [동(오른쪽),서,남,북(위쪽)]
 * - 주사위는 (x, y)에 존재하고, 모든 면에 0이 적혀있음
 * - 지도의 각 칸에 정수가 하나씩 존재하고 닿은 면에 정수를 기록하고 칸에 있는 정수는 0이 됨
 * - 바깥으로 이동할 땐 무시
 *  
 * 1. N, M, X, Y, K(명령 갯수) 를 입력받는다.
 * 2. 지도 정보를 입력 받는다.
 * 3. 명령을 받아 주사위를 이동한다.
 *  3-1. 이동하는 방향으로 주사위 값 재배치
 *  3-2. 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
 *  3-3. 아니라면 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
 *  
 */

public class Main {

	static class Dice {

		int x;
		int y;
		int fPosition;
		int uPosition;
		int[] numbers;

		Dice(int x, int y) {
			this.numbers = new int[7];
			this.x = x;
			this.y = y;
			this.fPosition = 6;
			this.uPosition = 1;
		}
		
		boolean move(int direction) {
			
			// 이동할 칸의 좌표를 받아서
			int nx = dice.x + dx[direction - 1];
			int ny = dice.y + dy[direction - 1];
			
			if(isValid(nx, ny)) {	// 이동할 칸의 좌표가 범위안에 있으면
				
				
				// 3-1. 이동하는 방향으로 주사위 값 재배치
				reallocateDice(direction);
				
				
				// 3-2. 이동한 칸에 쓰여 있는 수가 0이면
				if(map[nx][ny] == 0) {
					
					//  주사위의 바닥면에 쓰여 있는 수가 칸에 복사
					map[nx][ny] = numbers[1];
					
				} 
				
				// 3-3. 아니라면 
				else {
					// 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
					numbers[1] = map[nx][ny];
					map[nx][ny] = 0;
				}
				
				
				// 움직인 위치로 주사위를 이동
				this.x = nx;
				this.y = ny;
				
				return true;
				
			}
			
			return false;
			
		}
		
		void reallocateDice(int direction) {
			
			int tmp;
			
			switch(direction) {
				
				// 동
				case 1:
					tmp = numbers[3];
					numbers[3] = numbers[1];
					numbers[1] = numbers[4];
					numbers[4] = numbers[6];
					numbers[6] = tmp;
					break;
					
				// 서
				case 2:
					tmp = numbers[4];
					numbers[4] = numbers[1];
					numbers[1] = numbers[3];
					numbers[3] = numbers[6];
					numbers[6] = tmp;
					break;
					
				// 북   
				case 3:
					tmp = numbers[6];
					numbers[6] = numbers[2];
					numbers[2] = numbers[1];
					numbers[1] = numbers[5];
					numbers[5] = tmp;
					break;
					
				// 남 
				case 4:
					tmp = numbers[2];
					numbers[2] = numbers[6];
					numbers[6] = numbers[5];
					numbers[5] = numbers[1];
					numbers[1] = tmp;
					break;
					
			}
		
			
		}
		
		boolean isValid(int nx, int ny) {
			
			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
				return true;
			}
			
			return false;

		}

		@Override
		public String toString() {
			return "Dice [x=" + x + ", y=" + y + ", fPosition=" + fPosition + ", uPosition=" + uPosition + ", numbers="
				+ Arrays.toString(numbers) + "]";
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static final int EAST = 1;
	static final int WEST = 2;
	static final int NORTH = 3;
	static final int SOUTH = 4;

	// 동서북남 1234
	static int[] dx  = {0, 0, -1, 1};
	static int[] dy  = {1, -1, 0, 0};
	
	static int N, M, X, Y, K;
	static int[][] map;
	static Dice dice;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. N, M, X, Y, K(명령 갯수) 를 입력받는다.
		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		X = Integer.parseInt(inputs[2]);
		Y = Integer.parseInt(inputs[3]);
		K = Integer.parseInt(inputs[4]);

		// 2. 지도 정보를 입력 받는다.
		map = new int[N][M];
		for (int row = 0; row < N; row++) {
			inputs = br.readLine().trim().split(" ");
			for (int cols = 0; cols < M; cols++) {
				map[row][cols] = Integer.parseInt(inputs[cols]);
			}
		}

		// 3. 명령을 받아 주사위를 이동한다.
		dice = new Dice(X, Y);
		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < K; index++) {
			if(dice.move(Integer.parseInt(inputs[index]))) {
				sb.append(dice.numbers[6]).append("\n");
			}
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}