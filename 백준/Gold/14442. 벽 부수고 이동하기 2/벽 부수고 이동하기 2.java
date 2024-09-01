import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BOJ_벽부수고이동하기2
 * @author parkrootseok
 */
public class Main {

    public static class Node {

        int row;
        int col;
        int count;
        int breakCount;

        public Node(int row, int col, int count, int breakCount) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.breakCount = breakCount;
        }

    }

    public static final int[] dr = {-1, 1, 0, 0};
    public static final int[] dc = {0, 0, -1, 1};

    public static final int TRUE = 0;
    public static final int FALSE = 1;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;
    public static String[] inputs;

    public static int N;
    public static int M;
    public static int K;
    public static int[][] map;
    public static boolean[][][] isVisited;
    public static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        K = Integer.parseInt(inputs[2]);

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            inputs = br.readLine().trim().split("");
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);
            }
        }

        min = Integer.MAX_VALUE;
        isVisited = new boolean[K + 1][N][M];
        sb.append(bfs());

        bw.write(sb.toString());
        bw.close();

    }

    public static int bfs() {

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 1, 0));
        for (int k = 0; k  < K; k++) {
            isVisited[k][0][0] = true;
        }

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            if(node.row == N - 1 && node.col ==  M - 1) {
                min = Math.min(min, node.count);
                return node.count;
            }

            int nextRow, nextCol;
            for (int dir = 0; dir < dr.length; dir++) {

                nextRow = node.row + dr[dir];
                nextCol = node.col + dc[dir];

                if (nextRow < 0 || N <= nextRow || nextCol < 0 || M  <= nextCol) {
                    continue;
                }

                if (isVisited[node.breakCount][nextRow][nextCol]) {
                    continue;
                }

                if (map[nextRow][nextCol] == FALSE && node.breakCount < K && !isVisited[node.breakCount + 1][nextRow][nextCol]) {
                    isVisited[node.breakCount + 1][nextRow][nextCol] = true;
                    queue.offer(new Node(nextRow, nextCol, node.count + 1, node.breakCount + 1));
                }

                else if (map[nextRow][nextCol] == TRUE) {
                    isVisited[node.breakCount][nextRow][nextCol] = true;
                    queue.offer(new Node(nextRow, nextCol, node.count + 1, node.breakCount));
                }

            }

        }

        return -1;

    }

}