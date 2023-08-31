package inflearn.sec08.ex05;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Section 8 - 5 : 동전교환
 */
public class Main {

    static int ANSWER = Integer.MAX_VALUE, N, M;

    public void solution(int L, int sum, Integer[] coin) {

        if (sum > M) {
            return;
        }

        if (sum == M) {
            ANSWER = Math.min(ANSWER, L);
            return;
        }

        for (int i = 0; i < N; i++) {
            solution(L + 1, sum + coin[i], coin);
        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();   // 동전 종류
        Integer[] coin = new Integer[N];
        for (int i = 0 ; i < N ; i++) {
            coin[i] = sc.nextInt();
        }
        M = sc.nextInt();   // 거슬러줄 금액

        // 내림차순으로 변환하여 영향이 큰 수에 대하여 먼저 계산하여 성능 향상
        Arrays.sort(coin, Collections.reverseOrder());
        m.solution(0, 0, coin);
        System.out.println(ANSWER);

    }

}