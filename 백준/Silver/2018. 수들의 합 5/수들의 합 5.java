import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2018_수들의합5
 * @author parkrootseok
 *
 * - 자연수 N을 만들 수 있는 수열의 집합 개수를 구해라
 *
 * 1. 숫자를 입력
 *
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String inputs[];

	public static int number;
	public static int count;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 숫자를 입력
		number = Integer.parseInt(br.readLine().trim());

		count = 1;
		for (int curNumber = 1; curNumber <= number / 2; curNumber++) {
			dfs(curNumber, curNumber);
		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int preNumber, int sum) {

		if (sum > number) {
			return;
		}

		if (sum == number) {
			count++;
			return;
		}

		preNumber++;
		dfs(preNumber,sum + preNumber);
		
	}





}