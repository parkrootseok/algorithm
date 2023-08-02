package sec02.application;

import java.util.Scanner;
import sec02.solution.Sec02Solution;

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

    }

    public void ex08() {
    }

    public void ex09() {
    }

    public void ex10() {

    }

    public void ex11() {

    }

    public void ex12() {

    }

}

