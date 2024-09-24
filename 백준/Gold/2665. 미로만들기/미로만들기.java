import java.io.*;
import java.util.*;

/**
 * BOJ_미로만들기
 * @author parkrootseok
 */
public class Main {

    static class Position {

        int row;
        int col;
        int count;

        public Position(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int BLACK = 0;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int size;
    public static int[][] map;
    public static int min;
    public static int[][] isVisited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];
        for (int row = 0; row < size; row++) {
            String[] inputs = br.readLine().trim().split("");
            for (int col = 0; col < size; col++) {

                map[row][col] = Integer.parseInt(inputs[col]);

            }

        }

        min = Integer.MAX_VALUE;
        isVisited = new int[size][size];
        for (int row = 0; row < size; row++) {
            Arrays.fill(isVisited[row], 50 * 50);
        }

        bfs();

        sb.append(min);
        bw.write(sb.toString());
        bw.close();

    }

    public static void bfs() {

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(0, 0, 0));

        while (!queue.isEmpty()) {

            Position position = queue.poll();
            int curRow = position.row;
            int curCol =  position.col;
            int curCount = position.count;

            if (curRow == size - 1 && curCol == size - 1) {
                min = Math.min(min, curCount);
            }

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = curRow + dr[dir];
                int nCol = curCol + dc[dir];

                if (nRow < 0 || size <= nRow || nCol < 0 || size <= nCol) {
                    continue;
                }

                if (isVisited[nRow][nCol] <= curCount) {
                    continue;
                }

                if (map[nRow][nCol] == BLACK) {

                    if (curCount + 1 < isVisited[nRow][nCol]) {
                        isVisited[nRow][nCol] = curCount + 1;
                        queue.add(new Position(nRow, nCol, isVisited[nRow][nCol]));
                    }

                }

                else {
                    isVisited[nRow][nCol] = curCount;
                    queue.add(new Position(nRow, nCol, isVisited[nRow][nCol]));
                }

            }

        }

    }

}