import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

/**
 * BOJ_1786_찾기
 * @author parkrootseok
 * 
 * - KMP 알고리즘 
 *  
 * 1. 문자열과 찾을 패턴을 받는다.
 * 2. 패턴의 부분 문자열 중 접두사와 접미사가 일치하는 길이에 대한 정보를 생성
 * 3. 전처리한 정보를 통해 패턴이 일치하는 횟수와 나타나는 위치의 시작점 찾는다.
 **/
public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;
	
	static String origin;
	static String pattern;
	
	static int[] table;
	static int count;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 문자열과 찾을 패턴을 받는다. 
		origin = br.readLine();
		pattern = br.readLine();
		
		// 2. 패턴의 부분 문자열 중 접두사와 접미사가 일치하는 길이에 대한 정보를 생성
		init();
		
		// 3. 전처리한 정보를 통해 패턴이 일치하는 횟수와 나타나는 위치의 시작점 찾는다.
		KMP();
		
		bw.write(count + "\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}
	
	public static void init() {
		
		table = new int[pattern.length()];
		
		int prefixIdx = 0;
		for (int suffixIdx = 1; suffixIdx < pattern.length(); suffixIdx++) {
			
			// 마지막 문자와 접두사의 첫 문자가 다를 경우
			while(0 < prefixIdx && pattern.charAt(suffixIdx) != pattern.charAt(prefixIdx)) {
				
				// 현재 문자열 길이보다 짧은 길이를 가지는 부븐 일치 문자열의 마지막으로 이동
				prefixIdx = table[prefixIdx - 1];
			}
			
			// 마지막 문자와 접두사의 첫 문자가 같을 경우 테이블에 부븐 일치 문자열 길이를 저장
			if (pattern.charAt(suffixIdx) == pattern.charAt(prefixIdx)) {
				table[suffixIdx] = ++prefixIdx;
			}
			
		}
		
		return;
		
	} 
	
	public static void KMP() {
		
		count = 0;
		int patternIdx = 0;
		for (int originIdx = 0; originIdx < origin.length(); originIdx++) {
			
			// 본문에서의 마지막 문자와 접두사의 첫 문자가 다를 경우
			while(0 < patternIdx && origin.charAt(originIdx) != pattern.charAt(patternIdx)) {
				
				// 현재 문자열 길이보다 짧은 구간의 마지막 문자열로 이동
				patternIdx = table[patternIdx - 1];
			}
			
			// 본문에서의 마지막 문자와 접두사의 첫 문자가 같을 경우
			if (origin.charAt(originIdx) == pattern.charAt(patternIdx)) {
				
				// 패턴 문자열에서 마지막 이라면 카운팅 후 시작 위치를 기록
				if (patternIdx == pattern.length() - 1) {
					
					count++;
					sb.append((originIdx - patternIdx + 1) + " ");
					
					// 패턴 문자열의 마지막 문자를 부분 문자열로 가지는 부분 문자열의 시작점으로 이동 
					patternIdx = table[patternIdx];
					
				}
				
				// 패턴 문자열에서 마지막이 아니라면 다음 문자로 이동
				else {
					patternIdx++;
				}
			
			}
			
		}
		
	}
	
}