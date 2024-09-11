import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BOJ_파티
 * @author parkrootseok
 */
public class Main {

    public static class Vertex implements Comparable<Vertex> {

        int index;
        int cost;
        List<AdjacentVertex> adjacentVertices;

        public Vertex(int index) {
            this.index = index;
            this.adjacentVertices = new ArrayList<>();
        }

        @Override
        public int compareTo(Vertex v) {
            return Integer.compare(this.cost, v.cost);
        }

    }

    public static class AdjacentVertex {

        int to;
        int weight;

        public AdjacentVertex(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    public static final int INF = 10_000_000;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int N;
    public static int M;
    public static int X;

    public static Vertex[] vertices;
    public static int[] costs;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        X = Integer.parseInt(inputs[2]);

        vertices = new Vertex[N + 1];
        for (int idx = 1; idx <= N; idx++){
            vertices[idx] =  new Vertex(idx);
        }

        for (int idx = 0; idx < M; idx++) {

            inputs = br.readLine().trim().split(" ");

            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            int cost= Integer.parseInt(inputs[2]);

            vertices[from].adjacentVertices.add(new AdjacentVertex(to, cost));

        }

        costs = new int[N + 1];
        for (int to = 1; to <= N; to++){
            
            if (to == X) {
                continue;
            }

            dijkstra(to);
            costs[to] = vertices[X].cost;
            
        }

        dijkstra(X);
        int max = Integer.MIN_VALUE;
        for (int to = 1; to <= N; to++){
            max = Math.max( max, costs[to] + vertices[to].cost);
        }
        
        sb.append(max);
        bw.write(sb.toString());
        bw.close();

    }

    public static void dijkstra(int start) {

        boolean[] isVisited = new boolean[N + 1];

        PriorityQueue<Vertex> verticesQ = new PriorityQueue<>();
        for (int idx = 1; idx <= N; idx++) {

            if (idx == start)  {
                continue;
            }

            vertices[idx].cost = INF;

        }

        verticesQ.add(vertices[start]);
        vertices[start].cost = 0;

        while (!verticesQ.isEmpty()) {

            Vertex curVertex = verticesQ.poll();
            int weight = curVertex.cost;

            if (isVisited[curVertex.index]) {
                continue;
            }

            isVisited[curVertex.index] = true;

            for (AdjacentVertex adjacentVertex : curVertex.adjacentVertices) {

                Vertex nextVertex = vertices[adjacentVertex.to];

                if (nextVertex.cost > weight + adjacentVertex.weight) {
                    nextVertex.cost = weight + adjacentVertex.weight;
                    verticesQ.add(nextVertex);
                }

            }

        }


    }


}