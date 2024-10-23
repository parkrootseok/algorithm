import java.io.*;

/**
 * BOJ_연속합
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static int[] numbers;
    static int[] dp;
    static int max;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        numbers = new int[size + 1];
        String[] inputs = br.readLine().trim().split(" ");
        for (int s = 1; s <= size; s++) {
            numbers[s] = Integer.parseInt(inputs[s - 1]);
        }

        max = Integer.MIN_VALUE;
        dp = new int[size + 1];
        for (int n = 1; n <= size; n++) {
            dp[n] = Math.max(dp[n - 1] + numbers[n], numbers[n]);
            max = Math.max(dp[n], max);
        }

        sb.append(max).append("\n");
        bw.write(sb.toString());
        bw.close();

    }

}