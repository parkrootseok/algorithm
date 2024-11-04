import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
/**
 * BOJ_아기상어
 * @author parkrootseok
 *
 * - 아기 상어
 *  - 크기 2
 *  - 1초마다 상하좌우로 이동
 *   - 큰 물고기는 지나갈 수 없음
 *   - 동일한 물고기는 지나갈 수만 있음
 *   - 작은 물고기는 지나갈 수 있음(or 먹을 수 있음)
 *    - 자신의 크기와 같은 수의 물고기를 먹을 때 크기 1증가
 *
 *  - 이동과 동시에 물고기 섭취
 *  - 더이상 먹을 굴고기가 없을 때까지 진행
 *
 */

public class Main {

    static int SHARK_POSITION = 9;

    public static class Shark {

        int row;
        int col;
        int size = 2;
        int eatCount;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
            this.eatCount = 0;
        }

        public void move(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void eat(int row, int col) {
            map[row][col] = 0;
            shark.eatCount++;
        }

        public void upgrade() {
            if (size == eatCount) {
                eatCount = 0;
                size++;
            }
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "size=" + size +
                    ", eat=" + eatCount +
                    '}';
        }
    }

    public static class Fish {

        int row;
        int col;
        int distance;

        public Fish(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static Shark shark;
    static List<Fish> fishes;
    static int size;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];
        fishes = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            String[] inputs = br.readLine().trim().split(" ");
            for (int col = 0; col < size; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);

                if (map[row][col] == SHARK_POSITION) {
                    shark = new Shark(row, col);
                    map[row][col] = 0;
                }
            }
        }

        int time = 0;
        while (true) {

            // 먹을 수 있는 생선
            Fish find = bfs();

            // 먹을 수 있는 생선이 없다면 종료
            if (Objects.isNull(find)) {
                break;
            }

            // 시간은 먹을 수 있는 생선으로 이동하는 시간만큼 증가
            time += find.distance;

            // 생선 먹기
            shark.eat(find.row, find.col);

            // 생선 위치로 이동
            shark.move(find.row, find.col);

            // 상어 크기 증가
            shark.upgrade();

        }

        sb.append(time);
        bw.write(sb.toString());
        bw.close();

    }

    public static Fish bfs() {

        boolean[][] isVisited = new boolean[size][size];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {

            // 거리가 동일
            if (o1[2] == o2[2]) {
                // 높이가 동일
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }

           return Integer.compare(o1[2], o2[2]);

        });
        queue.add(new int[]{shark.row, shark.col, 0});
        isVisited[shark.row][shark.col] = true;

        while (!queue.isEmpty()) {

            int[] cShark = queue.poll();
            int cRow = cShark[0];
            int cCol = cShark[1];
            int cDis = cShark[2];

            if (map[cRow][cCol] != SHARK_POSITION && 0 < map[cRow][cCol] && map[cRow][cCol] < shark.size) {
                return new Fish(cRow, cCol, cDis);
            }

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = cRow + dr[dir];
                int nCol = cCol + dc[dir];

                if (outRange(nRow, nCol) || isVisited[nRow][nCol] || shark.size < map[nRow][nCol]) {
                    continue;
                }

                isVisited[nRow][nCol] = true;
                queue.add(new int[]{nRow, nCol, cDis + 1});

            }

        }

        return null;

    }

    public static boolean outRange(int row, int col) {
        return row < 0 || size <= row || col < 0 || size <= col;
    }

}