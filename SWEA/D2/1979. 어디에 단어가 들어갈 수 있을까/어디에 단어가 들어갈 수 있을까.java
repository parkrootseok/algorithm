import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static int N, K;
    static int[][] board;
    static boolean[][] visited;

    public static int solution(int pos) {

        int xCount = 0, yCount = 0, answer = 0;

        for (int y = 0 ; y < N ; y++) {

            if (board[pos][y] == 1) {
                xCount++;
            } else {

                if (xCount == K) {
                    answer++;
                }

                xCount = 0;
            }

        }

        for (int x = 0 ; x < N ; x++) {

            if (board[x][pos] == 1) {
                yCount++;
            } else {

                if (yCount == K) {
                    answer++;
                }

                yCount = 0;
            }

        }

        if (xCount == K) {
            answer++;
        }

        if (yCount == K) {
            answer++;
        }

        return answer;

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);

            board = new int[N][N];
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {

                input = br.readLine().split(" ");

                int k = 0;
                for (String n : input) {
                    board[j][k++] = Integer.parseInt(n);
                }

            }

            int answer = 0;
            for (int j = 0; j < N; j++) {
                answer += solution(j);
            }

            bw.write(answer + "\n");

        }

        bw.close();

    }

}