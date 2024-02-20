import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.sun.org.apache.bcel.internal.generic.ISHR;

import sun.security.krb5.internal.crypto.crc32;

/***
 * BOJ_2636_치즈
 * @author parkrootseok
 * 
 * - 치즈가 놓여져 있고, 치즈는 공기와 접촉된 칸은 한 시간이 지나면 녹아서 사라짐
 * - 치즈가 모두 녹아져 내리는데 걸리는 시간과, 모두 녹기 전에 남아있는 치즈 조각 수를 구해라
 * 
 * 1. 맵에 대한 크기를 받는다.
 * 2. 치즈가 놓여져 있는 위치에 대한 정보를 맵에 초기화	
 * 
 * 
 */

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static final int PIECE_OF_CHEEZE = 1;

	static int[][] map;
	
	static boolean[][] isVisited;
	static boolean[][] inAir;

	static int mapRow;
	static int mapCol;

	static int totalCheezeCount;

	public static boolean inRange(int row, int col) {
		
		if(0 <= row && row < mapRow && 0 <= col && col < mapCol) {
			
			return true;
			
		}
		
		
		return false;
		
	}
	
	public static void getInAirCheeze(int row, int col) {
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		isVisited[row][col] = true;
		
		int nextRow, nextCol;
		for(int dir = 0; dir < dx.length; dir++) {
			
			nextRow = row + dx[dir];
			nextCol = col + dy[dir];
			
			// 인덱스가 유효하면서
			if(!inRange(nextRow, nextCol)) {
				continue;
			} 
			
			// 방문한적 없고
			if(isVisited[nextRow][nextCol]) {
				continue;
			}
			
			// 치즈라면 표시하고 죵료
			if(map[nextRow][nextCol] == PIECE_OF_CHEEZE) {
				inAir[nextRow][nextCol] = true;
				continue;
			}
			
			// 치즈가 아니라면 이동
			getInAirCheeze(nextRow, nextCol);
			
		}
		

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 맵에 대한 크기를 받는다.
		inputs = br.readLine().trim().split(" ");
		mapRow = Integer.parseInt(inputs[0]);
		mapCol = Integer.parseInt(inputs[1]);

		// 2. 치즈가 놓여져 있는 위치에 대한 정보를 맵에 초기화
		map = new int[mapRow][mapCol];
		totalCheezeCount = 0;

		for (int curRow = 0; curRow < mapRow; curRow++) {

			inputs = br.readLine().trim().split(" ");

			for (int curCol = 0; curCol < mapCol; curCol++) {

				map[curRow][curCol] = Integer.parseInt(inputs[curCol]);

				if (map[curRow][curCol] == PIECE_OF_CHEEZE) {
					totalCheezeCount++;
				}

			}

		}

		// 3. 치즈를 녹이기 시작한다
		int totalHour = 0;
		int lastPieceOfCheeze = 0;
		inAir = new boolean[mapRow][mapCol];
		
		while (totalCheezeCount > 0) {

			totalHour++;
			int beforeMeltingTotalCheezeCount = totalCheezeCount;

			// 3-1. 공기와 맞닿아 있는 치즈와 갯수를 구한다.
			inAir = new boolean[mapRow][mapCol];
			isVisited = new boolean[mapRow][mapCol];
			getInAirCheeze(0, 0);

			// 3-2. 공기와 맞닿아 있는 치즈는 녹인다.
			for (int curRow = 0; curRow < mapRow; curRow++) {
				for (int curCol = 0; curCol < mapCol; curCol++) {
					if (inAir[curRow][curCol]) {
						map[curRow][curCol] = '0';
						totalCheezeCount--;
					}

				}
			}

			// 3-3. 더이상 녹일 치즈가 없다면 종료한다.
			if (totalCheezeCount == 0) {
				lastPieceOfCheeze = beforeMeltingTotalCheezeCount;
				break;
			}

		}

		sb.append(totalHour).append("\n").append(lastPieceOfCheeze);
		bw.write(sb.toString());
		bw.close();

	}

}