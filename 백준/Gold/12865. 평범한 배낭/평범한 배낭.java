import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * goal: k무게를 담을 수 있는 배낭에 넣을 수 있는 물건들의 최대가치 구하기
 * 
 * 1. 입력
 * 2. 물건 1~n을 고려할 때 특정 무게의 배낭에 담을 수 있는 최대가치를 담을 2차원 배열
 * 	- arr[n+1][k+1] 생성
 * 3. 점화식
 * 	- n번째까지 물건들을 w용량 배낭에 담는 최적해
 * 	-- n번째 물건을 배낭의 용량 w에 담을 수 있다면, 둘 중 더 큰 값 선정
 * 		--- n-1번째까지 물건들을 w배낭에 담는 최적해
 * 		--- n-1번째까지 물건들을 w-n번째물건무게 배낭에 담는 최적해 + n번째물건가치
 *  -- n번째 물건을 배낭의 용량 w에 담을 수 없다면
 *  	--- n-1번째까지 물건들을 w배낭에 담는 최적해
 * 
 */


public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int n, k;
	static int[] weight;
	static int[] value;
	
	static int[][] arr;
	
	
	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		//물건의 개수
		n = Integer.parseInt(st.nextToken());
		//배낭의 용량
		k = Integer.parseInt(st.nextToken());
		//각 물건의 무게와 가치
		weight = new int[n+1];
		value = new int[n+1];
		
		for(int idx=1; idx<=n; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			weight[idx]=Integer.parseInt(st.nextToken());
			value[idx]=Integer.parseInt(st.nextToken());
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		
		//최적해들을 담을 2차원 배열 생성
		arr = new int[n+1][k+1];
		
		//0번째 물건 초기화
		for(int idx=0; idx<=k; idx++) {
			arr[0][idx]=0;
		}
		//배낭의 용량이 0인 경우 초기화
		for(int idx=0; idx<=n; idx++) {
			arr[idx][0]=0;
		}
		
		//최적해 구하기
		for(int item=1; item<=n; item++) {
			for(int w=1; w<=k; w++) {
				if(weight[item]>w) {
					arr[item][w]=arr[item-1][w];
				}else {
					arr[item][w]=Integer.max(arr[item-1][w-weight[item]]+value[item],arr[item-1][w]);
				}
			}
		}
		
		//출력
		System.out.println(arr[n][k]);
	}
}
