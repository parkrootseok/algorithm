package sec08.ex09;

import java.util.Scanner;

/**
 * Section 8 - 9 : 조합 구하기
 */
public class Main {

    static int N, M;

    static int[] numbers;
    static boolean[] visited;

    public void solution(int L, int s) {

       if (L == M) {

           StringBuilder sb = new StringBuilder();

           for (int x : numbers) {
               sb.append(x + " ");
           }

           System.out.println(sb);

           return;
       }

       for (int i = s ; i <= N ; i++) {
           numbers[L] = i;
           solution(L + 1, i + 1);
       }

    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        numbers = new int[M];
        visited = new boolean[N + 1];

        m.solution(0, 1);

    }

}