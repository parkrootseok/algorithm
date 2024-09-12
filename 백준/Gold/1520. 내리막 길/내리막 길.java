import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_내리막길
 * @author parkrootseok
 */
public class Main {

    // 상, 하, 좌, 우
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int colSize;
    public static int rowSize;
    public static int[][] map;
    public static int count;
    public static int[][] dp;
    public static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        rowSize = Integer.parseInt(inputs[0]);
        colSize = Integer.parseInt(inputs[1]);

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            inputs = br.readLine().trim().split(" ");
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(inputs[col]);
            }
        }

        count = 0;
        dp = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            Arrays.fill(dp[row], -1);
        }

        dfs(0, 0);

        sb.append(dp[0][0]);
        bw.write(sb.toString());
        bw.close();

    }

    public static int dfs(int row, int col) {

        // 목표 지점에 도착했을 경우 1 반환
        if (row == rowSize - 1 && col == colSize - 1)  {
            return 1;
        }

        // 이미 탐색한 경로에 대해서 기존 값을 반환
        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        // 처음 방문한 경우 초기화
        dp[row][col] = 0;
        
        for (int dir = 0; dir < dr.length;  dir++) {

            int nextRow = row + dr[dir];
            int nextCol = col + dc[dir];

            if (nextRow < 0 || rowSize <= nextRow || nextCol < 0 || colSize <= nextCol) {
                continue;
            }

            if (map[row][col] <= map[nextRow][nextCol]) {
                continue;
            }

            // 도착 여부를 반영
            dp[row][col] += dfs(nextRow, nextCol);
            
        }

        return dp[row][col];

    }

}