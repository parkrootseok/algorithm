import java.io.*;
import java.util.*;
import org.w3c.dom.Node;

/**
 * BOJ_해킹
 * @author parkrootseok
 *
 * - 단방향 그래프
 * - a가 b에 의존한다면, b가 감염된 후 일정 시간 뒤 a도 감염
 * - 총 몇대의 컴퓨터가 감염되며 걸리는 시간을 구해라
 */
public class Main {

    static class Vertex {

        int name;
        ArrayList<Edge> edges;

        public Vertex(int name) {
            this.name = name;
            this.edges = new ArrayList<>();
        }

    }

    static class Edge implements Comparable<Edge> {

        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int testCount;
    public static int computerCount;
    public static int edgeCount;
    public static int origin;

    public static Vertex[] vertices;
    public static int[] distance;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        // 테스트 케이스 횟수 입력
        testCount = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCount; tc++) {

            // 컴퓨터 개수 n, 의존성 개수 d, 해킹당한 컴퓨터의 번호 c
            String[] inputs = br.readLine().trim().split(" ");
            computerCount = Integer.parseInt(inputs[0]);
            edgeCount = Integer.parseInt(inputs[1]);
            origin = Integer.parseInt(inputs[2]);

            vertices = new Vertex[computerCount + 1];
            for (int idx = 1; idx <= computerCount; idx++) {
                vertices[idx] = new Vertex(idx);
            }

            for (int idx = 0; idx < edgeCount; idx++) {
                inputs = br.readLine().trim().split(" ");
                int from = Integer.parseInt(inputs[0]);
                int to = Integer.parseInt(inputs[1]);
                int weight = Integer.parseInt(inputs[2]);

                vertices[to].edges.add(new Edge(from, weight));
            }

            int last = dijkstra();
            int count = 0;
            for (int idx = 1; idx <= computerCount; idx++) {
                if (distance[idx] != Integer.MAX_VALUE) {
                    count++;
                }
            }

            sb.append(count).append(" ").append(distance[last]).append("\n");

        }

        bw.write(sb.toString());
        bw.close();

    }

    public static int dijkstra() {

        distance = new int[computerCount + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        distance[origin] = 0;
        queue.add(new Edge(origin, 0));

        int last = origin;
        boolean[] isVisited = new boolean[computerCount + 1];
        while (!queue.isEmpty()) {

            Edge edge = queue.poll();
            int cur = edge.to;

            if (isVisited[edge.to]) {
                continue;
            }

            last = edge.to;
            isVisited[edge.to] = true;
            for (Edge e : vertices[cur].edges) {

                int next = e.to;
                int weight = e.weight;

                if (distance[next] > distance[cur] + weight) {
                    distance[next] = distance[cur] + weight;
                    queue.add(new Edge(next, distance[cur] + weight));
                }

            }

        }

        return last;

    }

}