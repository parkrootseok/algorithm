import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * SWEA_1238
 * @author parkrootseok
 * 
 * - 모든 섬을 터널로 연결
 * - 해저 터널을 건설할 때 부담금 정책이 존재
 * - 부담금 = E(세율) * L(터널 길이)^2
 * 
 * 1. 테스트 케이스 횟수를 받는다.
 * 2. 섬의 개수를 받는다.
 * 3. 섬들의 좌표 정보를 받는다
 * 4. 세율을 받는다.
 * 5. 모든 섬 끼리의 간선과 가중치를 만든다.
 * 6. 각 해저 터널을 탐색하여 모든 섬을 환경 부담금을 최소로 연결할 수 있을 때를 구해라
 *  6-1. 터널의 환경 부담금 오름차순으로 정렬
 *  6-2. 터널을 탐색
 * 	 6-2-1. 터널의 양 끝점이 사이클을 생성하면 패스
 *   6-2-2. 터널의 두 정점이 사이클을 생성하지 않으면 union하고 가중치를 누적
 *   6-2-3. 추가된 터널이 정점의 갯수 - 1을 만족하면 종료
 **/

class Solution {

	static class Island {

		int row;
		int col;
		double cost;

		Island(int row, int col) {
			this.row = row;
			this.col = col;
			this.cost = 0.0;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int tcNumber;

	static int[] unf;
	static int[] rank;
	
	static Island[] islands;
	static int islandNumber;
	static double scale;
	
	static List<double[]> tunnel;
	static int tunnelNumber;
	static double minTotalCharge;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수를 받는다.
		tcNumber = Integer.parseInt(br.readLine().trim());

		for (int curTestcase = 1; curTestcase <= tcNumber; curTestcase++) {

			// 2. 섬의 개수를 받는다.
			islandNumber = Integer.parseInt(br.readLine().trim());
			
			// 3. 섬들의 좌표 정보를 받는다
			int row, col;
			islands = new Island[islandNumber];
			String[] rowInputs = br.readLine().trim().split(" ");
			String[] colInputs = br.readLine().trim().split(" ");
			
			for (int island = 0; island < islandNumber; island++) {

				row = Integer.parseInt(rowInputs[island]);
				col = Integer.parseInt(colInputs[island]);

				islands[island] = new Island(row, col);
			}

			// 4. 세율을 받는다.
			scale = Double.parseDouble(br.readLine().trim());

			// 5. 모든 섬 끼리의 간선과 가중치를 만든다.
			int tunnelIdx = 0;
			tunnel = new ArrayList<>();
			for(int islandA = 0; islandA < islandNumber; islandA++) {
				
				for(int islandB = islandA + 1; islandB < islandNumber; islandB++) {
					
					tunnel.add(new double[3]);
					tunnel.get(tunnelIdx)[0] = islandA;
					tunnel.get(tunnelIdx)[1] = islandB;
					tunnel.get(tunnelIdx)[2] = getDistance(islands[islandA], islands[islandB]) * scale;
					
					tunnelIdx++;
					
				}
				
			}
			
			// 6. 각 해저 터널을 탐색하여 모든 섬을 환경 부담금을 최소로 연결할 수 있을 때를 구해라
			make(islandNumber);
			kruskal();
			
			sb.append("#").append(curTestcase).append(" ").append((long) minTotalCharge).append(" ").append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}
	
	public static void kruskal() {

		PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
			
			@Override
			public int compare(double[] o1, double[] o2) {
				
				return Double.compare(o1[2], o2[2]);
			}
			
		});
		
		// 6-1. 터널의 환경 부담금 오름차순으로 정렬
		for(int curTunnel = 0; curTunnel < tunnel.size(); curTunnel++) {
			pq.add(tunnel.get(curTunnel));
		}

		// 6-2. 터널을 탐색
		double charge = 0;
		int count = 0;
		while(true) {
			
			double[] curTunnel = pq.poll();
			
			int from =(int) curTunnel[0];
			int to = (int) curTunnel[1];
			double weight = curTunnel[2];
			
			// 6-2-1. 터널의 양 끝점이 사이클을 생성하면 패스
			if(find(from) == find(to)) {
				continue;
			}
			
			// 6-2-2. 터널의 두 정점이 사이클을 생성하지 않으면 union하고 가중치를 누적
			union(from, to);
			charge += weight;
			count++;
			
			// 6-2-3. 추가된 터널이 정점의 갯수 - 1을 만족하면 종료
			if(count == islandNumber - 1) {
				break;
			}
			
		}
		
		minTotalCharge = charge + 0.5;
		
	}

	public static void make(int elementNumber) {

		unf = new int[elementNumber + 1];
		rank = new int[elementNumber + 1];
		for (int curElement = 1; curElement <= elementNumber; curElement++) {
			unf[curElement] = curElement;
		}

	}

	public static void union(int elementA, int elementB) {

		int representationA = find(elementA);
		int representationB = find(elementB);

		// 두 원소의 대표가 같다면 이미 같은 집합
		if (representationA == representationB) {
			return;
		}

		// 아니라면 둘을 합집합 수행
		if (rank[representationA] > rank[representationB]) {
			// A가 속한 집합의 랭크가 더 같거나 높은 경우 B를 A 밑으로
			unf[representationB] = unf[representationA];
			return;
		}

		// B가 속한 집합의 랭크가 더 같거나 높은 경우 A를 B 밑으로
		unf[representationA] = unf[representationB];

		// 단, 두 합집합의 랭크가 같다면 밑으로 들어가게 될 집합의 랭크를 상승해야한다.
		if (rank[representationA] == rank[representationB]) {
			rank[representationB]++;
		}

	}

	public static int find(int element) {

		// 대표 노드 라면
		if (element == unf[element]) {
			return element;
		}

		// 대표 노드가 아니라면 찾으러 간다
		return unf[element] = find(unf[element]);

	}

	
	public static double getDistance(Island island1, Island island2) {
		
		return Math.pow(Math.abs(island1.row - island2.row), 2) + Math.pow(Math.abs(island1.col - island2.col), 2);
		
	}

	

}