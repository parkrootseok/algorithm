import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * SWEA_4014_활주로건설
 * @author parkrootseok
 * 
 * - 활주로 건설
 *  - 가로 방향 또는 세로 방향으로 가능성 확인
 *  - 높이가 모두 동일한 구간은 건설 가능
 * 
 * - 경사로
 *  - 높이는 항상 1
 *  - 낮은 지형의 높이가 경사로의 길이만큼 연속되는곳에 설치 가능
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 테스트 케이스 입력
 *  2-1. 지도 크기와 경사로 길이를 받기
 *  2-2. 높이에 대한 정보 받기
 * 3. 활주로 건설이 가능한 개수를 구한다.
 * 	3-1. 가로 체크
 * 	3-2. 세로 체크
 * 
 **/

public class Solution {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int testCaseNumber;
	
	static int[][] map;
	static int size;
	
	static int rampWidth;
	static int rampHeight = 1;
	
	static boolean[][] isPossible;
	
	public static void input() throws NumberFormatException, IOException {

		// 2-1. 지도 크기와 경사로 길이를 받기
		inputs = br.readLine().trim().split(" ");
		size = Integer.parseInt(inputs[0]);
		rampWidth = Integer.parseInt(inputs[1]);
	
		// 2-2. 높이에 대한 정보 받기
		map = new int[size][size];
		for (int row = 0; row < size; row++) {
			inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < size; col++) {				
				map[row][col] = Integer.parseInt(inputs[col]);
			}
			
		}
		
	} 
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 횟수 입력
		testCaseNumber = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCaseNumber; tc++) {
			
			// 2. 테스트 케이스 입력
			input();
			
			// 3. 활주로 건설이 가능한 개수를 구한다.
			int possibleCount = 0;
			for (int position = 0; position < size; position++) {
				
				// 3-1. 가로 조사
				if(checkRow(position)) {
					possibleCount++;
				}
				
				// 3-2. 세로 조사
				if(checkCol(position)) {
					possibleCount++;
				}
				
			}
			
			sb.append("#").append(tc).append(" ").append(possibleCount).append("\n");
			
		}
	
		bw.write(sb.toString());
		bw.close();
		
		return;
	
	}
	
	public static boolean checkRow(int position) {
		
		int curLength = 1;
		int curHeight = map[0][position];
		
		for (int curRow = 1; curRow < size; curRow++) {
			
			// 높이 차이가 없을 때
			if (curHeight == map[curRow][position]) {
				
				// 길이 증가
				curLength++;
				
			}
			
			// 높이가 1이 작은 지형을 만났을 때
			else if (curHeight - map[curRow][position] == 1) {
				
				// 현재 row + 너비가 size를 초과하면 설치 불가
				if (size < curRow + rampWidth) {
					return false;
				}
				
				// 너비만큼 높이 차이가 동일한 지형이 있는지 확인
				for (int curWidth = 1; curWidth < rampWidth; curWidth++) {
					if (curHeight - map[++curRow][position] != 1) {
						return false;
					}
				}
				
				// 경사로 건설이 가능하다면 현재 길이와 높이를 갱신
				curLength = 0;
				curHeight = map[curRow][position];
				
			}
			
			// 높이가 1이 큰 지형을 만났을 때
			else if (curHeight - map[curRow][position] == -1) {
				
				// 현재 길이가 너비보다 작다면 설치 불가
				if (curLength < rampWidth) {
					return false;
				}
				
				// 경사로 건설이 가능하다면 현재 길이와 높이를 갱신
				curLength = 1;
				curHeight = map[curRow][position];
				
			}

			// 그 외의 경우(활주로 건설 불가)
			else {
				return false;
			}

		}
		
		return true;
		
	}
	
	public static boolean checkCol(int position) {

		int curLength = 1;
		int curHeight = map[position][0];
		for (int curCol = 1; curCol < size; curCol++) {
			
			// 높이 차이가 없을 때
			if (curHeight == map[position][curCol]) {
				
				// 길이 증가
				curLength++;
				
			}
			
			// 높이가 1이 작은 지형을 만났을 때
			else if (curHeight - map[position][curCol] == 1) {
				
				// 현재 row + 너비가 size를 초과하면 설치 불가
				if (size < curCol + rampWidth) {
					return false;
				}
				
				// 너비만큼 높이 차이가 동일한 지형이 있는지 확인
				for (int curWidth = 1; curWidth < rampWidth; curWidth++) {
					if (curHeight - map[position][++curCol] != 1) {
						return false;
					}
				}
				
				// 경사로 건설이 가능하다면 현재 길이와 높이를 갱신
				curLength = 0;
				curHeight = map[position][curCol];
				
			}
			
			// 높이가 1이 큰 지형을 만났을 때
			else if (curHeight - map[position][curCol] == -1) {
				
				// 현재 길이가 너비보다 작다면 설치 불가
				if (curLength < rampWidth) {
					return false;
				}
				
				// 경사로 건설이 가능하다면 현재 길이와 높이를 갱신
				curLength = 1;
				curHeight = map[position][curCol];
				
			}

			// 그 외의 경우(활주로 건설 불가)
			else {
				return false;
			}

		}
		
		return true;

		
	}
    
}