import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_1068_트리
 * @author parkrootseok
 *
 * - 주어진 노드 번호를 지웠을 때 리프 노드의 개수를 구해라
 *
 * 1. 노드 개수 입력
 * 2. 노드 정보 입력
 * 3. 지울 노드 번호 입력
 * 4. 지울 노드가 루트 노드가 아니라면 dfs 탐색을 통해 리프 노드 카운팅
 */
public class Main {

	public static class Node {

		List<Integer> children;

		public Node() {
			this.children = new ArrayList<>();
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int nodeCount;
	public static Node[] nodes;
	public static int removeNodeNumber;
	public static int leafNodeCount;
	public static boolean[] isVisited;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 노드 개수 입력
		nodeCount = Integer.parseInt(br.readLine().trim());
		nodes = new Node[nodeCount];
		for (int index = 0; index < nodeCount; index++) {
			nodes[index] = new Node();
		}

		// 2. 노드 정보 입력
		int root = -1;
		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < nodeCount; index++) {

			int parent = Integer.parseInt(inputs[index]);
			if (parent != -1) {
				nodes[parent].children.add(index);
				nodes[index].children.add(parent);
			} else {
				root = index;
			}

		}

		// 3. 지울 노드 번호 입력
		removeNodeNumber = Integer.parseInt(br.readLine().trim());

		// 4. 지울 노드가 루트 노드가 아니라면 dfs 탐색을 통해 리프 노드 카운팅
		if (removeNodeNumber != root) {
			leafNodeCount = 0;
			isVisited = new boolean[nodeCount];
			dfs(root);
		}

		sb.append(leafNodeCount);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int curNodeNumber) {

		isVisited[curNodeNumber] = true;

		boolean isLeaf = true;
		for (int nextNodeNumber : nodes[curNodeNumber].children) {

			if (nextNodeNumber == removeNodeNumber || isVisited[nextNodeNumber]) {
				continue;
			}

			isLeaf = false;
			dfs(nextNodeNumber);

		}

		if (isLeaf) {
			leafNodeCount++;
		}

	}

}