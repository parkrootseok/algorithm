package sec09.ex05;

import java.util.ArrayList;
import java.util.Arrays;
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
 * Section 9 - 5 : A로 부터의 최소 비용 거리 구하기
 */
public class Main {

    static int ANSWER = 0;

    static int V, E;
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
    static int[] distinct;


    public void solution(int from) {

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        edges.add(new Edge(from, 0));
        distinct[from] = 0;

        while (!edges.isEmpty()) {

            Edge cur = edges.poll();

            int v = cur.getTo();
            int c = cur.getCost();

            if (c > distinct[v]) {
                continue;
            }

            for (Edge next : graph.get(v)) {

                if (distinct[next.getTo()] > c + next.getCost()) {  // cur -> next로 가는 비용이 최소가 아니면

                    distinct[next.getTo()] = c + next.getCost();    // 비용을 갱신하고
                    edges.offer(new Edge(next.getTo(), c + next.getCost()));    // 다음 정점을 탐색

                }

            }


        }


    }

    public static void main(String[] args) {

        sec09.ex05.Main m = new sec09.ex05.Main();
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();
        for (int i = 0 ; i <= V ; i++) {
            graph.add(new ArrayList<Edge>());
        }

        int from, to, cost;
        for (int i = 0; i < E ; i++) {

            from = sc.nextInt();
            to = sc.nextInt();
            cost = sc.nextInt();
            graph.get(from).add(new Edge(to, cost));

        }

        distinct = new int[V + 1];
        Arrays.fill(distinct, Integer.MAX_VALUE);

        m.solution(1);

        for (int i = 2 ; i <= V ; i++) {
            if (distinct[i] != Integer.MAX_VALUE) {
                System.out.println(i + " : " + distinct[i]);
            } else {
                System.out.println(i + " : " + "impossible");
            }
        }

    }
}