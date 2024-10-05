import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_가장긴증가하는부분수열2
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;
	static int[] numbers;
	static List<Integer> lis;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		numbers = new int[N];
		String[] inputs = br.readLine().trim().split(" ");
		for (int idx = 0; idx < N; idx++) {
			numbers[idx] = Integer.parseInt(inputs[idx]);
		}

		lis = new ArrayList<>();
		for (int idx = 0; idx < N; idx++) {

			int left = 0;
			int right = lis.size() - 1;

			// 비어있거나 최근에 삽입한 원소보다 크다면 삽입
			if (lis.isEmpty() || lis.get(right) < numbers[idx]) {
				lis.add(numbers[idx]);
				continue;
			}

			// 최근에 삽입한 원소보다 작은 숫자라면 현재 숫자와 같거나 큰 첫 위치를 찾아 변경
			lis.set(binarySearch(left, right, numbers[idx]), numbers[idx]);


		}

		sb.append(lis.size());
		bw.write(sb.toString());
		bw.close();

	}

	public static int binarySearch(int left, int right, int target) {


		while (left < right) {

			int mid = (left + right) >> 1;

			if (target <= lis.get(mid)) {
				right = mid;
			}

			else {
				left = mid + 1;
			}

		}

		return right;

	}

}