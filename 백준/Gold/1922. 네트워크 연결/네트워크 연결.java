import java.io.*;
import java.util.PriorityQueue;
import javax.management.openmbean.CompositeType;

/**
 * BOJ_네트워크연결
 * @author parkrootseok
 */

public class Main {

    static class Link implements Comparable<Link> {

        int org;
        int dest;
        int cost;

        public Link(int org, int dest, int cost) {
            this.org = org;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Link n) {
            return Integer.compare(this.cost, n.cost);
        }

    }

    static final int INF = 10_000_000;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int computerCount;
    static int linkCount;
    static PriorityQueue<Link> links;
    static int result;

    static int[] unf;
    static int[] rank;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        computerCount = Integer.parseInt(br.readLine().trim());
        linkCount = Integer.parseInt(br.readLine().trim());
        links = new PriorityQueue<>();
        for (int count = 0; count < linkCount; count++) {
            String[] inputs = br.readLine().trim().split(" ");
            int org = Integer.parseInt(inputs[0]);
            int dest = Integer.parseInt(inputs[1]);
            int cost = Integer.parseInt(inputs[2]);
            links.offer(new Link(org, dest, cost));
        }

        result = 0;
        kruskal();

        sb.append(result);
        bw.write(sb.toString());
        bw.close();

    }

    public static void kruskal() {

        init();

        int connectedCount = 0;
        while (!links.isEmpty() && connectedCount < computerCount - 1) {

            Link link = links.poll();
            int org = link.org;
            int dest = link.dest;
            int cost = link.cost;

            if (find(org) == find(dest)) {
                continue;
            }

            union(org, dest);
            result += cost;
            connectedCount++;

        }

    }

    public static void init() {
        unf = new int[computerCount + 1];
        rank = new int[computerCount + 1];

        for (int idx = 0; idx < computerCount; idx++) {
            unf[idx] = idx;
        }

    }

    public static void union(int a, int b) {

        int findA = find(a);
        int findB = find(b);

        if (findA == findB) {
            return;
        }

        unf[findA] = findB;

    }

    public static int find(int a) {

        if (a == unf[a]) {
            return a;
        }

        return unf[a] = find(unf[a]);

    }

}