import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

    static int[][] board;
    static int N, M;

    public static int solution(int x, int y) {

        int sum = 0;

        for (int i = 0 ; i < M ; i++) {

            for (int j = 0 ; j < M ; j++) {

                int nx = x + i;
                int ny = y + j;

                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    sum += board[nx][ny];
                }

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
            for (int j = 0 ; j < N ; j++) {
                for (int k = 0 ; k < N ; k++) {
                    max = Math.max(max, solution(j, k));
                }
            }
            
            bw.write(max + "\n");

        }

        bw.close();

    }

}