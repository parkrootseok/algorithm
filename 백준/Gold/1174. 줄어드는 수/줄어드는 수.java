import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BOJ_줄어드는수
 * @author parkrootseok
 */
public class Main {

	static final int[] DIGIT = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	static int order;
	static List<Long> numbers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		order = Integer.parseInt(br.readLine().trim());

		numbers = new ArrayList<>();
		create(0, 0);

		if (numbers.size() < order) {
			sb.append("-1");
		} else{
			Collections.sort(numbers);
			sb.append(numbers.get(order - 1));
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void create(int depth, long number) {

		if (depth == DIGIT.length) {

			if (!numbers.contains(number)) {
				numbers.add(number);
			}

			return;
		}

		create(depth + 1, number);

		create(depth + 1, (number * 10) + DIGIT[depth]);

	}

}