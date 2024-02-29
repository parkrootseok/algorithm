import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_17471_게리맨더링
 * @author parkrootseok
 * 
 * - 구역을 두 개의 선거구로 나누고
 * - 각 구역은 두 선거구 중 하나에 포함
 * - 선거구는 구역을 적어도 하나 포함
 * - 선거구에 포함되어 있는 구역은 모두 연결
 * - 두 개의 선거구의 인구 차이가 최소일 때 최솟값을 구하자
 * 
 * 1. 구역의 개수를 받는다.
 * 2. 구역의 인구수를 받는다.
 * 3. 각 구역의 인접한 구역을 받는다.
 *  3-1. 인접한 구역수를 받고
 *  3-2. 인접한 구역을 받는다
 *  3-3. 인접한 구역은 서로 인접
 * 4. 부분집합을 생성
 * 5. 생성된 두 집합의 연결성을 확인한다.
 *  5-1. 현재 선거구에 포함되는지 확인
 *   5-1-1. 포함하지 않는다면 패스
 *   5-1-2. 이미 방문한 지역이면 패스
 *   5-1-3. 포함한다면 다음 탐색
 *  5-2. 현재 선거구에 포함된 모든 지역이 연결되어있는지 확인
 *   5-2-1. 포함되어 있지 않다면 false
 *   5-2-2. 포함되어 있다면 true
 * 6. 두 집합 모두 연결된 경우 두 집합의 인구수 차이를 구하여 최소값인지 확인하고 갱신한다.
 **/

public class Main {

	static class Area {

		int number;
		int population;
		List<Area> adjacentArea;

		public Area(int number, int population) {
			this.number = number;
			this.population = population;
			this.adjacentArea = new ArrayList<>();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + number;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Area other = (Area)obj;
			if (number != other.number)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Area [number=" + number + "]";
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static Area[] areas;
	static int areaNumber;
	static int totalPopulation;
	static int minPopulation;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 구역의 개수를 받는다.
		areaNumber = Integer.parseInt(br.readLine().trim());
		areas = new Area[areaNumber + 1];

		// 2. 구역의 인구수를 받는다.
		totalPopulation = 0;
		inputs = br.readLine().trim().split(" ");
		for (int curArea = 1; curArea <= areaNumber; curArea++) {

			int population = Integer.parseInt(inputs[curArea - 1]);

			totalPopulation += population;

			areas[curArea] = new Area(curArea, population);

		}

		// 3. 각 구역의 인접한 구역을 받는다.
		for (int curArea = 1; curArea <= areaNumber; curArea++) {

			inputs = br.readLine().trim().split(" ");

			// 3-1. 인접한 구역수를 받고
			int number = Integer.parseInt(inputs[0]);

			if (number == 0) {
				continue;
			}

			// 3-2. 인접한 구역을 받는다
			for (int index = 1; index <= number; index++) {
				int adjacentArea = Integer.parseInt(inputs[index]);
				areas[curArea].adjacentArea.add(areas[adjacentArea]);
			}

		}

		// 4. 부분집합을 생성한다.
		minPopulation = Integer.MAX_VALUE;
		List<Area> electionAreaA = new ArrayList<>();
		List<Area> electionAreaB = new ArrayList<>();
		for (int number = 1; number < (1 << areaNumber) - 1; number++) {

			for (int bit = 0; bit < areaNumber; bit++) {

				// 집합에 포함
				if ((number & (1 << bit)) != 0) {
					electionAreaA.add(areas[bit + 1]);
				}

				// 집합에 미포함
				else {
					electionAreaB.add(areas[bit + 1]);
				}

			}

			// 5. 생성된 두 집합의 연결성을 확인한다.
			boolean isLinkedA = isLinked(electionAreaA);
			boolean isLinkedB = isLinked(electionAreaB);

			// 6. 두 집합 모두 연결된 경우 두 집합의 인구수 차이를 구하여 최소값인지 확인하고 갱신한다.
			if (isLinkedA && isLinkedB) {

				int aPopulation = getPopulation(electionAreaA);
				int bPopulation = totalPopulation - aPopulation;

				minPopulation = Math.min(minPopulation, Math.abs(aPopulation - bPopulation));

			}

			electionAreaA.clear();
			electionAreaB.clear();

		}

		if (minPopulation == Integer.MAX_VALUE) {
			minPopulation = -1;
		}

		sb.append(minPopulation).append("\n");
		bw.write(sb.toString());
		bw.close();

		return;

	}

	public static boolean isLinked(List<Area> electionArea) {

		Queue<Area> areaQ = new ArrayDeque<>();
		boolean[] isVisted = new boolean[areaNumber + 1];

		areaQ.add(electionArea.get(0));
		isVisted[electionArea.get(0).number] = true;

		while (!areaQ.isEmpty()) {

			Area curArea = areaQ.poll();

			// 5-1. 현재 선거구에 포함되는지 확인
			for (Area adj : curArea.adjacentArea) {

				// 5-1-1. 포함하지 않는다면 패스
				if (!electionArea.contains(adj)) {
					continue;
				}

				// 5-1-2. 이미 방문한 지역이면 패스
				if (isVisted[adj.number]) {
					continue;
				}

				// 5-1-3. 포함한다면 다음 탐색
				isVisted[adj.number] = true;
				areaQ.add(adj);

			}

		}

		// 5-2. 현재 선거구에 포함된 모든 지역이 연결되어있는지 확인
		for (Area curArea : electionArea) {

			// 5-2-1. 포함되어 있지 않다면 false
			if (!isVisted[curArea.number]) {
				return false;
			}

		}

		// 5-2-2. 포함되어 있다면 true
		return true;
	}

	public static int getPopulation(List<Area> electionArea) {

		int population = 0;

		for (Area area : electionArea) {
			population += area.population;
		}

		return population;

	}

}