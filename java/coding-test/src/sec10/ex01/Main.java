package sec10.ex01;

import java.util.Scanner;

/**
 * Section 10 - 1 : 계단 오르기
 */
public class Main {

    static int ANSWER = 0;
    static int N;

    public void  solution(int sum) {

        if (sum > N) {
            return;
        }

        if (sum == N) {
            ANSWER++;
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