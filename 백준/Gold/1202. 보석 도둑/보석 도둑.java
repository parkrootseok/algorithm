import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_보석도둑
 * @author parkrootseok
 *
 * 1. 완전 탐색
 * -> O([보석 갯수]! * [가방 갯수]) -> 최악의 경우 O(300,000! * 300,000) -> 시간 초과
 * 2. 그리디
 * -> O([보석 갯수] * [가방 갯수] * [PQ]) -> 최악의 경우 (300,000 * 300,000 * log 300,000) -> O (N log N)
 */
public class Main {

	static class Jewelry implements Comparable<Jewelry> {

		int weight;
		int cost;

		public Jewelry(int weight, int cost) {
			this.weight = weight;
			this.cost = cost;
		}

		@Override
		public int compareTo(Jewelry j) {

			if (this.weight == j.weight) {
				return Integer.compare(j.cost, this.weight);
			}

			return Integer.compare(this.weight, j.weight);

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int jewelryNumber;
	static int bagNumber;
	static Queue<Jewelry> jewelries;
	static int[] bags;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		jewelryNumber = Integer.parseInt(st.nextToken());
		bagNumber = Integer.parseInt(st.nextToken());

		jewelries = new PriorityQueue<>();
		for (int jewelry = 0; jewelry < jewelryNumber; jewelry++) {
			st = new StringTokenizer(br.readLine(), " ");

			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			jewelries.offer(new Jewelry(weight, cost));
		}

		bags = new int[bagNumber];
		for (int bag = 0; bag < bagNumber; bag++) {
			bags[bag] = Integer.parseInt(br.readLine().trim());
		}
		Arrays.sort(bags);

		sb.append(greedy());
		bw.write(sb.toString());
		bw.close();

	}

	public static long greedy() {

		Queue<Integer> costs = new PriorityQueue<>(Comparator.reverseOrder());

		long totalCost = 0;
		for (int bag = 0; bag < bagNumber; bag++) {

			// 현재, 가방에 넣을 수 있는 보석을 모두 넣은 후
			// - 보석은 무게에 대하여 오름차순, 가격에 대하여 내림차순 정렬된 상태
			while (!jewelries.isEmpty() && jewelries.peek().weight <= bags[bag]) {
				costs.offer(jewelries.poll().cost);
			}

			// 가방에 보석이 존재할 경우, 가장 우선순위가 높은 보석을 선택
			if (!costs.isEmpty()) {
				totalCost += costs.poll();
			}

		}

		return totalCost;

	}

}