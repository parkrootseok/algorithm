import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 파도반 수열
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, M, N, K, ANSWER;
    static long[] P;

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        T = Integer.parseInt(br.readLine());
        P = new long[101];
        Arrays.fill(P, 1);
        for (int i = 4 ; i <= 100 ; i++) {
            P[i] = P[i - 3] + P[i - 2];
        }

        for (int rep = 0 ; rep < T ; rep++) {
            N = Integer.parseInt(br.readLine());
            bw.write(P[N] + "\n");
        }

        bw.close();

    }

}