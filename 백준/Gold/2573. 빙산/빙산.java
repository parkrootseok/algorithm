import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_빙산
 * @author parkrootseok
 *
 * - 빙산의 높이는 바닷물에 접한 수 만큼 감소
 *  - 동, 서, 남, 북 탐색 필요
 *  - 0이하로는 줄어들지 않음
 *
 * - 주어진 빙산이 두 덩어이로 분리되는 최초 시간을 구해라
 */
public class Main {

    public static final int SEA = 0;
    public static final int[] dr = {-1, 1, 0, 0};
    public static final int[] dc = {0, 0, -1, 1};

    static class Node {

        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    static class Ice {

        int row;
        int col;
        int height;

        public Ice(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Ice{" +
                    "row=" + row +
                    ", col=" + col +
                    ", height=" + height +
                    '}';
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int rowSize;
    static int colSize;
    static int[][] map;
    static List<Ice> ices;

    static boolean[][] isVisited;
    static boolean isFinished;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        rowSize = Integer.parseInt(inputs[0]);
        colSize = Integer.parseInt(inputs[1]);

        map = new int[rowSize][colSize];
        ices = new ArrayList<>();
        for (int row = 0; row < rowSize; row++) {
            inputs = br.readLine().trim().split(" ");
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);
                if (map[row][col] != SEA) {
                    ices.add(new Ice(row, col, map[row][col]));
                }
            }
        }

        int result = simulation();
        if (isFinished) {
            sb.append(result);
        } else {
            sb.append(0);
        }

        bw.write(sb.toString());
        bw.close();

    }

    public static int simulation() {

        int depth = 0;
        while (isPossible()) {

            if (ices.isEmpty()) {
                break;
            }

            for (int size = 0; size < ices.size(); size++) {

                Ice ice = ices.get(size);
                ice.height -= melt(ice.row, ice.col);
                if (ice.height < 0) {
                    ice.height = 0;
                }

            }

            depth++;

            for (int size = 0; size < ices.size(); size++) {
                Ice ice = ices.get(size);
                map[ice.row][ice.col] = ice.height;

                if (ice.height == 0) {
                    ices.remove(size);
                    size--;
                }
            }

        }

        return depth;

    }

    public static int melt(int row, int col) {

        int count = 0;
        for (int dir = 0; dir < dr.length; dir++) {

            int nRow = row + dr[dir];
            int nCol = col + dc[dir];

            if (!inRange(nRow, nCol)) {
                continue;
            }

            if (map[nRow][nCol] == SEA) {
                count++;
            }

        }

        return count;

    }

    public static boolean isPossible() {

        isVisited = new boolean[rowSize][colSize];
        int count = 0;
        for (int row = 0; row < rowSize; row++) {

            for (int col = 0; col < colSize; col++) {

                if (isVisited[row][col] || map[row][col] == SEA) {
                    continue;
                }

                count++;
                bfs(row, col);

            }

        }

        if (count >= 2) {
            isFinished = true;
            return false;
        }

        return true;

    }

    public static void bfs(int row, int col) {

        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(new Node(row, col));
        isVisited[row][col] = true;

        while (!nodes.isEmpty()) {

            Node node = nodes.poll();
            int cRow = node.row;
            int cCol = node.col;

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = cRow + dr[dir];
                int nCol = cCol + dc[dir];

                if (!inRange(nRow, nCol)) {
                    continue;
                }

                if (isVisited[nRow][nCol] || map[nRow][nCol] == SEA) {
                    continue;
                }

                isVisited[nRow][nCol] = true;
                nodes.offer(new Node(nRow, nCol));

            }

        }

    }

    public static boolean inRange(int nRow, int nCol) {
        if (nRow < 0 || rowSize <= nRow || nCol < 0 || colSize <= nCol) {
            return false;
        }
        return true;
    }

}