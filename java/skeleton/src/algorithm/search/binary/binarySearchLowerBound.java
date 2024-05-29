package algorithm.search.binary;

/**
 * Binary Search Lower Bound(이진 탐색 - 하한)
 * - O(log n)
 * - 정렬 필수
 * - 탐색 값 이상인 첫 위치를 탐색
 * @author parkrootseok
 *
 * 1. 시작 위치와 마지막 위치를 초기화
 * 2. 중간 위치를 초기화
 * 3. 탐색값 <= 중앙값 이라면, 중앙값보다 왼쪽에 위치하므로 오른쪽 범위를 축소
 * 4. 중앙값 < 탐색값 이라면, 중앙값보다 오른쪽에 위치하므로 왼쪽 범위를 축소
 **/
public class binarySearchLowerBound {

	public static final int SIZE = 10;
	public static int[] numbers;

	public static void init() {

		for (int number = 1; number <= SIZE; number++) {
			numbers[number - 1] = number;
		}

	}


	public static void main(String[] args) {

		init();

	}

	public static int binarySearchLowerBound(int target) {

		// 1. 시작 위치와 마지막 위치를 초기화
		int left = 0;
		int right = numbers.length - 1;

		// 시작 위치가 마지막 위치보다 작다면 계속 반복 수행
		while (left < right) {

			// 2. 중간 지점을 초기화
			int mid = (left + right) / 2;

			// 3. 탐색값 <= 중앙값 이라면, 중앙값보다 왼쪽에 위치하므로 오른쪽 범위를 축소
			if (target <= numbers[mid]) {
				right = mid;
			}

			// 4. 중앙값 < 탐색값 이라면, 중앙값보다 오른쪽에 위치하므로 왼쪽 범위를 축소
			else if (numbers[mid] < target) {
				left = mid + 1;
			}

		}

		return left;

	}


}
