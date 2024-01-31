import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * BOJ_11660_구간합구하기5
 * @author parkrootseok
 * 
 * - NbyN 크기의 표에서 (x1, y1) 부터 (x2, y2)까지 합을 구해라
 * 
 * 1. N과 M을 받는다.
 * 2. N*N개의 수를 입력 받는다.
 * 3. (x1, y1) / (x2, y2)를 입력받고 합을 구한다.
 * 
 */

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuffer sb = new StringBuffer();
	static String[] inputs;

	static int N;
	static int M;
	static int map[][];

	public static void main(String args[]) throws Exception {

		// 1. N과 M을 받는다.
		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		// 2. N*N개의 수를 입력 받는다.
		map = new int[N+1][N+1];
		for(int row = 1; row <= N; row++) {
			
			inputs = br.readLine().trim().split(" ");
			for(int cols = 1; cols <= N; cols++) {
				map[row][cols] = Integer.parseInt(inputs[cols - 1]) + map[row][cols - 1];	
			}
		
		}
	
		// 3. 시점(x1, y1)과 종점(x2, y2)를 입력받고 합을 구한다.
		int x1, y1, x2, y2;
		for(int curM = 0; curM < M; curM++) {
			
			// 두 개의 시작과 끝 범위를 입력
			inputs = br.readLine().trim().split(" ");
			x1 = Integer.parseInt(inputs[0]);
			y1 = Integer.parseInt(inputs[1]);
			x2 = Integer.parseInt(inputs[2]);
			y2 = Integer.parseInt(inputs[3]);
			
			// x1 ~ x2은 행 범위, y1 ~ y2은 열 범위
			int sum = 0;
			for(int curX = x1; curX <= x2; curX++) {
				sum += map[curX][y2] - map[curX][y1 - 1];
			}
			
			sb.append(sum).append("\n");
			
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}