package inflearn.sec10.ex03;

import java.util.Scanner;

/**
 * Section 10 - 3 : 최대 부분 증가 수열
 */
public class Main {

    static int ANSWER = 0;
    static int N;
    static int[] numbers, dis;

    public void  solution() {

        dis[0] = 1;
        for (int i = 1 ; i < numbers.length ; i++) {

            int max = 0;

            for (int j = i - 1 ; j >= 0 ; j--) {

                if (numbers[j] < numbers[i] && dis[j] > max) {
                    max = dis[j];
                }

                dis[j] = max + 1;
                ANSWER = Math.max(ANSWER, dis[j]);

            }


        }


    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        numbers = new int[N];
        dis = new int[numbers.length];
        for (int i = 0 ; i < N ; i++) {
            numbers[i] = sc.nextInt();
        }

        m.solution();
        System.out.println(ANSWER);

    }

}