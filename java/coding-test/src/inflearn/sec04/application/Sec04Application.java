package inflearn.sec04.application;

import java.util.Scanner;
import inflearn.sec04.solution.Sec04Solution;

public class Sec04Application {

    public Sec04Application() {
    }

    private final Sec04Solution solution = new Sec04Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        int n  = sc.nextInt();
        String sign = sc.next();

        System.out.println(solution.votingResult(n, sign));

    }

    public void ex02() {

        String a = sc.next();
        String b = sc.next();

        System.out.println(solution.anagram(a, b));

    }

    public void ex03() {

        int a = sc.nextInt();
        int K = sc.nextInt();
        int[] A = new int[a];
        for (int i = 0 ; i < a ; i++) {

            A[i] = sc.nextInt();

        }

        for (int n : solution.kindOfSale(a, K, A)) {
            System.out.print(n + " ");
        }

    }

    public void ex04() {

        String s = sc.next();
        String t = sc.next();

        System.out.println(solution.findAnagram(s, t));

    }

    public void ex05() {

        int a = sc.nextInt();
        int k = sc.nextInt();
        int[] A = new int[a];
        for (int i = 0 ; i < a ; i++) {

            A[i] = sc.nextInt();

        }
        System.out.println(solution.maxNumber(A, k));

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

