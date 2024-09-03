import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import javax.imageio.ImageTranscoder;

/**
 * BOJ_욕심쟁이판다
 * @author parkrootseok
 *
 * - 판다가 가장 많이 움직일 수 있는 횟수를 찾아라
 *  - 단, 판다는 이동할 위치에 있는 대나무수가 현재 있는 대나무보다 많아야 함
 */
public class Main {

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, 1, -1};

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;
    public static String[] inputs;

    public static int size;
    public static int[][] forest;
    public static int[][] isVisited;
    public static int max;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        forest = new int[size][size];
        for (int row = 0; row < size; row++) {
            inputs = br.readLine().trim().split(" ");
            for (int col = 0; col < size; col++) {
                forest[row][col] = Integer.parseInt(inputs[col]);
            }
        }

        isVisited = new int[size][size];
        max = Integer.MIN_VALUE;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                max = Math.max(max, dfs(row, col));
            }
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.close();

    }

    public static int dfs(int row, int col) {

        if (isVisited[row][col] != 0) {
            return isVisited[row][col];
        }

        isVisited[row][col] = 1;

        for (int dir = 0; dir < dr.length; dir++) {

            int nextRow = row + dr[dir];
            int nextCol = col + dc[dir];

            if (nextRow < 0 || size <= nextRow || nextCol < 0 || size <= nextCol) {
                continue;
            }

            if (forest[nextRow][nextCol] <= forest[row][col]) {
                continue;
            }

            isVisited[row][col] = Math.max(isVisited[row][col], dfs(nextRow, nextCol) + 1);;

        }

        return isVisited[row][col];

    }

}