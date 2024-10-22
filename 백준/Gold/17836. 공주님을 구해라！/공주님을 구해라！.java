import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_공주님을구해라!
 * @author parkrootseok
 */

public class Main {

    static class Node {

        int row;
        int col;
        int time;
        int isPossible;

        public Node(int row, int col, int time, int isPossible) {
            this.row = row;
            this.col = col;
            this.time = time;
            this.isPossible = isPossible;
        }

    }


    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int rowSize;
    static int colSize;
    static int limit;
    static int[][] map;
    static int result;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        rowSize = Integer.parseInt(inputs[0]);
        colSize = Integer.parseInt(inputs[1]);
        limit = Integer.parseInt(inputs[2]);

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            inputs = br.readLine().trim().split(" ");
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);
            }
        }

        result = -1;
        bfs();

        if (result == -1) {
            sb.append("Fail");
        } else {
            sb.append(result);
        }

        bw.write(sb.toString());
        bw.close();

    }

    public static void bfs() {

        boolean[][][] isVisited = new boolean[2][rowSize][colSize];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, 0));
        isVisited[0][0][0] = true;

        while (!queue.isEmpty()) {

            Node node = queue.poll();
            int cRow = node.row;
            int cCol = node.col;
            int cTime = node.time;

            if (limit < cTime) {
                return;
            }

            if (cRow == rowSize - 1 && cCol == colSize - 1) {
                result = cTime;
                return;
            }

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = cRow + dr[dir];
                int nCol = cCol + dc[dir];

                if (nRow < 0 || rowSize <= nRow || nCol < 0 || colSize <= nCol) {
                    continue;
                }

                if (isVisited[node.isPossible][nRow][nCol]) {
                    continue;
                }

                if (node.isPossible == 0 && map[nRow][nCol] == 1) {
                   continue;
                }

                isVisited[node.isPossible][nRow][nCol] = true;

                if (map[nRow][nCol] == 0) {
                    queue.add(new Node(nRow, nCol, cTime + 1, node.isPossible));
                }

                else if (map[nRow][nCol] == 2) {
                    queue.add(new Node(nRow, nCol, cTime + 1, 1));
                }

                else if (node.isPossible == 1 && map[nRow][nCol] == 1) {
                    queue.add(new Node(nRow, nCol, cTime + 1, node.isPossible));
                }

            }

        }

    }

}