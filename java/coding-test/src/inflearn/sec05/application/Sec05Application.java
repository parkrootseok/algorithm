package inflearn.sec05.application;

import java.util.Scanner;
import inflearn.sec05.solution.Sec05Solution;

public class Sec05Application {

    public Sec05Application() {
    }

    private final Sec05Solution solution = new Sec05Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        String str = sc.next();
        System.out.println(solution.CheckParentheses(str));

    }

    public void ex02() {
        String str = sc.next();
        System.out.println(solution.removeCharacterInParentheses(str));
    }

    public void ex03() {

        int N = sc.nextInt();
        int[][] board = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int M = sc.nextInt();
        int[] moves = new int[M];
        for (int i = 0 ; i < M ; i++) {
            moves[i] = sc.nextInt();
        }

        System.out.println(solution.clawMachine(board, M, moves));

    }

    public void ex04() {

        String str = sc.next();
        System.out.println(solution.calculatePostfix(str));

    }

    public void ex05() {

        String str = sc.next();
        System.out.println(solution.ironRod(str));

    }

    public void ex06() {

        int n = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(solution.savePrincess(n, k));

    }

    public void ex07() {

        String r = sc.next();
        String c = sc.next();

        System.out.println(solution.isValidTimeTable(r, c));

    }

    public void ex08() {

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] risk = new int[N];
        for (int i = 0; i < N ; i++) {
            risk[i] = sc.nextInt();
        }

        System.out.println(solution.orderOfTreatment(risk, M));

    }

}

