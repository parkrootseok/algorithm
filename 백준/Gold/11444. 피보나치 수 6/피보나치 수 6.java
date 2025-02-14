import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * BOJ_피보나치 수 6
 * @author parkrootseok
 */
public class Main {

	static final int MAX = 100_000_000;
	static final int MOD_NUMBER = 1_000_000_007;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static long n;
	static long[][] martrix = {{1, 1}, {1, 0}};

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		n = Long.parseLong(br.readLine());

		sb.append(pow(new long[][]{{1, 1},{1, 0}}, n - 1)[0][0]);
		bw.write(sb.toString());
		bw.close();

	}

	public static long[][] pow(long[][] arr, long exp) {

		if (exp == 0 || exp == 1) {
			return arr;
		}

		long[][] tmp = pow(arr, exp / 2);
		tmp = multiply(tmp, tmp);

		if (exp % 2 == 1L) {
			return multiply(tmp, martrix);
		}

		return tmp;

	}

	public static long[][] multiply(long[][] m1, long[][] m2) {

		return new long[][]{
			{
				((m1[0][0] * m2[0][0]) + (m1[0][1] * m2[1][0])) % MOD_NUMBER, ((m1[0][0] * m2[0][1]) + (m1[0][1] * m2[1][1])) % MOD_NUMBER
			},
			{
				((m1[1][0] * m2[0][0]) + (m1[1][1] * m2[1][0])) % MOD_NUMBER, ((m1[1][0] * m2[0][1]) + (m1[1][1] * m2[1][1])) % MOD_NUMBER
			}
		};

	}


}