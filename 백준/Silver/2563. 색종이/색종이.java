import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2563_색종이
 * @author parkrootseok
 * 
 * - 흰색 도화지 있음(100 by 100)
 * - 검은색 색종이(10 by 10)를 붙일거임
 * - 색종이를 붙인 후 검은 영역의 넓이는?
 * 
 * 1. 색종이 수 입력
 * 2. 색종이 붙인 위치 입력(row 좌표, cols 좌표)
 * 3. 검은색 색종이만큼 도화지에 붙인다
 * 4. 색종이 영역을 계산 후 출력
 * 
 */

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int paperNumber;
	static boolean[][] drawingPaper = new boolean[100][100]; 
		
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 색종이 수 입력
		paperNumber = Integer.parseInt(br.readLine().trim());
		for(int curPaper = 0; curPaper < paperNumber; curPaper++) {
		
			// 2. 색종이 붙인 위치 입력(row 좌표, cols 좌표)
			inputs = br.readLine().trim().split(" ");
			int row = Integer.parseInt(inputs[0]);
			int cols = Integer.parseInt(inputs[1]);
			
			// 3. 검은색 색종이만큼 도화지에 붙인다
			for(int curRow = row; curRow < row + 10; curRow++) {
				for(int curCols = cols; curCols < cols + 10; curCols++) {
					drawingPaper[curRow][curCols] = true;
				}
			}
			
		}
		
		// 4. 색종이 영역을 계산 후 출력
		int black = 0;
		for(int row = 0; row < 100; row++) {
			for(int cols = 0; cols < 100 ; cols++) {
				if(drawingPaper[row][cols]) {
					black++;
				}
			}
		}

		sb.append(black);
		bw.write(sb.toString());
		bw.close();
		return;

	}

}