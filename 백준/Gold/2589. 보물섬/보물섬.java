import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * BOJ_보물섬
 *
 * @author parkrootseok
 * <p>
 * - 보물 - 최단 거리이면서 가장 긴 시간이 걸리는 육지 두 곳 - 두 곳을 최단 거리로 이동하는 시간?
 */
public class Main {

    public static class Node {

        int row;
        int col;
        int length;

        public Node(int row, int col, int length) {
            this.row = row;
            this.col = col;
            this.length = length;
        }

    }

    public static final char L = 'L';
    public static final char W = 'W';
    public static final int[] dr = {-1, 1, 0, 0};
    public static final int[] dc = {0, 0, -1, 1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;
    public static String[] inputs;

    public static int N;
    public static int M;
    public static int answer;
    public static char[][] map;
    public static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        map = new char[N][M];
        for (int row = 0; row < N; row++) {
            char[] chars = br.readLine().trim().toCharArray();
            for (int col = 0; col < M; col++) {
                map[row][col] = chars[col];
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] == L) {
                    isVisited = new boolean[N][M];
                    answer = Math.max(answer, bfs(row, col));
                }
            }
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.close();

    }

    public static int bfs(int row, int col) {

        int length = 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(row, col, 0));
        isVisited[row][col] = true;

        while (!queue.isEmpty()) {
            
            Node node = queue.poll();

            for (int dir = 0; dir < dr.length; dir++) {
                
                int nextRow = node.row + dr[dir];
                int nextCol = node.col + dc[dir];

                if (nextRow < 0 || N <= nextRow || nextCol < 0 || M <= nextCol) {
                    continue;
                }

                if (map[nextRow][nextCol] == W) {
                    continue;
                }
                
                if (isVisited[nextRow][nextCol]) {
                    continue;
                }

                queue.offer(new Node(nextRow, nextCol, node.length + 1));
                isVisited[nextRow][nextCol] = true;
                length = Math.max(length, node.length + 1);

            }

        }

        return length;

    }

}