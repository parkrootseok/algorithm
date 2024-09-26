import java.io.*;
import java.util.*;

/**
 * BOJ_안전영역
 * @author parkrootseok
 */
public class Main {

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int unsafe = -1;
    public static int safe = 1;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int N;
    public static int[][] map;
    public static int maxHeight;
    public static int[][] isVisited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            String[] inputs = br.readLine().split(" ");
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);
                maxHeight = Math.max(maxHeight, map[row][col]);
            }
        }

        int maxCount = 0;
        for (int h = 0; h < maxHeight; h++) {

            int count = 0;
            isVisited = new int[N][N];

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {

                    if (map[row][col] <= h) {
                        isVisited[row][col] = unsafe;
                    }

                    if (isVisited[row][col] == safe || isVisited[row][col] == unsafe) {
                        continue;
                    }

                    count++;
                    bfs(row, col, h);

                }
            }

            maxCount = Math.max(maxCount, count);

        }

        sb.append(maxCount);
        bw.write(sb.toString());
        bw.close();

    }

    public static void bfs(int row, int col, int height) {

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(row, col));
        isVisited[row][col] = safe;

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = node.row + dr[dir];
                int nCol = node.col + dc[dir];

                if (nRow < 0 || N <= nRow || nCol < 0 || N <= nCol) {
                    continue;
                }

                if (isVisited[nRow][nCol] == unsafe || isVisited[nRow][nCol] == safe) {
                    continue;
                }

                if (map[nRow][nCol] <= height) {
                    isVisited[nRow][nCol] = unsafe;
                    continue;
                }

                isVisited[nRow][nCol] = safe;
                queue.add(new Node(nRow, nCol));

            }

        }

    }

}