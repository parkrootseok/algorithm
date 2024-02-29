import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SWEA_5644_무선충전
 * @author parkrootseok
 * 
 * - 지도는 10by10 고정
 * - BC가 주어지고 최적의 BC를 선택
 * - BC는 충전 범위와 성능을 가지고 있고, 충전받는 객체와의 거리가 C이하일 때 접속이 가능
 * - 사용자의 이동 궤적이 주어지고 초마다 이동하는데 이동한 위치가 BC 범위안에 존재하면 충전이 가능
 * - 만약, 하나의 BC에 접속한 경우 균등하게 분배
 * - BC와 이동 궤적이 주어졌을 때 충전한 양의 최댓값을 구해라
 * - 사용자 A는 (0, 0) 사용자 B는 (9, 9)에서 출발
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 이동 시간과 BC의 갯수를 받는다
 * 3. 이동 정보를 받는다.
 * 4. BC의 정보를 받는다
 * 5. 이동을 시작하며 최대 충전량을 구한다.
 *  5-1. 충전 가능한 배터리 리스트 만들기
 *  5-2. a유저 먼저 선택하여 각각 최대 충전량을 구한다.
 *  5-3. b유저 먼저 선택하여 각각 최대 충전량을 구한다.
 *  5-4. a 먼저 충전량과 b 먼저 충전량을 비교하여 최대값으로 갱신
 *  5-5. 결과를 누적합
 *	5-6. 이동 진행
 * 6. 마지막으로 이동한 후 한번 더 수행
 **/

class Solution {

	static class Person {

		int row;
		int col;

		Person(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public void move(char command) {

			if (command == UP) {
				row--;
			}

			if (command == RIGHT) {
				col++;
			}

			if (command == DOWN) {
				row++;
			}

			if (command == LEFT) {
				col--;
			}

		}

	}

	static class BatteryCharger implements Comparable<BatteryCharger> {

		int row;
		int col;
		int coverage;
		int performance;

		BatteryCharger(int row, int col, int coverage, int performance) {
			this.row = row;
			this.col = col;
			this.coverage = coverage;
			this.performance = performance;
		}

		@Override
		public int compareTo(BatteryCharger o) {

			int diff = this.performance - o.performance;

			if (diff > 0) {
				return -1;
			}

			else {
				return 1;
			}

		}

	}

	static final char STOP = '0';
	static final char UP = '1';
	static final char RIGHT = '2';
	static final char DOWN = '3';
	static final char LEFT = '4';

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int tcNumber;

	static int mapRow = 10;
	static int mapCol = 10;

	static int totalTime;
	static int bcNumber;

	static char[] moveA;
	static char[] moveB;
	static List<BatteryCharger> batteryChargers;
	static int maxTotalCharge;

	public static boolean inCoverage(BatteryCharger bc, int row, int col) {

		int distance = Math.abs(bc.row - row) + Math.abs(bc.col - col);

		// 사람과의 거리 차이가 범위안에 존재할 경우 가능
		if (distance <= bc.coverage) {
			return true;
		}

		return false;

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine());

