import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_1456_거의소수
 * @author parkrootseok
 *
 * - 어떤 수가 소소의 N제곱 꼴일 때 그 수를 거의 소수라고 함
 * - A, B가 주어지고 A이상 B이하인 거의 소수의 개수를 구해라
 *
 * 1. 범위 입력
 * 2. 에스토라테네스 체 알고리즘을 활용 소수 판별 진행
 * 3. A ~ B 사이에 존재하는 거의 소수 카운팅
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static long start;
	public static long end;
	public static boolean[] isPrime;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 범위 입력
		inputs = br.readLine().trim().split(" ");
		start = Long.parseLong(inputs[0]);
		end = Long.parseLong(inputs[1]);

		// 2. 에스토라테네스 체 알고리즘을 활용 소수 판별 진행
		isPrime = new boolean[(int) (Math.sqrt(end) + 1)];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for (int number = 2; number <= Math.sqrt(end); number++) {
			if (isPrime[number]) {
				for (int checkNumber = number * 2; checkNumber <= Math.sqrt(end); checkNumber += number) {
					isPrime[checkNumber] = false;
				}
			}
		}

		// 3. A ~ B 사이에 존재하는 거의 소수 카운팅
		int count = 0;
		for (int number = 2; number <= Math.sqrt(end); number++) {

			if (isPrime[number]) {
				int exp = 2;
				long powNumber;
				while (true) {

					powNumber = (long) Math.pow(number, exp++);

					if (end < powNumber) {
						break;
					}

					if (start <= powNumber && powNumber <= end) {
						count++;
					}

				}
			}

		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

}