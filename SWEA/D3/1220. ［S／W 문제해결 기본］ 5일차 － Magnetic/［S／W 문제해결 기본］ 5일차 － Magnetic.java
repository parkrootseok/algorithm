import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K, ANSWER;
    static int[][] board;
    static int N_POLE = 1, S_POLE = 2;

    public static void solution(int col) {

        int last = 0;

        for (int row = 0 ; row < 100 ; row++) {

            if (board[row][col] == N_POLE) {

                last = N_POLE;

            } else if (board[row][col] == S_POLE) {

                if (last == N_POLE) {
                    ANSWER++;
                }

                last = S_POLE;

            }


        }

    }

    public static void main(String args[]) throws Exception {

//        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i);

            N = Integer.parseInt(br.readLine());

            board = new int[N][N];
            for (int j = 0; j < 100; j++) {
                int k = 0;
                String[] inputs = br.readLine().split(" ");
                for (String input : inputs) {
                    board[j][k++] = Integer.parseInt(input);
                }
            }

            ANSWER = 0;

            for (int j = 0; j < 100; j++) {
                solution(j);
            }

            bw.write(" " + ANSWER + "\n");
            bw.flush();

        }

        bw.close();

    }

}