package baekjoon.class2.p1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int CHESS_ROW = 8, CHESS_COLS = 8, MAX_TRANS_NUM = 64;
    public static final String[] BLACK_CHESS_BOARD = {"BWBWBWBW", "WBWBWBWB"};

    public static int M, N, ANSWER = Integer.MAX_VALUE;
    public static String[] board;

    public int solution(int sRow, int sCols) {

        int transNum = 0;
        for (int i = 0 ; i < CHESS_ROW ; i++) {

            int row = sRow + i;

            for (int j = 0 ; j < CHESS_COLS  ; j++) {

                int cols = sCols + j;

                if (board[row].charAt(cols) != BLACK_CHESS_BOARD[row % 2].charAt(j)) {
                    transNum++;
                }

            }

        }

        return Math.min(transNum, MAX_TRANS_NUM - transNum);

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new String[M];
        for (int i = 0 ; i < M ; i++) {
            board[i] = br.readLine();
        }

        // 체스판은 8 X 8 크기이므로 행과 열에서 8을 빼준다
        for (int i = 0 ; i <= M - CHESS_ROW ; i++) {
            for (int j = 0 ; j <= N - CHESS_COLS; j++) {
                int numbers = m.solution(i, j);
                ANSWER = Math.min(ANSWER, numbers);
            }
        }

        System.out.println(ANSWER);

    }


}