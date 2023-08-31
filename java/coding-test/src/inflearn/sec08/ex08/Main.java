package inflearn.sec08.ex08;

import java.util.Scanner;

/**
 * Section 8 - 8 : 수열 추측하기
 */
public class Main {

    static int N, F;
    static int[] numbers, p;
    static int[][] mem;
    static boolean flag = false;
    static boolean[] visited;

    public int memoryInit(int N, int R) {

        if (mem[N][R] > 0) {
            return mem[N][R];
        }
        if (N == R || R == 0) {
            return 1;
        }
        return  mem[N][R] = memoryInit(N - 1, R - 1) + memoryInit(N - 1, R);


    }

    public void solution(int L, int sum) {

        if (flag) {
            return;
        }

        if (L == N) {
            if (sum == F) {
                for (int x : numbers) {
                    System.out.printf(x + " ");
                }
                flag = true;
                return;
            }
            return;
        }



        for (int i = 0 ; i < N ; i++) {

            if (!visited[i]) {
                visited[i] = true;
                numbers[L] = i + 1;
                solution(L + 1, sum + (numbers[L] * p[L]));
                visited[i] = false;
            }

        }

    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        F = sc.nextInt();

        visited = new boolean[N];
        numbers = new int[N];
        mem = new int[N + 1][N + 1];
        p = new int[N];
        for (int i = 0 ; i < N ; i++) {
            p[i] = m.memoryInit(N - 1,i);
        }

        m.solution(0, 0);


    }

}