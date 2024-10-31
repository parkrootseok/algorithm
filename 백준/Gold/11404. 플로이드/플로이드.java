import java.io.*;
import java.util.Arrays;

/**
 * BOJ_플로이드
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static final int INF = 100_000_000;

    static int cityCount;
    static int busCount;
    static int[][] cost;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        cityCount = Integer.parseInt(br.readLine().trim());
        cost = new int[cityCount][cityCount];
        for (int city = 0; city < cityCount; city++) {
            Arrays.fill(cost[city], INF);
        }

        busCount = Integer.parseInt(br.readLine().trim());
        for (int bus = 0; bus < busCount; bus++) {
            String[] inputs = br.readLine().trim().split(" ");

            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);

            cost[from - 1][to - 1] = Math.min(cost[from - 1][to - 1], c);
        }

        for (int s = 0; s < cityCount; s++) {
            for (int from = 0; from < cityCount; from++) {
                for (int to = 0; to < cityCount; to++) {
                    if (from != to && cost[from][to] > cost[from][s] + cost[s][to]) {
                        cost[from][to] = cost[from][s] + cost[s][to];
                    }

                }
            }
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

}