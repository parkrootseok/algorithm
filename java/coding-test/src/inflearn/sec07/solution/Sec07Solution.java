package inflearn.sec07.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sec07Solution {

    static int ANSWER = 0;

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
    public void printBinaryNumber(int N) {

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
     * section 7 - 4 : 피보나치 수열
     */
    public int fibonacci(int N) {

        if (N == 1 || N == 2) {
            return 1;
        } else {
            return fibonacci(N - 1) + fibonacci(N - 2);
        }

    }

    /**
     * section 7 - 5 : 이진트리 순회
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

            for (int i = 1; i <= N; i++) {
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
            for (int i = 0; i < len; i++) {
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
     * section 7 - 8 : 송아지 구하기
     */
    public int saveCalf(int N, int M) {

        int[] ch = new int[10001];
        int[] distinct = {1, -1, 5};
        Queue<Integer> q = new LinkedList<>();

        ch[N] = 1;
        q.offer(N);

        int L = 0;
        while (!q.isEmpty()) {

            int len = q.size();

            for (int i = 0; i < len; i++) {
                int x = q.poll();

                for (int d : distinct) {
                    int nx = x + d;

                    if (nx == M)
                        return L + 1;
                    if (1 <= nx && nx <= 10000 && ch[nx] == 0) {
                        ch[nx] = 1;
                        q.offer(nx);
                    }

                }

            }

            L++;

        }

        return L;

    }

    /**
     * section 7 - 9 : Tree 말단 노드까지의 가장 짧은 경로(DFS)
     */
    public int minimumRouteDFS(int L, Node root) {

        if (root.getLeft() == null && root.getRight() == null) {
            return L;
        } else {
            return Math.min(minimumRouteDFS(L + 1, root.getLeft()), minimumRouteDFS(L + 1, root.getRight()));
        }


    }

    /**
     * section 7 - 10 : Tree 말단 노드까지의 가장 짧은 경로(BFS)
     */
    public int minimumRouteBFS(Node root) {

        Queue<Node> Q = new LinkedList<>();

        int L = 0;
        Q.offer(root);
        while (!Q.isEmpty()) {

            int len = Q.size();
            for (int i = 0; i < len; i++) {

                Node cur = Q.poll();
                Node lc = cur.getLeft(), rc = cur.getRight();

                if (lc == null && rc == null) {
                    return L;
                }

                if (lc != null) {
                    Q.offer(lc);
                }

                if (rc != null) {
                    Q.offer(rc);
                }

            }

            L++;

        }

        return L;

    }

    /**
     * section 7 - 12 : 경로 탐색(인접 행렬)
     */
    public void searchRouteForMatrix(int n, int to, int[][] graph, boolean[] check) {

        visitForMatrix(n, to, graph, check);
        System.out.println(ANSWER);

    }

    private boolean visitForMatrix(int n, int to, int[][] graph, boolean[] check) {

        if (to == n) {
            return true;
        }

        for (int i = 1; i <= n; i++) {

            if (graph[to][i] == 1 && check[i] == false) {
                check[i] = true;
                if (visitForMatrix(n, i, graph, check)) {
                    ANSWER++;
                }
                check[i] = false;
            }


        }

        return false;
    }

    /**
     * section 7 - 13 : 경로 탐색(인접 리스트)
     */
    public void searchRouteForList(int n, int to, ArrayList<ArrayList<Integer>> graph, boolean[] check) {

        visitForList(n, to, graph, check);
        System.out.println(ANSWER);

    }

    private boolean visitForList(int n, int to, ArrayList<ArrayList<Integer>> graph, boolean[] check) {

        if (to == n) {
            return true;
        }

        for (int v : graph.get(to)) {   // 연결된 노드 방문

            if (!check[v]) {
                check[v] = true;    // 방문 기록
                if (visitForList(n, v, graph, check)) { // 방문했을 때 원하는 정점이면 카운트
                    ANSWER++;
                }
                check[v] = false;   // 방문 기록 초기화
            }

        }

        return false;
    }

    /**
     * section 7 - 14 : 그래프 최단거리(BFS)
     */
    public void findGraphMinimumRoute(int n, ArrayList<ArrayList<Integer>> graph) {

        int[] dis = new int[n + 1];

        boolean[] check = new boolean[n + 1];
        minimumRoute(1, graph, dis, check);

        for (int i = 2 ; i <= n; i++) {
            System.out.println(i + " : " + dis[i]);
        }

    }

    private void minimumRoute(int to, ArrayList<ArrayList<Integer>> graph, int[] dis, boolean[] check) {

        Queue<Integer> q = new LinkedList<>();

        check[to] = true;
        dis[to] = 0;
        q.offer(to);
        while (!q.isEmpty()) {

            int cur = q.poll();

            for (int v : graph.get(cur)) {  //  현재 노드와 연결된 노드를 모두 탐색

                if (!check[v]) {    // 단, 이미 방문한 노드는 제외
                    check[v] = true;    // 방문한 노드 기록
                    q.offer(v);
                    dis[v] = dis[cur] + 1;  // 현재 노드로 부터 거리가 1씩 증가
                }

            }


        }


    }



}
