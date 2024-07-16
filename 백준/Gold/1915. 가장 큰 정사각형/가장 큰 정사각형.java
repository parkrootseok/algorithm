import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_1915_정사각형
 * @author parkrootseok
 *
 * - 배열애서 1로 이루어진 가장 큰 정사각형의 크기를 구해라
 *
 * 1. 배열 크기 입력
 * 2. 배열 정보 입력
 * 3. DP 알고리즘을 통해 구하기
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int rowSize;
	public static int colSize;
	public static int[][] arr;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 배열 크기 입력
		inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[0]);
		colSize = Integer.parseInt(inputs[1]);

		// 2. 배열 정보 입력
		arr = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {

			inputs = br.readLine().trim().split("");

			for (int col = 0; col < colSize; col++) {

				arr[row][col] = Integer.parseInt(inputs[col]);

			}

		}

		// 3. DP 알고리즘을 통해 구하기
		int maxLength = 0;
		int[][] dp = new int[rowSize + 1][colSize + 1];
		for (int row = 1; row <= rowSize; row++) {

			for (int col = 1; col <= colSize; col++) {

				// 현재 위치가 1이라면
				if (arr[row - 1][col - 1] == 1) {

					// 대각선, 왼쪽, 아래쪽 방향에 위치하는 크기 중 가장 작은값으로 초기화 후
					dp[row][col] = Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1]));

					// 크기 증가
					dp[row][col]++;

					// 최대 길이 갱신
					maxLength = Math.max(maxLength, dp[row][col]);

				}

			}

		}

		sb.append((int) Math.pow(maxLength, 2));
		bw.write(sb.toString());
		bw.close();

	}

}