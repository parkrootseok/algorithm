import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * BOJ_LCS2
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int[][] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		dp = new int[A.length + 1][B.length + 1];

		for (int i = 1 ; i <= A.length; i++) {

			for (int j = 1 ; j <= B.length; j++) {

				if (A[i - 1] == B[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}

			}

		}

		sb.append(dp[A.length][B.length]).append("\n").append(getLCS(A, B));
		bw.write(sb.toString());
		bw.close();

	}

	private static String getLCS(char[] A, char[] B) {

		StringBuilder LCS = new StringBuilder();
		int i = A.length;
		int j = B.length;

		while (0 < i && 0 < j) {

			if (dp[i][j] == dp[i - 1][j]) {
				i--;
			} else if (dp[i][j] == dp[i][j - 1]) {
				j--;
			} else {
				LCS.insert(0, A[i- 1]);
				i--;
				j--;
			}
			
		}

		return LCS.toString();

	}

}