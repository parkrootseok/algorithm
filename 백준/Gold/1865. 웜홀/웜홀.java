import java.io.*;
import java.util.*;

/**
 * BOJ_웜홀 - 슈퍼소스 버전
 */
public class Main {

    static class Vertex {
        int name;
        List<Edge> adjacentVertices;

        public Vertex(int name) {
            this.name = name;
            this.adjacentVertices = new ArrayList<>();
        }
    }

    static class Edge {
        int name;
        int time;
        public Edge(int name, int time) {
            this.name = name;
            this.time = time;
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int testCount;
    static int N;
    static int M;
    static int W;

    // 정점 배열(0번 = 슈퍼소스, 1..N = 실제 정점)
    static Vertex[] vertices; 
    static int[] distance;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        testCount = Integer.parseInt(br.readLine().trim());
        for (int tCount = 0; tCount < testCount; tCount++) {

            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            // 슈퍼소스 포함: 0..N => 총 (N+1)개
            vertices = new Vertex[N+1];
            for (int i = 0; i <= N; i++) {
                vertices[i] = new Vertex(i);
            }

            // 도로(양방향) 입력
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine(), " ");
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                vertices[S].adjacentVertices.add(new Edge(E, T));
                vertices[E].adjacentVertices.add(new Edge(S, T));
            }

            // 웜홀(단방향, 음수 가중치) 입력
            for (int w = 0; w < W; w++) {
                st = new StringTokenizer(br.readLine(), " ");
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                // 웜홀은 -T 로 처리
                vertices[S].adjacentVertices.add(new Edge(E, -T));
            }

            // **슈퍼소스(0)에서 모든 정점(1~N)으로 0 가중치 간선 추가** 
            for (int v = 1; v <= N; v++) {
                vertices[0].adjacentVertices.add(new Edge(v, 0));
            }

            // Bellman-Ford 실행
            boolean hasNegativeCycle = bellmanFord();

            sb.append(hasNegativeCycle ? "YES\n" : "NO\n");
        }

        bw.write(sb.toString());
        bw.close();
    }

    /**
     * 슈퍼소스(0)을 시작점으로 하는 Bellman-Ford
     * 그래프에 음수 사이클이 있으면 true, 없으면 false
     */
    public static boolean bellmanFord() {
        int INF = 100_000_000;
        distance = new int[N+1];
        Arrays.fill(distance, INF);

        // 슈퍼소스(0)까지의 거리를 0으로
        distance[0] = 0;

        // 실제 정점 총 개수: N+1 (0..N)
        // => 최단 거리 확정은 (N+1 - 1) = N번 반복
        // => 추가 1번 (즉, N번째 반복 시도)에서 갱신 일어나면 음수 사이클
        for (int count = 1; count <= N; count++) {
            for (int cur = 0; cur <= N; cur++) {
                // cur 정점에서 갈 수 있는 모든 간선 탐색
                for (Edge e : vertices[cur].adjacentVertices) {
                    int next = e.name;
                    int cost = e.time;
                    // Relaxation 조건
                    if (distance[cur] != INF && distance[next] > distance[cur] + cost) {
                        distance[next] = distance[cur] + cost;

                        // N번째 라운드에서 갱신 발생 => 음수 사이클
                        if (count == N) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}