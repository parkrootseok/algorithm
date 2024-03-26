import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_12865_평범한배낭
 * @author parkrootseok
 * 
 * - 배낭
 *  - 최대 K만큼 담을 수 있음
 * 
 * - 물건
 *  - 무게(W)와 가치(V)를 가짐
 * 
 * - 목표 가치가 최대이고 무게가 K를 넘치지 않게 담아야 함
 * 
 * 1. 물건의 개수와 배낭의 제한 무게를 받는다.
 * 2. 물건에 대한 정보를 받는다
 * 3. DP 알고리즘을 사용하여 상향식 접근을 통해 제한 무게에 대한 최대 가치를 도출
 *  3-1. 현재 무게의 가치의 최적값과 현재 물건을 담았을 때의 최적값을 비교하여 최대값으로 갱신
 */
public class Main {
	
	static class Thing {
		
		int weight;
		int value;
		
		public Thing(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
	}
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int number;
	static int limit;
	
	static Thing[] things;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 물건의 개수와 배낭의 제한 무게를 받는다.
		inputs = br.readLine().trim().split(" ");
		number = Integer.parseInt(inputs[0]);
		limit = Integer.parseInt(inputs[1]);
		
		// 2. 물건에 대한 정보를 받는다
		things = new Thing[number];
		for (int idx = 0; idx < number; idx++) {
			
			inputs = br.readLine().trim().split(" ");
			int w = Integer.parseInt(inputs[0]);
			int v = Integer.parseInt(inputs[1]);
			
			things[idx] = new Thing(w, v);
			
		}
		
		// 3. DP 알고리즘을 사용하여 상향식 접근을 통해 제한 무게에 대한 최대 가치를 도출
		dp = new int[limit + 1];
		for (int curThing = 0; curThing < number; curThing++) {
			
			
			for (int curWeight = limit; things[curThing].weight <= curWeight; curWeight--) {
				
				// 3-1. 현재 무게의 가치의 최적값과 현재 물건을 담았을 때의 최적값을 비교하여 최대값으로 갱신
				dp[curWeight] 
						= Math.max(dp[curWeight], dp[curWeight - things[curThing].weight] + things[curThing].value);
				
				
			}
			
		}
		
		sb.append(dp[limit]);
		bw.write(sb.toString());
		bw.close();
		return;
		
	}
 
}