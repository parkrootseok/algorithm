import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_7576_토마토
 * 
 * @author parkrootseok
 * 
 * - 토마토 
 *  - 익은 토마토와 인접한 익지 않은 토마토는 하루가 지나면 익음
 * 
 * - 모든 토마토가 익는 최소 날짜를 구해라 
 * - 단, 모든 토마토가 익지 않는 경우도 존재(-1 출력)
 * 
 * 1. 테스트 케이스 입력 
 *  1-1. 격자 크기 받기
 *  1-2. 격자 정보 입력
 * 2. BFS를 이용하여 모든 토마토가 익을 수 있는 날을 계산 
 * 3. 안 익은 토마토가 있는지 확인
 **/

class Main {

	static class Tomato {

		int row;
		int col;

		public Tomato(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	static final int RIPE_TOMATO = 1;
	static final int NOT_RIPE_TOMATO = 0;

	static final int[] dr = { 1, -1, 0, 0 };
	static final int[] dc = { 0, 0, 1, -1 };

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int[][] grid;
	static List<Tomato> tomatos;
	static int notRipeTomatoCount;
	static int rowSize;
	static int colSize;

	public static void input() throws IOException {

		// 1-1. 격자 크기 받기
		inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[1]);
		colSize = Integer.parseInt(inputs[0]);

		// 1-2. 격자 정보 입력
		tomatos = new ArrayList<>();
		grid = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {

			inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < colSize; col++) {

				grid[row][col] = Integer.parseInt(inputs[col]);
				
				if (grid[row][col] == RIPE_TOMATO) {
					tomatos.add(new Tomato(row, col));
				}
				
				if (grid[row][col] == NOT_RIPE_TOMATO) {
					notRipeTomatoCount++;
				}
				
				
			}

		}

	}

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 입력
		input();

		int day = 0;
		if (notRipeTomatoCount > 0) {
			
			// 2. BFS를 이용하여 모든 토마토가 익을 수 있는 날을 계산
			day = bfs();

			// 3. 안 익은 토마토가 있는지 확인
			for (int row = 0; row < rowSize; row++) {
				
				for (int col = 0; col < colSize; col++) {

					if (grid[row][col] == NOT_RIPE_TOMATO) {
						day = -1;
						break;
					}
				}

			}

		}
		
		
		sb.append(day);
		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static boolean isValid(int row, int col) {

		if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
			return true;
		}

		return false;

	}

	public static int bfs() {

		int day = -1;
		boolean[][] isVisited = new boolean[rowSize][colSize];
		Queue<Tomato> ripeTomatoQ = new ArrayDeque<>();

		// 이미 익은 토마토는 큐에 추가
		for (Tomato tomato : tomatos) {
			ripeTomatoQ.add(tomato);
			isVisited[tomato.row][tomato.col] = true;
		}

		while (!ripeTomatoQ.isEmpty()) {

			int size = ripeTomatoQ.size();
			day++;

			for (int curSize = 0; curSize < size; curSize++) {

				Tomato ripeTomato = ripeTomatoQ.poll();
				int cRow = ripeTomato.row;
				int cCol = ripeTomato.col;

				// 상하좌우 탐색
				for (int dir = 0; dir < dr.length; dir++) {

					int nRow = cRow + dr[dir];
					int nCol = cCol + dc[dir];

					if (!isValid(nRow, nCol)) {
						continue;
					}

					if (isVisited[nRow][nCol]) {
						continue;
					}

					// 익은 토마토 주변에 있는 안 익은 토마토는 익은 토마토로 변경
					if (grid[nRow][nCol] == NOT_RIPE_TOMATO) {
						grid[nRow][nCol] = RIPE_TOMATO;
						ripeTomatoQ.add(new Tomato(nRow, nCol));
						isVisited[nRow][nCol] = true;
					}

				}

			}

		}

		return day;

	}

}