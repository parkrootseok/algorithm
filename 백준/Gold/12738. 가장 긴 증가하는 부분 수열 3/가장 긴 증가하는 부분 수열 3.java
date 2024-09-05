import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_가장긴증가하는부분수열3
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int[] numbers;
	public static List<Integer> LIS;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		LIS = new ArrayList<>();
		numbers = new int[N + 1];
		inputs = br.readLine().trim().split(" ");
		for (int idx = 1; idx <= N; idx++) {
			numbers[idx] = Integer.parseInt(inputs[idx - 1]);
		}

		LIS.add(Integer.MIN_VALUE);
		for (int idx = 1; idx <= N; idx++) {

			int left = 1;
			int right = LIS.size() - 1;

			// 최대값보다 큰 경우 바로 추가
			if (LIS.get(right) < numbers[idx]) {
				LIS.add(numbers[idx]);
				continue;
			}

			// 그렇지 않은 경우 현재 수보다 같거나 큰 숫자에 다음에 추가
			LIS.set(binarySearch(left, right, numbers[idx]), numbers[idx]);

		}

		sb.append(LIS.size() - 1);
		bw.write(sb.toString());
		bw.close();

	}

	public static int binarySearch(int left, int right, int target) {

		while (left < right) {

			int mid = (left + right) >> 1;

			if (target <= LIS.get(mid)) {
				right = mid;
			}

			else {
				left = mid + 1;
			}

		}

		return right;

	}

}