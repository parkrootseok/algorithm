import java.io.*;
import java.util.Arrays;

/**
 * BOJ_LIS
 * @author parkrootseok
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int N;
	static int[] numbers;
	static int goal;
	static int count;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		input();

		twoPointer();

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static void twoPointer() {

		Arrays.sort(numbers);

		int left = 0;
		int right = N - 1;

		while (left < right) {

			int sum = numbers[left] + numbers[right];

			if (sum < goal) {
				left++;
			} else if (goal < sum) {
				right--;
			} else {
				right--;
				count++;
			}

		}

	}

	public static void input() throws IOException {

		N = Integer.parseInt(br.readLine().trim());

		numbers = new int[N];
		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < N; index++) {
			numbers[index] = Integer.parseInt(inputs[index]);
		}

		goal = Integer.parseInt(br.readLine().trim());
	}

}