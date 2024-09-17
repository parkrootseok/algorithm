import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_색종이만들기
 * @author parkrootseok
 */
public class Main {

	public static int WHITE = 0;
	public static int BLUE = 1;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static int[][] map;
	public static int whiteCount;
	public static int blueCount;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		map = new int[N][N];
		for (int row = 0; row < N; row++) {
			String[] inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(inputs[col]);
			}
		}

		recursive(0, 0, N);

		sb.append(whiteCount).append("\n").append(blueCount);
		bw.write(sb.toString());
		bw.close();

	}

	public static void recursive(int curRow, int curCol, int size) {

		if (size == 1) {

			if (map[curRow][curCol] == BLUE) {
				blueCount++;
			} else {
				whiteCount++;
			}

			return;

		}

		boolean isBlue = false;
		boolean isWhite = false;
		for (int row = curRow; row < curRow + size; row++) {

			for (int col = curCol; col < curCol + size; col++) {

				if (map[row][col] == BLUE) {
					isBlue = true;
				} else {
					isWhite = true;
				}

			}

			if (isBlue && isWhite) {
				break;
			}

		}

		if (isWhite && !isBlue) {
			whiteCount++;
		} else if (!isWhite && isBlue) {
			blueCount++;
		} else {
			recursive(curRow, curCol, size / 2);
			recursive(curRow + (size / 2), curCol, size / 2);
			recursive(curRow, curCol + (size / 2), size / 2);
			recursive(curRow + (size / 2), curCol + (size / 2), size / 2);
		}

	}

}