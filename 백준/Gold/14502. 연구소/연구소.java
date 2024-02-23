import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * BOJ_14502_연구소
 * @author parkrootseok
 * 
 * - 바이러스 확산을 막기 위해 벽을 세우려고 함
 * - 연구소는 N X M (빈 칸[0], 벽[1], 바이러스[2])
 * - 바이러스는 상하좌우 빈 칸으로 확산
 * - 새로 세울 수 있는 벽은 3개(꼭 3개)
 *  
 * 1. N과 M입력 받아 지도 생성
 * 2. 지도에 대한 정보 입력받기
 * 3. 총 3개의 좌표 조합을 만들고
 * 	3-1. 조합이 완료되었다면 
 *  3-2. 좌표 조합에 있는 위치에 벽을 세우고
 *  3-3. 바이러스를 퍼트린 후 안전 영역의 갯수를 구하고 이전 보다 크다면 초기화
 *  3-4. 지도 복구 후 종료
 * 
 */

public class Main {

	static class Position {

		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static final int EMPTY = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;

	static final int WALL_LIMIT_NUMBER = 3;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0 , 1, -1};
	
	static int ANSWER;
	static int N, M;
	static int[][] map;
	static Position[] combi;
	static boolean[][] isVisited;

	public static void combination(int level) {

		// 3-1. 조합이 완료되었다면
		if (level == WALL_LIMIT_NUMBER) {

			int[][] tmp = copyMap();

			// 3-2. 좌표 조합에 있는 위치에 벽을 세우고
			for (Position p : combi) {
				map[p.getX()][p.getY()] = WALL;
			}

			// 3-3. 바이러스를 퍼트린 후 안전 영역의 개수를 구하고 이전 보다 크다면 초기화
			ANSWER = Math.max(ANSWER, checkVirus());
			
			// 3-4. 지도 복구
			map = tmp;
			
			return;

		}

		for (int row = 0; row < N; row++) {
			for (int cols = 0; cols < M; cols++) {

				if (!isVisited[row][cols] && map[row][cols] != WALL && map[row][cols] != VIRUS) {
					isVisited[row][cols] = true;
					combi[level] = new Position(row, cols);
					combination(level + 1);
					isVisited[row][cols] = false;
				}

			}
		}

	}

	public static int[][] copyMap() {

		int[][] tmp = new int[N][M];

		for (int row = 0; row < N; row++) {
			tmp[row] = map[row].clone();
		}

		return tmp;

	}

	public static int checkVirus() {
		
		Queue<Position> virus = new LinkedList<>();
		
		for (int row = 0; row < N; row++) {
			for (int cols = 0; cols < M; cols++) {

				// 현재 위치한 칸이 바이러스라면
				if (map[row][cols] == VIRUS) {
					
					// 큐에 추가
					virus.add(new Position(row,cols));
				}

			}

		}
		
		// 큐에 들어있는
		while(!virus.isEmpty()) {
			
			// 바이러스의 위치르 꺼내서
			Position v = virus.poll();
			
			// + 방향으로 탐색 시작
			int nx, ny;
			for(int index = 0; index < dx.length; index++) {
				
				nx = v.x + dx[index];
				ny = v.y + dy[index];
				
				// 바이러스가 확산되면 해당 위치를 큐에 삽입
				if(0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == EMPTY) {
					map[nx][ny] = VIRUS;
					virus.add(new Position(nx, ny));
				}
				
			}
			
		}

		return calcSafeArea();

	}
	
	
	public static int calcSafeArea() {

		int count = 0;

		for (int row = 0; row < N; row++) {
			for (int cols = 0; cols < M; cols++) {

				if (map[row][cols] == 0) {
					count++;
				}

			}
		}

		return count;

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. N과 M입력 받아 지도 생성
		inputs = br.readLine().trim().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		map = new int[N][M];

		// 2. 지도에 대한 정보 입력받기
		for (int row = 0; row < N; row++) {

			inputs = br.readLine().trim().split(" ");

			for (int cols = 0; cols < M; cols++) {
				map[row][cols] = Integer.parseInt(inputs[cols]);
			}

		}

		// 3. 총 3개의 좌표 조합을 만들고
		ANSWER = 0;
		combi = new Position[3];
		isVisited = new boolean[N][M];
		combination(0);

		sb.append(ANSWER).append("\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}

}