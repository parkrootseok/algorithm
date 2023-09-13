package baekjoon.class2.p11050;

/**
 * 이항 계수 1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int[][] memory;

    public int solution(int n, int r) {

        if (memory[n][r] > 0) {
            return memory[n][r];
        }

        if (n == r || r == 0) {
            return 1;
        }

        return memory[n][r] = solution(n - 1, r - 1) + solution(n - 1, r);


    }
    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        memory = new int[n + 1][r + 1];
        System.out.println(m.solution(n, r));

    }

}