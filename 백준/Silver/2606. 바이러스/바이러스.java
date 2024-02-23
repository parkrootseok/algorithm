import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 바이러스
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, CNT = 0;
    static List<List<Integer>> computers = new ArrayList<>();
    static boolean visited[];

    public void solution() {

        Queue<List<Integer>> queue = new LinkedList<>();

        queue.offer(computers.get(1));
        visited[1] = true;

        while (!queue.isEmpty()) {

            List<Integer> cur = queue.poll();

            for (int to : cur) {

                if (!visited[to]) {
                    visited[to] = true;
                    queue.add(computers.get(to));
                    CNT++;
                }

            }

        }

    }

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        N = Integer.parseInt(br.readLine());
        M =  Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            computers.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            computers.get(to).add(from);
            computers.get(from).add(to);

        }

        main.solution();

        bw.write(CNT + "\n");
        bw.close();

    }

}