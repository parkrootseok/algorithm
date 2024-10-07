import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_헌내기는친구가필요해
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

    static final char WALL = 'X';
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int M;
    static char[][] map;

    static int sRow;
    static int sCol;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        map = new char[N][M];

        for (int row = 0; row < N; row++) {
            char[] chars = br.readLine().trim().toCharArray();
            for (int col = 0; col < M; col++) {
                map[row][col] = chars[col];
                if (map[row][col] == 'I') {
                    sRow = row;
                    sCol = col;
                }
            }
        }

        int count =  bfs();
        if (count == 0) {
            sb.append("TT");
        } else {
            sb.append(count);
        }
        
        bw.write(sb.toString());
        bw.close();

    }

    public static int bfs() {

        boolean[][] isVisited = new boolean[N][M];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(sRow, sCol));
        isVisited[sRow][sCol] = true;

        int count = 0;
        while (!queue.isEmpty()) {

            Node node = queue.poll();
            int cRow = node.row;
            int cCol = node.col;

            if (map[cRow][cCol] == 'P') {
                count++;
            }

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = cRow + dr[dir];
                int nCOl = cCol + dc[dir];

                if (nRow < 0 || N <= nRow || nCOl < 0 || M <= nCOl) {
                    continue;
                }

                if (isVisited[nRow][nCOl]) {
                    continue;
                }

                if (map[nRow][nCOl] == WALL) {
                    continue;
                }

                isVisited[nRow][nCOl] = true;
                queue.offer(new Node(nRow, nCOl));

            }

        }

        return count;

    }

}