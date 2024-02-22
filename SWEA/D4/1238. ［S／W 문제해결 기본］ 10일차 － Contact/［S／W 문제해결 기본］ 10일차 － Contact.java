import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * SWEA_1238_Contact
 * @author parkrootseok
 * 
 * - 비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때 가장 나중에 연락을 받게 되는 사람중 가장 번호가 큰 사람은?
 * 
 * 1. 비상연락망 정보와 연락을 시작할 사람에 대한 정보를 받는다.
 * 2. 연락망 정보를 초기화한다.
 * 3. 총 연락을 수행할 사람의 명수를 구하자
 * 4. BFS를 실행
 **/

class Solution {

	static class Contact {

		List<Integer> tel;

		public Contact() {
			this.tel = new ArrayList<>();
		}

	}

	static final int TOTAL_PERSON_NUMBER = 100;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int tcNumber = 10;

	static int length;
	static int start;
	
	static Contact[] contact;
	static boolean[] isSent;
	static boolean[] inContact;
	static int[] personLevel;
	static int totalPerson;
	static int maxPerson;
	
	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		for (int curTestcase = 1; curTestcase <= tcNumber; curTestcase++) {

			// 1. 비상연락망 정보와 연락을 시작할 사람에 대한 정보를 받는다.
			inputs = br.readLine().trim().split(" ");
			length = Integer.parseInt(inputs[0]);
			start = Integer.parseInt(inputs[1]);
			
			// 2. 연락망 정보를 초기화한다.
			contact = new Contact[TOTAL_PERSON_NUMBER + 1];
			for(int index = 0; index <= TOTAL_PERSON_NUMBER; index++) {
				contact[index] = new Contact();
			}
			
			inContact = new boolean[TOTAL_PERSON_NUMBER + 1];
			inputs = br.readLine().trim().split(" ");
			for(int index = 0; index < length / 2; index++) {
				
				int from = Integer.parseInt(inputs[2 * index]);
				int to = Integer.parseInt(inputs[2 * index + 1]);;
				
				inContact[from] = true;
				inContact[to] = true;
				
				if(!contact[from].tel.contains(to)) {
					contact[from].tel.add(to);
				}
				
			}
			
			// 3. 총 연락을 수행할 사람의 명수를 구하자
			for(boolean tel : inContact) {
				if(tel) {
					totalPerson++;
				}
			}
			
			// 4. BFS를 실행
			maxPerson = Integer.MIN_VALUE;
			personLevel = new int[TOTAL_PERSON_NUMBER + 1];
			isSent = new boolean[TOTAL_PERSON_NUMBER + 1];
			bfs();
			
			// 5. 최대 레벨을 가지고 있는 사람들의 번호를 조사하여 최대값을 찾는다.
			int level = 0;
			for(int person = 1; person <= TOTAL_PERSON_NUMBER; person++) {
				
				// 현재 레벨보다 높으면 
				if(level < personLevel[person]) {
					level = personLevel[person];
					maxPerson = person;
				}
				
				// 같다면 더 높은 숫자를 기록
				else if (level == personLevel[person] && maxPerson < person) {
					maxPerson = person;
				}

			}
			
			sb.append("#").append(curTestcase).append(" ").append(maxPerson).append(" ").append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}
	
	public static void bfs() {
		
		Queue<Integer> contactQ = new ArrayDeque<>();;
		
		// 4-1. 시작 순서로 받은 사람부터 큐에 삽입 후 탐색 시작
		int level = 0;
		contactQ.add(start);
		isSent[start] = true;
		
		while(!contactQ.isEmpty()) {
			
			// 4-2. 현재 연락을 취해야할 사람이 몇명인지 구하여 모두 수행
			int curSize = contactQ.size();
			level++;
			
			for(int size = 0; size < curSize; size++) {
				
				int person = contactQ.poll();
				Contact curContact = contact[person];
				personLevel[person] = level;
				
				for(int tel : curContact.tel) {
					
					// 4-3. 연락받은적 있다면 패스
					if(isSent[tel]) {
						continue;
					}
					
					// 4-4. 연락받은 적 없다면 큐에 삽입하여 다음에 연락하도록 한다.
					isSent[tel] = true;
					contactQ.add(tel);
					
				}
				
			}
			
			
		}
		
		
	}

}