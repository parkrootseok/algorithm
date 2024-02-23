import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static int ANSWER;
    static int[][] stuff;
    static int[] knapsack;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution(int cur) {

        for (int L = K; stuff[cur][0] <= L; L--){
            knapsack[L] = Math.max(knapsack[L], knapsack[L - stuff[cur][0]] + stuff[cur][1]);
        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            K = Integer.parseInt(inputs[1]);

            knapsack = new int[K + 1];
            stuff = new int[N][2];
            for (int j = 0; j < N; j++) {
                inputs = br.readLine().split(" ");
                for (int k = 0; k < stuff[j].length; k++) {
                    stuff[j][k] = Integer.parseInt(inputs[k]);
                }
            }

            for (int j = 0; j < N; j++) {
                solution(j);
            }

            bw.write(knapsack[K] + "\n");

        }

        bw.close();

    }

}