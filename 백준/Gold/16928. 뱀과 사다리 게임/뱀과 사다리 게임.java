import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;


/**
 * BOJ_뱀과사다리게임
 * @author parkrootseok
 */
public class Main {

    static class Node {

        int position;
        int count;

        public Node(int position, int count) {
            this.position = position;
            this.count = count;
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int M;
    static int min;
    static int[] connection;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        connection = new int[101];
        for (int idx = 0; idx < N + M; idx++) {
            inputs = br.readLine().trim().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            connection[from] = to;
        }

        min = Integer.MAX_VALUE;
        isVisited = new boolean[101];
        game();

        sb.append(min);
        bw.write(sb.toString());
        bw.close();

    }

    public static void game() {

        Queue<Node> nodes = new ArrayDeque<>();
        nodes.offer(new Node(1,0));
        isVisited[1] = true;

        while (!nodes.isEmpty()) {

            Node node = nodes.poll();
            int p = node.position;
            int c = node.count;

            if (p == 100) {
                min = Math.min(min, c);
                return;
            }

            for (int dice = 1; dice <= 6; dice++) {

                int nP = p + dice;

                if (100 < nP || nP < 1) {
                    continue;
                }

                if (connection[nP] > 0) {
                    nP = connection[nP];
                }

                if (!isVisited[nP]) {
                    isVisited[nP] = true;
                    nodes.offer(new Node(nP, c + 1));
                }

            }

        }

    }

}