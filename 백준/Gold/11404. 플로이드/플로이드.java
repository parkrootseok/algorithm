import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BOJ_플로이드
 * @author parkrootseok
 */

public class Main {

    static class City {

        int name;
        List<Node> adjacentCities = new ArrayList<>();

        public City(int name) {
            this.name = name;
        }

    }

    static class Node implements Comparable<Node> {

        int name;
        int cost;

        public Node(int name, int cost) {
            this.name = name;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static final int INF = 100_000_000;

    static City[] cities;
    static int cityCount;
    static int busCount;
    static int[][] cost;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        cityCount = Integer.parseInt(br.readLine().trim());
        cities = new City[cityCount];
        for (int city = 0; city < cityCount; city++) {
            cities[city] = new City(city);
        }

        busCount = Integer.parseInt(br.readLine().trim());
        for (int bus = 0; bus < busCount; bus++) {
            String[] inputs = br.readLine().trim().split(" ");

            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);

            cities[from - 1].adjacentCities.add(new Node(to - 1, c));
        }

        cost = new int[cityCount][cityCount];
        for (int city = 0; city < cityCount; city++) {
            dijkstra(city);
        }

        for (int from = 0; from < cityCount; from++) {
            for (int to = 0; to < cityCount; to++) {
                if (cost[from][to] == INF) {
                    sb.append("0").append(" ");    
                } else {
                    sb.append(cost[from][to]).append(" ");    
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();

    }

    public static void dijkstra(int start) {

        for (int city = 0; city < cityCount; city++) {
            cost[start][city] = INF;
        }

        boolean[] isVisited = new boolean[cityCount];
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        nodes.add(new Node(start, 0));
        cost[start][start] = 0;

        while (!nodes.isEmpty()) {

            Node cNode = nodes.poll();

            if (isVisited[cNode.name]) {
                continue;
            }

            isVisited[cNode.name] = true;

            for (Node nNode : cities[cNode.name].adjacentCities) {

                if (cost[start][nNode.name] > cost[start][cNode.name] + nNode.cost) {
                    cost[start][nNode.name] = cost[start][cNode.name] + nNode.cost;
                    nodes.add(new Node(nNode.name, cost[start][nNode.name]));
                }

            }

        }

    }

}