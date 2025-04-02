import java.io.*;
import java.util.*;

/**
 * BOJ_달빛여우
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int C;
	static int[] weights;
	static List<Integer> lSub;
	static List<Integer> rSub;


	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		weights = new int[N];
		for (int n = 0; n < N; n++) {
			weights[n] = Integer.parseInt(st.nextToken());
		}

		lSub = new ArrayList<>();
		rSub = new ArrayList<>();
		combination(lSub, 0, N >> 1, 0);
		combination(rSub, N >> 1, N, 0);
		Collections.sort(rSub);

		int totalCount = 0;
		for (int index = 0; index < lSub.size(); index++) {
			int count = binarySearch(0, rSub.size() - 1, lSub.get(index));
			totalCount += (count + 1);
		}

		sb.append(totalCount);
		bw.write(sb.toString());
		bw.close();

	}

	public static int binarySearch(int left, int right, int diff) {

		while (left <= right) {

			int mid = (left + right) >> 1;

			if (rSub.get(mid) <= C - diff) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		return right;

	}

	public static void combination(List<Integer> comb, int depth, int limit, int weight) {

		if (C < weight) {
			return;
		}

		if (depth == limit) {
			comb.add(weight);
			return;
		}

		combination(comb, depth + 1, limit, weight + weights[depth]);
		combination(comb, depth + 1, limit,  weight);

	}

}