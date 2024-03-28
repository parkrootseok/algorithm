import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_9205_맥주마시면서걸어가기
 * @author parkrootseok
 * 
 * - 맥주를 마시면서 걸어가자
 * 
 * - 맥주
 *   - 1박스(20개)
 *   - 50미터에 한 병 섭취
 *   - 이때, 50미터를 도착해서 마시는 것이 아닌 가기전에 미리 마시는 것
 *   - 즉, 다음으로 이동할 수 있는 거리는 최대 1000m
 * 
 * - 편의점
 *   - 맥주를 구매 가능
 *   - 기존 보유량 + 구매량 <= 20
 *   - 나선 직후 맥주를 마셔야함
 * 
 * - 좌표 사이의 거리
 *  - (x1-x2) + (y1-y2)
 * 
 * - 위 조건을 만족하여 도착 여부를 판단
 *   - 갈 수 있으면 happy
 *   - 못 가면 sad
 * 
 * 1. 테스트 케이스 개수 입력
 * 2. 맥주를 파는 편의점 갯수 입력
 * 3. 출발지, 편의점 위치, 도착지 좌표 입력
 * 4. 시작 지점부터 모든 편의점을 탐색하여 도착 지점으로 갈 수 있는 편의점이 있는지 확인
 **/
public class Main {

	static class Position {

		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	static final int POSSIBLE_DISTANCE = 1000;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int testCaseNumber;

	static int storeNumber;
	static Position[] stores;
	static Position org;
	static Position dist;

	static int[][] distances;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 개수 입력
		testCaseNumber = Integer.parseInt(br.readLine().trim());

		for (int tc = 0; tc < testCaseNumber; tc++) {

			// 2. 맥주를 파는 편의점 개수 받기
			storeNumber = Integer.parseInt(br.readLine().trim());

			// 3. 출발지, 편의점, 도착지 좌표 입력
			stores = new Position[storeNumber];
			for (int idx = 0; idx < storeNumber + 2; idx++) {

				inputs = br.readLine().trim().split(" ");
				int row = Integer.parseInt(inputs[0]);
				int col = Integer.parseInt(inputs[1]);

				// 출발지
				if (idx == 0) {
					org = new Position(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
				}

				// 도착지
				else if (idx == storeNumber + 1) {
					dist = new Position(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
				}

				// 편의점
				else {
					stores[idx - 1] = new Position(row, col);
				}

			}

			// 4. 시작 지점부터 모든 편의점을 탐색하여 도착 지점으로 갈 수 있는 편의점이 있는지 확인
			if (bfs(org)) {
				sb.append("happy").append("\n");
			}

			else {
				sb.append("sad").append("\n");
			}

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static boolean bfs(Position org) {

		boolean[] isVisited = new boolean[storeNumber];
		Queue<Position> positionQ = new ArrayDeque<>();
		positionQ.add(org);

		while (!positionQ.isEmpty()) {

			Position curPosition = positionQ.poll();

			// 현재 위치 ~ 도착 위치 거리가 도달 가능하다면 종료
			if (getDistance(curPosition.row, curPosition.col, dist.row, dist.col) <= POSSIBLE_DISTANCE) {
				return true;
			}

			for (int idx = 0; idx < storeNumber; idx++) {

				Position store = stores[idx];

				// 방문한 편의점이라면 스킵
				if (isVisited[idx]) {
					continue;
				}

				// 현재 위치 ~ 편의점 위치 거리가 도달 불가능하다면 스킵
				if (getDistance(curPosition.row, curPosition.col, stores[idx].row,
						stores[idx].col) > POSSIBLE_DISTANCE) {
					continue;
				}

				isVisited[idx] = true;
				positionQ.add(store);

			}

		}

		return false;

	}

}