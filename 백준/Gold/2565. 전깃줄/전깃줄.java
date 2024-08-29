import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_전깃줄
 * @author parkrootseok
 */
public class Main {

    public static class Cable implements Comparable<Cable>{

        int from;
        int to;

        public Cable(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Cable c) {
            return Integer.compare(this.from, c.from);
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;
    public static String[] inputs;

    public static int cableCount;
    public static Cable[] cables;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        cableCount = Integer.parseInt(br.readLine().trim());
        cables = new Cable[cableCount];
        for (int count = 0; count < cableCount; count++) {

            inputs = br.readLine().trim().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            cables[count] = new Cable(from, to);

        }

        Arrays.sort(cables);

        int[] dp = new int[cableCount];
        for (int row = 0; row < cableCount; row++) {

            dp[row] = 1;

            for (int col = 0; col < row; col++) {

                if (cables[col].to < cables[row].to) {
                    dp[row] = Math.max(dp[row], dp[col] + 1);
                }

            }
        }

        int max = Arrays.stream(dp).max().getAsInt();

        sb.append(cableCount - max);
        bw.write(sb.toString());
        bw.close();

    }

}