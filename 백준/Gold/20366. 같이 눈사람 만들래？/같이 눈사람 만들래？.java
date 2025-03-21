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

		sb.append(twoPointer());
		bw.write(sb.toString());
		bw.close();

	}

	public static int twoPointer() {

		int min = Integer.MAX_VALUE;
		for (int elsaBottom = 0; elsaBottom < N; elsaBottom++) {
			for (int elsaTop = elsaBottom + 1; elsaTop < N; elsaTop++) {

				int elsa = getHeight(elsaBottom, elsaTop);
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

					int anna = getHeight(annaBottom, annaTop);
					min = Math.min(min, getDiff(anna, elsa));

					if (anna > elsa) {
						// 안나가 만든 눈사람이 더 큰 경우 작게 조정
						annaTop--;
					} else if (anna < elsa) {
						// 안나가 만든 눈사람이 더 작은 경우 크게 조정
						annaBottom++;
					} else {
						// 같을 경우 0을 바로 리턴
						return 0;
					}

				}

			}
		}

		return min;

	}


	public static int getHeight(int b, int t) {
		return diameters[b] + diameters[t];
	}

	public static int getDiff(int anna, int elsa) {
		return Math.abs((anna) - (elsa));
	}

}