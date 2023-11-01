import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, ANSWER;
    static int[] board;

    public static boolean check(int D) {

        for (int i = 0 ; i < D ; i++) {
            
            if(board[D] == board[i]) {
                return false;
            }

            else if(Math.abs(D - i) == Math.abs(board[D] - board[i])){
                return false;
            }
        }

        return true;

    }

    public static void dfs(int D) {

        if (D == N) {
            ANSWER++;
            return;
        }

        for (int i = 0 ; i < N ; i++) {

            board[D] = i;

            if (check(D)) {
                dfs(D + 1);
            }

        }


    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            N = Integer.parseInt(br.readLine());
            board = new int[N];

            ANSWER = 0;
            dfs(0);
            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}