		for (int curTestCase = 1; curTestCase <= tcNumber; curTestCase++) {

			// 2. 이동 시간과 BC의 갯수를 받는다
			inputs = br.readLine().trim().split(" ");
			totalTime = Integer.parseInt(inputs[0]);
			bcNumber = Integer.parseInt(inputs[1]);

			// 3. 이동 정보를 받는다.
			moveA = br.readLine().trim().replace(" ", "").toCharArray();
			moveB = br.readLine().trim().replace(" ", "").toCharArray();

			// 4. BC의 정보를 받는다
			// col, row, 범위, 성능
			batteryChargers = new ArrayList<>();
			for (int curBc = 0; curBc < bcNumber; curBc++) {

				inputs = br.readLine().trim().split(" ");

				int col = Integer.parseInt(inputs[0]);
				int row = Integer.parseInt(inputs[1]);
				int coverage = Integer.parseInt(inputs[2]);
				int performance = Integer.parseInt(inputs[3]);

				batteryChargers.add(new BatteryCharger(row - 1, col - 1, coverage, performance));

			}

			// 5. 이동을 시작하며 최대 충전량을 구한다.
			Person A = new Person(0, 0);
			Person B = new Person(9, 9);

			List<BatteryCharger> isChargeableA;
			List<BatteryCharger> isChargeableB;
			maxTotalCharge = 0;
			for (int curTime = 0; curTime <= totalTime; curTime++) {

				// 5-1. 각 유저별로 충전 가능한 충전기에 대한 리스트를 생성한다.
				int curMaxTotalCharge = 0;
				isChargeableA = new ArrayList<>();
				isChargeableB = new ArrayList<>();

				for (int curBc = 0; curBc < bcNumber; curBc++) {

					// 유저 A와 충전 가능한 BC를 추가
					if (inCoverage(batteryChargers.get(curBc), A.row, A.col)) {
						isChargeableA.add(batteryChargers.get(curBc));
					}

					// 유저 B와 충전 가능한 BC를 추가
					if (inCoverage(batteryChargers.get(curBc), B.row, B.col)) {
						isChargeableB.add(batteryChargers.get(curBc));
					}

				}

				// 5-2. a유저 먼저 선택하여 각각 최대 충전량을 구한다.
				// a유저에 대한 최대 충전량	
				BatteryCharger aMaxCharger = new BatteryCharger(-1, -1, 0, 0);
				for (int index = 0; index < isChargeableA.size(); index++) {

					BatteryCharger curBc = isChargeableA.get(index);

					if (aMaxCharger.performance < curBc.performance) {
						aMaxCharger = curBc;
					}

				}

				// b유저에 대한 최대 충전량(단, a의 최고 충전량과 다를때)
				BatteryCharger bMaxCharger = new BatteryCharger(-1, -1, 0, 0);
				for (int index = 0; index < isChargeableB.size(); index++) {

					BatteryCharger curBc = isChargeableB.get(index);

					// 동일한 충전기가 아니고
					if (aMaxCharger.row == curBc.row && aMaxCharger.col == curBc.col) {
						continue;
					}

					// 현재 최고 성능보다 높을 경우
					if (bMaxCharger.performance < curBc.performance) {
						bMaxCharger = curBc;
					}

				}

				// 두 최대 충전량을 더해 기록
				curMaxTotalCharge = Math.max(curMaxTotalCharge, aMaxCharger.performance + bMaxCharger.performance);

				// 5-3. b유저 먼저 선택하여 각각 최대 충전량을 구한다.
				// b유저에 대한 최대 충전량
				bMaxCharger = new BatteryCharger(-1, -1, 0, 0);
				for (int index = 0; index < isChargeableB.size(); index++) {

					BatteryCharger curBc = isChargeableB.get(index);

					if (bMaxCharger.performance < curBc.performance) {
						bMaxCharger = curBc;
					}

				}

				// a유저에 대한 최대 중전량(단, b와 다를때)
				aMaxCharger = new BatteryCharger(-1, -1, 0, 0);
				for (int index = 0; index < isChargeableA.size(); index++) {

					BatteryCharger curBc = isChargeableA.get(index);

					// 동일한 충전기가 아니고
					if (bMaxCharger.row == curBc.row && bMaxCharger.col == curBc.col) {
						continue;
					}

					// 현재 최고 성능보다 높을 경우
					if (aMaxCharger.performance < curBc.performance) {
						aMaxCharger = curBc;
					}

				}

				// 5-4. a 먼저 충전량과 b 먼저 충전량을 비교하여 최대값으로 갱신
				curMaxTotalCharge = Math.max(curMaxTotalCharge, aMaxCharger.performance + bMaxCharger.performance);

				// 5-5. 결과를 누적합
				maxTotalCharge += curMaxTotalCharge;

				// 5-6. 이동 진행
				// 마지막 이동 후 충전량을 구할 경우에는 이동하지 않는다.
				if (curTime != totalTime) {
					A.move(moveA[curTime]);
					B.move(moveB[curTime]);
				}

			}

			sb.append("#").append(curTestCase).append(" ").append(maxTotalCharge).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}