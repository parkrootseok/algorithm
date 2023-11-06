import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, ANSWER;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void solution(int cnt, int cur) {

        ANSWER = Math.max(ANSWER, cnt);

        for (int i = 0; i < graph[cur].size(); i++) {

            int v = graph[cur].get(i);

            if (!visited[v]) {
                visited[v] = true;
                solution(cnt + 1, v);
                visited[v] = false;
            }

        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            M = Integer.parseInt(inputs[1]);

            graph = new List[N + 1];
            visited = new boolean[N + 1];
            for (int j = 1; j <= N; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < M; j++) {

                inputs = br.readLine().split(" ");

                int to = Integer.parseInt(inputs[0]);
                int from = Integer.parseInt(inputs[1]);

                graph[to].add(from);
                graph[from].add(to);

            }

            ANSWER = 0;
            for (int j = 1; j <= N; j++) {
                visited[j] = true;
                solution(1, j);
                visited[j] = false;
            }

            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}