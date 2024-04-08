import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BOJ_17472_다리만들기
 * @author parkrootseok
 *
 * - 모든 섬을 연결하기 위해서 다리가 필요
 * - 다리
 *  - 바다에만 건설 가능
 *  - 길이는 격자에서 차지하는 칸의 수(최소 2)
 *  - 세로 또는 가로 방향으로만 가능
 * - 모든 섬이 연결되었을 때 최소 다리 길이를 구해라
 *
 * 1. 입력 받기
 *  1-1. 지도에 대한 크기 입력
 *  1-2. 지도에 대한 정보 입력
 * 2. 섬을 구별하기 위해 인덱싱
 *  2-1. 인덱스를 가지는 섬을 생성
 *  2-2. 현재 위치로 부터 4방 탐색을 진행하여 갈 수 있는 위치를 탐색
 *   2-2-1. 갈 수 있는 위치에 인덱싱 및 섬 영역 리스트에 추가
 * 3. 섬의 모든 영역을 탐색하여 건설할 수 있는 모든 다리를 건설
 * 4. 크루스칼 알고리즘을 모든 섬이 연결됐을 때 최소 다리 길이 계산
 *  4-1. 서로소 집합을 사용하기 위한 초기 작업
 *  4-2. 모든 다리를 우선순위 큐에 삽입
 *  4-3. 가장 최소 길이를 가지는 다리부터 건설을 시작하여 모든 섬을 연결
 *  4-4. 연결 완료 확인
 **/
public class Main {

    static class Island {

        int idx;
        List<int []> areas;

        public Island(int idx) {
            this.idx = idx;
            this.areas = new ArrayList<>();
        }

    }

    static class Bridge implements Comparable<Bridge> {

        int from;
        int to;
        int length;

