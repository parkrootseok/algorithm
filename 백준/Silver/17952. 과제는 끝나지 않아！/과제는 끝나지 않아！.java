import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * 문제2_업무평가
 * @author 박근석
 *
 * - 업무가 너무 많음
 * - 업무를 아래 규칙대로 처리함
 *  - 최근에 주어진 순서대로 실행(업무를 받으면 바로 시작)
 *  - 새로운 업무가 추가되면 중단하고 새로운 업무를 시작
 *  - 업무가 끝났다면 이전에 하던 업무를 다시 이어서 시작
 *  
 * 1. 몇 분인지 입력
 * 2. N분째에 주어진 업무의 정보를 입력
 *  2-1. 0이 아니면 업무를 받음
 * 3. 업무가 존재하면 최근에 주어진 업무를 수행
 *  3-1. 업무를 수행 후 완료가 되었다면 평가 점수에 누적합
 *  3-2. 완료가 되지 않았다면 다시 업무에 넣는다.
 **/

public class Main {
	
	static class Work {
		
		int score;
		int time;
		
		Work(int score, int time) {
			this.score = score;
			this.time = time;
		}
				
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int totalMinute;
	static int totalScore;
	static Stack<Work> works;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 1. 몇 분인지 입력
		totalMinute = Integer.parseInt(br.readLine().trim());
	
		// 2. N분째에 주어진 업무의 정보를 입력
		works = new Stack<>();
		for(int curMinute = 1; curMinute <= totalMinute; curMinute++) {
			
			inputs = br.readLine().trim().split(" ");
			
			// 2-1. 0이 아니면 업무를 받음
			if(!inputs[0].equals("0")) {
				works.push(new Work(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
			}
			
			// 3. 업무가 존재하면 가장 최근에 주어진 업무를 수행
			doWork();
			
		}
		
		sb.append(totalScore).append("\n");
		bw.write(sb.toString());
		bw.close();
		
	}
	
	public static void doWork() {
		
		if(!works.isEmpty()) {
			
			Work lastestWork = works.pop();
			
			// 3-1. 업무를 수행 후 완료가 되었다면 평가 점수에 누적합 후 종료
			if(--lastestWork.time == 0) {
				totalScore += lastestWork.score;
				return;
			}
			
			// 3-2. 완료가 되지 않았다면 다시 업무에 넣는다.
			works.push(lastestWork);		
			
		}
		
	}
	
}