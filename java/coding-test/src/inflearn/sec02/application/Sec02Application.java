package inflearn.sec02.application;

import java.util.Scanner;
import inflearn.sec02.solution.Sec02Solution;

public class Sec02Application {

    public Sec02Application() {
    }

    private final Sec02Solution solution = new Sec02Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0 ; i < size ; i++) {

            arr[i] = sc.nextInt();

        }

        for (int x: solution.printBiggerThanFront(size, arr)) {
            System.out.print(x + " ");
        }


    }

    public void ex02() {

        int studentNumber = sc.nextInt();

        int[] height = new int[studentNumber];
        for (int i = 0; i < studentNumber;i++) {
            height[i] = sc.nextInt();

        }

        System.out.println(solution.calculateVisibleStudent(studentNumber, height));


    }

    public void ex03() {

        int rep = sc.nextInt();

        int[] A = new int[rep];
        int[] B = new int[rep];

        for (int i = 0; i < rep;i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 0; i < rep;i++) {
            B[i] = sc.nextInt();
        }

        for (char w: solution.whoIsWinner(rep, A, B)) {
            System.out.println(w);
        }

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

