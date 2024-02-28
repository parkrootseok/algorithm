import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_17070_파이프옮기기1
 * @author parkrootseok
 * 
 * - 파이프 옮기려고 함
 * - 파이프는 회전 방향
 *  - 가로, 세로, 오른쪽 아래 대각선
 * - 각 파이프 이동 가능 방향
 *  - 가로(가로, 대각선) / 세로(세로, 대각선) / 대각선(가로, 세로, 대각선)
 * - 파이프 초기 위치
 *  - (0, 0) / (0 , 1)
 * 
 * 
 * 1. 집 크기를 받아 집에 대한 정보를 초기화
 * 2. 파이프를 (n, n)으로 이동
 *  2-1. 파이프의 끝이 마지막 위치에 도달한 경우 카운트
 *  2-2. 연결 가능한 파이프를 찾아 연결
 **/

public class Main {

	static final int WIDTH = 0;
	static final int LENGTH = 1;
	static final int DIAGONAL = 2;

	// 우, 하, 대각선
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1};

	static int[][] connetablePosition = {

		{0, 2}, // 가로(우, 대각선 가능)
		{1, 2}, // 세로(아래, 대각선 가능)
		{0, 1, 2}, // 대각선(우, 아래, 가능)

	};

	static int[][] checkPosition = {

		{0}, // 가로(우 확인)
		{1}, // 세로(아래)
		{0, 1, 2}, // 대각선(우, 아래, 대각선 확인)

	};

	static class Pipe {

		// 파이프의 위치는 끝 부분만 기억하면 됨
		int row;
		int col;

		int direction;

		Pipe(int row, int col, int direction) {

			// 방향 설정(0 : 가로, 1 : 세로, 2 : 대각선)
			this.direction = direction;
			this.row = row;
			this.col = col;

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int homeSize;
	static int[][] home;
	static int reachableCount;
	static boolean[][] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 집 크기를 받아 집에 대한 정보를 초기화
		homeSize = Integer.parseInt(br.readLine().trim());

		home = new int[homeSize][homeSize];
		for (int row = 0; row < homeSize; row++) {

			inputs = br.readLine().trim().split(" ");

			for (int col = 0; col < homeSize; col++) {

				home[row][col] = Integer.parseInt(inputs[col]);

			}

		}

		// 2. 파이프를 (n, n)으로 이동
		isVisited = new boolean[homeSize][homeSize];
		reachableCount = 0;
		connectPipe(new Pipe(0, 1, WIDTH));

		sb.append(reachableCount).append("\n");
		bw.write(sb.toString());
		bw.close();

	}

	public static boolean isConnectable(int row, int col) {

		if (0 <= row && row < homeSize && 0 <= col && col < homeSize) {
			return true;
		}

		return false;

	}

	public static void connectPipe(Pipe curPipe) {

		// 2-1. 파이프의 끝이 마지막 위치에 도달한 경우 카운트
		if (curPipe.row == homeSize - 1 && curPipe.col == homeSize - 1) {
			reachableCount++;
			return;
		}

		// 2-2. 연결 가능한 파이프를 찾아 연결
		int curRow = curPipe.row;
		int curCol = curPipe.col;
		int curDirection = curPipe.direction;

		isVisited[curRow][curCol] = true;

		// dir 방향의 파이프를 설치
		/**
		 * 설치할 수 있는 방향
		 * - 가로(0) : 가로, 대각선
		 * - 세로(1) : 세로, 대각선
		 * - 대각선(2) : 가로, 세로, 대각선
		 */
		for (int connectDir : connetablePosition[curDirection]) {

			boolean isConnetable = true;

			// dir방향의 파이프를 설치하기 위해 확인해야 할 칸
			/**
			 * 확인할 방향
			 * 가로 : 가로
			 * 세로 : 세로
			 * 대각선 : 가로, 세로, 대각선 
			 **/
			for (int checkDir : checkPosition[connectDir]) {

				// 파이프 끝 위치
				int nextRow = curRow + dx[checkDir];
				int nextCol = curCol + dy[checkDir];

				// 파이프의 끝 부분이 집안에 존재하거나 설치할 곳이 벽이면 설치 불가
				if (!isConnectable(nextRow, nextCol) || home[nextRow][nextCol] == 1) {
					isConnetable = false;
					break;
				}

			}

			// 설치 불가하면 패스
			if (!isConnetable) {
				continue;
			}

			// 연결할 위치
			int connectRow = curRow + dx[connectDir];
			int connectCol = curCol + dy[connectDir];

			// 설치할 장소에 이미 설치되어 있을 경우 스킵
			if (isVisited[connectRow][connectCol]) {
				continue;
			}

			// 다음에 연결한 파이프를 파라미터로 재귀 호출을 통해 다시 연결을 시작한다.
			connectPipe(new Pipe(connectRow, connectCol, connectDir));

		}

		isVisited[curRow][curCol] = false;

	}

}