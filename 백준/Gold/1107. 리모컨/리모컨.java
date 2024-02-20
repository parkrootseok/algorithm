import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/***
 * BOJ_1107_리모컨
 * @author parkrootseok
 * 
 * - 0 ~ 9 버튼과 플러스, 마이너스를 조합해 가고자 하는 채널에 최대한 빨리 갈 수 있는 방법을 구해라
 * - 플러스는 채널 + 1, 마이너스는 채널 - 1
 * 
 * 1. 목표 채널을 받는다
 * 2. 고장난 버튼의 개수를 받는다.
 * 3. 고장난 버튼 정보를 받는다.
 * 4. 목표한 채널로 가기 위해 최소 클릭수를 구한다.
 * 4-1. 목표한 채널과 현재 채널이 같을 경우 카운트
 * 4-2. '+' or '-' 를 사용하여 바로 갈 수 있는 카운트
 * 4-3. 버튼을 조합하여 갈 수 있을 때 카운트
 * 
 */

public class Main {
	
	
	static final int START_CHANNEL = 100;
	static final int TOTAL_BUTTON_NUMBER = 10;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int targetChannel;
	static int targetChannelLength;
	
	static int wrongButtonNumber;

	static boolean[] wrongButtons;
	static int[] perm;
	
	static int totalClickCount;
	static int minDiffChannel;
	static int minDiff;
	
	public static void permutation(int level, int channel) {
		
		// N의 자리수의 끝에 위치하는 수라면 N + 1 자리의 첫숫자에도 최소값이 존재할 수 있기 때문에 N + 1 자리까지 만들고 탐색
		if (level == targetChannelLength + 1) {
			return;
		}
		
		// 최대 만들수 있는 자릿수는 6자리
		if (level == 6) {
			return;
		}
		
		for(int index = 0; index < TOTAL_BUTTON_NUMBER; index++) {
			
			// 고장나지 않은 버튼이라면 수열에 포함
			if(!wrongButtons[index]) {
				
				int nextChannel = channel * 10 + index;	
				int clickCount = Math.abs(targetChannel - nextChannel) + String.valueOf(nextChannel).length();
			
				totalClickCount = Math.min(totalClickCount, clickCount);
				
				permutation(level + 1, nextChannel);
				
			}
			
		}
		
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 목표 채널을 받는다
		input = br.readLine().trim();
		targetChannel = Integer.parseInt(input);
		targetChannelLength = input.length();
		
		// 2. 고장난 버튼의 개수를 받는다.
		wrongButtonNumber = Integer.parseInt(br.readLine().trim());
		
		// 3. 고장난 버튼 정보를 받는다. (단, 고장난 버튼이 없을 경우는 제외)
		wrongButtons = new boolean[TOTAL_BUTTON_NUMBER];
		
		if(wrongButtonNumber > 0) {
			inputs = br.readLine().trim().split(" ");
			for(int curButton = 0; curButton < wrongButtonNumber; curButton++) {
				wrongButtons[Integer.parseInt(inputs[curButton])] = true;
			}
		}
		
		// 4. 목표한 채널로 가기 위해 최소 클릭수를 구한다.
		totalClickCount = Integer.MAX_VALUE;
		
		// 4-1. 목표한 채널과 현재 채널이 같을 경우 카운트
		if(START_CHANNEL == targetChannel) {
			totalClickCount = 0;
		}
		
		// 4-2. '+' or '-' 를 사용하여 바로 갈 수 있는 카운트
		totalClickCount = Math.min(totalClickCount, Math.abs(targetChannel - START_CHANNEL));
		
		// 4-3. 버튼을 조합하여 갈 수 있을 때 카운트
		// 수열을 생성하여 가장 목표 채널과 가장 근사한 채널을 조합
		perm = new int[targetChannelLength];
		permutation(0, 0);
	
		sb.append(totalClickCount);
		bw.write(sb.toString());
		bw.close();

	}

}