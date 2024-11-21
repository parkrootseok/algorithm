import java.io.*;
import java.util.Stack;

/**
 * BOJ_가장긴증가하는부분수열4
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static int[] numbers;
    static int length;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        numbers = new int[size];

        String[] inputs = br.readLine().trim().split(" ");
        for (int idx = 0; idx < size; idx++) {
            numbers[idx] = Integer.parseInt(inputs[idx]);
        }

        int dp[] = new int[size + 1];
        for (int row = 0; row < size; row++) {

            dp[row] = 1;

            for (int col = 0; col < row; col++) {

                if (numbers[row] > numbers[col]) {
                    dp[row] = Math.max(dp[row], dp[col] + 1);
                }

            }

            length = Math.max(length, dp[row]);

        }

        sb.append(length).append("\n");

        Stack<Integer> lis = new Stack<>();
        for (int idx = size; idx >= 0; idx--) {

            if (dp[idx] == length) {
                lis.push(numbers[idx]);
                length--;
            }

        }

        while (!lis.isEmpty()) {
            sb.append(lis.pop()).append(" ");
        }

        bw.write(sb.toString());
        bw.close();

    }


}