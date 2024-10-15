import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.w3c.dom.Node;


/**
 * BOJ_케빈베이컨의6단계법칙
 * @author parkrootseok
 *
 * - 케빈 베이컨 = 단계의 총 합이 가장 작은 사람
 *
 *
 */
public class Main {

    static class Vertex {

        int name;
        List<Integer> adjacentVertices;

        public Vertex(int name) {
            this.name = name;
            this.adjacentVertices = new ArrayList<>();
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int M;
    static Vertex[] vertices;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        vertices = new Vertex[N + 1];
        for (int n = 1; n <= N; n++) {
            vertices[n] = new Vertex(n);
        }

        for (int m = 0; m < M; m++) {
            inputs = br.readLine().trim().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);

            vertices[from].adjacentVertices.add(to);
            vertices[to].adjacentVertices.add(from);
        }

        int min = Integer.MAX_VALUE;
        int[] result = new int[N + 1];
        for (int start = 1; start <= N; start++) {
            for (int end = 1; end <= N; end++) {
                if (start == end) {
                    continue;
                }
                result[start] += bfs(start, end);
            }
            min = Math.min(min, result[start]);
        }

        for (int n = 1; n <= N; n++) {
            if (min == result[n]) {
                sb.append(n);
                break;
            }
        }

        bw.write(sb.toString());
        bw.close();

    }

    public static int bfs(int start, int end) {

        boolean[] isVisited = new boolean[N + 1];
        Queue<Integer> pq = new ArrayDeque<>();
        pq.offer(start);

        int depth = 0;
        while (!pq.isEmpty()) {

            int size = pq.size();

            for (int s = 0; s < size; s++) {

                Vertex curV = vertices[pq.poll()];

                if (curV.name == end) {
                    return depth;
                }

                for (int nextV : curV.adjacentVertices) {

                    if (isVisited[nextV]) {
                        continue;
                    }

                    isVisited[nextV] = true;
                    pq.add(nextV);

                }

            }

            depth++;

        }

        return 0;

    }

}