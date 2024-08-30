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

		Arrays.sort(waters);
		int lt, rt;
		lt = 0;
		rt = size - 1;

		min = Integer.MAX_VALUE;
		int[] result = new int[2];
		while (lt < rt) {

			int curSum = waters[lt] + waters[rt];
			int diff = Math.abs(0 - curSum);

			if (min > diff) {
				min = diff;
				result[0] = waters[lt];
				result[1] = waters[rt];
			}

			if (curSum == 0) {
				result[0] = waters[lt];
				result[1] = waters[rt];
				break;
			}

			else if (curSum < 0) {
				lt++;
			}

			else {
				rt--;
			}

		}

		sb.append(result[0]).append(" ").append(result[1]);
		bw.write(sb.toString());
		bw.close();

	}

}