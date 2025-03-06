import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_구간나누기2
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int M;
	public static int[] numbers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		int max = 0;

		numbers = new int[N];
		inputs = br.readLine().trim().split(" ");
		for (int idx = 0; idx < N; idx++) {
			numbers[idx] = Integer.parseInt(inputs[idx]);
			max = Math.max(max, numbers[idx]);
		}

		sb.append(lower(0, max));
		bw.write(sb.toString());
		bw.close();

	}

	public static int lower(int low, int high) {

		while (low < high) {

			// 탐색할 그룹의 최대값
			int mid = (low + high) >> 1;

			// 현재 mid가 최대값 기준일 때 나올 수 있는 그룹의 개수
			int count = getMaxScore(mid);

			// 그룹이 M개 이하이면 더 최소인 값을 찾기 위해 오른쪽 범위를 축소
			if (count <= M) {
				high = mid;
			}

			else {
				low = mid + 1;
			}

		}

		return high;

	}

	public static int getMaxScore(int mid) {

		int groupCount = 1;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int idx = 0; idx < N; idx++) {

			max = Math.max(max, numbers[idx]);
			min = Math.min(min, numbers[idx]);

			if (mid < max - min) {
				groupCount++;
				max = Integer.MIN_VALUE;
				min = Integer.MAX_VALUE;
				idx--;
			}

		}

		return groupCount;

	}

}