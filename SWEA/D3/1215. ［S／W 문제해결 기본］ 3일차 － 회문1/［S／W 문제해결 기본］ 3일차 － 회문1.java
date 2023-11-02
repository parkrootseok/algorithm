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
    static String[][] board;

    public static void solution(int start) {

        for (int i = 0 ; i <= 8 - N; i++) {

            StringBuilder A = new StringBuilder();
            StringBuilder B = new StringBuilder();

            for (int j = i ; j < N + i ; j++) {
                A.append(board[start][j]);
                B.append(board[j][start]);
            }

            if (A.toString().equals(A.reverse().toString())) {
                ANSWER++;
            }

            if (B.toString().equals(B.reverse().toString())) {
                ANSWER++;
            }

        }



    }

    public static void main(String args[]) throws Exception {

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i);
            N = Integer.parseInt(br.readLine());

            board = new String[8][8];
            for (int j = 0 ; j < board.length ; j++) {
                String[] inputs = br.readLine().split("");
                int k = 0;
                for (String input : inputs) {
                    board[j][k++] = input;
                }
            }

            ANSWER = 0;
            for (int j = 0 ; j < 8 ; j++) {
                solution(j);
            }

            bw.write(" "  + ANSWER + "\n");

        }

        bw.close();

    }

}