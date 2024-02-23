import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SWEA_1228_암호문1
 * @author parkrootseok
 * 
 * - 주어진 명령어에 따라 기존 암호문을 수정
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 원본 암호문 입력
 * 3. 명령어 갯수 입력
 * 4. 명령어 입력
 * 5. 명령어 수행
 * 6. 암호문 수정 후 첫 10개만 출력
 *  
 */

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int tcNumber;
	static int commandNumber;
	static List<String> secrets;
	static String[] command;
	
	public static void runCommand() {
		
		int start, end;
		
        for (String cmd : command) {
        	
        	// 명령을 " "를 구분자로 하여 분리
            String[] splitCommand = cmd.split(" ");

            // start위치 부터 end개 삽입
            start = Integer.parseInt(splitCommand[0]);
            end = Integer.parseInt(splitCommand[1]);

            for (int commandIdx = 2 ; commandIdx < end + 2; start++, commandIdx++) {
                secrets.add(start, splitCommand[commandIdx]);
            }

        }

        return;

    }

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();


		 for (int curTC = 1; curTC <= 10; curTC++) {
	               
	    		// 1. 테스트 케이스 횟수 입력
	            tcNumber = Integer.parseInt(br.readLine().trim());
	            
	            // 2. 원본 암호문 입력
	            inputs = br.readLine().trim().split(" ");
	            secrets = new ArrayList<>();
	            for (String input : inputs) {
	                secrets.add(input);
	            }

	            // 3. 명령어 갯수 입력
	            commandNumber = Integer.parseInt(br.readLine());

	            // 4. 명령어 입력
	            command = br.readLine().split("I ");
	            command = Arrays.copyOfRange(command, 1, command.length);

	            // 5. 명령어 수행
	            runCommand();       
	            
	            // 6. 암호문 수정 후 첫 10개만 출력
	            sb.append("#").append(curTC).append(" ");
	            for (int index = 0; index < 10; index++) {
	                sb.append(secrets.get(index)).append(" ");
	            }
	            sb.append("\n");
	            
	        }

		bw.write(sb.toString());
		bw.close();
		return;

	}

}