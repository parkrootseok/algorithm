package sec06.application;

import java.util.ArrayList;
import java.util.Scanner;
import sec06.solution.Coordinate;
import sec06.solution.Sec06Solution;

public class Sec06Application {

    public Sec06Application() {
    }

    private final Sec06Solution solution = new Sec06Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        for (int x : solution.selectionSort(arr)) {
            System.out.print(x + " ");
        }

    }

    public void ex02() {

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        for (int x : solution.bubbleSort(arr)) {
            System.out.print(x + " ");
        }

    }

    public void ex03() {

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        for (int x : solution.insertionSort(arr)) {
            System.out.print(x + " ");
        }

    }

    public void ex04() {

        int S = sc.nextInt();
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        for (int x : solution.LeastRecentlyUsed(S, arr)) {
            System.out.print(x + " ");
        }

    }

    public void ex05() {

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.printf(solution.checkDuplication(arr));

    }

    public void ex06() {

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        for (int x : solution.mischief(arr)) {
            System.out.print(x + " ");
        }


    }

    public void ex07() {

        int N = sc.nextInt();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            coordinates.add(new Coordinate(x, y));
        }

        for (Coordinate c : solution.sortCoordinate(coordinates)) {
            System.out.println(c.getX() + " " + c.getY());
        }

    }

    public void ex08() {

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution.binarySearch(arr, M));

    }

    public void ex09() {

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution.musicVideo(arr, M));

    }

    public void ex10() {

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution.selectStall(arr, M));

    }

}

