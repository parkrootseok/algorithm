import java.io.*;
import java.util.*;

/**
 * BOJ_사전순최대공통부분수열
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int M;

	static List<Integer> A;
	static List<Integer> B;
	static List<Integer> result;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		A = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			A.add(Integer.parseInt(st.nextToken()));
		}

		M = Integer.parseInt(br.readLine());
		B = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < M; idx++) {
			B.add(Integer.parseInt(st.nextToken()));
		}

		result = new ArrayList<>();
		find(A, B);

		sb.append(result.size()).append("\n");
		if (!result.isEmpty()) {
			for (Integer r : result) {
				sb.append(r).append(" ");
			}
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void find(List<Integer> a, List<Integer> b) {

		if (a.isEmpty() || b.isEmpty()) {
			return;
		}

		int aMaxValue = Collections.max(a);
		int aMaxPos = a.indexOf(aMaxValue);

		int bMaxValue = Collections.max(b);
		int bMaxPos = b.indexOf(bMaxValue);

		if (aMaxValue == bMaxValue) {
			result.add(aMaxValue);
			find(a.subList(aMaxPos + 1, a.size()), b.subList(bMaxPos + 1, b.size()));
		} else if (aMaxValue > bMaxValue) {
			a.remove(aMaxPos);
			find(a, b);
		} else {
			b.remove(bMaxPos);
			find(a, b);
		}

	}

}