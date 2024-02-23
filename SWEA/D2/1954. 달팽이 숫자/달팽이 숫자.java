import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_1954_달팽이숫자
 * @author parkrootseok
 * 
 * - 1 ~ N*N까지 숫자가 시계방향으로 출력 
 *  
 *  1. 테스트 케이스 횟수를 입력받는다.
 *  2. N을 입력받는다.
 *  2. cols를 움직이면서 출력
 *  3. 출력할 횟수 - 1
 *  4. row를 움직이면서 출력
 *  5. 방향 전환
 *  	-> 증가하는 방향 or 감소하는 방향
 *  
 */

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuffer sb;
	static String[] inputs;

	static int tcNumber;
	static int N;
	static int[][] map;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuffer();
		
		// 1. 테스트 케이스 횟수를 입력받는다.
		tcNumber = Integer.parseInt(br.readLine().trim());
		
		for(int curTc = 1; curTc <= tcNumber; curTc++) {
			
			// 2. N을 입력받는다.
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			
			int direction = 1;	// 방향을 조정하기 위한 변수
			int curNumber = 1;	// 현재 숫자를 출력하기 위한 변수
			int cRow = 0;	// 행에 대한 변수
			int cCols = -1;	// 열에 대한 변수
			int cLimit = N;	// 출력할 횟수를 위한 변수
			
			while(curNumber <= N * N) {
				
				// 2. cols를 움직이면서 출력
				for(int limit = 0; limit < cLimit; limit++) {
					
					cCols += direction;
					map[cRow][cCols] = curNumber++;
					
				}
				
				// 3. 출력할 횟수 - 1
				cLimit--;
				
				// 4. row를 움직이면서 출력
				for(int limit = 0; limit < cLimit; limit++) {
					
					cRow += direction;
					map[cRow][cCols] = curNumber++;
					
				}
				
				// 5. 방향 전환	
				direction *= -1;
				
			}
			
			// 결과 출력
			sb.append("#").append(curTc).append("\n");
			for(int row = 0; row < N; row++) {
				
				for(int cols = 0; cols < N; cols++) {
					sb.append(map[row][cols]).append(" ");
				}
				sb.append("\n");
			}
			
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}