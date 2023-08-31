package inflearn.sec08.ex06;

import java.util.Scanner;

/**
 * Section 8 - 6 : 순열구하기
 */
public class Main {

    static int ANSWER = Integer.MAX_VALUE, N, M;

    public void solution(int L, int[] numbers, int[] seq, boolean[] visited) {

        if (L == M) {
            StringBuilder sb = new StringBuilder();
            for (int x : seq) {
                sb.append(x + " ");
            }
            System.out.println(sb);
            return;
        }

        for (int i = 0 ; i < N ; i++) {

            if (!visited[i]) {
                visited[i] = true;
                seq[L] = numbers[i];
                solution(L + 1, numbers, seq, visited);
                visited[i] = false; // 방문 기록 초기화
            }

        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        int[] numbers = new int[N];
        for (int i = 0 ; i < N ; i++) {
            numbers[i] = sc.nextInt();
        }

        int[] seq = new int[M];
        boolean[] visited = new boolean[N];
        m.solution(0, numbers, seq, visited);

    }

}