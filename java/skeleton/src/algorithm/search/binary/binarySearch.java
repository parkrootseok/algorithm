package algorithm.search.binary;

/**
 * Binary Search(이진 탐색)
 * - O(log n)
 * - 정렬 필수
 * @author parkrootseok
 *
 * 1. 시작 위치와 마지막 위치를 초기화
 * 2. 중간 위치를 초기화
 * 3. 탐색값 < 중앙값 이라면, 중앙값보다 왼쪽에 위치하므로 오른쪽 범위를 축소
 * 4. 중앙값 < 탐색값 이라면, 중앙값보다 오른쪽에 위치하므로 왼쪽 범위를 축소
 * 5. 탐색에 성공했다면 위치에 대한 인덱스를 반환
 **/
public class binarySearch {

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

	public static int binarySearch(int target) {

		// 1. 시작 위치와 마지막 위치를 초기화
		int left = 0;
		int right = numbers.length - 1;

		// 시작 위치가 마지막 위치보다 작거나 같다면 계속 반복 수행
		while (left <= right) {

			// 2. 중간 지점을 초기화
			int mid = (left + right) / 2;

			// 3. 탐색값 < 중앙값 이라면, 중앙값보다 왼쪽에 위치하므로 오른쪽 범위를 축소
			if (target < numbers[mid]) {
				right = mid - 1;
			}

			// 4. 중앙값 < 탐색값 이라면, 중앙값보다 오른쪽에 위치하므로 왼쪽 범위를 축소
			else if (numbers[mid] < target) {
				left = mid + 1;
			}

			// 5. 탐색에 성공했다면 위치에 대한 인덱스를 반환
			else {
				return mid;
			}

		}

		return -1;

	}


}
