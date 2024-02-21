import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/***
 * SWEA_3289_서로소집합
 * @author parkrootseok
 * 
 * - n개의 집합이 주어지고
 * - 합집합 연산과 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산 수행
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 집합의 갯수와 연산 횟수를 받는다.
 * 3. 집합을 초기화
 * 4. 연산 횟수만큼 연산에 대한 정보를 받아 수행한다(합집합 : 0, 같은 집합 확인 : 1)
 * 5. 연산 정보를 받아 연산을 수행
 *  5-1. 합집합 실행
 *  5-2. 같은 집합 원소인지 확인 하는 것은 결과를 출력(같지 않음 : 0, 같음 : 1)
 ***/

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int tcNumber;
	
	static int[] set;
	static int[] rank;
	
	static int elementNumber;
	static int commandNumber;
	
	public static void make(int elementNumber) {
		
		set = new int[elementNumber + 1];
		rank = new int[elementNumber + 1];
		for(int curElement = 1; curElement <= elementNumber; curElement++) {
			set[curElement] = curElement;
		}
		
	}
	
	public static void union(int elementA, int elementB) {
		
		int representationA = find(elementA);
		int representationB = find(elementB);
		
		// 두 원소의 대표가 같다면 이미 같은 집합
		if(representationA == representationB) {
			return;
		}
		
		// 아니라면 둘을 합집합 수행
		if(rank[representationA] > rank[representationB]) {
			// A가 속한 집합의 랭크가 더 같거나 높은 경우 B를 A 밑으로
			set[representationB] = set[representationA];
			return;
		} 
		
		// B가 속한 집합의 랭크가 더 같거나 높은 경우 A를 B 밑으로
		set[representationA] = set[representationB];
			
		// 단, 두 합집합의 랭크가 같다면 밑으로 들어가게 될 집합의 랭크를 상승해야한다.
		if(rank[representationA] == rank[representationB]) {
			rank[representationB]++;
		}
		
	}
	
	public static int find(int element) {
		
		// 대표 노드 라면
		if(element == set[element]) {
			return element;
		}
		
		// 대표 노드가 아니라면 찾으러 간다
		return set[element] = find(set[element]);
		
	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine());

		for (int curTestcase = 1; curTestcase <= tcNumber; curTestcase++) {

			sb.append("#").append(curTestcase).append(" ");
			
			// 2. 집합의 갯수와 연산 횟수를 받는다.
			inputs = br.readLine().trim().split(" ");
			elementNumber = Integer.parseInt(inputs[0]);
			commandNumber = Integer.parseInt(inputs[1]);
			
			// 3. 집합을 초기화
			make(elementNumber);
			
			// 4. 연산 횟수만큼 연산에 대한 정보를 받아 수행한다(합집합 : 0, 같은 집합 확인 : 1)
			int cmd, elementA, elementB;
			for(int curCommand = 0; curCommand < commandNumber; curCommand++) {
				
				// 5. 연산 정보를 받아 연산을 수행
				inputs = br.readLine().trim().split(" ");
				cmd = Integer.parseInt(inputs[0]);
				elementA = Integer.parseInt(inputs[1]);
				elementB = Integer.parseInt(inputs[2]);
				
				//  5-1. 합집합 실행
				if(cmd == 0) {
					union(elementA, elementB);
				}
				
				//  5-2. 같은 집합 원소인지 확인 하는 것은 결과를 출력(같지 않음 : 0, 같음 : 1)
				else {
					
					// 두 원소의 대표 원소가 같다면 합집합
					if(find(elementA) == find(elementB)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
					
				}
				
			}
			
			sb.append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}