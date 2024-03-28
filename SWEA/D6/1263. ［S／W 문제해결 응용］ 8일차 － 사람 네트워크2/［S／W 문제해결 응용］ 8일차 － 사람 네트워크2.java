import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * SWEA_1263_사람네트워크2
 * @author parkrootseok
 * 
 * - 사람 네트워크에서 누가 가장 영향력 있는지를 분석
 * 
 * 1. 테스트 케이스 횟수 받기
 * 2. 사람의 수와 영향력 점수를 행별로 입력 받기
 * 3. Floyd-Warshall 알고리즘을 통해 모든 사람간의 최단 경로를 구한다.
 * 4. 모든 사람 사이의 최단 경로 값을 이용하여 가장 경로 비용이 작은 사람을 구한다.
 **/
class Solution {

	static final int INF = 1000_000_000;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int testCaseNumber;

	static int peopleNumber;
	static int[][] influences;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 받기
		testCaseNumber = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCaseNumber; tc++) {

			// 2. 사람의 수와 영향력 점수를 행별로 입력 받기
			inputs = br.readLine().trim().split(" ");
			peopleNumber = Integer.parseInt(inputs[0]);

			influences = new int[peopleNumber][peopleNumber];
			inputs = Arrays.copyOfRange(inputs, 1, inputs.length);
			for (int row = 0; row < peopleNumber; row++) {

				for (int col = 0; col < peopleNumber; col++) {

					influences[row][col] = Integer.parseInt(inputs[(row * peopleNumber) + col]);

					if (influences[row][col] == 0) {
						influences[row][col] = INF;
					}
				}

			}

			// 3. Floyd-Warshall 알고리즘을 통해 모든 사람간의 최단 경로를 구한다.
			floydWarshall();

			// 4. 모든 사람 사이의 최단 경로 값을 이용하여 가장 경로 비용이 작은 사람을 구한다.
			int minInfluence = Integer.MAX_VALUE;
			for (int row = 0; row < peopleNumber; row++) {

				int sum = 0;
				for (int col = 0; col < peopleNumber; col++) {

					// 영향력 점수를 계산
					if (row != col && influences[row][col] != INF) {
						sum += influences[row][col];
					}

				}

				// 영향력 점수가 현재 최소 영향력 점수보다 작다면 갱신
				minInfluence = Math.min(minInfluence, sum);

			}

			sb.append("#").append(tc).append(" ").append(minInfluence).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static void floydWarshall() {

		for (int people = 0; people < peopleNumber; people++) {

			for (int start = 0; start < peopleNumber; start++) {

				// 경유지와 시작점 다르면서
				if (start != people) {

					for (int destination = 0; destination < peopleNumber; destination++) {

						// 시작점과 도착점이 다르고 도착점도 경유지와 다를 때
						if (start != destination && destination != people) {

							// start에서 destination으로 바로 갈 때의 비용과 people을 경유하여 destination으로 갈 때의 비용을 비교하여
							// 최소값인 경우로 갱신
							influences[start][destination] = Math.min(influences[start][destination],
									influences[start][people] + influences[people][destination]);

						}

					}

				}

			}

		}

	}

}