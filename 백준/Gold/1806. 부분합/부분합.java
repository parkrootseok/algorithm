import java.io.*;
import java.util.Arrays;

/**
 * BOJ_부분합
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static int target;
    static int[] numbers;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        size = Integer.parseInt(inputs[0]);
        target = Integer.parseInt(inputs[1]);
        numbers = new int[size + 1];

        inputs = br.readLine().trim().split(" ");
        for (int s = 0; s < size; s++) {
            numbers[s] = Integer.parseInt(inputs[s]);
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while (start <= end && end <= size) {

            // 목표하는 값보다 크거나 같다면
           if (target <= sum) {
               minLen = Math.min(minLen, end - start);
               sum -= numbers[start++];
           }

           // 목표하는 값보다 작다면
           else {
               sum += numbers[end++];
           }

        }

        if (minLen == Integer.MAX_VALUE) {
            minLen = 0;
        }

        sb.append(minLen).append("\n");
        bw.write(sb.toString());
        bw.close();

    }

}