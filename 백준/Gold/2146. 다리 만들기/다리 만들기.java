import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_다리만들기
 * @author parkrootseok
 *
 */
public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int[][] map;
    static boolean[][] isVisited;
    static List<List<int[]>> islands;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            String[] inputs = br.readLine().trim().split(" ");
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);
            }
        }

        int island = 1;
        islands = new ArrayList<>();
        islands.add(new ArrayList<>());
        isVisited = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {

                if (isVisited[row][col] || map[row][col] == 0) {
                    continue;
                }

                map[row][col] = island;
                islands.add(new ArrayList<>());
                islands.get(island).add(new int[] {row, col});
                bfs(row, col, island);
                island++;

            }
        }


        int min = Integer.MAX_VALUE;
        for (int curIsland = 1; curIsland < islands.size(); curIsland++) {
            int length = bridge(curIsland);
            min = Math.min(min, length);
        }

        sb.append(min);
        bw.write(sb.toString());
        bw.close();

    }

    public static void bfs(int row, int col, int name) {

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {row, col});
        isVisited[row][col] = true;

        while (!queue.isEmpty()) {

            int[] cPos = queue.poll();
            int cRow = cPos[0];
            int cCol = cPos[1];

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = cRow + dr[dir];
                int nCol = cCol + dc[dir];

                if (outRange(nRow, nCol)) {
                    continue;
                }

                if (isVisited[nRow][nCol] || map[nRow][nCol] == 0) {
                    continue;
                }

                isVisited[nRow][nCol] = true;
                map[nRow][nCol] = name;
                islands.get(name).add(new int[] {nRow, nCol});
                queue.offer(new int[] {nRow, nCol});

            }

        }

    }

    public static int bridge(int number) {

        isVisited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] piece : islands.get(number)) {
            isVisited[piece[0]][piece[1]] = true;
            queue.add(new int[]{piece[0], piece[1], 0});
        }

        while (!queue.isEmpty()) {

            int[] cPos = queue.poll();
            int cRow = cPos[0];
            int cCol = cPos[1];
            int cLength = cPos[2];

            if (map[cRow][cCol] != 0 && map[cRow][cCol] != number) {
                return cLength - 1;
            }

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = cRow + dr[dir];
                int nCol = cCol + dc[dir];

                if (outRange(nRow, nCol)) {
                    continue;
                }

                if (isVisited[nRow][nCol]) {
                    continue;
                }

                isVisited[nRow][nCol] = true;
                queue.offer(new int[] {nRow, nCol, cLength + 1});

            }

        }

        return 0;

    }

    private static boolean outRange(int nRow, int nCol) {
        if (nRow < 0 || N <= nRow || nCol < 0 || N <= nCol) {
            return true;
        }
        return false;
    }

}