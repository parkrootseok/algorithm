import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, R;
    static String ANSWER;
    static String[][] board;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution(int depth, int x, int y) {

        if (!board[x][y].equals(".")) {
            for (int i = 0; i < dx.length; i++) {
                check(depth + 1, x + dx[i], y + dy[i], dx[i], dy[i]);
            }
        }

        return;

    }

    public static void check(int depth, int x, int y, int dx, int dy) {

        if (!(0 <= x & x < N & 0 <= y & y < N)) {
            return;
        } else if (board[x][y].equals(".")) {
            return;
        } else if (depth == 5) {
            ANSWER = "YES";
            return;
        }

        check(depth + 1, x + dx, y + dy, dx, dy);

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            N = Integer.parseInt(br.readLine());

            board = new String[N][N];
            for (int j = 0; j < N; j++) {
                board[j] = br.readLine().split("");
            }

            ANSWER = "NO";
            for (int j = 0; j < N; j++) {

                if (ANSWER.equals("YES")) {
                    break;
                }
                for (int k = 0; k < N; k++) {
                    solution(1, j, k);
                }

            }

            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}