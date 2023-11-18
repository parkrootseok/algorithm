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

    static int N, D, X, Y;
    static int ANSWER;
    static int[] sequence;
    static int[] dp;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution() {

        double scope = D * 2 + 1;
        ANSWER = (int) Math.ceil(N / scope);

    }


    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);    // 꽃의 개수
            D = Integer.parseInt(inputs[1]);    // 분무기의 범위 +-

            solution();

            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}