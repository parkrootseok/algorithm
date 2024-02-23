import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/***
 * BOJ_17281_야구
 * @author parkrootseok
 * 
 * - N 이닝 게임 진행
 * - 경기 시작 전 타순을 정해야 함
 * - 1-9번의 선수가 있고, 1번 선수는 4번 타자
 * - 가장 많은 득점을 하는 타순을 찾고, 득점까지 구해라
 * 
 * 1. 이닝 수를 받는다.
 * 2. 이닝 수 만큼 각 이닝에서 얻는 결과가 1번 ~ N번 까지 주어진다
 * 3. 플레이어를 통해 라인업을 생성한다.
 * 4. 라인업이 완성되었 다면 게임을 시작한다.
 *  4-1. 이닝을 진행하며 완성한 라인업 순서대로 진행
 *  4-2. 이닝을 시작할 때 아웃카운트, 주루 상황 초기화
 *  4-3. outCount가 3개인지 확인하고 만족하다면 종료
 *   4-3-1. 종료하기 전에 게임 점수가 현재 최대값보다 높다면 초기화
 */

public class Main {
	
	static class Player {
		
		int number;
		int[] acting;
		
		Player(int number, int[] acting) {
			this.number = number;
			this.acting = acting;
		}
		
	}
	
	static class Game {
		
		int score;			// 점수
		int outCount; 		// 아웃 카운트
		boolean[] base;		// 루에 대한 정보
	
		Game() {
			this.score = 0;
			this.outCount = 0;
			this.base = new boolean[4];
		}

		
		public void play(int act) {
			
			// 아웃
			if(act == OUT) {
				outCount++;
			}
			
			// 1루타
			if(act == HIT_1) {
				
				// 3루에 주자가 있다면 점수 추가
				if(base[3]) {
					score++;
					base[3] = false;
				}
				
				// 진출한 주자 이동
				for(int baseNumber = 3; 1 < baseNumber; baseNumber--) {
					base[baseNumber] = base[baseNumber - 1];
				}
				
				// 타자 이동
				base[1] = true;
				
			}
			
			
			// 2루타
			if(act == HIT_2) {
				
				// 2루, 3루에 주자가 있다면 점수 추가
				for(int baseNumber = 2; baseNumber <= 3; baseNumber++) {
					
					if(base[baseNumber]) {
						score++;
						base[baseNumber] = false;
					}
					
				}
				
				// 진출한 주자 이동
				base[3] = base[1];
				base[1] = false;
				
				// 타자 이동
				base[2] = true;
				
			}

			
			// 3루타
			if(act == HIT_3) {
	
				// 1, 2, 3루에 주자가 있다면 점수 추가
				for(int baseNumber = 1; baseNumber <= 3; baseNumber++) {
					
					if(base[baseNumber]) {
						score++;
						base[baseNumber] = false;
					}
					
				}
				
				// 타자 이동
				base[3] = true;
				
			}

			// 홈런
			if(act == HOMERUN) {
				
				// 진출한 주자가 있을 경우 점수 추가
				for(int baseNumber = 1; baseNumber <= 3; baseNumber++) {
					
					if(base[baseNumber]) {
						score++;
						base[baseNumber] = false;
					}
					
				}
				
				// 타자 점수 추가
				score++;
				
			}


		}
		
		// 게임은 아웃카운트가 총 3개이면 종료
		public boolean isFinish() {
			
			if(outCount == 3) {
				
				return true;
				
			}
			
			return false;
			
		}
		
		
	}
	
	static final int OUT  = 0;
	static final int HIT_1  = 1;
	static final int HIT_2  = 2;
	static final int HIT_3  = 3;
	static final int HOMERUN = 4;
	
	static final int TOTAL_PLAYER = 9;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;
	
	static int totalInning;
	
	static Player[] players;
	static Player[] lineUp;
	static boolean[] isSelected;
	
	static int maxScore;
	
	public static void selectPlayer(int level) {
		
		// 라인업이 완성
		if(level == TOTAL_PLAYER + 1) {
			
			// 4. 라인업이 완성되었 다면 게임을 시작한다.
			Game game = new Game();
			
			// 4-1. 이닝을 진행하며 완성한 라인업 순서대로 진행
			Queue<Player> order = new ArrayDeque<>();
			for(int curPlayer = 1; curPlayer <= TOTAL_PLAYER; curPlayer++) {
				order.add(lineUp[curPlayer]);
			}
			
			for(int curInning = 0; curInning < totalInning; curInning++) {
				
				// 4-2. 이닝을 시작할 때 아웃카운트, 주루 상황 초기화
				game.outCount = 0;
			
				for(int baseNumber = 1; baseNumber <= 3; baseNumber++) {
					game.base[baseNumber] = false;
				}
				
				while(true) {
					
					// 4-3. outCount가 3개인지 확인하고 만족하다면 종료
					if(game.isFinish()) {
						// 4-3-1. 종료하기 전에 게임 점수가 현재 최대값보다 높다면 초기화
						maxScore = Math.max(maxScore, game.score);
						break;
					}
					
					// 현재 타석에 있는 타자
					Player curPlayer = order.poll(); 
					
					// 타격 결과
					int curPlayerAct = curPlayer.acting[curInning]; 
					
					// 게임 시뮬레이션
					game.play(curPlayerAct);
					
					// 타격을 마친 타자 복귀
					order.offer(curPlayer);
					
				}
				
			}
			
			return;
		}
		
		// 4번 타자는 정해져있음
		if(level == 4) {
			selectPlayer(level + 1);
			return;
		}
		
		// 1번 선수는 이미 4번 타자로 기용
		for (int index = 2; index <= TOTAL_PLAYER; index++) {
			
			// 기용이 안된 선수 라면
			if(!isSelected[index]) {
				
				isSelected[index] = true;
				
				// 현재 level에 해당하는 순번으로 선수를 기용
				lineUp[level] = players[index];
				selectPlayer(level + 1);
				
				isSelected[index] = false;
				
			}
			
		}
			
		
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 이닝 수를 받는다.
		inputs = br.readLine().trim().split(" ");
		totalInning = Integer.parseInt(inputs[0]); 
		
		// 2. 선수들에 대한 정보를 초기화
		players = new Player[TOTAL_PLAYER + 1];
		for(int curPlayer = 1; curPlayer <= TOTAL_PLAYER; curPlayer++) {
			
			players[curPlayer] = new Player(curPlayer, new int[totalInning]);
			
		}
		
		// 2. 이닝 수 만큼 각 이닝에서 얻는 결과가 1번 ~ N번 까지 주어진다
		for(int curInning = 0; curInning < totalInning; curInning++) {
			
			inputs = br.readLine().trim().split(" ");
			
			for(int curPlayer = 1; curPlayer <= TOTAL_PLAYER; curPlayer++) {
				
				players[curPlayer].acting[curInning] = Integer.parseInt(inputs[curPlayer - 1]);
				
			}
			
		}
		
		// 3. 플레이어를 통해 라인업을 생성한다.
		lineUp = new Player[TOTAL_PLAYER + 1];
		isSelected = new boolean[TOTAL_PLAYER + 1];
		
		// 단, 1번 선수는 항상 4번타자
		isSelected[1] = true;
		lineUp[4] = players[1];
		selectPlayer(1);
		
		sb.append(maxScore).append(" ");
		bw.write(sb.toString());
		bw.close();

	}

}