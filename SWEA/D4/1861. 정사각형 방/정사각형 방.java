import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * SWEA_1861_정사각형방
 * @author parkrootseok
 * 
 * - 방이 존재하고 상하좌우 이동 가능
 * - 단, 현재 방보다 정확히 1이 큰 방만 이동 가능
 * - 어떤 방에서 출발해야 가장 많이 이동할 수 있는지 구해라
 * - 단, 여럿이면 적힌 숫자가 가장 작은것을 출력
 * 
 * 1. 테케 횟수 입력
 * 2. 방 크기 입력
 * 3. 방에 대한 입력
 * 4. 하나의 방에서 4방 탐색을 진행
 *  4-1. 다음 방으로 이동가능 하면 재귀 호출
 * 5. 탐색한 방 수가 최대이면 초기화 후 위치 저장
 *  
 */

class Solution {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int tcNumber;
	static int roomSize;

	static int[][] room;
	static boolean[][] isVisted;

	static int maxMoveCount;
	static int minRoomNumber;
	static int moveCount;
	
	public static boolean isValid(int row, int cols) {

		if (!(0 <= row && row < roomSize && 0 <= cols && cols < roomSize)) {
			return false;
		}

		return true;

	}

	public static boolean isPossible(int curRoom, int nextRoom) {

		int diff = nextRoom - curRoom;

		if (diff == 1) {
			return true;
		}

		return false;

	}

	public static void getMoveCount(int row, int cols) {
		
		int nextRow, nextCols;
		for (int index = 0; index < dx.length; index++) {

			nextRow = row + dx[index];
			nextCols = cols + dy[index];

			// 인덱스가 범위내에 존재하고
			if (!isValid(nextRow, nextCols)) {
				continue;
			}

			// 두 방의 차이가 정확이 1이면
			if (!isPossible(room[row][cols], room[nextRow][nextCols])) {
				continue;
			}

			// 4-1. 다음 방으로 이동가능 하면 재귀 호출 방문 횟수 증가
			moveCount++;
			getMoveCount(nextRow, nextCols);

		}

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테케 횟수 입력
		tcNumber = Integer.parseInt(br.readLine().trim());

		for (int curTC = 1; curTC <= tcNumber; curTC++) {

			// 2. 방 크기 입력
			roomSize = Integer.parseInt(br.readLine().trim());

			// 3. 방에 대한 입력
			room = new int[roomSize][roomSize];
			for (int row = 0; row < roomSize; row++) {
				inputs = br.readLine().trim().split(" ");
				for (int cols = 0; cols < roomSize; cols++) {
					room[row][cols] = Integer.parseInt(inputs[cols]);
				}
			}

			// 4. 하나의 방에서 4방 탐색을 진행
			maxMoveCount = Integer.MIN_VALUE;
			minRoomNumber = roomSize * roomSize + 1;
			for (int row = 0; row < roomSize; row++) {
				for (int cols = 0; cols < roomSize; cols++) {
					
					moveCount = 1;
					getMoveCount(row, cols); 

					// 5. 탐색한 방 수가 최대이면 초기화 후 위치 저장
					if (moveCount == maxMoveCount) {
						minRoomNumber = Math.min(minRoomNumber, room[row][cols]);
						maxMoveCount = moveCount;
					} else if (moveCount > maxMoveCount) {
						minRoomNumber = room[row][cols];
						maxMoveCount = moveCount;
					}

				}
			}

			// 결과 출력
			sb.append("#").append(curTC).append(" ").append(minRoomNumber).append(" ").append(maxMoveCount)
				.append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}