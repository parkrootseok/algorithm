package ser07.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import sec06.solution.Coordinate;

public class Sec07Solution {

    public Sec07Solution() {
    }

    /**
     * section 7 - 1 : 재귀함수
     */
    public void recursiveFunction(int N) {

        if (N == 1) {
            System.out.printf(N + " ");
            return;
        }

        recursiveFunction(N - 1);
        System.out.printf(N + " ");

    }

    /**
     * section 7 - 2 : 이진수 출력
     */
    public void printBinaryNumber (int N) {

        if (N == 0) {
            return;
        }

        printBinaryNumber(N / 2);
        System.out.printf((N % 2) + " ");

    }

    /**
     * section 7 - 3 : 팩토리얼
     */
    public int factorial(int N) {

        if (N == 1) {
            return 1;
        }

        return N * factorial(N - 1);

    }

    /**
     * section 6 - 4 : 피보나치 수열
     */
    public int fibonacci(int N) {

        if (N == 1 || N == 2) {
            return  1;
        } else {
            return fibonacci(N - 1) + fibonacci(N - 2);
        }

    }

    /**
     * section 6 - 5 : 이진트리 순회
     */
    public void traverseBinaryTree(BinaryTree bTree) {

        Node root = bTree.getRoot();

        System.out.println("전위 순회");
        PreOrder(root);
        System.out.println();

        System.out.println("중위 순회");
        InOrder(root);
        System.out.println();

        System.out.println("후위 순회");
        PostOrder(root);
        System.out.println();

    }

    private void PreOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.printf(node.getData() + " ");
        PreOrder(node.getLeft());
        PreOrder(node.getRight());
    }

    private void InOrder(Node node) {

        if (node == null) {
            return;
        }

        InOrder(node.getLeft());
        System.out.printf(node.getData() + " ");
        InOrder(node.getRight());
    }
    private void PostOrder(Node node) {

        if (node == null) {
            return;
        }

        PostOrder(node.getLeft());
        PostOrder(node.getRight());
        System.out.printf(node.getData() + " ");
    }

    /**
     * section 7 - 6 : 부분 집합 구하기(DFS : 깊이 우선 탐색)
     */
    public void findSubset(int L, int N, boolean[] check) {

        if (L == N + 1) { // 탈출 조건

            StringBuilder sb = new StringBuilder();

            for (int i = 1 ; i <= N ; i++) {
                if (check[i]) {
                    sb.append(i + " ");
                }
            }

            if (sb.length() > 0) {
                System.out.println(sb);
            }

            return;

        }

        check[L] = true;    // 원소 L을 사용
        findSubset(L + 1, N, check);

        check[L] = false;   // 원소 L을 미사용
        findSubset(L + 1, N, check);

    }

    /**
     * section 7 - 7 : 이진트리 레벨탐색(BFS : 넓이 우선 탐색)
     */
    public void searchLevel(Node root) {

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int L = 0;
        while (!q.isEmpty()) {

            int len = q.size();
            System.out.print(L + " : ");
            for (int i = 0 ; i < len ; i++) {
                Node cur = q.poll();
                System.out.print(cur.getData() + " ");

                if (cur.getLeft() != null) {
                    q.add(cur.getLeft());
                }

                if (cur.getRight() != null) {
                    q.add(cur.getRight());
                }
            }
            L++;
            System.out.println();
        }
    }

    /**
     * section 6 - 8 : 이분검색
     */
    public int binarySearch(int[] arr, int M) {

        int ans = 0;
        Arrays.sort(arr);

        int lt = 0, rt = arr.length - 1;

        while (lt <= rt) {

            int mid = (lt + rt) / 2;

            if (arr[mid] == M) {
                ans = mid + 1;
                break;
            } else if (arr[mid] < M) {
                lt = mid + 1;
            } else {
                rt =  mid - 1;
            }

        }


        return ans;
    }

    /**
     * section 6 - 9 : 뮤직비디오
     */
    public int musicVideo(int[] arr, int M) {

        int ans = 0;

        int lt = Arrays.stream(arr).max().getAsInt();
        int rt = Arrays.stream(arr).sum();

        while (lt <= rt) {

            int mid = (lt + rt) / 2;

            if (count(arr, mid) <= M) {
                ans = mid;
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }

        }

        return ans;

    }

    private int count(int[] arr, int mid) {

        int cnt = 1, sum = 0;
        for (int x : arr) {

            if (sum + x > mid) {
                cnt++;
                sum = x;
            } else {
                sum += x;
            }

        }

        return cnt;

    }

    /**
     * section 6 - 10 : 마구간 정하기
     */
    public int selectStall(int[] arr, int M) {

        int ans = 0;

        Arrays.sort(arr);
        int lt = 1;
        int rt = arr[arr.length - 1];

        while (lt <= rt) {

            int mid = (lt + rt) / 2;

            if (minDistinctCount(arr, mid) >= M) {  // M개 보다 많으면 간격이 더 넓어야 하고
                ans = mid;
                lt = mid + 1;
            } else {    // M개 보다 작으면 너무 긴격을 줄여야 함
                rt = mid - 1;
            }

        }

        return ans;

    }

    private int minDistinctCount(int[] arr, int mid) {

        int cnt = 1, sum = 0, ep = 0;

        for (int i = 0; i < arr.length ; i++) {

            if (arr[i] - arr[ep] >= mid) {
                cnt++;
                ep = i;
            }

        }

        return cnt;

    }

}
