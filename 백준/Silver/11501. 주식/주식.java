import java.io.*;
import java.util.*;

/**
 * BOJ_주식
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		while (0 < T--) {

			int N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine(), " ");
			int[] price = new int[N];
			Stack<Integer> max = new Stack<>();
			for (int n = 0; n < N; n++) {
				price[n] = Integer.parseInt(st.nextToken());
			}

			int peek = 0;
			long profit = 0;
			for (int n = N - 1; 0 <= n; n--) {
				if (peek < price[n]) {
					// 현재 최고가 보다 크다면 갱신
					peek = price[n];
				} else if (price[n] < peek) {
					// 현재 최고가 보다 작다면 판매
					profit += (peek - price[n]);
				}
			}

			sb.append(profit).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}