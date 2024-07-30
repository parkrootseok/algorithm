import java.util.*;

/**
 * PG_다리를지나는트럭
 * @author parkrootseok
 *
 * - 일차선 다리를 트럭 여러 대가 정해진 순으로 건넌다
 * - 모든 트럭이 건너기위해 몇 초가 걸리는지 구해라
 *  - 단, 다리에 올라갈 수 있는 트럭의 최대 개수와 무게가 정해져 있다.
 *  - 무게는 트럭이 완전히 올랐을 떄만 측정한다.
 *
 * 1. 시간 증가
 * 2. 트럭 이동
 * 3. 트럭 다리로 이동
 */
class Solution {
    
    public static class Truck {

		int weight;
		int curLength;

		public Truck(int weight, int curLength) {
			this.weight = weight;
			this.curLength = curLength;
		}

	}

    
    public int solution(int bridge_length, int weight, int[] truck_weights) {

		int time = 0;
		int index = 0;
		int totalWeight = 0;

		Queue<Truck> trucks = new ArrayDeque<>();
		while (true) {

			// 1. 시간 증가
			time++;

			// 2. 트럭 이동
			int size = trucks.size();
			for (int curSize = 0; curSize < size; curSize++) {
				Truck truck = trucks.poll();
				truck.curLength++;

				if (truck.curLength == bridge_length) {
					totalWeight -= truck.weight;
					continue;
				}
				trucks.add(truck);
			}

			// 3. 트럭 다리로 이동
			if (index < truck_weights.length && totalWeight + truck_weights[index] <= weight) {
				totalWeight += truck_weights[index];
				trucks.add(new Truck(truck_weights[index++], 0));
			}

			if (trucks.isEmpty() && !(index < truck_weights.length)) {
				break;
			}

		}

		return time;

	}
    
}