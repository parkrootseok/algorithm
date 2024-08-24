import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_별찍기10
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;
	public static char[][] stars;

	public static int N;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		stars = new char[N][N * 2];
		for (int row = 0; row < N; row++) {
			Arrays.fill(stars[row], ' ');
		}

		printStar(0, N - 1, N);

		for (int row = 0; row < N; row++) {

			for (int col = 0; col < N * 2; col++) {

				sb.append(stars[row][col]);

			}

			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void printStar(int curRow, int curEdge, int size) {

		if (size == 3) {

			stars[curRow][curEdge] = '*';

			stars[curRow + 1][curEdge - 1] = stars[curRow + 1][curEdge + 1] = '*';

			stars[curRow + 2][curEdge] = '*';
			stars[curRow + 2][curEdge + 1] = stars[curRow + 2][curEdge - 1] = '*';
			stars[curRow + 2][curEdge + 2] = stars[curRow + 2][curEdge - 2] = '*';
			
		}

		else {
			printStar(curRow, curEdge, size / 2);
			printStar(curRow + (size / 2), curEdge + (size / 2), size / 2);
			printStar(curRow + (size / 2), curEdge - (size / 2), size / 2);
		}

	}

}