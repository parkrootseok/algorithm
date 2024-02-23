import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/***
 * BOJ_1759_암호만들기
 * @author parkrootseok
 * 
 * - 암호는 서로 다른 L개 알파벳 소문자로 구성
 * - 최소 한 개의 모음, 최소 두 개의 자음
 * - 알파벳은 증가하는 순서로 배열
 * 
 * 1. 암호의 길이와 주어진 알파벳에 대한 갯수를 받는다.
 * 2. 알파벳을 입력 받고 자음, 모음으로 분리한다.
 * 3. 주어진 알파벳을 암호의 길이에 맞게 조합한다.
 *  3-1. 암호가 완성되면 자음, 모음 조건을 확인
 ***/

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static List<Character> jaum = new ArrayList<>();
	
	static char[] alphabet;
	static char[] secret;
	static int secretLenght;
	static int alphabetNumber;
	
	public static void combination(int level, int start) {
		
		// 3-1. 암호가 완성되면 자음, 모음 조건을 확인
		if(level == secretLenght) {
			
			int jaumCount = 0;
			for(char alphabet : secret) {
				if(jaum.contains(alphabet)) {
					jaumCount++;
				}
			}
			
			// 자음은 최소  1개 모음은 최소 2개로 이루어져야함
			if(!(jaumCount >= 1 && (secretLenght - jaumCount) >= 2)) {
				return;
			}
			
			for(char alphabet : secret) {
				sb.append(alphabet);
			}
			sb.append("\n");
			
			return;
		
		}
		
		for(int alphabetIndex = start; alphabetIndex < alphabetNumber; alphabetIndex++) {
			
			secret[level] = alphabet[alphabetIndex];
			combination(level + 1, alphabetIndex + 1);
			
		}
		
	}
	

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		jaum.add('a');
		jaum.add('e');
		jaum.add('i');
		jaum.add('o');
		jaum.add('u');
		
		// 1. 암호의 길이와 주어진 알파벳에 대한 갯수를 받는다.
		inputs = br.readLine().trim().split(" ");
		secretLenght = Integer.parseInt(inputs[0]);
		alphabetNumber = Integer.parseInt(inputs[1]);
		
		// 2. 알파벳을 입력 받는다.
		alphabet = new char[alphabetNumber];
		inputs = br.readLine().trim().split(" ");
		for(int index = 0; index < alphabetNumber; index++) {
			alphabet[index] = inputs[index].charAt(0);
		}
		
		// 3. 주어진 알파벳을 암호의 길이에 맞게 조합한다
		// 단, 알파벳의 순서는 오름차순이므로 미리 알파벳 배열 정렬을 수행
		Arrays.sort(alphabet);
		secret = new char[secretLenght];
		combination(0, 0);
		
		sb.append("\n");
		bw.write(sb.toString());
		bw.close();

		return;

	}

}