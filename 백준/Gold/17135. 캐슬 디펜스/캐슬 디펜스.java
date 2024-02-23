import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/***
 * BOJ_17135_캐슬디펜스
 * @author parkrootseok
 * 
 * - N + 1번 행에 성이 존재
 * - 성에 궁수 3명을 배치(성에만 배치 가능)
 * - 턴 마다 궁수는 적 하나 공격 가능(모든 궁수는 동시에 공격)
 * - 공격하는 적은 D이하인 적 중에서 가장 가까운 적(여럿이라면 가장 왼쪽)
 * - 같은 적이 여러 궁수에게 공격 당할 수 있음
 * - 궁수의 공격이 끝나면 적 이동
 * - 적은 아래로 한 칸 이동(성에 도달하면 게임 제외)
 * - 모든 적이 격자판에서 없어지면 게임 끝
 * 
 * 1. 격자판의 크기(r, c)와 궁수의 공격 거리 입력 및 격자에 대한 정보를 받아 초기화
 * 2. Column 에서 총 3명의 궁수가 위치할 수 있는 모든 조합을 생성
 *  2-1. 현재 레벨이 궁수의 수와 일치하다면 조합이 완성
 *  2-2. 조합 생성
 * 
 * 
 */

public class Main {

	static class Bow {

		int row;
		int col;
		Enemy attackableEnemy;

		Bow(int row, int col) {
			this.row = row;
			this.col = col;
			attackableEnemy = null;
		}

		@Override
		public String toString() {
			return "Bow [row=" + row + ", col=" + col + ", attackableEnemy=" + attackableEnemy + "]\n";
		}

	}

	static class Enemy {

		int row;
		int col;

		Enemy(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public boolean moveFoward() {

			// 현재 행이 성의 바로 밑이라면 이동 불가
			if (row == gridRow - 1) {
				return false;
			}

			// 그 외에 경우에는 이동 가능
			row++;
			return true;

		}

		@Override
		public String toString() {
			return "Enemy [row=" + row + ", col=" + col + "]\n";
		}

	}

	static int BOW_NUMBER = 3;
	static int EMPTY = 0;
	static int ENEMY = 1;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int[][] grid;
	static int gridRow;
	static int gridCol;
	static int attackRange;

	static List<Enemy> enemies;
	static Bow[] bows;

	static int maxEnemyCount;

	public static void combination(int level, int start) {

		// 2-1. 현재 레벨이 궁수의 수와 일치하다면 조합이 완성

		if (level == BOW_NUMBER) {

			List<Enemy> enemyTmp = new ArrayList<>();
			for(Enemy enemy : enemies) {
				enemyTmp.add(new Enemy(enemy.row, enemy.col));
			}
			
			int enemyCount = 0;
			while (!enemyTmp.isEmpty()) {

				// 3. 공격을 진행하기전 궁수의 공격 여부와 현재 공격할 적에 대한 정보를 초기회
				for (Bow bow : bows) {
					bow.attackableEnemy = null;
				}

				// 4. 공격이 가능하고 가장 자신보다 가장 왼쪽에 있는 적을 찾는다
				for (Bow bow : bows) {

					int minD = Integer.MAX_VALUE;
					
					for (Enemy enemy : enemyTmp) {

						int distance = Math.abs(bow.row - enemy.row) + Math.abs(bow.col - enemy.col);
						
						// 공격이 가능한 적들에 대해서
						if (!isAttackable(distance)) {
							continue;
						}

						// 처음이라면 공격 대상 지정
						if (bow.attackableEnemy == null) {
							minD = distance;
							bow.attackableEnemy = enemy;
						}

						// 가장 가까운 적부터 제거
						else if (distance < minD) {
							minD = distance;
							bow.attackableEnemy = enemy;
						} 
						
						// 거리가 동일하면 현재 공격할 적보다 왼쪽에 있는 적부터 제거
						else if(distance == minD && enemy.col < bow.attackableEnemy.col) {
							minD = distance;
							bow.attackableEnemy = enemy;
						}
 
					}

				}

				// 5. 모든 궁수가 공격할 적이 정해졌으므로 공격을 시작
				for (Bow bow : bows) {

					Enemy curAttackEnemy = bow.attackableEnemy;

					// 공격할 적이 리스트에 존재하다면 제거 후 카운팅
					if (curAttackEnemy != null && enemyTmp.contains(curAttackEnemy)) {
						enemyCount++;
						enemyTmp.remove(curAttackEnemy);
					}

				}

				// 6. 공격이 끝났다면 적들을 한 칸 성으로 전진
				for (int index = 0; index < enemyTmp.size(); index++) {

					// 이동이 불가하다면 성으로 들어가는 경우이므로 적을 삭제
					if (!enemyTmp.get(index).moveFoward()) {
						enemyTmp.remove(index);
						index--;
					}

				}
				
			}

			maxEnemyCount = Math.max(maxEnemyCount, enemyCount);
			return;

		}

		// 2-2. 조합 생성
		for (int index = start; index < gridCol; index++) {
			bows[level] = new Bow(gridRow, index);
			combination(level + 1, index + 1);
		}

	}

	public static boolean isAttackable(int distance) {

		if (distance <= attackRange) {
			return true;
		}

		return false;

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 격자판의 크기(r, c)와 궁수의 공격 거리 입력 및 적에 대한 정보를 받아 적 목록을 생성
		inputs = br.readLine().trim().split(" ");
		gridRow = Integer.parseInt(inputs[0]);
		gridCol = Integer.parseInt(inputs[1]);
		attackRange = Integer.parseInt(inputs[2]);

		enemies = new ArrayList<>();
		for (int row = 0; row < gridRow; row++) {
			inputs = br.readLine().trim().split(" ");
			for (int col = 0; col < gridCol; col++) {
				if (Integer.parseInt(inputs[col]) == ENEMY) {
					enemies.add(new Enemy(row, col));
				}
			}
		}

		// 2. Column 에서 총 3명의 궁수가 위치할 수 있는 모든 조합을 생성
		bows = new Bow[BOW_NUMBER];
		maxEnemyCount = Integer.MIN_VALUE;
		combination(0, 0);

		sb.append(maxEnemyCount);
		bw.write(sb.toString());
		bw.close();

	}

}