import java.io.*;
import java.nio.file.NotLinkException;
import java.util.*;

/**
 * BOJ_알고스팟
 * @author parkrootseok
 */
public class Main {

    public static class Node {

        int row;
        int col;
        int count;

        public Node(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

    }

    public static final int[] dr = {-1,1,0,0};
    public static final int[] dc = {0,0,-1,1};
    public static final int WALL = 1;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int N;
    public static int M;
    public static int[][] map;
    public static int[][] isVisited;
    public static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        map = new int[M][N];
        for (int row = 0; row < M; row++) {
            inputs = br.readLine().trim().split("");
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);
            }
        }

        min = Integer.MAX_VALUE;
        isVisited = new int[M][N];
        for (int row = 0; row < M; row++) {
            Arrays.fill(isVisited[row], Integer.MAX_VALUE);
        }
        bfs();

        sb.append(min);
        bw.write(sb.toString());
        bw.close();

    }

    public static void bfs() {

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0,0, 0));

        while (!queue.isEmpty()) {

            Node node = queue.poll();
            int curRow = node.row;
            int curCol = node.col;
            int curCount = node.count;

            if (curRow == M - 1 && curCol == N - 1) {
                min = Math.min(min, curCount);
            }

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = curRow + dr[dir];
                int nCol = curCol + dc[dir];

                if (nRow < 0 || M <= nRow || nCol < 0 || N <= nCol) {
                    continue;
                }

                if (curCount + 1 < isVisited[nRow][nCol] && map[nRow][nCol] == WALL) {
                    isVisited[nRow][nCol] = curCount + 1;
                    queue.add(new Node(nRow, nCol, curCount + 1));
                }

                else if (curCount < isVisited[nRow][nCol] && map[nRow][nCol] == 0) {
                    isVisited[nRow][nCol] = curCount;
                    queue.add(new Node(nRow, nCol, curCount));
                }

            }

        }
    }

}