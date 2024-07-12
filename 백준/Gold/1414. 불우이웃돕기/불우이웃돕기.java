import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

/**
 * BOJ_1414_불우이웃돕기
 * @author parkrootseok
 *
 * - 집에 N개 방이 존재
 * - 각각 방에는 모두 컴퓨터가 존재
 *  - 컴퓨터는 랜선으로 연결
 * - A, B가 서로 연결 되어 있거나, 다른 컴퓨터를 통해 연결 되어 있다면 서로 통신 가능
 * - N개의 컴퓨터를 모두 연결
 * - N개의 컴퓨터가 모두 연결되어 있는 랜선 길이가 주어질 때, 기부할 수 있는 랜선 길이의 최대값은?
 *
 * 1. 컴퓨터 개수 입력
 * 2. 랜선 길이 입력
 * 3. 크루스칼 알고리즘을 통해 모든 컴퓨터를 연결할 수 있는 최소 길이 계산
 * 4. 최소 길이 값이 -1인지 확인
 * 	4-1. -1이라면 연결 불가이므로 -1 출력
 * 	4-2. -1이 아니라면 최대 기부할 수 있는 랜선 길이를 출력
 */
public class Main {

	public static class Cable implements Comparable<Cable> {

		int from;
		int to;
		int length;

		public Cable(int from, int to, int length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public int compareTo(Cable o) {
			return Integer.compare(this.length, o.length);
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static char[] inputs;

	public static int computerCount;
	public static PriorityQueue<Cable> cableQ;
	public static int[] unf;
	public static int[] rank;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 컴퓨터 개수 입력
		computerCount = Integer.parseInt(br.readLine().trim());

		// 2. 랜선 길이 입력
		int answer = 0;
		cableQ = new PriorityQueue<>();
		for (int row = 0; row < computerCount; row++) {

			inputs = br.readLine().trim().toCharArray();

			for (int col = 0; col < computerCount; col++) {

				int length = 0;

				if (Character.isUpperCase(inputs[col])) {
					length = inputs[col] - 'A' + (27);
				}

				else if (Character.isLowerCase(inputs[col])) {
					length = inputs[col] - 'a' + 1;
				}

				else {
					continue;
				}

				cableQ.add(new Cable(row + 1, col + 1, length));
				answer += length;

			}
		}

		// 3. 크루스칼 알고리즘을 통해 모든 컴퓨터를 연결할 수 있는 최소 길이 계산
		make();
		int useMinLength = kruskal();

		// 4. 최소 길이 값이 -1인지 확인
		if (useMinLength == -1) {
			// 4-1. -1이라면 연결 불가이므로 -1 출력
			sb.append("-1");
		} else {
			// 4-2. -1이 아니라면 최대 기부할 수 있는 랜선 길이를 출력
			sb.append(answer - useMinLength);
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void make() {
		unf = new int[computerCount + 1];
		rank = new int[computerCount + 1];
		for (int index = 1; index <= computerCount; index++) {
			unf[index] = index;
		}
	}

	public static int find(int element) {

		if (unf[element] == element) {
			return element;
		}

		return unf[element] = find(unf[element]);

	}

	public static void union(int to, int from) {

		int repTo = find(to);
		int repFrom = find(from);

		if (repTo == repFrom) {
			return;
		}

		if(rank[repTo] > rank[repFrom]) {
			unf[repFrom] = repTo;
            return;
		}

		unf[repTo] = repFrom;

		if (rank[repTo] == rank[repFrom]) {
			rank[repFrom]++;
		}

	}

	public static int kruskal() {

		int useCableCount = 0;
		int useLength = 0;
		
		while(!cableQ.isEmpty() && useCableCount < computerCount - 1) {

			Cable cable = cableQ.poll();

			if (find(cable.to) != find(cable.from)) {
				union(cable.to, cable.from);
				useLength += cable.length;
				useCableCount++;
			}

		}

		if (useCableCount != computerCount - 1) {
			return -1;
		}

		return useLength;

	}

}