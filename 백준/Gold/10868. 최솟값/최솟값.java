import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_10868_최솟값
 * @author parkrootseok
 *
 * N개의 정수들 중 A번 부터 B번 정수 사이에 최솟값을 구해라
 *
 * 1. 정수의 개수와 구간이 주어지는 횟수 입력
 * 2. 정수 입력
 * 3. 세그먼트 트리 초기화
 */
public class Main {

	public static class SegmentTree {

		int size;
		int[] element;

		public SegmentTree() {
			this.size = (int) Math.pow(2, (int) Math.ceil(Math.log(numberSize) / Math.log(2)) + 1);
			this.element = new int[size];
		}

		public int init(int curNode, int start, int end) {

			// 리프 노드라면 자기 자신을 저장
			if (start == end) {
				return element[curNode] = numbers[start];
			}

			// 리프 노드가 아니라면 좌, 우 노드를 탐색
			int mid = (start + end) / 2;
			return element[curNode] = Math.min(
				init(curNode * 2, start, mid),
				init(curNode * 2 + 1, mid + 1, end)
			);

		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;;

	public static SegmentTree segmentTree;
	public static int numberSize;
	public static int rangeNumber;
	public static int[] numbers;
	public static int minValue;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정수의 개수와 구간이 주어지는 횟수 입력
		inputs = br.readLine().trim().split(" ");
		numberSize = Integer.parseInt(inputs[0]);
		rangeNumber = Integer.parseInt(inputs[1]);

		// 2. 정수 입력
		numbers = new int[numberSize + 1];
		for (int index = 1; index <= numberSize; index++) {
			numbers[index] = Integer.parseInt(br.readLine().trim());
		}

		// 3. 세그먼트 트리 초기화
		segmentTree = new SegmentTree();
		segmentTree.init(1, 1, numberSize);

		// 3. 구간을 받고 최솟값 구하기
		for (int curRangeNumber = 0 ; curRangeNumber < rangeNumber; curRangeNumber++) {

			inputs = br.readLine().trim().split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);

			minValue = Integer.MAX_VALUE;

			if (a == 1 && b == numberSize) {
				minValue = segmentTree.element[1];
			}

			else {
				getMinValue(1, 1, numberSize, a, b);
			}

			sb.append(minValue).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int getMinValue(int curNode, int start, int end, int targetStart, int targetEnd) {

		// 범위를 벗어난 경우 종료
		if (targetStart > end || targetEnd < start) {
			return Integer.MAX_VALUE;
		}

		// 범위 안에 들어올 경우 최솟값을 갱신 후 종료
		if (targetStart <= start && end <= targetEnd) {
			return segmentTree.element[curNode];
		}

		int mid = (start + end) / 2;
		return minValue = Math.min(
			getMinValue(curNode * 2, start, mid, targetStart, targetEnd),
			getMinValue(curNode * 2 + 1, mid + 1, end, targetStart, targetEnd)
		);

	}

}