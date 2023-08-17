package sec08.ex04;

import java.util.Scanner;

/**
 * Section 8 - 4 : 중복 순열
 */
public class Main {

    static int N, M;

    public void solution(int L, int[] seq) {

        if (L == M) {

            StringBuilder sb =  new StringBuilder();

            for (int x : seq) {
               sb.append(x + " ");
            }

            System.out.println(sb);
            return;

        }

        for(int i = 1 ; i <= N ; i++) {
            seq[L] = i;
            solution(L + 1, seq);
        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        int[] seq = new int[M];
        m.solution(0, seq);
    }

}