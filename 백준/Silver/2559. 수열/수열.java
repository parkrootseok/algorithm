import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * BOJ_수열
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int size;
	static int offset;
	static int[] numbers;
	static int[] sum;
	static int max;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		size = Integer.parseInt(inputs[0]);
		offset = Integer.parseInt(inputs[1]);

		numbers = new int[size];
		sum = new int[size + 1];
		inputs = br.readLine().trim().split(" ");
		for (int s = 0; s < size; s++) {
			numbers[s] = Integer.parseInt(inputs[s]);
			sum[s + 1] = (numbers[s] + sum[s]);
		}

		max = Integer.MIN_VALUE;
		for (int range = offset; range <= size; range++) {
			max = Math.max(max, sum[range] - sum[range - offset]);
		}

		sb.append(max);
		bw.write(sb.toString());
		bw.close();

	}

}