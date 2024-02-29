import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * BOJ_1010_최단경로
 * @author parkrootseok
 * 
 * - 서쪽 사이트와 동쪽 사이트를 연결
 * - 단, 사이트에는 단 하나의 다리만 존재
 * - 다리는 최대로 많이 연결할 때 경우의 수는?
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 동, 서쪽 사이트의 갯수를 받는다.
 * 3. 연결할 수 있는 경우의 수를 구한다.
 **/

public class Main {
	
	static final int MAX_BRIDGE_NUMBER = 30;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;
	
	static int testNumber;
	static int[][] mem;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 테스트 횟수를 받는다.
		testNumber = Integer.parseInt(br.readLine().trim());
		
		// 2. 동, 서쪽 사이트의 갯수를 받는다.
		mem = new int[MAX_BRIDGE_NUMBER + 1][MAX_BRIDGE_NUMBER + 1];
		int east, west;
		for(int curTest = 0; curTest < testNumber; curTest++) {
			
			inputs = br.readLine().trim().split(" ");
			
			east = Integer.parseInt(inputs[0]);
			west = Integer.parseInt(inputs[1]);
			
			// 3. 연결할 수 있는 경우의 수를 구한다.
			sb.append(binomalCoefficient(west, east)).append("\n");
			
		}

		
		bw.write(sb.toString());
		bw.close();

	}
	
	public static int binomalCoefficient(int west, int east) {
		
		if(mem[west][east] > 0) {
			return mem[west][east]; 
		}
		
		if(west == east || east == 0) {
			return 1;
		}
		
		return mem[west][east] = binomalCoefficient(west - 1, east - 1) + binomalCoefficient(west - 1, east);
		
	}

}