import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static int[][] board;
    static int N, M;

    public static int checkPlus(int x, int y) {

        int sum = board[x][y];


        for (int i = 1 ; i < M ; i++) {

            int px = x - i;
            int nx = x + i;

            int py = y - i;
            int ny = y + i;


            if(0 <= px && px < N) {
                sum += board[px][y];
            }

            if(0 <= nx && nx < N) {
                sum += board[nx][y];
            }

            if(0 <= py && py < N) {
                sum += board[x][py];
            }

            if(0 <= ny && ny < N) {
                sum += board[x][ny];
            }

        }

        return sum;

    }

    public static int checkCross(int x, int y) {


        int sum = board[x][y];

        for (int i = 1 ; i < M ; i++) {

            int px = x - i;
            int py = y - i;

            int nx = x + i;
            int ny = y + i;


            if(0 <= px && px < N && 0 <= py && py < N) {
                sum += board[px][py];
            }

            if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                sum += board[nx][ny];
            }

            if(0 <= nx && nx < N && 0 <= py && py < N) {
                sum += board[nx][py];
            }

            if(0 <= px && px < N && 0 <= ny && ny < N) {
                sum += board[px][ny];
            }

        }

        return sum;


    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            board = new int[N][N];
            for (int j = 0; j < N; j++) {
                input = br.readLine().split(" ");

                for (int k = 0; k < input.length; k++) {
                    board[j][k] = Integer.parseInt(input[k]);
                }

            }

            long max = Long.MIN_VALUE;
            long pMax = Long.MIN_VALUE, cMax = Long.MIN_VALUE;
            for (int j = 0 ; j < N ; j++) {
                for (int k = 0 ; k < N ; k++) {
                    pMax = checkPlus(j, k);
                    cMax = checkCross(j, k);
                    max = Math.max(max, Math.max(pMax, cMax));
                }
            }

            bw.write(max + "\n");

        }

        bw.close();

    }

}