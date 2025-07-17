import java.io.*;
import java.util.*;

/**
 * BOJ_계란으로 계란치기
 * @author parkrootseok
 */


public class Main {

	static class Egg {
		int S;
		int W;

		public Egg(int s, int w) {
			S = s;
			W = w;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static Egg[] eggs;
	static int answer;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		eggs = new Egg[N];
		for (int index = 0; index < N; index++) {
			st = new StringTokenizer(br.readLine().trim());
			int S = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			eggs[index] = new Egg(S, W);
		}

		bruteforce(0, 0);

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
	}

	public static void bruteforce(int curIndex, int count) {

		// 가장 오른쪽 계란을 도달한 경우
		if (curIndex == N) {
			// 깨진 계란 수를 체크 후 종료
			answer = Math.max(answer, count);
			return;
		}

		// 현재 계란이 깨져있거나, 다른 계란이 모두 깨진 경우
		if (eggs[curIndex].S <= 0 || count == N - 1) {
			// 다음 계란 선택
			bruteforce(curIndex + 1, count);
			return;
		}

		// 다른 계란들 중
		for (int index = 0; index < N; index++) {
			// 자기 자신이거나 깨진 계란이라면 스킵
			if (index == curIndex || eggs[index].S <= 0) {
				continue;
			}

			int curCount = 0;

			// 계란 치기를 수행 후
			eggs[curIndex].S -= eggs[index].W;
			eggs[index].S -= eggs[curIndex].W;

			if(eggs[curIndex].S <= 0) {
				curCount++;
			}
			if(eggs[index].S <= 0) {
				curCount++;
			}

			// 다음 계란 선택
			bruteforce(curIndex + 1, count + curCount);

			// 상태 복구
			eggs[curIndex].S += eggs[index].W;
			eggs[index].S += eggs[curIndex].W;
		}
	}

}
