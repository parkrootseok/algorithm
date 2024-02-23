import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 랜선 자르기
 */

public class Main {

    static int N, K;
    static long MAX = Integer.MIN_VALUE, ANSWER = 0;
    static long[] wire;
    public void solution(long start, long end) {


        long mid = (start + end) / 2;

        if (mid == 0 || start > end) {
            return;
        }

        int answer = 0;
        for (int i = 0 ; i < K ; i++) {
            answer += wire[i] / mid;
        }

        if (answer >= N) {
            ANSWER = Math.max(ANSWER, mid);
            solution(mid + 1, end);
        } else if (answer < N) {
            solution(start, mid - 1);
        }

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        wire = new long[K];
        for (int i = 0 ; i < K ; i++) {
            wire[i] = Long.parseLong(br.readLine());
            MAX = Math.max(MAX, wire[i]);
        }

        m.solution(1, MAX);
        bw.write(ANSWER + "");
        bw.close();

    }

}