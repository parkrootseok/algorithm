import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_1231_중위순회
 * @author parkrootseok
 * 
 * - N개의 정점의 개수가 주어지고
 * - N개의 정점으로 이루어진 트리에 대한 정보가 주어짐
 * - 트리를 중위순회한 결과를 출력
 * 
 * 1. 각 테스트 케이스마다 정점의 개수를 받는다.
 * 2. 정점의 갯수만큼 정점의 정보를 받아 저장.
 * 3. 중위 순회를 수행
 *  3-1. 현재 노드의 왼쪽 노드를 계속 탐색
 *  3-2. 부모 노드 탐색
 *  3-3. 오른쪽 노드 탐색
 *  
 */

public class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int vertexNumber;
	static String[] vertices;
	
	public static void inOrder(int parent) {
		
		// 존재하지 않는 정점을 만나면 종료
		if (vertexNumber < parent) {
			return;
		}
		
		// 3-1. 현재 노드의 왼쪽 노드를 계속 탐색
		inOrder(parent * 2);
		
		// 3-2. 부모 노드 탐색
		sb.append(vertices[parent]);
		
		// 3-3. 오른쪽 노드 탐색
		inOrder(parent * 2 + 1);
		
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		for (int curT = 1 ; curT <= 10; curT++) {
			
			// 1. 각 테스트 케이스마다 정점의 개수를 받는다.
			vertexNumber = Integer.parseInt(br.readLine().trim());
			vertices = new String[vertexNumber + 1];
			
			// 2. 정점의 갯수만큼 정점의 정보를 받아 저장.
			for (int curVertex = 1 ; curVertex <= vertexNumber; curVertex++) {
				
				inputs = br.readLine().trim().split(" ");
				vertices[Integer.parseInt(inputs[0])] = inputs[1];
				
			}
			
			// 3. 중위 순회를 수행
			sb.append("#").append(curT).append(" ");
			inOrder(1);
			sb.append("\n");
			
		}

		bw.write(sb.toString());
		bw.close();
		return;

	}
	
}