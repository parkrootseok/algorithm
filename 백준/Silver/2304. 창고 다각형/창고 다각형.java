import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * BOJ_창고다각형
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int count;
	static int[] numbers;
	static int[] LR;
	static int[] RL;
	static int[] answer;

 	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		count = Integer.parseInt(br.readLine().trim());

		numbers = new int[1001];
		for (int c = 0; c < count; c++) {

			String[] inputs = br.readLine().trim().split(" ");

			int L = Integer.parseInt(inputs[0]);
			int H = Integer.parseInt(inputs[1]);
			numbers[L] = H;

		}

		LR = new int[1001];
		RL = new int[1002];
		for (int l = 1; l <= 1000; l++) {
			LR[l] = Math.max(numbers[l], LR[l - 1]);
			RL[1000 - l + 1] = Math.max(numbers[1000 - l + 1], RL[1000 - l + 2]);
		}

		int sum = 0;
		answer = new int[1001];
		for (int l = 1; l <= 1000; l++) {
			answer[l] = Math.min(LR[l], RL[l]);
			sum += answer[l];
		}

		sb.append(sum);
		bw.write(sb.toString());
		bw.close();

	}

}