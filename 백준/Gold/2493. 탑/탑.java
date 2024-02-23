import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * BOJ_2493_탑
 * @author parkrootseok
 * 
 * - N개의 높이가 다른 탑을 세우고, 레이저 송신기 설치
 * - 레이저는 왼쪽 방향으로 발사하고, 가장 먼저 만나는 탑에서만 수신 가능
 * - 단, 해당하는 탑은 자기 탑보다 높이가 같거나 높아야함
 * 
 * 1. 탑의 개수를 입력 받는다.
 * 2. 탑에 대한 높이 정보를 받는다.
 *  2-1. 이전 탑에 대한 정보가 있고
 *   2-1-1. 이전 탑의 높이가 현재보다 크다면
 *   2-1-2. 이전 탑의 높이가 현재보다 작다면
 *  2-2. 이전 탑에 대한 정보가 없다면 0을 출력
 * 
 */
class Top {
	
	int position;
	int height;
	
	public Top(int position, int height) {
		this.position = position;
		this.height = height;
	}

}

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int topNumber;
	static Top[] tops;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 탑의 개수를 입력 받는다.
		topNumber = Integer.parseInt(br.readLine().trim());

		// 2. 탑에 대한 높이 정보를 받는다.
		Stack<Top> prevTops = new Stack<>();
		inputs = br.readLine().trim().split(" ");
		
		for (int idx = 1; idx <= topNumber; idx++) {
					
			Top curTop = new Top(idx, Integer.parseInt(inputs[idx - 1]));
			
			// 2-1. 이전 탑에 대한 정보가 있고
			while(!prevTops.isEmpty()) {
				
				// 2-1-1. 이전 탑의 높이가 현재보다 크다면
				if(prevTops.peek().height >= curTop.height) {
					// 위치 출력 후 종료
					sb.append(prevTops.peek().position).append(" ");
					break;
				}
				
				// 2-1-2. 이전 탑의 높이가 현재보다 작다면 삭제
				prevTops.pop();
				
			}
			
			// 2-2. 이전 탑에 대한 정보가 없다면
			if(prevTops.isEmpty()) {
				//  0을 출력
				sb.append(0).append(" ");
			} 
			
			prevTops.push(curTop);
			
		}
		
		bw.write(sb.toString());
		bw.close();
		return;

	}

}