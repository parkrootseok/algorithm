import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/***
 * BOJ_2839_설탕배달
 * @author parkrootseok
 * 
 * - 설탕 Nkg 배달
 * - 봉지는 3, 5 존재
 * - 최대한 적은 봉지
 * - 봉지 몇개 가져가야해? 불가하면 -1 출력
 * 
 * 1. 배댈해야하는 설탕을 입력 받는다
 * 2. 필요한 봉지의 개수를 구한다
 *  2-1. 4or7인 경우는 불가
 *  2-2. 5의 배수인 경우는 5로 나눈 몫이 정답
 *  2-3. 나머지가 1or3인 경우 5로 나눈 몫 + 1
 *  2-4. 나머지가 2or4인 경우 5로 나눈 몫 + 2
 *  
 */

public class Main {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static final int KG_3 = 3;
	static final int KG_5 = 5;

	static int needSugar;
	static int totalPlasticBag;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 배댈해야하는 설탕을 입력 받는다
		needSugar = Integer.parseInt(br.readLine().trim());

		// 2. 필요한 봉지의 개수를 구한다.

		// 2-1. 4or7인 경우는 불가
		if (needSugar == 4 || needSugar == 7) {
			totalPlasticBag = -1;
		}

		// 2-2. 5의 배수인 경우는 5로 나눈 몫이 정답
		else if (needSugar % 5 == 0) {
			totalPlasticBag = needSugar / 5;

		}

		//  2-3. 나머지가 1or3인 경우 5로 나눈 몫 + 1
		else if (needSugar % 5 == 1 || needSugar % 5 == 3) {
			totalPlasticBag = (needSugar / 5) + 1;
		}

		// 2-4. 나머지가 2or4인 경우 5로 나눈 몫 + 2
		else if (needSugar % 5 == 2 || needSugar % 5 == 4) {
			totalPlasticBag = (needSugar / 5) + 2;
		}

		sb.append(totalPlasticBag);
		bw.write(sb.toString());
		bw.close();

	}

}