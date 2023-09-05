package baekjoon.class2.p2231;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static int M, ANSWER = Integer.MAX_VALUE;

    public void solution() {

        for (int i = 1 ; i < M; i++) {

            int sum = 0;
            int tmp = i;

            while (tmp > 0) {
                sum += tmp % 10;
                tmp /= 10;
            }

            sum += i;

            if (M == sum) {
                ANSWER = Math.min(ANSWER, i);
            }

        }


        if (ANSWER == Integer.MAX_VALUE) {
            ANSWER = 0;
        }

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();

        m.solution();
        System.out.println(ANSWER);

    }


}