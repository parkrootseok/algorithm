package baekjoon.class2.p2798;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 블랙잭
 */
public class Main {

    private static int N, M, ANSWER = Integer.MIN_VALUE;
    private static int[] numbers, card;
    private static boolean[] checked;

    public void solution(int L) {

        if (L == 3) {

            int sum = Arrays.stream(card).sum();

            if (sum <= M) {
                ANSWER = Math.max(ANSWER, sum);
            }

            return;
        }


        for (int i  = 0 ; i < numbers.length ; i++) {

            if (!checked[i]) {
                checked[i] = true;
                card[L] = numbers[i];
                solution(L + 1);
                checked[i] = false;
             }

        }

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        numbers = new int[N];
        card = new int[3];
        checked = new boolean[N];
        for (int i = 0 ; i < N ; i++) {
            numbers[i] = sc.nextInt();
        }

        m.solution(0);
        System.out.println(ANSWER);

    }


}