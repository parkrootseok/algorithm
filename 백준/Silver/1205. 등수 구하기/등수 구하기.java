import java.io.*;
import java.util.*;

/**
 * BOJ_등수구하기
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int newScore;
	static int P;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		newScore = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		if (N == 0) {
			sb.append(1);
		} else {

			st = new StringTokenizer(br.readLine(), " ");

			List<Integer> scores = new ArrayList<>();
			for (int index = 0; index < N; index++) {
				scores.add(Integer.parseInt(st.nextToken()));
			}

			scores.add(upperBound(scores, newScore), newScore);

			int curRank = 0;
			Map<Integer, Integer> rank = new HashMap<>();
			for (int score : scores) {
				if (!rank.containsKey(score)) {
					rank.put(score, ++curRank);
				} else {
					curRank++;
				}
			}

			int diff = scores.lastIndexOf(newScore) - scores.indexOf(newScore);
			if (P < rank.get(newScore) + diff) {
				sb.append(-1);
			}

			if (sb.length() == 0) {
				sb.append(rank.get(newScore));
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int upperBound(List<Integer> scores, int target) {

		int left = 0;
		int right = scores.size();

		while (left < right) {

			int mid = (left + right) >> 1;
			if (target <= scores.get(mid)) {
				left = mid + 1;
			} else {
				right = mid;
			}

		}

		return right;

	}

}