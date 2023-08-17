package sec08.ex03;

import java.util.Scanner;

/**
 * Section 8 - 3 : 최대 점수 구하기
 */
public class Main {

    static int ANSWER = Integer.MIN_VALUE, N, M;

    public void solution(int start, int totalScore, int totalTime, int[] score, int[] time) {

        if (totalTime > M) {
            return;
        }
        if (start == N) {
            ANSWER = Math.max(ANSWER, totalScore);
            return;
        }

        solution(start + 1, totalScore + score[start], totalTime + time[start], score, time);
        solution(start + 1, totalScore, totalTime, score, time);
    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        int[] score = new int[N];
        int[] time = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = sc.nextInt();
            time[i] = sc.nextInt();
        }

        m.solution(0, 0, 0, score, time);
        System.out.println(ANSWER);

    }
}