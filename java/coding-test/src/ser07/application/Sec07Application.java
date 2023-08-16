package ser07.application;

import java.util.ArrayList;
import java.util.Scanner;
import sec06.solution.Coordinate;
import ser07.solution.BinaryTree;
import ser07.solution.Node;
import ser07.solution.Sec07Solution;

public class Sec07Application {

    public Sec07Application() {
    }

    private final Sec07Solution solution = new Sec07Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        int N = sc.nextInt();
        solution.recursiveFunction(N);

    }

    public void ex02() {

        int N = sc.nextInt();
        solution.printBinaryNumber(N);

    }

    public void ex03() {
        int N = sc.nextInt();
        System.out.println(solution.factorial(N));
    }

    public void ex04() {

        int N = sc.nextInt();

        for (int i = 1 ; i <= N ; i++) {
            System.out.printf(solution.fibonacci(i) + " ");
        }
    }

    public void ex05() {

        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));

        BinaryTree bTree = new BinaryTree(root);

        solution.traverseBinaryTree(bTree);

    }

    public void ex06() {

        int N = sc.nextInt();
        boolean[] check = new boolean[N + 1];

        solution.findSubset(1, N, check);

    }

    public void ex07() {

        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));

        BinaryTree bTree = new BinaryTree(root);
        solution.searchLevel(bTree.getRoot());

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

