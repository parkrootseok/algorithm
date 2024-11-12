import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_트리의지름
 * @author parkrootseok
 */

public class Main {

    static class Vertex {

        int name;
        List<Node> adjacent;

        public Vertex(int name) {
            this.name = name;
            this.adjacent = new ArrayList<>();
        }

    }

    static class Node {

        int dest;
        int weight;

        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

    }

    public static int INF = 10_000_000;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;


    static int vertexCount;
    static Vertex[] vertices;
    static boolean[] isVisited;
    static int max;
    static int v1;
    static int v2;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        vertexCount = Integer.parseInt(br.readLine().trim());
        vertices = new Vertex[vertexCount + 1];
        for (int idx = 1; idx <= vertexCount; idx++) {
            vertices[idx] = new Vertex(idx);
        }

        for (int idx = 1; idx < vertexCount; idx++) {

            String[] inputs = br.readLine().trim().split(" ");
            int org = Integer.parseInt(inputs[0]);
            int dest = Integer.parseInt(inputs[1]);
            int weight = Integer.parseInt(inputs[2]);

            vertices[org].adjacent.add(new Node(dest, weight));
            vertices[dest].adjacent.add(new Node(org, weight));

        }

        max = Integer.MIN_VALUE;
        isVisited = new boolean[vertexCount + 1];

        isVisited[1] = true;
        dfs(1, 0);
        dfs(v1, 0);
        
        sb.append(max);
        bw.write(sb.toString());
        bw.close();

    }

    public static void dfs(int cVertex, int length) {

        if (max < length) {
            max = length;
            v1 = cVertex;
        }

        for (Node adj : vertices[cVertex].adjacent) {

            if (isVisited[adj.dest]) {
                continue;
            }

            isVisited[cVertex] = true;
            dfs(adj.dest, length + adj.weight);
            isVisited[cVertex] = false;

        }

    }

}