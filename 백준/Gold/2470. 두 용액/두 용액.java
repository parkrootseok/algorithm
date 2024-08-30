import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_두용액
 * @author parkrootseok
 *
 * - 산성, 알카리성 용액 존재
 *  - 산성 (1 ~ 1_000,000_000)
 *  - 알카리성 (-1 ~ -1_000_000_000)
 * - 특성값
 *  - 혼합에 사용된 각 용액의 특성값의 합
 * - 목표
 *  - 특성값이 0에 가장 가까운 용액 제조
 *  - 두 용액의 종류는 상관 없음
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int size;
	public static int[] waters;
	public static int min;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		size = Integer.parseInt(br.readLine().trim());
		waters = new int[size];
		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < size; index++) {
			waters[index] = Integer.parseInt(inputs[index]);
		}

		min = Integer.MAX_VALUE;
		Arrays.sort(waters);
		
		int[] result = new int[2];
		int left = 0;
		int right = size - 1;
		while (left < right) {

			int sum = waters[left] + waters[right];
			int diff = Math.abs(0 - sum);

			if (min > diff) {
				min = diff;
				result[0] = waters[left];
				result[1] = waters[right];
			}

			if (sum == 0) {
				result[0] = waters[left];
				result[1] = waters[right];
				break;
			}

			else if (sum < 0) {
				left++;
			}

			else {
				right--;
			}

		}

		sb.append(result[0]).append(" ").append(result[1]);
		bw.write(sb.toString());
		bw.close();

	}

}