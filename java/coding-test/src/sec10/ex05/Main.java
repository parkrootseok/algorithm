package sec10.ex05;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Section 10 - 5 : 동전 교환
 */
public class Main {

    static int N, T;
    static int[] coin, dy;

    public void  solution() {

        dy[0] = 0;

        for (int i = 0 ; i < N ; i++) {

            for (int j = coin[i] ; j <= T ; j++) {

                dy[j] = Math.min(dy[j], dy[j - coin[i]] + 1 );

            }

        }


    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        coin = new int[N];
        for (int i = 0 ; i < N ; i++) {
            coin[i] = sc.nextInt();
        }

        T = sc.nextInt();
        dy = new int[T + 1];
        Arrays.fill(dy, Integer.MAX_VALUE);

        m.solution();
        System.out.println(dy[T]);

    }

}