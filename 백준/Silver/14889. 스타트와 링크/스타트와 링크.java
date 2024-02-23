import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * BOJ 14889_스타트와링크
 * 
 * 1. 축구를 위해 모인 사람의 수 N을 입력 받는다. 
 * 2. N명의 선수들의 시너지 정보를 받는다.
 * 3. N명의 선수 중 중복없이 N / 2 명을 뽑아 스타트팀을 만든다.
 * 	3-1. 스타트 팀을 만든 경우 두 팀의 능력치 차이를 계산하여 최소값일 때 저장하고
 *  3-2. 아니라면 조합을 만든다
 *  
 */

class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] input;			
	
	static int playerNumber;			// 축구 참여 인원
	static int[][] synergeAbilityBoard;	// 시너지 능력
	static int[] startTeam;				// 스타트 팀
	static int[] linkTeam;				// 링크 팀
	static int minDifference;			// 두 조합의 차이의 최소값
	
	public static void combination(int teamNumber, int startPlayerIndex) {
		
		// 3-1. 팀이 완성되었다면 두 팀의 능력치 차이를 계산하여 최소값이면 저장
		if (teamNumber == playerNumber / 2) {
			
			int startTeamScore = 0;
			int linkTeamScore = 0;
			
			// 링크 팀을 구한다
			int linkTeamIndex = 0;
			for	(int playerIndex = 0 ; playerIndex < playerNumber ; playerIndex++) {
				
				boolean isLinkTeam = true;
				
				for	(int teamIndex = 0; teamIndex < teamNumber; teamIndex++) {
					
					if (playerIndex == startTeam[teamIndex]) {	// 스타트팀에 존재하면
						isLinkTeam = false;	// 링크팀이 아니다
					}
					
				}
				
				if(isLinkTeam) {	// 만약 스타트팀이 아니라면 링크팀
					linkTeam[linkTeamIndex++] = playerIndex;
				}
				
			}
			
			// start와 link 팀 점수 계산
			for	(int teamRow = 0; teamRow < teamNumber; teamRow++) {
				
				for (int teamCol = teamRow + 1; teamCol < teamNumber; teamCol++) {
					
					if(teamRow != teamCol) {
						startTeamScore += synergeAbilityBoard[startTeam[teamRow]][startTeam[teamCol]];
						startTeamScore += synergeAbilityBoard[startTeam[teamCol]][startTeam[teamRow]];
						linkTeamScore += synergeAbilityBoard[linkTeam[teamRow]][linkTeam[teamCol]];
						linkTeamScore += synergeAbilityBoard[linkTeam[teamCol]][linkTeam[teamRow]];
					}
					
				}
				
			}
			
			// 두 팀 시너지 차이의 최소값을 구하여 저장
			minDifference = Math.min(minDifference, Math.abs(startTeamScore - linkTeamScore));
			return;
			
		}
		
		// 3-2. 팀이 완성되지 않았으면 다시 진행
		for	(int playerIdx = startPlayerIndex; playerIdx < playerNumber; playerIdx++) {
			
			startTeam[teamNumber] = playerIdx;
			combination(teamNumber + 1, playerIdx + 1);
			
		}
		
	}
	
	public static void main(String args[]) throws Exception {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		minDifference = Integer.MAX_VALUE;

		// 1. 축구를 하는 사람의 수를 입력 받는다.
		playerNumber = Integer.parseInt(br.readLine().trim());
		startTeam = new int[playerNumber/2];
		linkTeam = new int[playerNumber/2];
		
		// 2. N명의 팀의 시너지 정보를 받는다.
		synergeAbilityBoard = new int[playerNumber][playerNumber];
		
		for	(int mapRow = 0; mapRow < playerNumber ; mapRow++) {
			
			input = br.readLine().trim().split(" ");
			
			for(int mapCol = 0 ; mapCol < input.length ; mapCol++) {
				synergeAbilityBoard[mapRow][mapCol] = Integer.parseInt(input[mapCol]);
				
			}
			
		}
		
		// 3. N명의 사람 중 중복없이 2명을 뽑아 팀을 완성한다.
		combination(0, 0);
		
		bw.write(minDifference + "\n");
		bw.close();
		return;
		
	}
	
}