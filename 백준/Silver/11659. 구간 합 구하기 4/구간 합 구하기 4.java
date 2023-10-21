import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 구간 합 구하기 4
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, M, N, K, ANSWER;
    static int[] numbers;
    static int[] sum;

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        numbers = new int[M + 1];
        st = new StringTokenizer(br.readLine(), " ");

        int i = 1;
        while (st.hasMoreTokens()) {
            numbers[i] = numbers[i - 1] + Integer.parseInt(st.nextToken());
            i++;
        }

        for (i = 0 ; i < N ; i++) {

            st = new StringTokenizer(br.readLine(), " ");

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            ANSWER = numbers[e] - numbers[s - 1];
            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}