import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static int N, M, X, Y;
    static int ANSWER;
    static int[] sequence;
    static int[] dp;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution() {


        for (int i = 0; i < N - 1; i++) {

            for (int j = i + 1; j < N; j++) {

                if (sequence[i] < sequence[j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }

            }

        }


    }


    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            N = Integer.parseInt(br.readLine());
            sequence = new int[N];
            dp = new int[N];
            Arrays.fill(dp, 1);
            String[] inputs = br.readLine().split(" ");

            int j = 0;
            for (String input : inputs) {
                sequence[j++] = Integer.parseInt(input);
            }

            solution();
            Arrays.sort(dp);

            bw.write(dp[N - 1] + "\n");

        }

        bw.close();

    }

}