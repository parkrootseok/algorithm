package inflearn.sec10.ex02;

import java.util.Scanner;

/**
 * Section 10 - 2 : 돌다리 건너기;
 */
public class Main {

    static int ANSWER = 0;
    static int N;

    public void  solution(int sum) {

        if (sum >= N) {
            ANSWER++;
            return;
        }

        for (int i = 1; i <= 2; i++) {
            solution(sum + i);
        }


    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        m.solution(0);
        System.out.println(ANSWER);

    }

}