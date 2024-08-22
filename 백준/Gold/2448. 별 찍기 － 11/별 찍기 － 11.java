import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_별찍기11
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int N;
	public static char[][] stars;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		stars = new char[N][N * 2 - 1];

		for (int row = 0; row < N ; row++) {
			Arrays.fill(stars[row], ' ');
		}

		printStar(0, N - 1, N);

		for (int row = 0; row < N; row++) {

			for (int col = 0; col < N * 2 - 1; col++) {

				sb.append(stars[row][col]);

			}

			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void printStar(int curRow, int curCenter, int size) {

		if (size == 3) {

			stars[curRow][curCenter] = '*';
			stars[curRow + 1][curCenter - 1] = stars[curRow + 1][curCenter + 1] = '*';
			stars[curRow + 2][curCenter - 2] = stars[curRow + 2][curCenter - 1] = '*';
			stars[curRow + 2][curCenter + 1] = stars[curRow + 2][curCenter + 2] = '*';
			stars[curRow + 2][curCenter] = '*';
			return;

		} else {
			printStar(curRow, curCenter, size / 2);
			printStar(curRow + (size / 2), curCenter - (size / 2), size / 2);
			printStar(curRow + (size / 2), curCenter + (size / 2), size / 2);
		}

	}

}