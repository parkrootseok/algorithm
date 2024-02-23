import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SWEA_1233_사칙연산유효성검사
 * @author parkrootseok
 * 
 * - 사칙연산으로 구성되어 있는 이진 트리 존재
 * - 해당 트리를 계산 가능 여부를 판단해라
 * 
 * 1. 노드의 개수 입력
 * 2. 노드 정보 입력
 * 3. 트리 탐색
 *  3-1. 리프 노드를 찾고
 *  3-2. 리프노드가 숫자인지 확인
 *  
 */

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int nodeNumber;
	static boolean isPossible;
	static String[] node;

	public static void searchTree(int root) {

		int leftChild = root * 2;
		int rightChild = root * 2 + 1;

		// 불가능하면 종료
		if (!isPossible) {
			return;
		}

		// 3-1. 리프 노드일 때
		if (nodeNumber < leftChild && nodeNumber < rightChild) {

			// 3-2. 리프노드가 숫자인지 확인

			// 숫자라면 종료
			if (Character.isDigit(node[root].charAt(0))) {
				return;
			}

			// 숫자가 아니라면 
			else {
				// 불가능
				isPossible = false;
				return;
			}

		}

		// 리프 노드가 아니라면 계속 탐색 진행
		searchTree(leftChild);
		searchTree(rightChild);

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		for (int curTC = 1; curTC <= 10; curTC++) {

			// 1. 노드의 개수 입력
			nodeNumber = Integer.parseInt(br.readLine().trim());
			node = new String[nodeNumber + 1];

			// 2. 노드 정보 입력
			for (int curNode = 1; curNode <= nodeNumber; curNode++) {
				inputs = br.readLine().trim().split(" ");
				node[curNode] = inputs[1];
			}

			// 3. 트리 탐색
			isPossible = true;
			searchTree(1);

			sb.append("#").append(curTC).append(" ");
			if (!isPossible) {
				sb.append("0").append("\n");
			} else {
				sb.append("1").append("\n");
			}

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}