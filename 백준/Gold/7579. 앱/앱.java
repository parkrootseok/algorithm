import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_7579_앱
 * @author parkrootseok
 * 
 * - 스마트폰
 *  - 메모리에 앱들에 대한 상태를 유지
 *  - 이는 메모리 부족을 야기
 *  - 이를 해결하기 위해 앱을 메모리에서 제거
 *
 * - 앱
 *  - 앱은 사용 메모리(M)과 다시 실행하는 것에 대한 비용(C)를 가지고 있음
 *
 * - 앱 비활성화 로직
 *  - 메모리 옹량이 부족한 상태에서 새로운 앱을 실행
 *  - 활성화 된 앱을 종료하여 새로운 앱이 필요로하는 메모리(M) 이상 확보
 *  - 이때, 비활성화 할 앱들에 대한 비용을 최소화하여 메모리(M)을 확보
 * 
 * 1. 앱의 개수와 총 메모리 용량을 받는다.
 * 2. 앱의 메모리 정보를 받는다.
 * 3. 앱의 재실행 비용 정보를 받는다.
 * 4. DP 알고리즘을 사용하여 상향식 접근을 통해 최적값 도출
 *  4-1. 현재 코스트를 사용했을 때 확보한 메모리보다 현재 앱을 종료했을 때 확보할 수 있는 메모리가 더 크다면 갱신 
 */
public class Main {
	
	static class App {
		
		int memory;
		int cost;
		
		public App(int memory, int cost) {
			this.memory = memory;
			this.cost = cost;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}
		
	}
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int appNumber;
	static int needMemoryCapacity;
	static int maxCost;
	
	static App[] apps;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 앱의 개수와 필요한 메모리 용량을 받는다.
		inputs = br.readLine().trim().split(" ");
		appNumber = Integer.parseInt(inputs[0]);
		needMemoryCapacity = Integer.parseInt(inputs[1]);
		
		// 2. 앱의 메모리 정보를 받는다.
		apps = new App[appNumber];
		inputs = br.readLine().trim().split(" ");
		for (int idx = 0; idx < appNumber; idx++) {
			
			int m = Integer.parseInt(inputs[idx]);
			apps[idx] = new App(m, 0);
			
		}
		
		// 3. 앱의 재실행 비용 정보를 받는다.
		maxCost = 0;
		inputs = br.readLine().trim().split(" ");
		for (int idx = 0; idx < appNumber; idx++) {
	
			int c = Integer.parseInt(inputs[idx]);
			apps[idx].setCost(c);
			maxCost += c;
			
		}
		
		// 4. DP 알고리즘을 사용하여 상향식 접근을 통해 최적값 도출
		dp = new int[maxCost + 1];
		for (int curApp = 0; curApp < appNumber; curApp++) {
			
			for (int curCost = maxCost; apps[curApp].cost <= curCost; curCost--) {
				
				// 4-1. 현재 코스트를 사용했을 때 확보한 메모리보다 현재 앱을 종료했을 때 확보할 수 있는 메모리가 더 크다면 갱신 
				dp[curCost] = Math.max(dp[curCost], dp[curCost - apps[curApp].cost] + apps[curApp].memory);
			
				
			}
			
		}
		
		for (int curCost = 0; curCost <= maxCost; curCost++) {
			
			if (dp[curCost] >= needMemoryCapacity) {
				sb.append(curCost);
				break;
			}
			
		}

		bw.write(sb.toString());
		bw.close();
		return;
		
	}
 
}