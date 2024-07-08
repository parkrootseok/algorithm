import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1940_주몽
 * @author parkrootseok
 *
 * - 갑옷을 만드는데 필요한 번호가 주어지고, 2개 재료 번호의 합이 정확히 일치할 때 제작이 가능
 * - 재료 개수, 필요한 번호, 재료에 대한 정보가 주어졌을 때
 * - 몇개의 갑옷을 만들 수 있는지 구해라
 *
 * 1. 재료 개수 입력
 * 2. 목표 번호 입력
 * 3. 재료 정보 입력
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String inputs[];

	public static int gradientCount;
	public static int target;
	public static int[] gradients;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 재료 개수 입력
		gradientCount = Integer.parseInt(br.readLine().trim());

		// 2. 목표 번호 입력
		target = Integer.parseInt(br.readLine().trim());

		// 3. 재료 정보 입력
		inputs = br.readLine().trim().split(" ");
		gradients = new int[gradientCount];
		for (int index = 0; index < gradientCount; index++) {
			gradients[index] = Integer.parseInt(inputs[index]);
		}

		int answer = 0;
		for (int row = 0; row < gradientCount; row++) {

			for (int col = row + 1; col < gradientCount; col++) {

				if (gradients[row] + gradients[col] == target) {
					answer++;
				}

			}

		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

}