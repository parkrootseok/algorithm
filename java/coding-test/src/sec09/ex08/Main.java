package sec09.ex08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge> {

    private int to;
    private int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
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
 * Section 9 - 7 : 원더랜드(프림)
 */
public class Main {

    static int ANSWER = 0;

    static int V, E;
    static boolean[] checked;

    static PriorityQueue<Edge> pQ = new PriorityQueue<>();
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();

    public void solution(int start) {

        pQ.offer(new Edge(1, 0));
        while (!pQ.isEmpty()) {

            Edge cur = pQ.poll();

            int from = cur.getTo();
            if (!checked[from]) {
                checked[from] = true;
                ANSWER += cur.getCost();
            }

            for (Edge next : graph.get(from)) {

                if (!checked[next.getTo()]) {
                    pQ.offer(new Edge(next.getTo(), next.getCost()));
                }

            }

        }


    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        checked = new boolean[V + 1];
        for (int i = 0 ; i <= V ; i++) {
            graph.add(new ArrayList<Edge>());
        }

        int from, to, cost;
        for (int i = 1; i <= E; i++) {

            from = sc.nextInt();
            to = sc.nextInt();
            cost = sc.nextInt();

            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));

        }


        m.solution(1);
        System.out.println(ANSWER);


    }

}