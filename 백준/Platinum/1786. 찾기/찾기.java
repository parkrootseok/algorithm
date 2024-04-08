import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * goal: pattern이 text에 등장하는 index를 출력
 * 
 * 0. 정리할 데이터 및 함수
 * 	0-1. ansCnt: 패턴이 몇번 등장하는지를 저장할 변수
 * 	0-2. ansList: 패턴이 등장하는 인덱스를 저장할 int[]
 * 	0-3. preprocessing(): 이동경로 테이블 moveTable을 생성하는 메소드
 * 	0-4. textLength: 본문길이
 * 	0-5. patternLength: 패턴길이
 * 
 * 
 * 1. 입력
 * 	1-1. text
 * 	1-2. pattern
 * 
 * 2. KMP 알고리즘 구현
 * 	2-1. 이동경로 테이블 생성
 * 	2-2. 본문포인터 textPointer, 패턴포인터 patternPointer
 * 	2-3. textPointer를 textLength길이까지 1씩 증가시키며 반복
 * 		2-3-1. 본문과 패턴의 문자가 같으면 다음 문자 확인
 * 		2-3-2. 본문과 패턴의 문자가 다르면 patternPointer를 이동경로테이블 기준으로 옮기기
 * 		2-3-3. j가 patternLength까지 증가하면 ansCnt+1, ansList[ansCnt]=textPointer
 * 
 * 
 * 
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int ansCnt;
	static List<Integer> ansList;
	
	static String text;
	static String pattern;
	static int textLength;
	static int patternLength;
	static int textPointer=0;
	static int patternPointer=0;
	
	static int[] moveTable;
	
	public static int[] preprocessing(int[] moveTable) {
		// prefixPointer = 접두사 인덱스j, suffixPointer = 접미사 인덱스i
	    int prefixPointer = 0;
	    
	    // 패턴을 처음부터 돌면서 체크 
	    for (int suffixPointer = 1; suffixPointer < patternLength; suffixPointer++) { 
	        // 만약 반복되는 패턴이 없으면, prefixPointer = 0 까지 돌아감 
	        while(prefixPointer > 0 && pattern.charAt(suffixPointer) != pattern.charAt(prefixPointer)) {
	        	prefixPointer = moveTable[prefixPointer-1];
	        }
	        
	        // 일치할때, 접두사의 길이 저장 
	        // 현재 맞은 idx의 +1은 길이이자, 다음 체크할 idx가 됨
	        if (pattern.charAt(suffixPointer) == pattern.charAt(prefixPointer)) {
	        	prefixPointer++;
	        	//suffixPointer++;
	        	moveTable[suffixPointer] = prefixPointer;
	        }
	    }
	    
	    return moveTable;
	}
	
	
	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		text = br.readLine();
		pattern = br.readLine();
		textLength = text.length();
		patternLength = pattern.length();
		ansCnt=0;
		ansList = new ArrayList<Integer>();
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
//		System.out.println(text);
//		System.out.println(pattern);
		
		//KMP알고리즘 구현
		//1. 이동경로 테이블 생성
		moveTable = new int[patternLength];
		moveTable = preprocessing(moveTable);
//		for(int idx=0; idx<patternLength; idx++) {
//			System.out.print(moveTable[idx]+" ");
//		}
//		System.out.println();
		
		
		//2. 탐색
		while(textPointer<textLength) {
			//2-1. 본문과 패턴의 문자가 같으면 다음 문자 확인
			//patternPointer==0 || 
			if(text.charAt(textPointer)==pattern.charAt(patternPointer)) {
				textPointer++;
				patternPointer++;
			}
			else if(patternPointer==0) {
				textPointer++;
			}
			//2-2. 본문과 패턴의 문자가 다르면 patternPointer를 이동경로테이블 기준으로 옮기기
			else {
				patternPointer=moveTable[patternPointer-1];
			}
			//2-3. patternPointer가 patternLength까지 증가하면 ansCnt+1, ansList[ansCnt]=textPointer
			if(patternPointer==patternLength) {
				ansCnt+=1;
				ansList.add(textPointer-(patternLength-1));
				
				patternPointer=moveTable[patternPointer-1];

				
				
			}
		}
		
		//출력
		System.out.println(ansCnt);
		for(int idx=0; idx<ansList.size(); idx++) {
			System.out.print(ansList.get(idx)+" ");
		}
	}
}
