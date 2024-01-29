import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_17478_재귀함수가뭔가요?_박근석
 * @author parkrootseok
 * 
 * - 챗봇의 응답을 출력하는 프로그램
 * 
 * 1. 재귀 횟수를 입력 받는다.
 * 2. 입력 받은 횟수동안 재귀함수를 실행한다
 * 
 */

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static final String[] SENTENCE_LIST = {
		"\"재귀함수가 뭔가요?\"\n",
		"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
		"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
		"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n",
		"라고 답변하였지.\n"
	};
	
	static int tNumber;

	/**
	 * @param level 현재 출력한 횟수
	 * @param prefix 출력할 문장에 대한 prefix
	 * @throws IOException
	 */
	public static void response(int level, String prefix) throws IOException {
		
		// 모든 답변에 대하여 level만큼 prefix를 출력
		// 현재 레벨이 입력받은 재귀 횟수와 같다면 종료
		
		// 종료 조건
		if(level == tNumber) {
			bw.write(prefix + SENTENCE_LIST[0]);
			bw.write(prefix + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			bw.write(prefix + SENTENCE_LIST[4]);
			return;
		}
		
		// 전 처리 로직
		for(int sentenceIdx = 0; sentenceIdx < 4; sentenceIdx++) {
			bw.write(prefix + SENTENCE_LIST[sentenceIdx]);
		}
		
		// 재귀 함수 호출
		response(level + 1, prefix + "____");
		
		// 후 처리 로직
		bw.write(prefix + SENTENCE_LIST[4]);
		
	}
	
	public static void main(String[] agrs) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수 입력
		tNumber = Integer.parseInt(br.readLine().trim());
		
		// 2. 입력 받은 횟수동안 재귀함수를 실행한다
		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		response(0, "");
		
		bw.close();
		return;
		
	}
	
}