package inflearn.sec09.ex06;

import java.util.Scanner;

/**
 * Section 9 - 6 : 친구인가?
 */
public class Main {

    static String ANSWER = "NO";
    static int[] unf;
    static int N, M;

    public static int Find(int v) {


        if (v == unf[v]) return v;
        else return unf[v] = Find(unf[v]);

    }

    public static void Union(int a, int b) {

        int fa = Find(a);
        int fb = Find(b);
        if (fa != fb) unf[fa] = fb;

    }


    public void solution(int A, int friend) {



    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        unf = new int[N+1];
        for (int i = 1 ; i <= N ; i++) {
            unf[i] = i;
        }

        int A, B;
        for (int i = 1; i <= M; i++) {

            A = sc.nextInt();
            B = sc.nextInt();

            Union(A, B);

        }

        A = sc.nextInt();
        B = sc.nextInt();

        int fa = Find(A);
        int fb = Find(B);

        if (fa == fb) {
            ANSWER = "YES";
        }

        System.out.println(ANSWER);

    }

}