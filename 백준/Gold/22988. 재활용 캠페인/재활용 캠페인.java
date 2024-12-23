import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_재활용캠페인
 * @author parkrootseok
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;

	static int N;
	static long X;
	static double half;
	static long[] numbers;
	static int count;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		half  = X / 2.0;
		int remain = twoPointer();
		count += (remain / 3);

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static int twoPointer() {

		Arrays.sort(numbers);

		int remain = 0;
		int left = 0;
		int right = N - count - 1;

		while (left <= right) {

			if (left == right) {
				remain++;
				break;
			}

			long sum = numbers[left] + numbers[right];

			if (half <= sum) {
				count++;
				left++;
				right--;
			} else {
				left++;
				remain++;
			}

		}

		return remain;

	}

	public static void input() throws IOException {

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		X = Long.parseLong(st.nextToken());

		numbers = new long[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int index = 0; index < N; index++) {

			long number = Long.parseLong(st.nextToken());

			if (X <= number) {
				count++;
				continue;
			}

			numbers[index] = number;

		}

	}

}