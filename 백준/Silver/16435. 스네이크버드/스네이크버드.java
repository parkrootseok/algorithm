import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * BOJ_16435_스네이크버드
 * @author parkrootseok
 * 
 * - 스네이크버드는 과일을 먹으면 길이가 1 증가
 * - 과일들은 지상과 떨어져 있음
 * - 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일만 먹을 수 있음
 * - 처음 길이가 L일때 최대 길이는?
 * 
 * 1. 과일의 갯수와 스네이크버드의 초기 길이를 입력
 * 2. 과일의 높이를 입력
 * 3. 입력받은 과일의 높이를 오름차순으로 정렬
 * 4. 오름차순으로 정렬된 과일들을 탐색
 *  4-1. 크다면 종료
 *  4-2. 스네이크버드와 같거나 작다면 먹고 길이 증가
 */

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int fruitNumber;
	static int snakeBirdLength;
	static int[] fruits;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
	
		// 1. 과일의 갯수와 스네이크버드의 초기 길이를 입력
		inputs = br.readLine().trim().split(" ");
		fruitNumber = Integer.parseInt(inputs[0]);
		snakeBirdLength = Integer.parseInt(inputs[1]);
		
		// 2. 과일의 높이를 입력
		fruits = new int[fruitNumber];
		inputs = br.readLine().trim().split(" ");
		for(int curFruit = 0; curFruit < fruitNumber; curFruit++) {
			fruits[curFruit] = Integer.parseInt(inputs[curFruit]);
		}
		
		// 3. 입력받은 과일의 높이를 오름차순으로 정렬
		Arrays.sort(fruits);
		
		
		// 4. 오름차순으로 정렬된 과일들을 탐색
		for(int fruit : fruits) {
			
			// 4-1. 크다면 종료
			if(fruit > snakeBirdLength) {
				break;
			}
			
			// 4-2. 스네이크버드와 같거나 작다면 먹고 길이 증가
			snakeBirdLength++;
			
		}
		
		sb.append(snakeBirdLength);
		bw.write(sb.toString());
		bw.close();
		
	}

}