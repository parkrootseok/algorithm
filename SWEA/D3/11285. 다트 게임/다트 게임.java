import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static int N, D, X, Y;
    static int ANSWER;

    static double[] board;
    static double[] position;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        board = new double[]{
                Math.pow(20, 2),
                Math.pow(40, 2),
                Math.pow(60, 2),
                Math.pow(80, 2),
                Math.pow(100, 2),
                Math.pow(120, 2),
                Math.pow(140, 2),
                Math.pow(160, 2),
                Math.pow(180, 2),
                Math.pow(200, 2)
        };

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            N = Integer.parseInt(br.readLine());    // 화살의 개수
            position = new double[N];

            ANSWER = 0;
            for (int j = 0; j < N; j++) {
                
                String[] inputs = br.readLine().split(" ");
                position[j] = Math.pow(Integer.parseInt(inputs[0]), 2) + Math.pow(Integer.parseInt(inputs[1]), 2);

                for (int k = 0; k < 10; k++) {

                    if(position[j] <= board[k]) {
                        ANSWER += (10 - k);
                        break;
                    }

                }

            }

            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}