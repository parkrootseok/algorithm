import java.io.*;
import java.util.*;

/**
 * BOJ_좋다
 * @author parkrootseok
 */
public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int N;
    public static int[] numbers;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());

        numbers = new int[N];
        String[] inputs = br.readLine().trim().split(" ");
        for (int idx = 0; idx < N; idx++) {
            numbers[idx] = Integer.parseInt(inputs[idx]);
        }

        Arrays.sort(numbers);

        int answer = 0;
        for (int idx = 0; idx < N; idx++) {

            int target = numbers[idx];
            int start = 0;
            int end = N - 1;

            while (start < end) {

                long sum = numbers[start] + numbers[end];

                if (start == idx) {
                    start++;
                    continue;
                }

                if (end == idx) {
                    end--;
                    continue;
                }

                if (target == sum) {
                    answer++;
                    break;
                }

                else if (target < sum) {
                    end--;
                }

                else {
                    start++;
                }

            }

        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.close();

    }

}