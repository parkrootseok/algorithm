import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/***
 * BOJ_13023_ABCDE
 * @author parkrootseok
 * 
 * - 사람들에게 0번 부터 N - 1번호 부여
 * - 사람들 특정 관계를 가지고 있는지 확인하는 프로그램 작성
 * - 특정 관계는 A - B, B - C, C - D, D - E
 * - 즉, 모든 친구들이 하나의 집합에 속해 있어야 한다.
 * 
 * 1. 사람의 수와 관계의 수를 받는다
 * 2. 친구 관계를 받아 이를 맺어준다.
 * 3. 친구 관계를 확인해 특정 조건 관계와 동일한지 확인한다
 *  3-1. 탐색 높이가 4라면 조건 만족
 * 4. 결과를 출력
 ***/

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int personNumber;
	static int relationNumber;
	
	static List<List<Integer>> friends;
	static boolean[] isVisited;
	static int result;
	
	public static void dfs(int friend, int level) {
		
		// 3-1. 탐색 높이가 4라면 조건 만족
		if(level == 4) {
			result = 1;
			return;
		}
		
		for(int curFriend : friends.get(friend)) {
			
			if(!isVisited[curFriend]) {
				isVisited[friend] = true;
				dfs(curFriend, level + 1);
				isVisited[friend] = false;
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 사람의 수와 관계의 수를 받는다
		inputs = br.readLine().trim().split(" ");
		personNumber = Integer.parseInt(inputs[0]);
		relationNumber = Integer.parseInt(inputs[1]);
		
		// 2. 초기 관계를 설정
		friends = new ArrayList<List<Integer>>();
		for(int curFriend = 0; curFriend < personNumber; curFriend++) {
			
			friends.add(new ArrayList<>());
			
		}
		
		// 2. 친구 관계를 받아 이를 맺어준다.
		int friendA, friendB;
		for(int curRelation = 0; curRelation < relationNumber; curRelation++) {
			
			inputs = br.readLine().trim().split(" ");
			friendA = Integer.parseInt(inputs[0]);
			friendB = Integer.parseInt(inputs[1]);
			
			friends.get(friendA).add(friendB);
			friends.get(friendB).add(friendA);
			
		}
		
		// 3. 친구 관계를 확인해 특정 조건 관계와 동일한지 확인한다
		isVisited = new boolean[personNumber];
		for(int curFriend = 0; curFriend < personNumber; curFriend++) {
			
			isVisited[curFriend] = true;
			dfs(curFriend, 0);
			isVisited[curFriend] = false;
			
			if(result == 1) {
				break;
			}

		}
		
		sb.append(result).append("\n");
		bw.write(sb.toString());
		bw.close();

		return;

	}

}