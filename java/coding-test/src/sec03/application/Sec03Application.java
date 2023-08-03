package sec03.application;

import java.util.Scanner;
import sec03.solution.Sec03Solution;

public class Sec03Application {

    public Sec03Application() {
    }

    private final Sec03Solution solution = new Sec03Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        int a = sc.nextInt();
        int[] A = new int[a];
        for (int i = 0 ; i < a ; i++) {

            A[i] = sc.nextInt();

        }

        int b = sc.nextInt();
        int[] B = new int[b];
        for (int i = 0 ; i < b ; i++) {

            B[i] = sc.nextInt();

        }

        for (int x: solution.mergeArray(A, B)) {
            System.out.print(x + " ");
        }


    }

    public void ex02() {

        int a = sc.nextInt();
        int[] A = new int[a];
        for (int i = 0 ; i < a ; i++) {

            A[i] = sc.nextInt();

        }

        int b = sc.nextInt();
        int[] B = new int[b];
        for (int i = 0 ; i < b ; i++) {

            B[i] = sc.nextInt();

        }

        for (int x: solution.findCommonElement(A, B)) {
            System.out.print(x + " ");
        }

    }

    public void ex03() {

        int a = sc.nextInt();
        int K = sc.nextInt();
        int[] A = new int[a];
        for (int i = 0 ; i < a ; i++) {

            A[i] = sc.nextInt();

        }

        System.out.println(solution.calculateMaximumSales(K, A));

    }

    public void ex04() {

        int n = sc.nextInt();
        solution.fibonacciSequence(n);

    }

    public void ex05() {

        int n = sc.nextInt();
        solution.countDecimalNumber(n);

    }

    public void ex06() {

        int rep = sc.nextInt();

        int[] numbers = new int[rep];

        for (int i = 0; i < rep;i++) {
            numbers[i] = sc.nextInt();
        }

        for (int x : solution.reverseNumberIsDecimal(rep, numbers)) {
            System.out.print(x + " ");
        }

    }

    public void ex07() {

        int rep = sc.nextInt();
        int[] numbers = new int[rep];

        for (int i = 0; i < rep;i++) {
            numbers[i] = sc.nextInt();
        }

        System.out.println(solution.score(rep, numbers));

    }

    public void ex08() {

        int rep = sc.nextInt();
        int[] numbers = new int[rep];

        for (int i = 0; i < rep;i++) {
            numbers[i] = sc.nextInt();
        }
        for (int x: solution.ranking(rep, numbers)) {
            System.out.print(x + " ");
        }
    }

    public void ex09() {

        int rep = sc.nextInt();
        int[][] numbers = new int[rep][rep];

        for (int i = 0; i < rep;i++) {
            for (int j = 0; j < rep;j++) {
                numbers[i][j] = sc.nextInt();
            }
        }

        System.out.println(solution.findMatrixMaxSum(rep, numbers));

    }

    public void ex10() {

        int rep = sc.nextInt();
        int[][] numbers = new int[rep][rep];

        for (int i = 0; i < rep;i++) {
            for (int j = 0; j < rep;j++) {
                numbers[i][j] = sc.nextInt();
            }
        }

        System.out.println(solution.findTop(rep, numbers));

    }

    public void ex11() {
        int rep = sc.nextInt();
        int[][] numbers = new int[rep][rep];

        for (int i = 0; i < rep;i++) {
            for (int j = 0; j < rep;j++) {
                numbers[i][j] = sc.nextInt();
            }
        }

        System.out.println(solution.selectClassPresident(rep, numbers));
    }

    public void ex12() {

        int N = sc.nextInt();   // 학생 수
        int M = sc.nextInt();   // 시험 횟수

        int[][] numbers = new int[M][N];

        for (int i = 0; i < M;i++) {
            for (int j = 0; j < N;j++) {
                numbers[i][j] = sc.nextInt();
            }
        }

        System.out.println(solution.mentor(M, N, numbers));

    }

}

