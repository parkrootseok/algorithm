import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_같이눈사람만들래?
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;;

	static int N;
	static int[] diameters;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");

		diameters = new int[N];
		for (int n = 0; n < N; n++) {
			diameters[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(diameters);

		int min = Integer.MAX_VALUE;
		for (int elsaBottom = 0; elsaBottom < N; elsaBottom++) {

			for (int elsaTop = elsaBottom + 1; elsaTop < N; elsaTop++) {

				int annaBottom = 0;
				int annaTop = N - 1;

				while (annaBottom < annaTop) {

					if (annaBottom == elsaBottom || annaBottom == elsaTop) {
						annaBottom++;
						continue;
					}

					if (annaTop == elsaBottom || annaTop == elsaTop) {
						annaTop--;
						continue;
					}

					// 차이가 크다는 것은, 한쪽이 너무 작거나, 너무 크다는 것을 의미함
					// 현재 정렬된 상태이므로, 오른쪽 범위를 줄이면 차이를 작게
					// 왼쪽을 줄이면 차이를 크게 만드는 것임
					int anna = getHeight(annaBottom, annaTop);	// 안나의 눈사람 크기
					int elsa = getHeight(elsaBottom, elsaTop);	// 엘사의 눈사람 크기

					min = Math.min(min, getDiff(anna, elsa));

					if (anna > elsa) {
						annaTop--;
					} else {
						annaBottom++;
					}

				}

			}

		}

		sb.append(min);
		bw.write(sb.toString());
		bw.close();

	}

	public static int getHeight(int b, int t) {
		return diameters[b] + diameters[t];
	}

	public static int getDiff(int anna, int elsa) {
		return Math.abs((anna) - (elsa));
	}

}