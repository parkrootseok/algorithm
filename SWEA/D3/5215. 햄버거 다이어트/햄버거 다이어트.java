import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    static int N, L, ANSWER;
    static int[] score;
    static int[] cal;

    public static void solution(int depth, int s, int c) {

        if (c > L) {
            return;
        }

        if (c <= L) {
            ANSWER = Math.max(ANSWER, s);
        }

        if(depth == N) {
            return;
        }

        solution(depth + 1, s + score[depth], c + cal[depth]);
        solution(depth + 1, s, c);

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            L = Integer.parseInt(inputs[1]);

            score = new int[N];
            cal = new int[N];
            for (int j = 0 ; j < N ; j++) {
                inputs = br.readLine().split(" ");
                score[j] = Integer.parseInt(inputs[0]);
                cal[j] = Integer.parseInt(inputs[1]);
            }

            ANSWER = 0;
            solution(0, 0, 0);
            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}