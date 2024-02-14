import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/***
 * BOJ_1992_쿼드트리
 * @author parkrootseok
 * 
 * - 영상이 모두 0으로만 되어 있으면 압축 결과는 0
 * - 모두 1로만 되어 있으면 압축 결과는 1
 * - 0과 1이 섞여 있으면 4개의 영상으로 나누어 압축
 * 
 * 1. 영상에 대한 크기와 정보를 받아 초기화
 * 2. 압축 진행
 * 	2-1. 현재 크기에 대한 영상이 모두 동일한지 확인
 * 
 */

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int size;
	static int[][] map;

	public static void compression(int startRow, int startCol, int size) {
		
		// 현재 위치에서 현재 사이즈의 배열의 크기를 조사하여 동일한 원소로 이루어져 있는지 확인
		boolean isEqual = true;
		
		int value = map[startRow][startCol];
		for(int curRow = startRow; curRow < startRow + size; curRow++) {
			
			for(int curCol = startCol; curCol < startCol + size; curCol++) {
				
				if(value != map[curRow][curCol]) {
					isEqual = false;
					break;
				}
				
			}
				
		}
		
		// 만약 같다면
		if(isEqual) {
			// 현재 값을 출력하고 종료
			sb.append(value);
			return;
		}
		
		sb.append("(");
		
		int nextSize = size / 2;
		
		// 현재 사이즈를 이용해서 시작 인덱스를 변경하여 재귀 호출
		
		// 왼쪽 위
		compression(startRow, startCol, nextSize);
		
		// 오른쪽 위
		compression(startRow, startCol + nextSize, nextSize);
		
		// 왼쪽 아래
		compression(startRow + nextSize, startCol, nextSize);
		
		// 오른쪽 아래
		compression(startRow + nextSize, startCol + nextSize, nextSize);
		
		sb.append(")");
		
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 영상에 대한 크기와 정보를 받아 초기화
		size = Integer.parseInt(br.readLine().trim());
		
		map = new int[size][size];
		for(int row = 0 ; row < size; row++) {
			inputs = br.readLine().trim().split("");
			for(int col = 0 ; col < size; col++ ) {
				map[row][col] = Integer.parseInt(inputs[col]);
			}
		}
		
		// 2. 압축을 실시한다.
		compression(0, 0, size);
		
		bw.write(sb.toString());
		bw.close();

	}
	
}