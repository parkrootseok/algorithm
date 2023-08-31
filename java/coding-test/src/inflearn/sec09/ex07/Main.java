package inflearn.sec09.ex07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Edge implements Comparable<Edge> {

    private int from;
    private int to;
    private int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }

}

/**
 * Section 9 - 7 : 원더랜드(크루스칼)
 */
public class Main {

    static int ANSWER = 0;
    static int V, E;
    static int[] unf;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static int Find(int v) {

        if (v == unf[v]) {
            return v;
        } else {
            return unf[v] = Find(unf[v]);
        }
    }

    public static void Union(int A, int B) {

        int fa = Find(A);
        int fb = Find(B);

        if (fa != fb) unf[fa] = fb;

    }

    public void solution() {

        int cnt = 0;
        for (Edge e : edges) {

            if (Find(e.getTo()) != Find(e.getFrom())) {
                ANSWER += e.getCost();
                Union(e.getTo(), e.getFrom());
                cnt++;
            }

            if (cnt == E - 1) {
                break;
            }

        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        unf = new int[V + 1];
        for (int i = 1 ; i <= V ; i++) {
            unf[i] = i;
        }

        int from, to, cost;
        for (int i = 1; i <= E; i++) {

            from = sc.nextInt();
            to = sc.nextInt();
            cost = sc.nextInt();

            edges.add(new Edge(from, to ,cost));

        }

        Collections.sort(edges);

        m.solution();
        System.out.println(ANSWER);


    }

}