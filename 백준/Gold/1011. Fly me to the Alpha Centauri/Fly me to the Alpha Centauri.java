import java.io.*;

/**
 * BOJ_FlymetotheAlphaCentauri
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int testCount;
	static int start;
	static int end;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		testCount = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < testCount; tc++) {

			String[] inputs = br.readLine().trim().split(" ");
			start = Integer.parseInt(inputs[0]);
			end = Integer.parseInt(inputs[1]);

			int distance = end - start;
			int sqrt = (int) Math.sqrt(distance);

			// 제곱수 다음부터 이동 횟수 1회 증가하므로 -1
			if (distance == sqrt * sqrt) {
				sb.append(2 * sqrt - 1).append("\n");

			}

			// 제곱수 일때 이동 횟수
			else if (distance <= sqrt * sqrt + sqrt) {
				sb.append(2 * sqrt).append("\n");
			}

			// 제곱수 이후로 이동 횟수 1회 증가하므로 + 1
			else {
				sb.append(2 * sqrt + 1).append("\n");
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

}