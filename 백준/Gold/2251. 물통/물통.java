import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/**
 * BOJ_2251_물통
 * @author parkrootseok
 *
 * - A, B, C 물통 존재
 *  - A, B 물통은 비어있고, C 물통은 가득 차 있는 상태
 * - 물을 다른 곳에 부을 수 있는데, 한 물통이 비거나 한 뭁통이 가득 찰 때 까지 가능
 * - 첫 번째 물통이 비어있을 때 가능한 세 번째 물통에 담겨있을 수 있는 물의 양을 모두 구해라
 *
 * 1. 물통 정보 입력
 * 2. bfs 탐색을 활용하여 a 물통에 물이 없을 때 존재할 수 있는 c 물통의 양을 구하기
 */
public class Main {

	public static class Node {

		int a;
		int b;
		int c;

		public Node(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String inputs[];

	public static int[] bottles;
	public static boolean[][][] waters;
	public static ArrayList<Integer> answers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 물통 정보 입력
		inputs = br.readLine().trim().split(" ");
		bottles = new int[3];
		for (int index = 0; index < 3; index++) {
			bottles[index] = Integer.parseInt(inputs[index]);
		}

		answers = new ArrayList<>();
		waters = new boolean[bottles[0] + 1][bottles[1] + 1][bottles[2] + 1];

		// 2. bfs 탐색을 활용하여 a 물통에 물이 없을 때 존재할 수 있는 c 물통의 양을 구하기
		bfs();

		Collections.sort(answers);
		for (int index = 0; index < answers.size(); index++) {
			sb.append(answers.get(index)).append(" ");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void bfs() {

		Queue<Node> nodeQ = new ArrayDeque<>();
		nodeQ.add(new Node(0, 0, bottles[2]));

		while (!nodeQ.isEmpty()) {

			Node curNode = nodeQ.poll();
			int a = curNode.a;
			int b = curNode.b;
			int c = curNode.c;

			// 이미 만들어진 조합이라면 스킵
			if (waters[a][b][c]) {
				continue;
			}

			// 사용한 조합 체크
			waters[a][b][c] = true;

			// a 물통에 물이 없다면 기록
			if (a == 0) {
				answers.add(c);
			}

			// 물통을 이동하는 방법은 총 6가지가 존재하고, 넘칠때와 넘치지 않을 때를 구분하여 작업 수행
			// A to B
			if (a + b > bottles[1]) {
				nodeQ.add(new Node(a - (bottles[1] - b), bottles[1] , c));
			}

			else {
				nodeQ.add(new Node(0, a + b, c));
			}

			// A to C
			if (a + c > bottles[2]) {
				nodeQ.add(new Node(a - (bottles[2] - c), b , bottles[2]));
			}

			else {
				nodeQ.add(new Node(0, b, a + c));
			}

			// B to A
			if (b + a > bottles[0]) {
				nodeQ.add(new Node(bottles[0], b - (bottles[0] - a), c));
			}

			else {
				nodeQ.add(new Node(a + b, 0, c));
			}

			// B to C
			if (b + c > bottles[2]) {
				nodeQ.add(new Node(a, b - (bottles[2] - c), bottles[2]));
			}

			else {
				nodeQ.add(new Node(a, 0, c + b));
			}

			// C to A
			if (c + a > bottles[0]) {
				nodeQ.add(new Node(bottles[0], b, c - (bottles[0] - a)));
			}

			else {
				nodeQ.add(new Node(a + c, b, 0));
			}

			// C to B
			if (b + c > bottles[1]) {
				nodeQ.add(new Node(a, bottles[1], c - (bottles[1] - b)));
			}

			else {
				nodeQ.add(new Node(a, b + c, 0));
			}


		}

	}


}