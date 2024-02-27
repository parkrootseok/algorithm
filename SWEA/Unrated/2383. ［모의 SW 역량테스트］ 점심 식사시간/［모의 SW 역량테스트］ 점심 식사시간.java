import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * SWEA_2383_점심식사시간
 * @author parkrootseok
 * 
 * - 방에 사람들이 존재
 * - 점심을 먹기 위해 최대한 빠른 시간 내에 내려가야 함
 * - 계단 입구까지 이동 시간 : r1-r2 + c1 - c2
 * - 계단을 내려가는 시간
 *  - 도착 1분 후 아래칸으로 이동
 *  - 계단 위에는 동시에 최대 3명
 *  - 계단 길이가 주어지고 완전히 내려가는데 길이 만큼 소요
 * - 모든 사람이 이동을 완료하는 최소가 되는 경우를 출력
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 방 크기를 받는다.
 * 3. 방에 대한 정보를 받는다.
 * 4. 1번 계단을 선택하는 경우와 아닌 경우로 나누자
 *  4-1. 계단을 이용할 때 걸리는 총 시간을 구한다
 **/

public class Solution {

	static class Person implements Comparable<Person> {

		int row;
		int col;
		int arriveTime;
		int position;
		boolean isReady;

		Person(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Person [row=" + row + ", col=" + col + "]";
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.arriveTime, o.arriveTime);
		}

	}

	static class Stair {

		int row;
		int col;
		int length;

		Stair(int row, int col, int length) {
			super();
			this.row = row;
			this.col = col;
			this.length = length;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int tcNumber;
	static int[][] room;
	static int roomSize;
	static int minTotalHour;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine().trim());

		for (int curTc = 1; curTc <= tcNumber; curTc++) {

			// 2. 방 크기를 받는다.
			roomSize = Integer.parseInt(br.readLine().trim());

			// 3. 방에 대한 정보를 받는다.
			List<Person> people = new ArrayList<>();
			List<Stair> steps = new ArrayList<>();

			room = new int[roomSize][roomSize];
			for (int row = 0; row < roomSize; row++) {

				inputs = br.readLine().trim().split(" ");

				for (int col = 0; col < roomSize; col++) {

					room[row][col] = Integer.parseInt(inputs[col]);

					// 3-1. 사람인 경우 리스트에 추가
					if (room[row][col] == 1) {
						people.add(new Person(row, col));
					}

					// 3-2. 계단인 경우 리스트에 추가
					if (room[row][col] > 1) {
						steps.add(new Stair(row, col, room[row][col]));
					}

				}

			}

			// 4. 1번 계단을 선택하는 경우와 아닌 경우로 나누자 
			minTotalHour = Integer.MAX_VALUE;
			List<Person> stepAPerson = new ArrayList<>();
			List<Person> stepBPerson = new ArrayList<>();

			for (int bitSequence = 0; bitSequence < (1 << people.size()); bitSequence++) {

				for (int bit = 0; bit < people.size(); bit++) {

					if ((bitSequence & (1 << bit)) != 0) {
						stepAPerson.add(people.get(bit));
						continue;
					}

					stepBPerson.add(people.get(bit));

				}

				int totalAHour = Integer.MIN_VALUE;
				int totalBHour = Integer.MIN_VALUE;

				// 집합에 사람이 포함되어 있는 집합만 시간을 계산한다.
				if (stepAPerson.size() != 0) {
					totalAHour = getTotalHour(steps.get(0), stepAPerson);
				}

				if (stepBPerson.size() != 0) {
					totalBHour = getTotalHour(steps.get(1), stepBPerson);
				}

				minTotalHour = Math.min(minTotalHour, Math.max(totalAHour, totalBHour));

				stepAPerson.clear();
				stepBPerson.clear();

			}

			sb.append("#").append(curTc).append(" ").append(minTotalHour).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int getHour(int pr, int pc, int sr, int sc) {

		return Math.abs(pr - sr) + Math.abs(pc - sc);

	}

	public static int getTotalHour(Stair stair, List<Person> arrivedPeople) {

		// 4-1. 계단을 이용할 때 걸리는 총 시간을 구한다
		Queue<Person> stairQ = new ArrayDeque<>();
		Queue<Person> watingQ = new ArrayDeque<>();

		// 4-1-1. 계단에 도착하는 시간을 구하자
		for (Person p : arrivedPeople) {
			p.isReady = false;
			p.position = 0;
			p.arriveTime = getHour(p.row, p.col, stair.row, stair.col);
		}

		// 4-1-2. 도착 시간을 오름차순 정렬
		Collections.sort(arrivedPeople);

		// 4-1-3. 계단을 이용할 때 걸리는 총 시간을 구한다.
		// 계단을 내려가기 시작하는 시간은 도착 시간 + 1
		int completeCount = 0;
		int totalTime = 1;
		while (completeCount < arrivedPeople.size()) {

			// 도착한 사람이 있다면 하나의 계단을 내려가자
			int curPersonNumber = stairQ.size();
			for (int curPerson = 0; curPerson < curPersonNumber; curPerson++) {

				boolean isExit = false;
				Person p = stairQ.poll();

				// 내려갈 준비가 된 사람들은 계단을 내려간다.
				if (p.isReady) {

					// 현재 위치를 증가하여 계단 이동 
					if (++p.position == stair.length) {

						// 증가한 위치가 계단 끝이라면 탈출
						completeCount++;
						isExit = true;

					}

				}

				// 탈출하지 못했다면 다시 큐로 삽입
				if (!isExit) {
					stairQ.add(p);
				}

				// 탈출 했다면 계단큐에 공백이 생겼으므로 대기열에서 가져온다.
				else {
					
					if (!watingQ.isEmpty()) {
						Person wait = watingQ.poll();
						stairQ.add(wait);
					}
					
				}

			}

			// 완료 숫자가 도착한 사람들의 숫자와 같다면 모두 탈출 했으므로 종료
			if (completeCount == arrivedPeople.size()) {
				return totalTime;
			}

			// 현재 시간에 도착한 사람이 있는지 확인 
			for (Person p : arrivedPeople) {

				// 이미 도착한 사람이라면 패스
				if (p.isReady) {
					continue;
				}

				// 도착 후 계단으로 내려갈 준비가 끝났다면 
				if (totalTime == p.arriveTime + 1) {

					// 계단큐에 삽입
					if (stairQ.size() < 3) {
						// 계단에 3명이 존재하지 않는다면 계단으로 들어감
						stairQ.add(p);
					}

					else {
						// 계단에 3명이 존재하면 대키 큐로 이동
						watingQ.add(p);
					}

					// 내려갈 준비 완료
					p.isReady = true;

				}

			}

			totalTime++;

		}

		return totalTime;

	}

}