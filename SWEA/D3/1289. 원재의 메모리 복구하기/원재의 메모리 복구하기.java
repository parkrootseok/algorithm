import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_1289_원재의메모리복구하기_박근석
 * @author parkrootseok
 * 
 * - 메모리 bit 중 하나를 골라 메모리의 끝까지 덮어씌우기를 반복
 * - 원래 상태로 돌아가기 위해서 몇번을 진행?
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 테스트 케이스 횟수를 반복하면서 원래 메모리 값 입력
 * 3. MSB부터 탐색 시작
 *  3-1. 가장 마지막 bit와 다르다면 카운트 후 마지막 bit값 초기화
 * 
 */

public class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	
	static int tNumber;
	static String origin;
	static char lastBit;
	static int count;

	public static void main(String[] agrs) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 1. 테스트 케이스 개수 입력
		tNumber = Integer.parseInt(br.readLine().trim());
		
		// 2. 테스트 케이스 횟수를 반복하면서 원래 메모리 값 입력
		for(int curT = 1; curT <= tNumber; curT++) {			
			
			origin = br.readLine().trim();
			
			// 3. MSB부터 탐색 시작
			lastBit = '0';
			count = 0;
			for(char curBit : origin.toCharArray()) {
				
				// 3-1. 가장 마지막 bit와 다르다면 카운트 후 마지막 bit값 초기화
				if(curBit != lastBit) {
					lastBit = curBit;
					count++;
				}
				
			}
			
			bw.write(String.format("#%d %d\n", curT, count));
			
		}
		
		bw.close();
		return;
		
	}
	
}