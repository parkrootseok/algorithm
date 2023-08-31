package inflearn.sec07.application;

import java.util.ArrayList;
import java.util.Scanner;
import inflearn.sec07.solution.BinaryTree;
import inflearn.sec07.solution.Node;
import inflearn.sec07.solution.Sec07Solution;

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

        System.out.println(solution.saveCalf(N, M));

    }

    public void ex09() {

        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));

        BinaryTree bTree = new BinaryTree(root);
        System.out.println(solution.minimumRouteDFS(0, bTree.getRoot()));
    }

    public void ex10() {

        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));

        BinaryTree bTree = new BinaryTree(root);
        System.out.println(solution.minimumRouteBFS(bTree.getRoot()));

    }

    public void ex12() {

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0 ; i < m ;i++) {

            int to = sc.nextInt();
            int from = sc.nextInt();

            graph[to][from] = 1;

        }

        boolean[] check = new boolean[n + 1];
        check[1] = true;
        solution.searchRouteForMatrix(n,1, graph, check);

    }

    public void ex13() {

        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        for (int i = 0 ; i <= n ;i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0 ; i < m ;i++) {
            int to = sc.nextInt();
            int from = sc.nextInt();

            graph.get(to).add(from);
        }


        boolean[] check = new boolean[n + 1];
        check[1] = true;
        solution.searchRouteForList(n,1, graph, check);

    }

    public void ex14() {

        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        for (int i = 0 ; i <= n ;i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0 ; i < m ;i++) {
            int to = sc.nextInt();
            int from = sc.nextInt();

            graph.get(to).add(from);
        }

        solution.findGraphMinimumRoute(n, graph);

    }

}