        public Bridge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Bridge o) {
            return Integer.compare(this.length, o.length);
        }

    }

    static final int SEA = 0;
    static final int ISLAND = 1;

    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static String[] inputs;
    static String input;

    static int rowSize, colSize;
    static int[][] map;

    static List<Island> islands;
    static boolean[][] isVisited;

    static List<Bridge> bridges;
    static int minBridgeLength;

    static int[] unf;
    static int[] rank;

    public static void init() {

        unf = new int[islands.size() + 1];
        rank = new int[islands.size() + 1];

        for (int idx = 1; idx <= islands.size(); idx++) {
            unf[idx] = idx;
        }

    }

    public static void union(int from, int to) {

        int findA = find(from);
        int findB = find(to);

        if (findA == findB) {
            return;
        }

        // 출발지에서 더 많은 섬을 이동할 수 있다면
        if (rank[findA] > rank[findB]) {

            // 도착지에서 출발지로 연결되도록 설정
            unf[findB] = findA;
            return;

        }

        // 갈 수 있는 섬의 개수가 동일하거나 도착지에서 더 많은 섬으로 갈 수 있다면 출발지에서 도착지로 연결되도록 설정
        unf[findB] = unf[findA];

        // 섬의 개수가 동일할 때만 랭크 증가
        if (rank[findA] == rank[findB]) {
            rank[findB]++;
        }

        return;

    }

    public static int find(int island) {

        if (island == unf[island]) {
            return island;
        }

        return unf[island] = find(unf[island]);

    }

    public static void input() throws IOException {

        // 1-1. 지도에 대한 크기 입력
        inputs = br.readLine().trim().split(" ");
        rowSize = Integer.parseInt(inputs[0]);
        colSize = Integer.parseInt(inputs[1]);

        // 1-2. 지도에 대한 정보 입력
        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {

            inputs = br.readLine().trim().split(" ");

            for (int col = 0; col < colSize; col++) {

                map[row][col] = Integer.parseInt(inputs[col]);

            }

        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        // 1. 입력 받기
        input();

        // 2. 섬을 구별하기 위해 인덱싱
        islands = new ArrayList<>();
        isVisited = new boolean[rowSize][colSize];
        int islandIdx = 0;
        for (int row = 0; row < rowSize; row++) {

            for (int col = 0; col < colSize; col++) {

                // 현재 좌표가 섬 영역 후보이고 방문하지 않은 곳이라면
                if (map[row][col] == ISLAND && !isVisited[row][col]) {

                    // 인덱스를 가지는 섬을 생성 후
                    Island island = new Island(++islandIdx);

                    // 섬을 이룰 수 있는 영역을 찾아 동일한 인덱스 할당
                    indexing(island, row, col);

                }

            }

        }

        // 3. 섬의 모든 영역을 탐색하여 건설할 수 있는 모든 다리를 건설
        bridges = new ArrayList<>();
        for (Island island : islands) {
            buildBridge(island);
        }

        // 4. 크루스칼 알고리즘을 모든 섬이 연결됐을 때 최소 다리 길이 계산
        kruskal();

        sb.append(minBridgeLength);
        bw.write(sb.toString());
        bw.close();
        return;

    }

    public static void indexing(Island island, int row, int col) {

        // 2-1. 현재 위치로 부터 4방 탐색을 진행하여 갈 수 있는 위치를 탐색
        Queue<int[]> areaQ = new ArrayDeque<>();
        isVisited[row][col] = true;
        areaQ.add(new int[] {row, col});

        while (!areaQ.isEmpty()) {

            int[] area = areaQ.poll();
            int curRow = area[0];
            int curCol = area[1];

            // 2-2-1. 갈 수 있는 위치에 인덱싱 및 섬 영역 리스트에 추가
            map[curRow][curCol] = island.idx;
            island.areas.add(new int[] {curRow, curCol});

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = curRow + dr[dir];
                int nCol = curCol + dc[dir];

                // 유효한 범위내에 존재하고
                if (!(0 <= nRow && nRow < rowSize && 0 <= nCol && nCol < colSize)) {
                    continue;
                }

                // 방문한 적 없으며
                if (isVisited[nRow][nCol]) {
                    continue;
                }

                // 바다가 아닌
                if (map[nRow][nCol] == SEA) {
                    continue;
                }

                // 영역을 큐에 추가하고 방문 처리
                isVisited[nRow][nCol] = true;
                areaQ.add(new int[] {nRow, nCol});

            }

        }

        // 현재 인덱스를 가지는 섬에 포함된 모든 영역을 다 찾고 리스트에 추가 후 종료
        islands.add(island);

    }

    public static void buildBridge(Island island) {

        // 3-1. 현재 섬의 모든 영역에 대해서 4방 탐색
        for (int[] area : island.areas) {

            int row = area[0];
            int col = area[1];

            for (int dir = 0; dir < dr.length; dir++) {

                boolean isLinked = false;
                int nRow = row;
                int nCol = col;
                int bridgeLength = 0;
                while (true) {

                    nRow += dr[dir];
                    nCol += dc[dir];

                    // 유효한 범위내에 존재하고
                    if (!(0 <= nRow && nRow < rowSize && 0 <= nCol && nCol < colSize)) {
                        break;
                    }

                    // 동일한 섬이 아니면서
                    if (map[nRow][nCol] == island.idx) {
                        break;
                    }

                    // 3-2. 다른 섬에 도착했을때
                    if (ISLAND <= map[nRow][nCol]) {
                        
                        // 3-2-1. 다리 길이가 2미만 이라면 종료
                        if (bridgeLength < 2) {
                            break;
                        }
                        
                        // 3-2-2. 2이상이면 다리 건설
                        bridges.add(new Bridge(island.idx, map[nRow][nCol], bridgeLength));
                        break;
                    }

                    // 위 3조건을 모두 만족하지 않는다면 다리 건설
                    bridgeLength++;

                }

            }

        }

    }

    public static void kruskal() {

        // 4-1. 서로소 집합을 사용하기 위한 초기 작업
        init();

        // 4-2. 모든 다리를 우선순위 큐에 삽입
        PriorityQueue<Bridge> bridgeQ = new PriorityQueue<>();
        for(Bridge bridge : bridges) {
            bridgeQ.add(bridge);
        }

        // 4-3. 가장 최소 길이를 가지는 다리부터 건설을 시작하여 모든 섬을 연결
        int connectedBridgeCount = 0;
        while (!bridgeQ.isEmpty()) {

            Bridge bridge = bridgeQ.poll();
            int from = bridge.from;
            int to = bridge.to;

            // 두 섬이 연결되어 있지 않다면
            if (find(from) != find(to)) {

                // 두 간선을 연결
                union(from, to);

                // 다리 길이를 누적합
                minBridgeLength += bridge.length;

                // 연결된 다리수를 카운트
                connectedBridgeCount++;

            }

        }

        // 4-4. 연결 완료 확인
        if (connectedBridgeCount != islands.size() - 1) {
            minBridgeLength = -1;
        }

    }

}