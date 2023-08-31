package inflearn.sec03.application;

import java.util.Scanner;
import inflearn.sec03.solution.Sec03Solution;

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

        int size = sc.nextInt();
        int target = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0 ; i < size ; i++) {

            arr[i] = sc.nextInt();

        }

        System.out.println(solution.continuousSubsequence(size, target, arr));

    }

    public void ex05() {

        int target = sc.nextInt();

        System.out.println(solution.sumContinuousNumber(target));

    }

    public void ex06() {

        int size = sc.nextInt();
        int rep = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0 ; i < size ; i++) {

            arr[i] = sc.nextInt();

        }

        System.out.println(solution.maximumLengthContinuousSubsequence(size, rep, arr));

    }
}

