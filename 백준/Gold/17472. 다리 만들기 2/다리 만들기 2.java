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
 * 2. 섬을 구별하기 위해 인덱스 할당
 * 3. 건설할 수 있는 모든 다리 건설
 * 4. 크루스칼 알고리즘을 모든 섬이 연결됐을 때 최소 다리 길이 계산
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

    public static int find(int a) {

        if (a == unf[a]) {
            return a;
        }

        return unf[a] = find(unf[a]);

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

        // 2. 섬을 구별하기 위해 인덱스 할당
        islands = new ArrayList<>();
        isVisited = new boolean[rowSize][colSize];
        int islandIdx = 1;
        for (int row = 0; row < rowSize; row++) {

            for (int col = 0; col < colSize; col++) {

                // 현재 좌표가 섬이고 방문하지 않았다면
                if (map[row][col] == ISLAND && !isVisited[row][col]) {

                    // 섬 영역에 대해 인덱스 할당
                    allocateIndex(row, col, islandIdx);

                    // 할당이 끝난 후 인덱스 증가
                    islandIdx++;

                }

            }

        }

        // 3. 각 섬의 모든 영역을 탐색하여 건설할 수 있는 모든 다리를 건설
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

    public static void kruskal() {

        // 3-1. 크루스칼 알고리즘을 위해 UnionFind에 대한 배열을 초기화
        init();

        // 3-2. 모든 다리를 우선순위 큐에 삽입
        PriorityQueue<Bridge> bridgeQ = new PriorityQueue<>();
        for(Bridge bridge : bridges) {
            bridgeQ.add(bridge);
        }

        // 연결된 간선 수가 총 섬의 개수보다 하나 작을 때 까지 탐색
        boolean[] isLinked = new boolean[islands.size() + 1];
        int connectedBridgeCount = 0;
        while (!bridgeQ.isEmpty()) {

            Bridge bridge = bridgeQ.poll();
            int from = bridge.from;
            int to = bridge.to;

            // 두 섬이 연결되어 있지 않다면
            if (find(from) != find(to)) {

                // 두 간선을 연결
                union(from, to);

                // 연결 여부 표시
                isLinked[from] = true;
                isLinked[to] = true;

                // 다리 길이를 누적합
                minBridgeLength += bridge.length;

                // 연결된 다리수를 카운트
                connectedBridgeCount++;

            }

        }

        // 만약, 방문하지 못한 섬이 하나라도 있다면 실패
        for (int index = 1; index <= islands.size(); index++) {

            if (!isLinked[index]) {
                minBridgeLength = -1;
                return;
            }

        }

        // 사용한 다리의 개수가 섬보다 1개 적지 않으면 실패
        if (connectedBridgeCount != islands.size() - 1) {
            minBridgeLength = -1;
        }

    }

    public static void allocateIndex(int row, int col, int index) {

        // index를 가지는 섬에 대한 객체를 생성
        Island island = new Island(index);

        // 섬의 영역을 동일한 index로 관리하기 위해 탐색 시작
        Queue<int[]> areaQ = new ArrayDeque<>();
        isVisited[row][col] = true;
        areaQ.add(new int[] {row, col});

        while (!areaQ.isEmpty()) {

            int[] area = areaQ.poll();
            int curRow = area[0];
            int curCol = area[1];

            // 현재 지점에서 갈 수 있는 위치에 저장된 값을 섬의 인덱스로 변환 및 섬 영역에 추가
            map[curRow][curCol] = index;
            island.areas.add(new int[] {curRow, curCol});

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = curRow + dr[dir];
                int nCol = curCol + dc[dir];

                // 유효한 범위내에 존재하고
                if (!(0 <= nRow && nRow < rowSize && 0 <= nCol && nCol < colSize)) {
                    continue;
                }

                // 방문한 적 없는
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

        // 각 섬의 모든 영역을 탐색하여 건설할 수 있는 모든 다리를 건설
        for (int[] area : island.areas) {

            int row = area[0];
            int col = area[1];

            // 현재 영역에서 4방 탐색 진행
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

                    // 다른 섬에 도착한 경우 종료
                    if (ISLAND <= map[nRow][nCol]) {
                        isLinked = true;
                        break;
                    }

                    // 위 3조건을 모두 만족하지 않는다면 다리 건설
                    bridgeLength++;

                }

                // 섬이 연결되었고 이를 연결하는 다리의 길이가 2이상이라면
                if (isLinked && bridgeLength >= 2) {
                    // 출발, 도착 지점과 다리의 길이를 기록
                    bridges.add(new Bridge(island.idx, map[nRow][nCol], bridgeLength));
                }

            }

        }

    }

}