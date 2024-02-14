import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/***
 * BOJ_3109_빵집
 * @author parkrootseok
 * 
 * - 첫번 열은 근처 빵집의 가스관
 * - 마지막 열은 원웅이의 빵집
 * - 가스관과 빵집을 연결하는 파이프 설치
 * - 건물이 있는 곳은 설치 불가
 * - 연결하는 파이프라인은 반드시 첫번째 열에서 시작하고 마지막 열에서 끝
 * - 파이프는 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결 가능
 * - 파이프라인은 겹칠 수 없음
 * - 파이프라인의 최대 개수는?
 * 
 * 1. r,c 에 대한 정보 입력
 * 2. 빵집 근처에 대한 정보 입력
 * 3. 행을 탐색하며 파이프 연결을 시작
 *  3-1. 끝 지점까지 도착 확인
 *  3-2. 다음 파이프를 연결할 곳을 탐색
 *   3-2-1. 다음 위치가 유효한 인덱스안에 존재하고 빌딩도 파이프도 아니라면
 *   3-2-2. 파이프 연결
 *   3-2-3. 이미 파이프가 완성이 되었다면 더이상 진행하지 않도록 종료
 * 4. curRow에서 시작해서 파이프 연결이 가능하다면 카운트
 * 
 */

public class Main {
	
	// 오른쪽 위, 중간, 오른쪽 아래
	// 파이프를 위에서 부터 아래 방향으로 연결해야 많이 연결 가능
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};

	static char BUILDING = 'x';
	static char PIPE = '-';
	
	static int START_COL;
	static int END_COL;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int mapRow;
	static int mapCol;
	static char[][] map;

	static boolean isPossible;
	static int connectCount;
	
	public static boolean inRange(int row, int col) {
		
		if(0 <= row && row < mapRow && 0 <= col && col < mapCol) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 파이프 연결
	 */
	public static void connectPipe(int startRow, int startCol) {
		
		// 3-1. 끝 지점까지 도착 확인(파이프 연결이 완료된 경우)
		if(startCol == END_COL) {
			// 한 번 파이프가 연결되면 동일한 경로에 파이프는 사용할 수 없기 때문에 파이프 연결이 완료되었다고 표시
			isPossible = true;
			return;
		}
		
		// 3-2. 다음 파이프를 연결할 곳을 탐색
		int nextRow, nextCol = startCol + 1;
		for(int direction = 0; direction < dx.length; direction++) {
			
			// 3-2-1. 다음 위치가 유효한 인덱스안에 존재하고 빌딩도 파이프도 아니라면
			// 다음 위치
			nextRow = startRow + dx[direction];
			
			// 범위를 벗어나지 않고
			if(!inRange(nextRow, nextCol)) {
				continue;
			}
			
			// 다음 위치가 빌딩도 아니고
			if(map[nextRow][nextCol] == BUILDING) {
				continue;
			}
			
			// 다음 위치가 파이프도 아니라면
			if(map[nextRow][nextCol] == PIPE) {
				continue;
			}
			
			// 3-2-2. 파이프 연결
			map[nextRow][nextCol] = PIPE;
			connectPipe(nextRow, nextCol);
			
			// 3-2-3. 이미 파이프가 완성이 되었다면 더이상 진행하지 않도록 종료
			if(isPossible) {
				return;
			}
			
		}
			
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. r,c 에 대한 정보 입력
		inputs = br.readLine().trim().split(" ");
		mapRow = Integer.parseInt(inputs[0]);
		mapCol = Integer.parseInt(inputs[1]);
		
		// 2. 빵집 근처에 대한 정보 입력
		map = new char[mapRow][mapCol];
		for(int curRow = 0; curRow < mapRow; curRow++) {
			inputs = br.readLine().trim().split("");
			for(int curCol = 0; curCol < mapCol; curCol++) {
				map[curRow][curCol] = inputs[curCol].charAt(0);
			}
		}
		
		// 3. 행을 탐색하며 파이프 연결을 시작
		START_COL = 0;
		END_COL = mapCol - 1;
		for(int curRow = 0; curRow < mapRow; curRow++) {
			
			isPossible = false;
			
			connectPipe(curRow, START_COL);
			
			// 4. curRow에서 시작해서 파이프 연결이 가능하다면 카운트
			if(isPossible) {
				connectCount++;
			}
			
		}
		
		sb.append(connectCount).append("\n");
		bw.write(sb.toString());
		bw.close();

	}

}