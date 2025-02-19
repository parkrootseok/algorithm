import java.io.*;
import java.util.*;

/**
 * BOJ_행렬제곱
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static long B;
	static int[][] matrix;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		matrix = new int[N][N];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; col++) {
				matrix[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		matrix = pow(matrix, B);
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				sb.append(matrix[row][col] % 1000);
				if (col != N - 1) {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.close();

	}
	
	public static int[][] pow(int[][] cMatrix, long exp) {
		
		if (exp == 1) {
			return cMatrix;
		}
		
		int[][] result = pow(cMatrix, exp >> 1);
		result = multiply(result, result);
		
		if (exp % 2 == 1) {
			result = multiply(result, cMatrix);
		}

		return result;
		
	}
	
	public static int[][] multiply(int[][] a, int[][] b) {

		int[][] result = new int[N][N];

		for (int row = 0; row < N; row++) {

			for (int col = 0; col < N; col++) {

				int sum = 0;

				for (int index = 0; index < N; index++) {
					sum += a[row][index] * b[index][col];
				}

				result[row][col] = sum % 1000;

			}

		}

		return result;

	}

}