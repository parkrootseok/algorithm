import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, X, Y, K, ANSWER;
    static int BLACK = 1, WHITE = 2;
    static int[][] board;

    public static void solution(int x, int y, int c) {

        if (board[x][y] == 0) {

            board[x][y] = c;    // (x, y)에 돌을 놓고

            for (int dx = -1; dx <= 1; dx++) {  // 좌, 우, 상, 하, 대각선의 돌을 뒤집자
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        flipStone(x, y, dx, dy, c);
                    }
                }
            }

        }

    }

    public static void flipStone(int x , int y, int dx, int dy, int c) {

        int opponent = (c == 1) ? 2 : 1;
        int nx = x + dx;
        int ny = y + dy;
        boolean flag = false;

        while (1 <= nx && nx <= N && 1 <= ny && ny <= N) {  // 유효한 범위 내에서

            if (board[nx][ny] == opponent) {    // 자신의 돌과 색이 다르다면
                nx += dx;
                ny += dy;
                flag = true;    // 뒤집을 수 있음
            } else if (board[nx][ny] == c) {    // 만약, 같은 돌을 만났을 때
                if (flag) { // 이전에 다른 색의 돌을 만났다면
                    while (nx != x || ny != y) {    // 이전의 만났던 모든 돌을 뒤집고
                        nx -= dx;
                        ny -= dy;
                        board[nx][ny] = c;
                    }
                }
                break;  // 종료
            } else {
                break;  // 둘 다 아니면 종료
            }

        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            M = Integer.parseInt(inputs[1]);

            board = new int[N + 1][N + 1];
            int mid = N / 2;
            board[mid][mid] = WHITE;
            board[mid][mid + 1] = BLACK;
            board[mid + 1][mid] = BLACK;
            board[mid + 1][mid + 1] = WHITE;

            for (int j = 0; j < M; j++) {

                inputs = br.readLine().split(" ");

                int x = Integer.parseInt(inputs[0]);
                int y = Integer.parseInt(inputs[1]);
                int color = Integer.parseInt(inputs[2]);

                solution(x, y, color);

            }


            int b = 0, w = 0;
            for (int j = 1 ; j <= N; j++) {

                for (int k = 1 ; k <= N; k++) {

                    if (board[j][k] == BLACK) {
                        b++;
                    } else if (board[j][k] == WHITE) {
                        w++;
                    }

                }
            }

            bw.write(" " + b + " " + w + "\n");

        }

        bw.close();

    }

}