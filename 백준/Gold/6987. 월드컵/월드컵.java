import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/***
 * BOJ_6987_월드컵
 * @author parkrootseok
 * 
 * - 총 6개 팀에 대한 승, 무승부, 패 정보가 주어지고
 * - 이 기록이 가능한지를 판단해라
 * - 입력은 승, 무, 패 순으로 주어지고
 * - 3개씩 총 6번 주어짐
 * 
 * 1. 6개의 나라에 대한 승무패 기록을 받는다
 * 2. 
 * 
 */

public class Main {

	static class Country {

		int win;
		int draw;
		int lose;

		public Country(int win, int draw, int lose) {
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int TOTAL_COUNTRY_NUMBER = 6;
	static int TOTAL_MATCH = 15;

	static int totalWin;
	static int totalDraw;
	static int totalLose;

	static Country[] countries;
	static Country[][] allMatch;

	static int result;
	static boolean isPossible;

	public static void runMatch(int curMatch) {

		// 3-1. 매치가 총 15번 진행된 경우 모두 진행되었으므로 true 리턴
		if (curMatch == TOTAL_MATCH) {
			isPossible = true;
			return;
		}

		Country home = allMatch[curMatch][0];
		Country away = allMatch[curMatch][1];

		// 홈팀이 이기고 어웨이가 진 경우
		if (home.win > 0 && away.lose > 0) {
			
			// 진행된 게임을 반영하고
			home.win--;
			away.lose--;
			
			// 다음 매치 진행
			runMatch(curMatch + 1);
			
			// 매치가 끝난 경우 다시 복구
			home.win++;
			away.lose++;
			
		}
		
		// 두 팀이 비긴 경우
		if (home.draw > 0 && away.draw > 0) {

			// 진행된 게임을 반영하고
			home.draw--;
			away.draw--;
			
			// 다음 매치 진행
			runMatch(curMatch + 1);
			
			// 매치가 끝난 경우 다시 복구
			home.draw++;
			away.draw++;
			
		}
		
		// 홈팀이 지고 어웨이가 이긴 경우
		if (home.lose > 0 && away.win > 0) {
			
			// 진행된 게임을 반영하고
			home.lose--;
			away.win--;
			
			// 다음 매치 진행
			runMatch(curMatch + 1);
			
			// 매치가 끝난 경우 다시 복구
			home.lose++;
			away.win++;
			
		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		for (int testCase = 0; testCase < 4; testCase++) {

			// 1. 6개의 나라에 대한 승무패 기록을 받는다
			boolean flag = true;

			inputs = br.readLine().trim().split(" ");
			countries = new Country[TOTAL_COUNTRY_NUMBER];
			for (int curCountry = 0; curCountry < TOTAL_COUNTRY_NUMBER; curCountry++) {

				int win = Integer.parseInt(inputs[3 * curCountry]);
				int draw = Integer.parseInt(inputs[3 * curCountry + 1]);
				int lose = Integer.parseInt(inputs[3 * curCountry + 2]);

				// 승무패 기록이 총합이 5가 아닌 경우 종료
				if (win + draw + lose != 5) {
					sb.append(0).append(" ");
					flag = false;
					break;
				}

				countries[curCountry] = new Country(win, draw, lose);

			}

			// 2. 대진표를 생성
			// TOTAL_MATCH 만큼 2개의 팀이 경기를 진행
			// A - B, C, D, E, F
			// B - C, D, E, F
			// C - D, E, F
			// D - E, F
			// E - F

			int curMatch = 0;
			allMatch = new Country[TOTAL_MATCH][2];
			for (int home = 0; home < TOTAL_COUNTRY_NUMBER; home++) {

				for (int away = home + 1; away < TOTAL_COUNTRY_NUMBER; away++) {

					allMatch[curMatch][0] = countries[home];
					allMatch[curMatch][1] = countries[away];
					curMatch++;

				}

			}

			if (!flag) {
				continue;
			}

			// 3. 미리 작성한 대진표를 통해 매치를 진행
			result = 1;
			isPossible = false;

			runMatch(0);

			if (!isPossible) {
				result = 0;
			}

			sb.append(result).append(" ");

		}

		bw.write(sb.toString());
		bw.close();

	}

}