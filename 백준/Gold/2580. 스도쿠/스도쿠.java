import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BOJ_스도쿠
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static final int SIZE = 9;
    static int[][] map;
    static List<int []> candidates;
    static boolean isFinished;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        map = new int[SIZE][SIZE];
        candidates = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            String[] inputs = br.readLine().trim().split(" ");
            for (int col = 0; col < SIZE; col++){
                map[row][col] = Integer.parseInt(inputs[col]);

            }
        }

        isFinished = false;
        sudoku();

        bw.write(sb.toString());
        bw.close();

    }

    public static void sudoku() {

        if (isFinished) {
            return;
        }

        int[] pos = getCandidate();

        if (Objects.isNull(pos)) {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    sb.append(map[row][col]).append(" ");
                }
                sb.append("\n");
            }
            isFinished = true;
            return;
        }

        for (int number = 1; number <= SIZE; number++) {
            if (isPossible(pos[0], pos[1], number)) {
                map[pos[0]][pos[1]] = number;
                sudoku();
                map[pos[0]][pos[1]] = 0;
            }
        }

    }

    public static int[] getCandidate() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (map[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }

        return null;
    }

    public static boolean isPossible(int cRow, int cCol, int number) {

        for (int idx = 0; idx < SIZE; idx++) {

            if (map[idx][cCol] == number || map[cRow][idx] == number) {
                return false;
            }

        }

        int sRow = cRow / 3 * 3;
        int sCol = cCol / 3 * 3;
        for (int row = sRow; row < sRow +3; row++) {
            for (int col = sCol; col < sCol +3; col++) {

                if (map[row][col] == number) {
                    return false;
                }

            }
        }

        return true;

    }

}