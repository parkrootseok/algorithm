import java.io.*;
import java.util.*;

/**
 * BOJ_곱셈
 * @author parkrootseok
 */
public class Main {

	public static int UP = 0;
	public static int DOWN = 1;
	public static int NONE = 2;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static long A;
	static long B;
	static long C;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		sb.append(divideConquer(A, B));
		bw.write(sb.toString());
		bw.close();
	}

	public static long divideConquer(long A, long exponent) {

		if(exponent == 1) {
			return A % C;
		}
		
		long temp = divideConquer(A, exponent >> 1);

		if(exponent % 2 == 1) {
			return (temp * temp % C) * A % C;
		}
		return temp * temp % C;

	}
}