import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, L, ANSWER;
    static int[][] board;

    public static int solution(int cur) {

        int col = 0, row = 0, diagonal = 0;

        for (int i = 0 ; i < 100; i++) {

            if (cur == 0) {
                diagonal += board[i][i];
            }

            if (cur == 99) {
                diagonal += board[cur - i][i];
            }

            col += board[cur][i];
            row += board[i][cur];

        }

        if (cur == 0 || cur == 99) {
            return Math.max(diagonal, Math.max(col, row));
        }

        return Math.max(col, row);

    }

    public static void main(String args[]) throws Exception {

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i);
            N = Integer.parseInt(br.readLine());

            board = new int[100][100];
            for (int j = 0 ; j < board.length ; j++) {
                String[] inputs = br.readLine().split(" ");
                int k = 0;
                for (String input : inputs) {
                    board[j][k++] = Integer.parseInt(input);
                }
            }

            ANSWER = 0;
            for (int j = 0 ; j < 100 ; j++) {
                ANSWER = Math.max(ANSWER, solution(j));
            }

            bw.write(" "  + ANSWER + "\n");

        }

        bw.close();

    }

}