import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * BOJ_15686_치킨배달
 * @author parkrootseok
 * 
 * 치킨 거리 : 집과 가장 가까운 치킨집 거리
 * 도시의 치킨 거리 : 모든 집의 치킨 거리의 합
 * 최소 도시의 치킨 거리 : 모든 집의 치킨 거리의 합이 최소
 * 
 * 1. 도시의 크기를 받는다.
 * 2. 도시에 대한 정보를 받는다 (0 : 빈 칸, 1 : 집, 2 : 치킨집)
 * 3. 모든 치킨집에서 M개를 뽑아 조합을 만든다
 *  3-1. 조합이 완성되면 최소의 도시 치킨 거리를 구한다.
 */

class Home {

	int x;
	int y;

	Home(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

class Shop {

	int x;
	int y;

	Shop(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class Main {
	
	public static int getDistance(int x1, int y1, int x2, int y2) {

		return Math.abs(x1 - x2) + Math.abs(y1 - y2);

	}
	
	public static void dfs(int level, int start) {
		
		// 3-1. 조합이 완성되면 최소의 도시 치킨 거리를 구한다.
		if (level == M) {	// 조합 완성
			
			int sum = 0;
			
			for (Home h : homes) {	// 각 집에 대하여
				
				int dis = Integer.MAX_VALUE; 
				
				for (Shop s : combi) {	// 가게와의 최소 거리를 구하고(즉, 치킨 거리를 구한다)
				
					dis = Math.min(dis, getDistance(h.x, h.y, s.x, s.y));
					
				}
				
				sum += dis;	// 최소 거리를 더한다.
				
			}
			
			// M개 가게들의 도시 치킨 거리를 비교하여 최소값으로 초기화
			answer = Math.min(answer, sum);
			return;
			
		}
		
		for(int index = start; index < shops.size() ; index++) {
			combi[level] = shops.get(index);
			dfs(level + 1, index + 1);
		}
		
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static final int HOME = 1;
	static final int SHOP = 2;

	static int answer;
	static int N;
	static int M;
	
	static List<Home> homes = new ArrayList<>();
	static List<Shop> shops = new ArrayList<>();
	static Shop[] combi;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		//	1. 도시의 크기와 폐업하지 않을 치킨집 갯수를 받는다
		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		// 2. 도시에 대한 정보를 받는다 (0 : 빈 칸, 1 : 집, 2 : 치킨집)
		int type;
		for (int row = 1; row <= N; row++) {

			inputs = br.readLine().split(" ");

			for (int cols = 1; cols <= N; cols++) {
				
				type = Integer.parseInt(inputs[cols - 1]);
				
				if (type == HOME) {
					homes.add(new Home(row, cols));
				}
				
				if (type == SHOP) {
					shops.add(new Shop(row, cols));
				}
				
			}

		}

		// 3. M개의 중복없는 치킨집 조합을 만든다.
		answer = Integer.MAX_VALUE;
		combi = new Shop[M];
		dfs(0, 0);


		sb.append(answer).append("\n");
		bw.write(sb.toString());
		bw.close();

		return;

	}

}