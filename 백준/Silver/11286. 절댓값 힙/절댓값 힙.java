import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_11286_절댓값힙
 * @author parkrootseok
 * 
 * - 배열에 정수 x 삽입
 * - 배열에서 절대값이 가장 작은 값 출력 후 제거
 * 
 * 1. 연산의 개수 입력
 * 2. 연산의 갯수만큼 연산 정보 입력(0이면 가장 작은 값 출력 후 제거, 아니라면 삽입)
 * 
 */

class Heap {

	int[] datas;
	int index;

	public Heap(int size) {
		index = 0;
		this.datas = new int[size];
	}

	public void add(int data) {

		datas[++index] = data;
		up(index);

	}

	private void up(int curIndex) {

		int parent = curIndex / 2;

		// 현재 인덱스가 루트 노드이면 종료
		if (parent < 1) {
			return;
		}

		// 부모 노드보다 절대값이 크다면 종료
		if (Math.abs(datas[parent]) < Math.abs(datas[curIndex])) {
			return;
		}

		// 절대값이 같고 데이터가 더 크다면 종료
		else if (Math.abs(datas[parent]) == Math.abs(datas[curIndex]) && datas[parent] < datas[curIndex]) {
			return;
		}

		// 절대값이 작거나 같고 데이터가 더 작다면 교환
		int child = datas[curIndex];
		datas[curIndex] = datas[parent];
		datas[parent] = child;

		up(parent);

	}

	public int remove() {

		// 데이터가 존재하지 않으면 종료
		if (isEmpty()) {
			return 0;
		}

		// 루트 노드의 값을 저장
		int root = datas[1];

		// 루트 노드에 가장 마지막에 위치한 데이터 삽입
		datas[1] = datas[index];

		// 루트 노드부터 다시 힙을 정렬
		down(1);
		index--;

		return root;
	}

	private void down(int curIndex) {

		int lChild = curIndex * 2;
		int rChild = lChild + 1;

		// 자식 노드가 존재하지 않으면 종료
		if (lChild > index) {
			return;
		}

		// 좌, 우 자식 노드 중 절대값과 데이터가 가장 작은 노드에 대한 인덱스를 검색
		int smaller = lChild;
		if (rChild <= index) {

			// 절대값이 작다면 변경
			if (Math.abs(datas[smaller]) > Math.abs(datas[rChild])) {
				smaller = rChild;
			}

			// 절대값이 같고 데이터가 작아도 변경
			else if (Math.abs(datas[smaller]) == Math.abs(datas[rChild]) && datas[smaller] > datas[rChild]) {
				smaller = rChild;
			}

		}

		// 현재 노드의 절대값이 더 작다면 종료
		if (Math.abs(datas[smaller]) > Math.abs(datas[curIndex])) {
			return;
		}

		// 현재 노드의 데이터가 더 작아도 죵료
		else if (Math.abs(datas[smaller]) == Math.abs(datas[curIndex]) && datas[smaller] > datas[curIndex]) {
			return;
		}

		// 가장 작은 노드와 현재 노드를 교환
		int child = datas[smaller];
		datas[smaller] = datas[curIndex];
		datas[curIndex] = child;

		// 교환한 위치부터 다시 힙 정렬 진행
		down(smaller);

	}

	private boolean isEmpty() {

		// 데이터가 존재하지 않으면 종료
		if (index == 0) {
			return true;
		}

		return false;

	}

}

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int operatorNumber;
	static int operator;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 연산의 개수 입력
		operatorNumber = Integer.parseInt(br.readLine().trim());

		// 2. 연산의 갯수만큼 연산 정보 입력(0이면 가장 작은 값 출력 후 제거, 아니라면 삽입)
		Heap heap = new Heap(operatorNumber + 1);
		for (int curNumber = 0; curNumber < operatorNumber; curNumber++) {

			operator = Integer.parseInt(br.readLine().trim());

			if (operator == 0) {
				sb.append(heap.remove()).append("\n");
			} else {
				heap.add(operator);
			}

		}

		sb.append("\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}

}