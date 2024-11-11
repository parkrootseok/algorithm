import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * BOJ_네트워크연결
 * @author parkrootseok
 */

public class Main {

    static class Computer {

        int name;
        ArrayList<Node> adjacent;

        public Computer(int name) {
            this.name = name;
            this.adjacent = new ArrayList<>();
        }

    }

    static class Node implements Comparable<Node> {

        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }

    }

    static final int INF = 10_000_000;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int computerCount;
    static int linkCount;

    static int[] minCost;
    static Computer[] computers;
    static int result;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        computerCount = Integer.parseInt(br.readLine().trim());
        minCost = new int[computerCount + 1];
        computers = new Computer[computerCount + 1];
        for (int count = 1; count <= computerCount; count++) {
            computers[count] = new Computer(count);
        }

        linkCount = Integer.parseInt(br.readLine().trim());
        for (int count = 0; count < linkCount; count++) {
            String[] inputs = br.readLine().trim().split(" ");
            int org = Integer.parseInt(inputs[0]);
            int dest = Integer.parseInt(inputs[1]);
            int cost = Integer.parseInt(inputs[2]);
            computers[org].adjacent.add(new Node(dest, cost));
            computers[dest].adjacent.add(new Node(org, cost));
        }

        result = 0;
        prim();
        for (int idx = 1; idx <= computerCount; idx++) {
            result += minCost[idx];
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.close();

    }

    public static void prim() {

        boolean[] isVisited = new boolean[computerCount + 1];
        PriorityQueue<Node> nodes = new PriorityQueue<>();

        Arrays.fill(minCost, INF);
        nodes.offer(new Node(1, 0));
        minCost[1] = 0;

        while (!nodes.isEmpty()) {

            Node cNode = nodes.poll();
            Computer cCom = computers[cNode.dest];

            if (isVisited[cCom.name]) {
                continue;
            }

            isVisited[cCom.name] = true;

            for (Node nNode : cCom.adjacent) {

                if (!isVisited[nNode.dest] && minCost[nNode.dest] > nNode.cost) {
                    minCost[nNode.dest] = nNode.cost;
                    nodes.offer(new Node(nNode.dest, minCost[nNode.dest]));
                }

            }

        }

    }

}