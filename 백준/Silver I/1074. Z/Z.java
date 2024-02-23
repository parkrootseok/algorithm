import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/***
 * BOJ_1074_Z
 * @author parkrootseok
 * 
 * - 2^N x 2^N 크기를 가지는 배열을 z모양 탐색
 * - 왼쪽 위 -> 오른쪽 위 -> 왼쪽 아래 -> 오른쪽 아래 순서로 방문
 * - r행 c열을 몇 번째로 방문하는지 출력
 * 
 * 1. N과 r,c 에 대한 정보를 입력
 * 2. 탐색할 2차원 배열을 생성
 * 3. 분할 정복 알고리즘을 통해 z방문을 실시
 *  3-1. 배열을 4등분
 *  3-2. 왼쪽 위에 존재
 *  3-3. 오른쪽 위에 존재
 *  3-4. 왼쪽 아래에 존재
 *  3-5. 오른쪽 아래에 존재
 */

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int exp;
	static int targetRow;
	static int targetCol;
	static int ArraySize;
	static int visitCount;

	public static void divideConquer(int row, int col, int size) {

		if (size == 1) {
			sb.append(visitCount);
			return;
		}
		
		// 3-1. 배열을 4등분
		// 현재 배열을 4등분 했을 떄 배열의 크기
		int splitSize = size / 2;
		
		// 3-2. 왼쪽 위에 존재
		if (row < splitSize && col < splitSize) {
			visitCount += 0;
			divideConquer(row, col, splitSize);
		}
		
		// 3-3. 오른쪽 위에 존재
		else if (row < splitSize && col < splitSize + splitSize) {
			visitCount += splitSize * splitSize * 1;
			divideConquer(row, col - splitSize, splitSize);
		}
		
		// 3-4. 왼쪽 아래에 존재
		else if (row < splitSize + splitSize && col < splitSize) {
			visitCount += splitSize * splitSize * 2;
			divideConquer(row - splitSize, col, splitSize);
		}
		
		// 3-5. 오른쪽 아래에 존재
		else if (row < splitSize + splitSize && col < splitSize + splitSize) {
			visitCount += splitSize * splitSize * 3;
			divideConquer(row - splitSize, col - splitSize, splitSize);
		}
		
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. N과 r,c 에 대한 정보를 입력
		inputs = br.readLine().trim().split(" ");
		exp = Integer.parseInt(inputs[0]);
		targetRow = Integer.parseInt(inputs[1]);
		targetCol = Integer.parseInt(inputs[2]);

		// 2. 분할 정복 알고리즘을 통해 z방문을 실시
		ArraySize = (int) Math.pow(2, exp);
		divideConquer(targetRow, targetCol, ArraySize);

		bw.write(sb.toString());
		bw.close();

	}

}