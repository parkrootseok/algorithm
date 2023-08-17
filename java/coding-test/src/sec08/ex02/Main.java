package sec08.ex02;

import java.util.Scanner;

/**
 * Section 8 - 2 : 바둑이 승차
 */
public class Main {

    static int ANSWER = Integer.MIN_VALUE, N, C;

    public void solution(int start, int sum, int[] weight) {

        if (sum > C) {
            return;
        }
        if (start == N) {
            ANSWER = Math.max(ANSWER, sum);
            return;
        }

        solution(start + 1, sum + weight[start], weight);
        solution(start + 1, sum, weight);
    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        C = sc.nextInt();
        N = sc.nextInt();
        int[] weight = new int[N];
        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
        }

        m.solution(0, 0, weight);
        System.out.println(ANSWER);

    }
}